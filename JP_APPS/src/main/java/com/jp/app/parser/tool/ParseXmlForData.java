package com.jp.app.parser.tool;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseXmlForData extends JFrame{
	
	private static final long serialVersionUID = 8730711366011287288L;
	private List<String> tagList=new ArrayList<String>();
	public String fileType;
	public JTextField txtDirSel;
	public File resultDir;
	public File selectedDir;

	public static JFileChooser dirChooser;
	public static Container c;
	public static int w = 500;
	public static int h = 300;
	public static String strFrame;

	public static JButton butInitial;
	public static JButton butGenReport;

	public static JLabel lblName;
	public static JLabel lblFooter;
	List<File> fileList=new ArrayList<File>();
	static Document dom;
	private static Map<String, String> nameType=new HashMap<String, String>();
	private static final String COMMA=",";
	private static StringBuffer outBuf=new StringBuffer();
	public static final char[] LINE_SEPARATOR = "\r\n".toCharArray();
	 
	
	public ParseXmlForData(){
		setTitle("Help Contents Generation Utility");
		setSize(400, 300);
		setVisible(true);
		c = getContentPane();
		GridBagLayout gbl = new GridBagLayout();
		c.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weightx = 0;
		gbc.weighty = 0;

		lblName = new JLabel("Utility");
		add(lblName, gbc, 0, 1, 1, 1);

		txtDirSel = new JTextField(20);
		add(txtDirSel, gbc, 0, 4, 1, 1);

		butInitial = new JButton("Folder-Location");
		add(butInitial, gbc, 1, 4, 1, 1);

		butGenReport = new JButton("Generate-Data");
		add(butGenReport, gbc, 1, 8, 1, 1);
		butGenReport.setEnabled(true);

		lblFooter = new JLabel("Utility Developed by Dimit Chadha");
		add(lblFooter, gbc, 0, 10, 1, 1);

		butInitial.addActionListener(new addEvent());
		butGenReport.addActionListener(new addEvent());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParseXmlForData parseXmlForData=new ParseXmlForData();
		parseXmlForData.accessProperties();
		parseXmlForData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parseXmlForData.setVisible(true);
	}

	/**
	 * @param butFinal
	 * @param gbc
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 */

	/**
	 * 
	 */
	private void accessProperties() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("tags");
		String tageTypes = resourceBundle.getString("TAGSTYPE");
		fileType=resourceBundle.getString("FILETYPE").toString();
		StringTokenizer stringTokenizer = new StringTokenizer(tageTypes, ",");
		for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
			tagList.add((String) stringTokenizer.nextElement());
		}
	}

	public void add(Component comp, GridBagConstraints gbc, int x, int y,int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(comp, gbc);
	}
	
	
	public class addEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getActionCommand();
			//try {
				if (evt == "Folder-Location") {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					dirChooser.showOpenDialog(c);
					selectedDir = dirChooser.getSelectedFile();
					if (selectedDir == null) {
						int sel = JOptionPane.showConfirmDialog(ParseXmlForData.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
					} else {
						txtDirSel.setText(selectedDir.toString());
						txtDirSel.setEditable(false);
					}
				} else if (evt == "Generate-Data" && (selectedDir != null)) {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					dirChooser.showOpenDialog(c);
					resultDir = dirChooser.getSelectedFile();
					if (resultDir == null) {
						int sel = JOptionPane.showConfirmDialog(ParseXmlForData.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
					} else {
						GenerateData();
					}
				}
		}

		private void GenerateData() {
			try {
				List<File> fileList = getFileListing(selectedDir);
				String reportFilePath = CreateFile(fileList);
				int sel = JOptionPane.showConfirmDialog(ParseXmlForData.c,
						"Report Generated!! File Stored in " + reportFilePath,
						"Report Generated", JOptionPane.CLOSED_OPTION,
						JOptionPane.PLAIN_MESSAGE);
			} catch (FileNotFoundException ioe) {
				ioe.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @param tempDir
	 * @return result Modify by SumitK on dated 02.12.2005 for giving option to
	 *         retrieve FileTypes from loc.peoperties
	 */
	public List<File> getFileListing(File selectedDir) throws FileNotFoundException {

		try {
			validateDirectory(selectedDir);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
		File[] filesAndDirs = selectedDir.listFiles();
		List filesDirs = Arrays.asList(filesAndDirs);
		Iterator filesDirIter = filesDirs.iterator();
		boolean flag;
		File file = null;
		List<File> result = new ArrayList<File>();
		while (filesDirIter.hasNext()) {
			flag = false;
			file = (File) filesDirIter.next();
			if (file.isFile()) { // file
				//int fileTypesLen = fileType.length;
				//for (int i = 0; (fileTypesLen > 0) && (null != fileType); i++) {
					if (!(file.getName().toString().endsWith(fileType))) {
						continue;
					} else {
						flag = true;
					}
				//}
				if (flag) {
					//String Cpath = file.getAbsolutePath();
					//String fileName = file.getName();
					//String path = Cpath.substring(0, Cpath.indexOf(fileName));
					//result.put(fileName, path);
					fileList.add(file);
					flag = false;
				}
			} else { // dir
				// must be a directory recursive call! add no size//
				List<File> deeperList = getFileListing(file);
				if (deeperList.size() > 0) {
					// result.addAll(deeperList);
					result.addAll(deeperList);
					//result.putAll(deeperList);
				} else {
					//for (int i = 0; (fileType.length > 0)
					//		&& (null != fileType[i]); i++) {
						if (!(file.getName().toString().endsWith(fileType))) {
							continue;
						} else {
							flag = true;
						}
					//}
					if (flag) {
					//	String Cpath = file.getAbsolutePath();
					//	String fileName = file.getName();
					//	String path = Cpath.substring(0, Cpath
//								.indexOf(fileName));
//						result.put(fileName, path);
						fileList.add(file);
						flag = false;
					}
				}
			}
		}
		return fileList;
	}
	
	/**
	 * @param tempDir
	 */
	private String CreateFile(List<File> fileList) throws IOException {
		//PrintWriter printRecs = null;
		String reportFilePath = "";
		//try {
			Date today = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
			reportFilePath = resultDir.toString() + "\\" + "HelpContents("
					+ dateFormat.format(today).toString() + ")" + ".csv";
			//printRecs = new PrintWriter(new FileWriter(new File(reportFilePath)));
//		} catch (IOException ioe) {
//		}
		
		Iterator<File> files = fileList.iterator();
		createData(files,reportFilePath);
		
//		while (files.hasNext()) {
//			
//			String fileName = files.next().toString();
//			String path = fileList.get(fileName).toString();
//			printRecs.println(fileName + "\t" + path);
//			printRecs.flush();
//		}
//		printRecs.close();
		return reportFilePath;
	}

	
	private void createData(Iterator<File> files, String reportFilePath) {
		try {
			PrintWriter printRecs=new PrintWriter(new FileWriter(new File(reportFilePath)));		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			while (files.hasNext()) {
				File file=files.next();
				dom= db.parse(file);
				Element element = dom.getDocumentElement();
				String pageId=element.getAttribute("id");
				Iterator<String> itr=tagList.iterator();
				nameType=new HashMap<String, String>();
				outBuf=new StringBuffer();
				while(itr.hasNext()){
					String tagName=itr.next();
					parseDocument(tagName,element);
				}
				printData(pageId);
				printRecs.print(outBuf.toString());
				printRecs.flush();
			}
			printRecs.close();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	/**
	 * @param tempDir1
	 */
	public static void validateDirectory(File selectedDir)
			throws FileNotFoundException {

		if (selectedDir == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!selectedDir.exists()) {
			throw new FileNotFoundException("Directory does not exist: "
					+ selectedDir);
		}
		if (!selectedDir.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: "
					+ selectedDir);
		}
		if (!selectedDir.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: "
					+ selectedDir);
		}
	}
	
	private static void parseDocument(String tagName, Element docEle){
		NodeList nl = docEle.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				Element el = (Element)nl.item(i);				
				String fieldId = getTextValue(el,"jp:name");
				String type = getTextValue(el,"jp:type");
				if(fieldId!=null){
					if("text".equalsIgnoreCase(type)){
						fieldId="simple_"+fieldId+"_text";					
					}else if("boolean".equalsIgnoreCase(type)){
						fieldId="simple_"+fieldId+"_boolean";	
					}else if("file".equalsIgnoreCase(type)){
						fieldId="simple_"+fieldId+"_file";	
					}else if("INTEGER".equalsIgnoreCase(type)){
						fieldId="simple_"+fieldId+"_integer";
					}
					nameType.put(fieldId, type);
				}
				
			}
		}
	}
	
	private static void printData(String pageId) {
		Set<String> fieldIdSet = nameType.keySet();
		Iterator<String> itr=fieldIdSet.iterator();
		while(itr.hasNext()){
			String fieldId = itr.next();
			outBuf.append(pageId);//pageId
			outBuf.append(COMMA);
			outBuf.append(fieldId);//field Id
			outBuf.append(COMMA); 
			outBuf.append(COMMA); //page_alias
			outBuf.append(COMMA); //field_alias
			outBuf.append("Sample Help  of "+pageId);//existing help
			outBuf.append(COMMA);
			outBuf.append(COMMA);
			outBuf.append("2009-09-20-08.46.32.674003");//last changed
			outBuf.append(COMMA);
			outBuf.append("90220");
			outBuf.append(COMMA);
			outBuf.append("90190");
			outBuf.append(COMMA);
			outBuf.append("A");
			outBuf.append(COMMA);
			outBuf.append("Mr Bat101 Batman101");
			outBuf.append(COMMA);
			outBuf.append("Mr Bat102 Batman102");
			outBuf.append(LINE_SEPARATOR);
		}
	}
	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
}
