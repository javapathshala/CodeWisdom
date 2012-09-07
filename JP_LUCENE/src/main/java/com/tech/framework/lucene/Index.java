/*
 * File: Index.java
 * Date: May 4, 2011
 *
 * This application is part of COCC banking solutions.
 * Its unauthorized use is explicitly prohibited as is any
 * alteration or addition made by any of its users without due written
 * consent from COCC.
 * This program is protected by copyright law and by
 * international conventions of intellectual property.  Its unauthorized
 * use gives COCC the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 */
package com.tech.framework.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.WildcardTermEnum;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.jp.application.common.TechConstants;

/**
 * Description : To index the cached value
 * 
 * @author dimit.chadha
 * 
 */
@SuppressWarnings("deprecation")
public class Index implements TechConstants {

	public static final String FIELD_IDENTIFIER = "any"; // Doesn't matter what
															// we call it,
															// because searches
															// cover all fields
	public static final String DOCUMENT_IDENTIFIER = "key";
	public static final String INDEX_IDENTIFIER = "index";
	private static final String UNIQUE_DOCUMENT_IDENTIFIER = "uniqueId";

	private final RAMDirectory ramDirectory;
	private IndexSearcher searcher = null;
	private final String indexKey;

	@SuppressWarnings("rawtypes")
	public Index(RAMDirectory ramDirectory, String key, List specs,
			IndexSpecifier indexSpecifier) {
		this.ramDirectory = ramDirectory;
		indexKey = key;
		try {
			if (IndexReader.indexExists(ramDirectory)) {
				IndexReader reader = IndexReader.open(ramDirectory, false);
				for (Iterator itr = specs.iterator(); itr.hasNext();) {
					IndexSpecification spec = (IndexSpecification) itr.next();
					reader.deleteDocuments(new Term(UNIQUE_DOCUMENT_IDENTIFIER,
							indexKey + spec.getKey()));
				}
				reader.close();
			}

			// IndexWriter writer = new IndexWriter(ramDirectory, new
			// StandardAnalyzer(), false);
			// IndexWriter writer = new IndexWriter(ramDirectory,
			// new StandardAnalyzer(Version.LUCENE_35), true,
			// IndexWriter.MaxFieldLength.UNLIMITED);
			// Directory index = new RAMDirectory();
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35,
					new StandardAnalyzer(Version.LUCENE_35));
			IndexWriter writer = new IndexWriter(ramDirectory, config);

			for (Iterator itr = specs.iterator(); itr.hasNext();) {
				IndexSpecification spec = (IndexSpecification) itr.next();
				append(writer, spec.getKey(), spec.getIndexFields());
			}
			//writer.optimize();
			writer.commit();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void append(IndexWriter writer, String documentKey,
			String[] properties) throws IOException {
		Document doc = new Document();
		doc.add(new Field(UNIQUE_DOCUMENT_IDENTIFIER, indexKey + documentKey,
				Field.Store.NO, Field.Index.NOT_ANALYZED));
		doc.add(new Field(INDEX_IDENTIFIER, indexKey, Field.Store.NO,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(DOCUMENT_IDENTIFIER, documentKey, Field.Store.YES,
				Field.Index.NO));
		for (int i = 0; i < properties.length; i++) {
			String property = properties[i];
			doc.add(new Field(FIELD_IDENTIFIER, property, Field.Store.NO,
					Field.Index.ANALYZED));
		}
		writer.addDocument(doc);
	}

	public Set<String> query(String query) throws IOException {
		Set<String> keys = new HashSet<String>();
		// Hits hits = queryCache(query);
		ScoreDoc[] hits = queryCache(query);
		int hitsLen = hits.length;
		for (int i = 0; i < hitsLen; i++) {
			int docId = hits[i].doc;
			Document doc = searcher.doc(docId);
			String keyResult = doc.getField(DOCUMENT_IDENTIFIER).stringValue();
			keys.add(keyResult);
		}
		return keys;
	}

	private ScoreDoc[] queryCache(String queryString) throws IOException {
		ScoreDoc[] hits = {};
		if (searcher == null) {
			searcher = new IndexSearcher(ramDirectory);
		} else if (!searcher.getIndexReader().isCurrent()) {
			searcher = new IndexSearcher(ramDirectory);
		}
		Query query = createQuery(queryString);
		int hitsPerPage = 100;
		Query indexKeyQuery = new TermQuery(
				new Term(INDEX_IDENTIFIER, indexKey));
		Filter filter = new QueryWrapperFilter(indexKeyQuery);
		// TopDocCollector collector = new TopDocCollector(hitsPerPage);
		TopScoreDocCollector collector = TopScoreDocCollector.create(
				hitsPerPage, true);
		searcher.search(query, filter, collector);
		hits = collector.topDocs().scoreDocs;
		return hits;
	}

	private Query createQuery(String queryString) {

		if (queryString == null || BLANK.equals(queryString)) {
			return createWildcardQuery("*");
		}
		ArrayList<String> tokenList = parseQueryString(queryString);
		BooleanQuery query = new BooleanQuery();
		Iterator<String> itr = tokenList.iterator();
		while (itr.hasNext()) {
			Query wildcardQuery = createWildcardQuery(itr.next());
			query.add(wildcardQuery, BooleanClause.Occur.MUST);
		}

		// for (int i = 0; i < size; i++) {
		// //Token token = (Token) tokenList.get(i);
		// Query wildcardQuery = createWildcardQuery(token.term());
		// query.add(wildcardQuery, BooleanClause.Occur.MUST);
		// }
		return query;
	}

	private ArrayList<String> parseQueryString(String queryString) {
		QueryStringParser queryStringParser = new QueryStringParser(
				new CustomAnalyzer());
		return queryStringParser.parse(queryString);
	}

	private Query createWildcardQuery(String queryString) {
		Query wildcardQuery;
		if (queryString.indexOf(WildcardTermEnum.WILDCARD_STRING) != queryString
				.length() - 1) {
			queryString = queryString + WildcardTermEnum.WILDCARD_STRING;
		}
		wildcardQuery = new WildcardQuery(new Term(FIELD_IDENTIFIER,
				queryString));
		return wildcardQuery;
	}
}
