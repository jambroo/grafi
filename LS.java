import java.io.File;

public class LS {
	public static void main(String[] args) {
		int offset;
		int limit;
		String pathStr;

		try {
			pathStr = args[0];
			File f = new File(pathStr);
			f.isDirectory();
		} catch (Exception e) {
			throw e;
		}

		try {
			offset = Integer.parseInt(args[1]);
			limit = Integer.parseInt(args[2]);
		} catch (Exception e) {
			throw e;
		}

		LSRun ls = new LSRun(pathStr, offset, limit);
		ls.run();
	}
}

class LSRun {
	private File pathFile;
	private int offset;
	private int limit;
	private int count;
	private int outputCount;

	public LSRun(String path, int offset, int limit) {
		this.count = 0;
		this.outputCount = 0;
		this.pathFile = new File(path);
		this.offset = offset;
		this.limit = limit;
	}

	public void run() {
		this.lsFiles(this.pathFile);
	}

	private void lsFiles(File file) {
		this.lsFiles(file, "", 0);
	}
	
	private void printFileInfo(File file, int depth) {
		if (this.count >= this.offset) {
			for (int i=0; i<depth; i++) {
				System.out.print("\t");
			}
			System.out.print(file.getName());
			if (file.isDirectory()) {
				System.out.print(" :");
			} else {
				this.outputCount++;
			}
			System.out.println();
		}
		this.count++;
	}

	private File[] lsFiles(File file, String parentDirs, int depth) {
		if (this.outputCount >= this.limit) {
			return null;
		}
		if (file.isFile()) {
			this.printFileInfo(file, depth);
			
			return null;
		}
		
		File[] files = file.listFiles();

		for (File f : files) {
			if (this.outputCount >= this.limit) {
				return null;
			}

			this.printFileInfo(f, depth);		
			if (f.isDirectory()) {		
				this.lsFiles(f, f.getAbsolutePath(), depth+1);
			}
		}

		return null;
	}

}
