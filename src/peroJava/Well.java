package peroJava;

import ij.*;
import ij.process.*;
import ij.io.*;
import ij.plugin.ContrastEnhancer;

public class Well {
	
	private ImagePlus imp;
	
	public Well(ImagePlus imp) {
		
		ImageProcessor ip = imp.getProcessor().convertToFloat();
		this.imp = imp;
		this.imp.setProcessor(ip);
	}
	
    // ***** Removed for Azure *****
    //	public void showImage() {
    //		imp.show();
    //	}
	
	public void contrastEnhance() {
	    // ***** This is what causes the problem????? *****
		//IJ.run(imp, "Enhance Contrast", "saturated=0.35 normalize");
		double saturated = 0.35;
		ContrastEnhancer contrastEnhancer = new ContrastEnhancer();
		contrastEnhancer.setNormalize(true); // requires latest ImageJ library
		contrastEnhancer.stretchHistogram(imp, saturated);
		imp.getProcessor().resetMinAndMax();
		imp.updateImage();		
	}
	
	public void meanShift(int spacial, float color) {
		
		Mean_Shift ms = new Mean_Shift();
		ImageProcessor ip = this.imp.getProcessor();
		
		ms.isRGB = this.imp!=null && this.imp.getType()==ImagePlus.COLOR_RGB;
		ms.rad = spacial;
		ms.rad2 = spacial*spacial;
		ms.radCol = color;
		ms.radCol2 = color*color;
		
		ms.run(ip);
		this.imp.setProcessor(ip);
	}
	
    //	public void topHat() {	
    //	}
    //	
    //	public void histogram(){
    //		
    //	}
	
	public void saveAs(String path, String subdir, String name){
		FileSaver fs = new FileSaver(this.imp);
		fs.saveAsPng(path + subdir + name);
	}
}