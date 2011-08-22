package au.wow.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	// doesn't close streams!
	public static void copyStreams(InputStream in, OutputStream out) throws IOException {
		int read;
		byte[] fileBuffer = new byte[32768];
		while ((read = in.read(fileBuffer)) > 0) {
			out.write(fileBuffer, 0, read);
		}
	}
}
