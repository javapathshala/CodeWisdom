/**
 * 
 */
package com.jp.app.loc;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author dimit.chadha
 * 
 */
public class LOC extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ArrayList<Object> result_sizes;
	public static HashMap<String, String> InitialMap;
	public static HashMap<String, String> FinalMap;
	public static String resultpath = "";
	public static String initialfilepath = "";
	public static String newfilepath = "";
	public static File tempDir = null;
	public static File tempDir1 = null;
	public static File tempDir2 = null;
	public static List<File> filesInitial;
	public static List<File> filesFinal;
	public static JFileChooser dirChooser;
	public static Container c;
	public static int w = 500;
	public static int h = 300;
	public static String strFrame;
	public static JTextField txtInitial;
	public static JTextField txtFinal;
	public static JButton butInitial;
	public static JButton butFinal;
	public static JButton butGenReport;
	public static File dirSelectInitial;
	public static File dirSelectFinal;
	public static File dirSelectResult;
	public static JLabel lblName;
	public static JLabel lblFooter;
	public static String currentLine;
	public static String nextLine;
	public static final String START_COMMENT = "/*";
	public static final String END_COMMENT = "*/";
	public static final String SL_COMMENT = "//";
	public static final String XML_START_COMMENT = "<!--";
	public static final String XML_END_COMMENT = "-->";
	public static int startIndex; // index of /* OR <!-- etc.
	public static int endIndex;
	public static BufferedReader in = null;
	public static Date dd;
	public static DateFormat df;
	private static ResourceBundle resourceBundle = null;
	public static final String COMMENT = "COMMENT";
	public static int comment;
	// Added by SumitK on dated 02.12.2005 for giving option to retrieve
	// FileTypes from loc.peoperties
	public static final String FILETYPE = "FILETYPE";
	public static String fileTypes;
	public static final String fileType[] = new String[10];
	static int sel;

	/**
	 * 
	 */
	public LOC() {
		setTitle("LOC Generating Utility");
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

		lblName = new JLabel("LOC Generating Utility");
		add(lblName, gbc, 0, 1, 1, 1);

		txtInitial = new JTextField(20);
		add(txtInitial, gbc, 0, 4, 1, 1);

		butInitial = new JButton("Old-Release");
		add(butInitial, gbc, 1, 4, 1, 1);

		txtFinal = new JTextField(20);
		add(txtFinal, gbc, 0, 6, 1, 1);

		butFinal = new JButton("New-Release");
		add(butFinal, gbc, 1, 6, 1, 1);
		butFinal.setEnabled(false);

		butGenReport = new JButton("Generate-Report");
		add(butGenReport, gbc, 1, 8, 1, 1);
		butGenReport.setEnabled(false);

		lblFooter = new JLabel("Utility Developed by Dimit Chadha");
		add(lblFooter, gbc, 0, 10, 1, 1);

		butInitial.addActionListener(new addEvent());
		butFinal.addActionListener(new addEvent());
		butGenReport.addActionListener(new addEvent());

	}

	public class addEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getActionCommand();
			try {
				if (evt == "Old-Release") {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					// dirChooser.show();
					dirChooser.showOpenDialog(c);
					dirSelectInitial = dirChooser.getSelectedFile();
					if (dirSelectInitial == null) {
						sel = JOptionPane.showConfirmDialog(LOC.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
					} else {
						txtInitial.setText(dirSelectInitial.toString());
						txtInitial.setEditable(false);
						butFinal.setEnabled(true);
					}
				} else if (evt == "New-Release") {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					// dirChooser.show(true);
					dirChooser.showOpenDialog(c);

					dirSelectFinal = dirChooser.getSelectedFile();
					if (dirSelectFinal == null) {
						sel = JOptionPane.showConfirmDialog(c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
					} else {
						txtFinal.setText(dirSelectFinal.toString());
						txtFinal.setEditable(false);
						butGenReport.setEnabled(true);
					}

				} else if (evt == "Generate-Report"
						&& (dirSelectFinal != null || dirSelectInitial != null)) {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					// dirChooser.show();
					dirChooser.showOpenDialog(c);

					dirSelectResult = dirChooser.getSelectedFile();
					if (dirSelectResult == null) {
						sel = JOptionPane.showConfirmDialog(LOC.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
					} else {
						System.out.print(dirSelectResult);
						generateReport();
					}
				}
			} catch (FileNotFoundException ioe) {
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		dd = new Date();
		df = new SimpleDateFormat("dd-MM-yy");
		resourceBundle = ResourceBundle.getBundle("loc");
		comment = Integer.valueOf(resourceBundle.getString(COMMENT)).intValue();
		// Added by SumitK
		fileTypes = resourceBundle.getString(FILETYPE);
		StringTokenizer stringTokenizer = new StringTokenizer(fileTypes, ",");
		for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
			fileType[i] = (String) stringTokenizer.nextElement();
		}
		LOC locdata = new LOC();
		locdata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		locdata.setVisible(true);
	}

	/**
	 * @param butFinal
	 * @param gbc
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 */

	public void add(Component comp, GridBagConstraints gbc, int x, int y,
			int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(comp, gbc);
	}

	/**
	 * 
	 */
	public static void generateReport() throws FileNotFoundException {

		try {
			tempDir1 = dirSelectInitial;
			tempDir = tempDir1;
			result_sizes = new ArrayList<Object>(10000);
			filesInitial = getFileListing(tempDir);
			writeToFile(tempDir);
			tempDir = null;
			tempDir2 = dirSelectFinal;
			tempDir = tempDir2;
			result_sizes.clear();
			result_sizes = new ArrayList<Object>(10000);
			filesFinal = getFileListing(tempDir);
			writeToFile(tempDir);
			findDifference(dirSelectResult);
			sel = JOptionPane.showConfirmDialog(c,
					"Report Generated!! File Stored in " + resultpath,
					"Report Generated", JOptionPane.CLOSED_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException ioe) {
			ioe.getMessage();
		}
	}

	/**
	 * @param tempDir
	 */
	private static void writeToFile(File tempDir) throws FileNotFoundException {
		PrintWriter initialfiles = null;
		PrintWriter newfiles = null;
		Iterator<?> filesIter = null;

		Iterator<Object> filesSizeIter = result_sizes.iterator();
		String str = "";
		boolean printIt = false;
		startIndex = endIndex = -1;

		if (tempDir == tempDir1) {

			try {
				initialfilepath = tempDir.toString() + "\\Initial.txt";
				initialfiles = new PrintWriter(new FileWriter(new File(
						initialfilepath)));
				filesIter = filesInitial.iterator();
				InitialMap = new HashMap<String, String>();

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} else {
			try {
				newfilepath = tempDir.toString() + "\\Final.txt";
				newfiles = new PrintWriter(
						new FileWriter(new File(newfilepath)));
				filesIter = filesFinal.iterator();
				FinalMap = new HashMap<String, String>();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		while (filesIter.hasNext()) {

			File f = (File) filesIter.next();
			if (f.isFile()) {
				str = f.getName() + " " + filesSizeIter.next();
				printIt = true;
			} else {
				filesSizeIter.next();
			}
			if (printIt) {
				if (tempDir == tempDir1) {
					initialfiles.println(str);
					int o = str.indexOf(" ");
					String jj = str.substring(0, o);
					int zz = str.lastIndexOf(" ");
					String yy = str.substring(zz + 1);
					InitialMap.put(jj, yy);
					initialfiles.flush();
					printIt = false;
				} else {
					newfiles.println(str);
					int o1 = str.indexOf(" ");
					String jj1 = str.substring(0, o1);
					int zz1 = str.lastIndexOf(" ");
					String yy1 = str.substring(zz1 + 1);
					FinalMap.put(jj1, yy1);
					newfiles.flush();
					printIt = false;
				}
			}
		}
		if (tempDir == tempDir1) {
			initialfiles.close();
			filesSizeIter = null;
			result_sizes.clear();
		} else {
			newfiles.close();
		}
	}

	/**
	 * @param dirSelectResult
	 */
	public static void findDifference(File dirSelectResult) {
		int initialCount = 0;
		int finalCount = 0;
		int diff = 0;
		int totalCount = 0;
		Object o;
		Object o1;

		String str3 = "";

		PrintWriter resultfiles = null;
		try {
			resultpath = dirSelectResult.getAbsolutePath() + "\\" + "Result("
					+ df.format(dd).toString() + ")" + ".xls";
			resultfiles = new PrintWriter(new FileWriter(new File(resultpath)));
		} catch (IOException ioe) {
		}

		str3 = "The Line Of Code Status Report";
		resultfiles.println(str3);
		str3 = "";
		resultfiles.println(str3);
		str3 = "Date: ";
		resultfiles.print(str3);
		resultfiles.println(df.format(dd).toString());
		str3 = "";
		resultfiles.println(str3);
		resultfiles.println(str3);
		str3 = "File Name\t\tChanged Code-Lines\t\tStatus";
		resultfiles.println(str3);

		str3 = "";
		resultfiles.println(str3);

		resultfiles.println(str3);
		Iterator<?> it = InitialMap.keySet().iterator();

		while (it.hasNext()) {
			o = it.next();
			initialCount = Integer.parseInt((InitialMap.get(o)));

			if (FinalMap.get(o) == null) {
				str3 = o + "\t" + "\t" + "-" + initialCount + "\t" + "\t"
						+ "File Deleted";
				initialCount = -initialCount;
				totalCount = totalCount + initialCount;
			} else {
				finalCount = Integer.parseInt((FinalMap.get(o)));
				diff = finalCount - initialCount;
				str3 = o + "\t" + "\t" + diff + "\t" + "\t" + "No Change";
				totalCount = totalCount + diff;
				FinalMap.remove(o);
			}
			resultfiles.println(str3);
			resultfiles.flush();
		}

		if (!FinalMap.isEmpty()) {
			Iterator<?> it1 = FinalMap.keySet().iterator();
			while (it1.hasNext()) {
				o1 = it1.next();
				finalCount = Integer.parseInt((FinalMap.get(o1)));
				str3 = o1 + "\t" + "\t" + "+" + finalCount + "\t" + "\t"
						+ "File Added";
				totalCount = totalCount + finalCount;
				resultfiles.println(str3);
			}
			// resultfiles.println("");
			// resultfiles.println("");
			// resultfiles
			// .println("Total Line of Code" + "\t" + "\t" + totalCount);
			// resultfiles.flush();
		}
		resultfiles.println("");
		resultfiles.println("");
		resultfiles.println("Total Line of Code" + "\t" + "\t" + totalCount);
		resultfiles.flush();
		InitialMap.clear();
		FinalMap.clear();
		resultfiles.close();
	}

	/**
	 * @param tempDir
	 * @return result Modify by SumitK on dated 02.12.2005 for giving option to
	 *         retrieve FileTypes from loc.peoperties
	 */
	public static List<File> getFileListing(File tempDir)
			throws FileNotFoundException {
		List<File> result = new ArrayList<File>(500);
		try {
			validateDirectory(tempDir);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}

		File[] filesAndDirs = tempDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		Iterator<File> filesIter = filesDirs.iterator();
		File file = null;
		boolean flag;
		while (filesIter.hasNext()) {
			flag = false;
			file = (File) filesIter.next();
			if (!file.isFile()) {

				// must be a directory recursive call! add no size//
				List<File> deeperList = getFileListing(file);
				if (deeperList != null) {
					result.addAll(deeperList);
				} else {
					// Added by SumitK
					for (int i = 0; (fileType.length > 0)
							&& (null != fileType[i]); i++) {
						if ((null != fileType[i])
								&& !(file.getName().toString()
										.endsWith(fileType[i].toString()))) {
							continue;
						} else {
							flag = true;
						}

					}
					if (flag) {
						result.add(file);
						result_sizes.add(countLines(file));
						flag = true;
					}

				}
			} else {
				// get the size
				for (int i = 0; (fileType.length > 0) && (null != fileType[i]); i++) {
					if (!(file.getName().toString().endsWith(fileType[i]
							.toString()))) {
						continue;
					} else {
						flag = true;
					}
				}
				if (flag) {
					result.add(file);
					result_sizes.add(countLines(file));
					flag = true;

				}
			}
		}
		return result;
	}

	/**
	 * @param file
	 * @return
	 */
	private static Object countLines(File file) {
		int lineCount = 0;
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("Error.  Can't open file.");
			return "-1";
		}

		try {

			currentLine = "";
			nextLine = "";
			currentLine = in.readLine();
			while (currentLine != null) {

				nextLine = currentLine.trim();
				if (comment == 1) {
					if (nextLine.length() > 0
							&& (!(nextLine.length() >= 2 && nextLine.substring(
									0, 2).equals(SL_COMMENT)))) // if not a
																// blank line
					{

						lineCount++;

						if ((file.getName().toString().endsWith(".java"))) {
							startIndex = nextLine.indexOf(START_COMMENT);
						} else {
							startIndex = nextLine.indexOf(XML_START_COMMENT);
						}

						if (startIndex == 0) {
							lineCount--;
						}

						if (startIndex >= 0) {
							searchForEnd(file);
							continue;
						}
					}
				} else if (comment == 0 && nextLine != null) {
					lineCount++;
				}

				currentLine = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e
					+ "           Error. Problem with reading from file.");
			return "-1";
		}

		return lineCount + "";
	}

	/**
	 * @throws Exception
	 */
	protected static void searchForEnd(File file) throws Exception {
		boolean found = false;

		while (!found) {

			if ((file.getName().toString().endsWith(".java"))) {
				endIndex = nextLine.indexOf(END_COMMENT);
			} else {
				endIndex = nextLine.indexOf(XML_END_COMMENT);
			}
			if (endIndex >= 0) {
				found = true;
				if (nextLine.length() >= (endIndex + 2)) {
					currentLine = nextLine.substring(endIndex + 2);
					return;
				} else
					currentLine = in.readLine();
			} else
				nextLine = in.readLine();
		} // end while

		return;
	}

	/**
	 * @param tempDir1
	 */
	public static void validateDirectory(File tempDir)
			throws FileNotFoundException {

		if (tempDir == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!tempDir.exists()) {
			throw new FileNotFoundException("Directory does not exist: "
					+ tempDir);
		}
		if (!tempDir.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: " + tempDir);
		}
		if (!tempDir.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: "
					+ tempDir);
		}
	}

}