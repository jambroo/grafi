import java.io.File;

public class LS {
	public static void main(String[] args) {
		String pathStr = "/home/jamie/Downloads/";
		LSRun ls = new LSRun(pathStr);
		ls.run();
	}
}

class LSRun {
	private File pathFile;

	public LSRun(String path) {
		this.pathFile = new File(path);
	}

	public void run() {
		this.lsFiles(this.pathFile);
	}

	private void lsFiles(File file) {
		this.lsFiles(file, "", 0);
	}
	
	private void printFileInfo(File file, int depth) {
		for (int i=0; i<depth; i++) {
			System.out.print("\t");
		}
		System.out.print(file.getName());
		if (file.isDirectory()) {
			System.out.print(" :");
		}
		System.out.println();
	}

	private File[] lsFiles(File file, String parentDirs, int depth) {
		if (file.isFile()) {
			this.printFileInfo(file, depth);
			
			return null;
		}
		
		File[] files = file.listFiles();

		for (File f : files) {
			this.printFileInfo(f, depth);		
			if (f.isDirectory()) {		
				this.lsFiles(f, f.getAbsolutePath(), depth+1);
			}
		}

		return null;
	}

}
