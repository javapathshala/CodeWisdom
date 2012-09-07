/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.app.fileextension;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class ChangeFileExtension extends JFrame {

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
	public ChangeFileExtension() {
		setTitle("Change File Extensions");
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChangeFileExtension changeFileExtension = new ChangeFileExtension();
		// readFolder.accessProperties();
		changeFileExtension.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		changeFileExtension.setVisible(true);
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
						int sel = JOptionPane.showConfirmDialog(
								ChangeFileExtension.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
					} else {
						txtDirSel.setText(selectedDir.toString());
						txtDirSel.setEditable(false);
					}
				} else if (evt == "Generate-Report" && (selectedDir != null)) {
					// dirChooser = new JFileChooser();
					// dirChooser.setCurrentDirectory(null);
					// dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					// dirChooser.showOpenDialog(c);
					// resultDir = dirChooser.getSelectedFile();
					// if (resultDir == null) {
					// int sel =
					// JOptionPane.showConfirmDialog(ChangeFileExtension.c,
					// "Pls Select the Folder !", "Select Folder",
					// JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
					// } else {
					GenerateReport();
					// }
				}
			} catch (FileNotFoundException ioe) {
			} catch (IOException e1) {
			}
		}
	}

	public void GenerateReport() throws IOException {
		try {
			Map fileList = getFileListing(selectedDir);
			// / String reportFilePath = WriteToFile(fileList);
			int sel = JOptionPane.showConfirmDialog(ChangeFileExtension.c,
					"Report Generated!! File Stored in " + "",
					"Report Generated", JOptionPane.CLOSED_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException ioe) {
			ioe.getMessage();
		}
	}

	/**
	 * @param tempDir
	 * @return result Modify by SumitK on dated 02.12.2005 for giving option to
	 *         retrieve FileTypes from loc.peoperties
	 */
	public Map getFileListing(File selectedDir) throws FileNotFoundException {

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
		File oldfile = null;
		Map result = new HashMap();
		while (filesDirIter.hasNext()) {
			// flag = false;
			oldfile = (File) filesDirIter.next();
			if (oldfile.isFile()) { // file

				// BufferedReader in = new BufferedReader(new
				// InputStreamReader(System.in));
				// System.out.print("Please enter the filename with extension to be changed: ");
				// String oldfileExtension = in.readLine();
				// File oldfile = new File(oldfileExtension);
				// if (!oldfile.exists()) {
				// System.out.println("File does not exist.");
				// System.exit(0);
				// }
				// int dotPos = oldfileExtension.lastIndexOf(".");
				// String strExtension = oldfileExtension.substring(dotPos + 1);
				// String strFilename = oldfileExtension.substring(0, dotPos);
				// String newfileExtension = in.readLine();
				// String strNewFileName = strFilename + "." + newfileExtension;
				// File newfile = new File(strNewFileName);
				// boolean Rename = oldfile.renameTo(newfile);
				// if (!Rename) {
				// System.out.println("FileExtension hasn't been changed successfully.");
				// } else {
				// System.out.println("FileExtension has been changed successfully.");
				// }
				// }

				// change file extension old=.txt to .doc
				String oldfilename = oldfile.getPath();
				String newfilename = oldfilename.replace(".txt", ".doc");
				newfilename = newfilename.replace("Recovered_Generic Email",
						"mail");
				//
				// int dotPos = oldfileExtension.lastIndexOf(".");
				// String strExtension = oldfileExtension.substring(dotPos + 1);
				// String strFilename = oldfileExtension.substring(0, dotPos);

				// String newfileExtension = "doc";
				// String strNewFileName = strFilename + "." + newfileExtension;
				// String strNewFileName = file.getName() + "." +
				// newfileExtension;

				// System.out.println(oldfile.getPath());
				File newfile = new File(newfilename);
				boolean Rename = oldfile.renameTo(newfile);
				if (!Rename) {
					System.out
							.println("FileExtension hasn't been changed successfully.");
				} else {
					System.out
							.println("FileExtension has been changed successfully.");
				}

				// int fileTypesLen = fileType.length;
				// for (int i = 0; (fileTypesLen > 0) && (null != fileType[i]);
				// i++) {
				// if
				// (!(file.getName().toString().endsWith(fileType[i].toString())))
				// {
				// continue;
				// } else {
				// flag = true;
				// }
				// }
				// if (flag) {
				// String Cpath = file.getAbsolutePath();
				// String fileName = file.getName();
				// String path = Cpath.substring(0, Cpath.indexOf(fileName));
				// result.put(fileName, path);
				// flag = false;
				// }
			} else { // dir
				// must be a directory recursive call! add no size//
				Map deeperList = getFileListing(oldfile);
				if (deeperList.size() > 0) {
					// result.addAll(deeperList);
					result.putAll(deeperList);
				} else {
					// for (int i = 0; (fileType.length > 0) && (null !=
					// fileType[i]); i++) {
					// if ((null != fileType[i]) &&
					// !(file.getName().toString().endsWith(fileType[i].toString())))
					// {
					// continue;
					// } else {
					// flag = true;
					// }
					// }
					// if (flag) {
					// String Cpath = file.getAbsolutePath();
					// String fileName = file.getName();
					// String path = Cpath.substring(0,
					// Cpath.indexOf(fileName));
					// result.put(fileName, path);
					// flag = false;
					// }
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

	// public static void main(String[] args) throws IOException {

	// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	// System.out.print("Please enter the filename with extension to be changed: ");
	// String oldfileExtension = in.readLine();
	// File oldfile = new File(oldfileExtension);
	// if (!oldfile.exists()) {
	// System.out.println("File does not exist.");
	// System.exit(0);
	// }
	// int dotPos = oldfileExtension.lastIndexOf(".");
	// String strExtension = oldfileExtension.substring(dotPos + 1);
	// String strFilename = oldfileExtension.substring(0, dotPos);
	// String newfileExtension = in.readLine();
	// String strNewFileName = strFilename + "." + newfileExtension;
	// File newfile = new File(strNewFileName);
	// boolean Rename = oldfile.renameTo(newfile);
	// if (!Rename) {
	// System.out.println("FileExtension hasn't been changed successfully.");
	// } else {
	// System.out.println("FileExtension has been changed successfully.");
	// }
	// }
	public void add(Component comp, GridBagConstraints gbc, int x, int y,
			int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(comp, gbc);
	}
}
