package com.jambroo.grafi.LS;

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
