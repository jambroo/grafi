package main.java.com.jambroo.grafi;

import java.awt.*;
import java.io.File;

class PhotoFiles {
	private File pathFile;
	private int offset;
	private int limit;
	private int count;
	private int outputCount;
	private PhotoFile[] files;
	private boolean debug;
	private int thumbHeight;
	private Dimension dimensions;

	public PhotoFiles(File pathFile, int offset, int limit, Dimension dimensions, boolean debug) {
		this.count = 0;
		this.outputCount = 0;
		this.offset = offset;
		this.limit = limit;
		this.pathFile = pathFile;
		this.debug = debug;
        	this.dimensions = dimensions;
        	this.files = new PhotoFile[limit];
		this.getFiles(this.pathFile, "", 0);
	}

	public PhotoFiles(File pathFile, int offset, int limit, Dimension dimensions) {
		this(pathFile, offset, limit, dimensions, false);
	}

	public PhotoFile[] get() {
		return this.files;
	}
	
	private void printFileInfo(File file, int depth) {
		for (int i=0; i<depth; i++) {
			System.out.print("\t");
		}
		System.out.print(file.getName());
		if (file.isDirectory()) {
			System.out.print(" :");
		} else {
			System.out.println(this.outputCount);
		}
		System.out.println();
	}

	private void outputFile(File file) {
		if (this.count >= this.offset) {
			if (!file.isDirectory()) {
				String extension = "";
				String filename = file.getName();
				int i = filename.lastIndexOf('.');
				if (i > 0) {
				    extension = filename.substring(i+1);
				}
				if ((extension.toLowerCase().matches("jpg")) ||
				    (extension.toLowerCase() == "gif") ||
				    (extension.toLowerCase() == "png")) {

                    			this.files[this.outputCount] = new PhotoFile(file.getAbsolutePath());

                    			this.outputCount++;
				}
			}
			this.count++;
		}
	}

	private void getFiles(File file, String parentDirs, int depth) {
		if (this.outputCount >= this.limit) {
			return;
		}
		if (file.isFile()) {
			if (this.debug) {
				this.printFileInfo(file, depth);
			}

			this.outputFile(file);		

			return;
		}
		
		File[] files = file.listFiles();

		for (File f : files) {
			if (this.outputCount >= this.limit) {
				return;
			}
			
			if (this.debug) {
				this.printFileInfo(f, depth);		
			}

			this.outputFile(f);
				
			if (f.isDirectory()) {		
				this.getFiles(f, f.getAbsolutePath(), depth+1);
			}
		}

		return;
	}

	public PhotoThumb[] getThumbs(int currentX, int currentY) {
		this.thumbHeight = 200;

		PhotoThumb[] thumbs = new PhotoThumb[this.files.length];

		// Loop through change lines, etc
		for (int i=0; i<this.files.length; i++) {
			try {
				PhotoFile photo = this.files[i];
				if (this.files[i] != null) {
					if ((photo.getWidth() > 0) && (photo.getHeight() > 0)) {
						int width = (int) Math.round(this.thumbHeight/(((double) photo.getHeight())/((double) photo.getWidth())));
						if (currentX+width > this.dimensions.getWidth()) {
							currentX = 0;
							currentY += this.thumbHeight;
						}

						thumbs[i] = photo.getThumb(currentX, currentY, width, this.thumbHeight);

						currentX += width;
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		return thumbs;
	}
}
