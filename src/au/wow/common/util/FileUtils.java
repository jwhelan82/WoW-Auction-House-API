package au.wow.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.WritableByteChannel;

public class FileUtils {

	public static byte[] readFile(File file, boolean lock) throws IOException {
		FileChannel in = null;
		FileLock fl = null;
		try {
			in = new FileInputStream(file).getChannel();
			if (lock) {
				fl = in.lock(0L, Long.MAX_VALUE, true);
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			WritableByteChannel out = Channels.newChannel(baos);
			in.transferTo(0, in.size(), out);
			return baos.toByteArray();
		} finally {
			if (fl != null && fl.isValid()) {
				fl.release();
			}
			if (in != null) {
				in.close();
			}
		}
	}
	
	public static String readFileString(File file) throws IOException {
		return readFileString(file, false);
	}

	public static String readFileString(File file, boolean lock)
			throws IOException {
		return new String(readFile(file, lock), "UTF-8");
	}
}
