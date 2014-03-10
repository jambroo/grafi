import java.io.File;

public class LS {

	public static void main(String[] args) {
		String pathStr = "/home/james/Downloads/";
		File path = new File(pathStr);

		this.lsFiles(new File(pathStr));
	}

	private File[] lsFiles(File path) {
		return this.lsFiles(path, "", 0);
	}

	private File[] lsFiles(File path, String parentDirs, int depth) {
		if (path.isFile()) {
			// do tabs and output str


			File[] files = new File[1];
			file[0] = path;

			return files;
		}
		
		File[] files = path.listFiles();

		for (File f : files) {
			if (f.isFile()) {
				for (int i=0; i<depth; i++) {
					System.out.print("\t");
				}
				System.out.println(f.getName());
			} else if (f.isDirectory()) {
				this.lsFiles(f, f.getAbsolutePath(), depth+1);
			}
		}
	}

}
