package com.jp.app.mp3;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.blinkenlights.jid3.ID3Tag;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;

import com.jp.app.readFolder.ReadFolder;

public class UpdateSongAttributes extends JFrame {

	private static final long serialVersionUID = 1L;
	public String fileType[] = new String[10];
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

	/**
	 * 
	 */
	public UpdateSongAttributes() {
		setTitle("Songs Update Utility");
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

		butGenReport = new JButton("Generate-Report");
		add(butGenReport, gbc, 1, 8, 1, 1);
		butGenReport.setEnabled(true);

		lblFooter = new JLabel("Utility Developed by Dimit Chadha");
		add(lblFooter, gbc, 0, 10, 1, 1);

		butInitial.addActionListener(new addEvent());
		butGenReport.addActionListener(new addEvent());

	}

	public class addEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getActionCommand();
			try {
				if (evt == "Folder-Location") {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					dirChooser.showOpenDialog(c);
					selectedDir = dirChooser.getSelectedFile();
					if (selectedDir == null) {
						int sel = JOptionPane.showConfirmDialog(ReadFolder.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						System.out.println(sel);
					} else {
						txtDirSel.setText(selectedDir.toString());
						txtDirSel.setEditable(false);
					}
				} else if (evt == "Generate-Report" && (selectedDir != null)) {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					dirChooser.showOpenDialog(c);
					resultDir = dirChooser.getSelectedFile();
					if (resultDir == null) {
						int sel = JOptionPane.showConfirmDialog(ReadFolder.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						System.out.println(sel);
					} else {
						GenerateReport();
					}
				}
			} catch (FileNotFoundException ioe) {
			} catch (IOException e1) {
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UpdateSongAttributes updateSongAttributes = new UpdateSongAttributes();
		updateSongAttributes.accessProperties();
		updateSongAttributes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateSongAttributes.setVisible(true);
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
		// ResourceBundle resourceBundle =
		// ResourceBundle.getBundle("readFolder");
		// String fileTypes = resourceBundle.getString("FILETYPE");
		// StringTokenizer stringTokenizer = new StringTokenizer(fileTypes,
		// ",");
		// for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
		// fileType[i] = (String) stringTokenizer.nextElement();
		// }
		fileType[0] = "mp3";
	}

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
	public void GenerateReport() throws IOException, Exception {
		try {
			// Map<String, String> fileList = getFileListing(selectedDir);
			List<File> fileList = getFileListing(selectedDir);
			// String reportFilePath = updateSongInfo(fileList);//
			// WriteToFile(fileList);
			updateSongInfo(fileList);// WriteToFile(fileList);
			int sel = JOptionPane.showConfirmDialog(c,
					"Report Generated!! File Stored in " + "Dimit",
					"Report Generated", JOptionPane.CLOSED_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			System.out.println(sel);
		} catch (FileNotFoundException ioe) {
			ioe.getMessage();
		}
	}

	private void updateSongInfo(List<File> fileList) throws Exception {
		MediaFile oMediaFile = null;
		ID3Tag[] aoID3Tag = null;
		int length = 0;
		String album = "";
		String title = "";

		for (File file : fileList) {
			oMediaFile = new MP3File(file);
			aoID3Tag = oMediaFile.getTags();
			length = aoID3Tag.length;
			for (int i = 0; i < length; i++) {
				if (aoID3Tag[i] instanceof ID3V1_0Tag) {
					ID3V1_0Tag oID3V1_0Tag = (ID3V1_0Tag) aoID3Tag[i];
					// does this tag happen to contain a title?
					//if (oID3V1_0Tag.getTitle() != null) {
						System.out.println("Title = " + oID3V1_0Tag.getTitle());
						String[] split = file.getName().split("\\.");
						String fileName = split[0];
						if (title == "" || !title.equals(fileName)) {
							oID3V1_0Tag.setTitle(fileName);
						}
						oID3V1_0Tag.setAlbum("Sufi");
						System.out.println("File Name  = " + split[0]);
						oMediaFile.setID3Tag(oID3V1_0Tag);
						oMediaFile.sync();

					//}
					// etc.
				} else if (aoID3Tag[i] instanceof ID3V2_3_0Tag) {
					ID3V2_3_0Tag oID3V2_3_0Tag = (ID3V2_3_0Tag) aoID3Tag[i];
					// check if this v2.3.0 frame contains a title, using the
					// actual
					// frame name
					if (oID3V2_3_0Tag.getTIT2TextInformationFrame() != null) {
						// System.out.println("Title 2 = "
						// + oID3V2_3_0Tag.getTIT2TextInformationFrame()
						// .getTitle());
						album = oID3V2_3_0Tag.getAlbum();
						title = oID3V2_3_0Tag.getTitle();
						if (title == null || title == "") {
							title = "";
						}
						System.out.println("Album 2= " + album);
						System.out.println("Title 2 = " + title);
						String[] split = file.getName().split("\\.");
						String fileName = split[0];
						if (title == "" || !title.equals(fileName)) {
							oID3V2_3_0Tag.setTitle(fileName);
						}
						oID3V2_3_0Tag.setAlbum("Sufi");
						System.out.println("File Name 2 = " + split[0]);
						oMediaFile.setID3Tag(oID3V2_3_0Tag);
						oMediaFile.sync();
					}

				}
			}
			aoID3Tag = null;
			oMediaFile = null;
		}

	}

	// the file we are going to read
	// File oSourceFile = new File("D:/Dimit/Songs/Punjabi/Amplifier.mp3");

	// create an MP3File object representing our chosen file
	// MediaFile oMediaFile = new MP3File(oSourceFile);

	// any tags read from the file are returned, in an array, in an order
	// which you should not assume
	// ID3Tag[] aoID3Tag = oMediaFile.getTags();
	// let's loop through and see what we've got
	// (NOTE: we could also use getID3V1Tag() or getID3V2Tag() methods, if
	// we specifically want one or the other)

	// for (int i = 0; i < length; i++) { // check to see if we read a v1.0 tag,
	// or a v2.3.0 tag (just for // example..) if (aoID3Tag[i] instanceof
	// ID3V1_0Tag) { ID3V1_0Tag oID3V1_0Tag = (ID3V1_0Tag) aoID3Tag[i]; // does
	// this tag happen to contain a title? if (oID3V1_0Tag.getTitle() != null) {
	// System.out.println("Title 1 = " + oID3V1_0Tag.getTitle());
	// System.out.println("Album 1 = " + oID3V1_0Tag.getAlbum()); } // etc. }
	// else if (aoID3Tag[i] instanceof ID3V2_3_0Tag) { ID3V2_3_0Tag
	// oID3V2_3_0Tag = (ID3V2_3_0Tag) aoID3Tag[i]; // check if this v2.3.0 frame
	// contains a title, using the actual // frame name if
	// (oID3V2_3_0Tag.getTIT2TextInformationFrame() != null) { //
	// System.out.println("Title 2 = " // +
	// oID3V2_3_0Tag.getTIT2TextInformationFrame() // .getTitle()); String album
	// = oID3V2_3_0Tag.getAlbum(); String title = oID3V2_3_0Tag.getTitle();
	// System.out.println("Album 2= " + album); System.out.println("Title 2 = "
	// + title); String[] split = oSourceFile.getName().split("\\."); String
	// fileName = split[0]; if (!title.equals(fileName)) {
	// oID3V2_3_0Tag.setTitle(fileName);
	// *
	// * } oID3V2_3_0Tag.setAlbum("Dance-Dance");
	// * System.out.println("File Name 2 = " + split[0]);
	// * oMediaFile.setID3Tag(oID3V2_3_0Tag); oMediaFile.sync(); } // but check
	// * using the convenience method if it has a year set // (either way works)
	// * // try { // System.out.println("Year = " + oID3V2_3_0Tag.getYear()); //
	// * // reads // // TYER // // frame // } catch (ID3Exception e) { // //
	// error
	// * getting year.. if one wasn't set //
	// * System.out.println("Could get read year from tag: " // + e.toString());
	// * // } // etc. } }
	// *
	// * return null; }
	//

	/**
	 * @param tempDir
	 */
	/**
	 * private String WriteToFile(Map<String, String> fileList) throws
	 * IOException { PrintWriter printRecs = null; String reportFilePath = "";
	 * try { Date today = new Date(); DateFormat dateFormat = new
	 * SimpleDateFormat("dd-MM-yy"); reportFilePath = resultDir.toString() +
	 * "\\" + "Result(" + dateFormat.format(today).toString() + ")" + ".xls";
	 * printRecs = new PrintWriter( new FileWriter(new File(reportFilePath))); }
	 * catch (IOException ioe) { } Iterator<?> files =
	 * fileList.keySet().iterator(); while (files.hasNext()) { String fileName =
	 * files.next().toString(); String path = fileList.get(fileName).toString();
	 * printRecs.println(fileName + "\t" + path); System.out.print("lib/" +
	 * fileName + ";"); printRecs.flush(); } printRecs.close(); return
	 * reportFilePath; }
	 **/
	/**
	 * @param tempDir
	 * @return result Modify by SumitK on dated 02.12.2005 for giving option to
	 *         retrieve FileTypes from loc.peoperties
	 */
	// public Map<String, String> getFileListing(File selectedDir)
	public List<File> getFileListing(File selectedDir)
			throws FileNotFoundException {

		try {
			validateDirectory(selectedDir);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}

		File[] filesAndDirs = selectedDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		Iterator<File> filesDirIter = filesDirs.iterator();
		boolean flag;
		File file = null;
		// Map<String, String> result = new HashMap<String, String>();
		List<File> result = new ArrayList<File>();
		String fileName = null;
		while (filesDirIter.hasNext()) {
			flag = false;
			file = (File) filesDirIter.next();
			if (file.isFile()) { // file
				int fileTypesLen = fileType.length;
				for (int i = 0; (fileTypesLen > 0) && (null != fileType[i]); i++) {
					if (!(file.getName().toString().endsWith(fileType[i]
							.toString()))) {
						continue;
					} else {
						flag = true;
					}
				}
				if (flag) {
					// String Cpath = file.getAbsolutePath();
					// fileName = file.getName();
					// String path = Cpath.substring(0,
					// Cpath.indexOf(fileName));
					// result.put(fileName, path);
					result.add(file);
					flag = false;
				}
			} else { // dir
				// must be a directory recursive call! add no size//
				// Map<String, String> deeperList = getFileListing(file);
				List<File> deeperList = getFileListing(file);
				if (deeperList.size() > 0) {
					// result.addAll(deeperList);
					// result.putAll(deeperList);
					result.addAll(deeperList);
				} else {
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
						String Cpath = file.getAbsolutePath();
						fileName = file.getName();
						// String path = Cpath.substring(0,
						// Cpath.indexOf(fileName));
						// result.put(fileName, path);
						result.add(file);
						flag = false;
					}
				}
			}
		}
		return result;
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
}
