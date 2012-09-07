/**
 * 
 */
package com.jp.app.pdf;

/**
 * @author dimit.chadha
 *
 */

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.jp.application.common.TechConstants;



public class CreatePDF implements TechConstants{

	private static final String LINE_SEP = "\r\n";

	private PdfPCell cell;

	private PdfPTable table;

	private Document document;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreatePDF pdfCreation = new CreatePDF();
		pdfCreation.showPdf();
	}

	private void showPdf() {
		long start = System.currentTimeMillis();
		try {
			OutputStream file = new FileOutputStream(new File("OutPut-PDF.pdf"));

			document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, file);
			document.open();

			BaseFont verdana = BaseFont.createFont(
					"C:\\WINDOWS\\FONTS\\VERDANA.TTF", BaseFont.CP1252,
					BaseFont.NOT_EMBEDDED);
			Font header = new Font(verdana, 12, Font.BOLD,
					new Color(0, 51, 102));
			Font subhead = new Font(verdana, 8, Font.BOLD,
					new Color(0, 51, 102));
			Font normal = new Font(verdana, 7, Font.NORMAL);
			Font bold = new Font(verdana, 7, Font.BOLD);

			// header
			com.lowagie.text.Image image = Image.getInstance("Dimit.jpg");
			image.scaleAbsoluteWidth(72);
			image.scaleAbsoluteHeight(73);
			cell = new PdfPCell(image);
			table = new PdfPTable(1);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.setWidthPercentage(100f);
			table.addCell(cell);

			document.add(table);

			// heading
			document.add(new Phrase("Transaction Report", header));
			document.add(new Phrase(LINE_SEPARATOR));
			document.add(new Phrase("Account Set: Account Set23", subhead));
			document.add(new Phrase(LINE_SEPARATOR));

			document.add(new Phrase(
					"Transaction Report on 22/10/2009 at 14:14:14 IST"
							+ LINE_SEP, subhead));
			document.add(new Phrase("Date From: " + LINE_SEP, subhead));
			document.add(new Phrase("Details: " + LINE_SEP, subhead));
			document.add(new Phrase("Amount From: ", subhead));
			document.add(new Phrase("0.00 Amount To:Maximum ", subhead));
			document.add(new Phrase(LINE_SEPARATOR));
			document.add(new Phrase(LINE_SEPARATOR));

			createTable(5);
			setCellProperties("Account Name:", bold);
			addTableRight();
			setCellProperties("Account082856589", normal);
			addTableLeft();
			setCellProperties("", normal);
			addTableRight();
			setCellProperties("Account Number:", bold);
			addTableRight();
			setCellProperties("01580 80251178", normal);
			addTableLeft();
			setCellProperties("Account Type:", bold);
			addTableRight();
			setCellProperties("Current Account", normal);
			addTableLeft();
			setCellProperties("", normal);
			addTableRight();
			setCellProperties("Currency:", bold);
			addTableRight();
			setCellProperties("GBP", normal);
			addTableLeft();
			setCellProperties("Latest Cleared for Interest:", bold);
			addTableRight();
			setCellProperties("777.00 CR", normal);
			addTableLeft();
			setCellProperties("", normal);
			addTableRight();
			setCellProperties("Last Night's Closing Cleared for Interest:",
					bold);
			addTableRight();
			setCellProperties("4,343.00 CR", normal);
			addTableLeft();
			setCellProperties("Latest Cleared for Fate:", bold);
			addTableRight();
			setCellProperties("1,666.00 CR", normal);
			addTableLeft();
			setCellProperties("", normal);
			addTableRight();
			setCellProperties("Last Night's Closing Cleared for Fate:", bold);
			addTableRight();
			setCellProperties("5,454.76 CR", normal);
			addTableLeft();
			setCellProperties("Latest Ledger:", bold);
			addTableRight();
			setCellProperties("9,977.00 CR", normal);
			addTableLeft();
			setCellProperties("", normal);
			addTableRight();
			setCellProperties("Last Night's Closing Ledger", bold);
			addTableRight();
			setCellProperties("1,755.00 CR", normal);
			addTableLeft();

			document.add(table);

			document.add(new Phrase(LINE_SEPARATOR));
			document.add(new Phrase(LINE_SEPARATOR));

			// 3rd block
			createTable(6);
			setListCellProperties("Date:", bold);
			setListCellProperties("Description:", bold);
			setListCellProperties("Details:", bold);
			setListCellProperties("Payments:", bold);
			setListCellProperties("Receipts:", bold);
			setListCellProperties("Balance:", bold);
			for (int i = 1; i <= 6; i++) {
				createCell("22/10/2009", normal, i);
				createCell("Dimit", normal, i);
				createCell("Chadha", normal, i);
				createCell("VIII", normal, i);
				createCell("2222", normal, i);
				createCell("547", normal, i);
			}
			document.add(table);
			document.close();

			// file.close();
			long end = System.currentTimeMillis();
			System.out.println("Time Taken : ");
			System.out.println(end - start);
		} catch (Exception e) {
		}
	}

	private void createCell(String data, Font font, int i) {
		Phrase phrase = new Phrase(data, font);
		cell = new PdfPCell(phrase);
		if (i % 2 == 0) {
			setGreyCellProperties();
		} else {
			setWhiteCellProperties();
		}
		table.addCell(cell);
	}

	private PdfPCell setCellProperties(String data, Font font) {
		Phrase phrase = new Phrase(data, font);
		cell = new PdfPCell(phrase);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setNoWrap(true);
		return cell;
	}

	private void addTableRight() {
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		table.addCell(cell);
	}

	private void addTableLeft() {
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		table.addCell(cell);
	}

	private void createTable(int cols) {
		table = new PdfPTable(cols);
		table.setWidthPercentage(100f);
	}

	private void setListCellProperties(String data, Font font) {
		Phrase phrase = new Phrase(data, font);
		cell = new PdfPCell(phrase);
		cell.setPaddingTop(5);
		cell.setBorderColor(Color.WHITE);
		cell.setBackgroundColor(new java.awt.Color(0xEE, 0xEE, 0xEE));
		table.addCell(cell);
	}

	private void setWhiteCellProperties() {
		cell.setPaddingTop(5);
		cell.setBorderColor(Color.WHITE);
		cell.setBackgroundColor(Color.WHITE);
		table.addCell(cell);
	}

	private void setGreyCellProperties() {
		cell.setPaddingTop(5);
		cell.setBorderColor(Color.WHITE);
		cell.setBackgroundColor(new java.awt.Color(0xEE, 0xEE, 0xEE));
		table.addCell(cell);
	}
}
