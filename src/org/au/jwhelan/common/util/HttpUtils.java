package org.au.jwhelan.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;

/**
 * Class to perform various convenience methods for creating HTTP connections,
 * retrieving data from connection output streams.  
 * 
 * @author James Whelan
 *
 */
public class HttpUtils {

	final static String GET = "GET";
	
	public static HttpURLConnection getHttpConnection(String URL) throws IOException {
		return getHttpConnection(URL, null, 0);
	}
	
	public static HttpURLConnection getHttpConnection(String URL, String proxyHost, int proxyPort) throws IOException {
		URL url = new URL(URL);
		
		Proxy proxy = null;
		
		if (proxyHost != null && proxyPort > 0) {
			proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
		}
		
		HttpURLConnection connection = (HttpURLConnection) (proxy == null ? 
				url.openConnection() : url.openConnection(proxy));
		
		connection.setRequestMethod(GET);

		connection.setAllowUserInteraction(false);
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);

		return connection;
	}
	
	public static String getOutputStringFromConnection(HttpURLConnection conn) throws IOException {
		
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] data = new byte[32768];
		int len = 0;
		
		while ((len = bis.read(data)) > 0) {
			baos.write(data, 0, len);
		}
		String results = baos.toString();
		baos.close();
		bis.close();
		
		return results;
	}
	
}
