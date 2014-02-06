package peroJava;

import ij.*;
import java.io.*;
import javax.swing.*;

public class Test {
	
	public static void main(String[] args) {
		
	    // ***** this is to read the directory where the images saved
	    // ***** Jimmy has rewritten this part such that no dialog will pop out
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(fc);
		String path = fc.getSelectedFile().toString();

// Parameters for Mean-Shift --
		int sr = 3;
		int cr = 25;
		
		File file = new File(path);
		File[] files = file.listFiles();
		for (int fileInList = 0; fileInList < files.length; fileInList++) {
			if (files[fileInList].isFile() == true && !files[fileInList].getName().startsWith(".")) {
				
				String name = files[fileInList].getName();
				
				ImagePlus imp = IJ.openImage(new File(path, name).toString());
				Well well1 = new Well(imp);
				well1.contrastEnhance();

// Uncomment for executing Mean-Shift --
				well1.meanShift(sr, cr);
				
				// well1.showImage();
				well1.saveAs(path, "/enhanced", name);
			}
		}
	}
}