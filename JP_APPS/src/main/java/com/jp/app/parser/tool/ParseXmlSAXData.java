package com.jp.app.parser.tool;

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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.Document;

public class ParseXmlSAXData extends JFrame {

	private static final long serialVersionUID = 8730711366011287288L;

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
	
	public static JButton cancel;

	public static JLabel lblName;

	public static JLabel lblFooter;

	

	static Document dom;

	public ParseXmlSAXData() {
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

		cancel = new JButton("Close");
		add(cancel, gbc, 0, 10, 1, 1);
		cancel.setEnabled(true);
		
		lblFooter = new JLabel("Utility Developed by Dimit Chadha");
		add(lblFooter, gbc, 0, 13, 1, 1);
		
		butInitial.addActionListener(new addEvent());
		butGenReport.addActionListener(new addEvent());
		cancel.addActionListener(new addEvent());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParseXmlSAXData parseXmlSAXData = new ParseXmlSAXData();
		parseXmlSAXData.accessProperties();
		parseXmlSAXData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parseXmlSAXData.setVisible(true);
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
		fileType = resourceBundle.getString("FILETYPE").toString();
	}

	public void add(Component comp, GridBagConstraints gbc, int x, int y,
			int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(comp, gbc);
	}

	public class addEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getActionCommand();
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
			}else if(evt=="Close"){
				System.exit(0);
				
			}
		}

		/**
		 * 
		 */
		private void GenerateData() {
			try {
				List<File> fileList = getFileListing(selectedDir);
				Collections.sort(fileList);
				String reportFilePath = new XmlFileReader().createFile(fileList,resultDir);
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
	public List<File> getFileListing(File selectedDir)
			throws FileNotFoundException {
		List<File> fileList = new ArrayList<File>();
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
				if (!(file.getName().toString().endsWith(fileType))) {
					continue;
				} else {
					flag = true;
				}
				if (flag) {
					fileList.add(file);
					flag = false;
				}
			} else { // dir
				List<File> deeperList = getFileListing(file);
				if (deeperList.size() > 0) {
					result.addAll(deeperList);
				} else {
					if (!(file.getName().toString().endsWith(fileType))) {
						continue;
					} else {
						flag = true;
					}
					if (flag) {
						fileList.add(file);
						flag = false;
					}
				}
			}
		}
		return fileList;
	}

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