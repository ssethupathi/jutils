package temenos.security.tls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class TlsClient {

	private static final String URL = "URL";
	private static String urlStr = "https://temenos.com";
	private MyBufferedOutputStream logger = new MyBufferedOutputStream();
	private String dumpDir;

	static {
		urlStr = System.getProperty(URL, urlStr);
	}

	private StringBuffer buffer = new StringBuffer();

	public static void main(String[] args) {
		new TlsClient().execute();
	}

	private void execute() {
		try {
			System.setOut(new PrintStream(logger));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			URL url = new URL(urlStr);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			captureCertificate(con);
			captureContent(con);
			if (!dumpContent()) {
				System.out.println(buffer.toString());
			}
			if (!dumpLog()) {
				System.out.println(logger.getContent());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void captureCertificate(HttpsURLConnection con) {
		if (con != null) {
			try {
				buffer.append("Http Status: " + con.getResponseCode());
				buffer.append("\n").append(
						"Cipher Suite: " + con.getCipherSuite());
				buffer.append("\n").append("\n");
				Certificate[] certs = con.getServerCertificates();
				int count = 0;
				for (Certificate cert : certs) {
					buffer.append("\n").append("Cert Count :" + count++);
					buffer.append("\n").append("Cert Type : " + cert.getType());
					buffer.append("\n").append(
							"Cert Hash Code : " + cert.hashCode());
					buffer.append("\n").append(
							"Cert Public Key Algorithm : "
									+ cert.getPublicKey().getAlgorithm());
					buffer.append("\n").append(
							"Cert Public Key Format : "
									+ cert.getPublicKey().getFormat());
					buffer.append("\n").append("\n");
				}
			} catch (SSLPeerUnverifiedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void captureContent(HttpsURLConnection con) {
		if (con != null) {
			try {
				buffer.append("\n").append("Content:");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String input;
				while ((input = br.readLine()) != null) {
					buffer.append("\n").append(input);
				}
				br.close();
			} catch (IOException e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				buffer.append(errors.toString());
			}
		}
	}

	private boolean dumpContent() {
		return dump(buffer.toString(), getContentPath());
	}

	private boolean dumpLog() {
		return dump(logger.getContent(), getSslLogPath());
	}

	private boolean dump(String content, Path path) {
		BufferedWriter writer = null;
		try {
			Files.createDirectories(path.getParent());
			Path filePath = Files.createFile(path);
			writer = new BufferedWriter(new FileWriter(filePath.toFile()));
			writer.write(content);
			writer.flush();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					// do nothing
				}
			}
		}
	}

	private Path getContentPath() {
		String pathStr = getDumpDirPath() + "/ssl_client_content.txt";
		return Paths.get(pathStr);
	}

	private Path getSslLogPath() {
		String pathStr = getDumpDirPath() + "/ssl_client_log.txt";
		return Paths.get(pathStr);
	}

	private String getDumpDirPath() {
		if (dumpDir == null) {
			String pwd = TlsClient.class.getProtectionDomain().getCodeSource()
					.getLocation().getPath();
			dumpDir = System.getProperty("os.name").contains("indow") ? pwd
					.substring(1) : pwd;
			dumpDir = dumpDir + urlStr.replaceAll("\\W", "_") + "/"
					+ System.currentTimeMillis();
		}
		return dumpDir;
	}

	private static class MyBufferedOutputStream extends OutputStream {

		private StringBuilder sb = new StringBuilder();

		@Override
		public void write(int b) throws IOException {
			sb.append((char) b);
		}

		public String getContent() {
			return sb.toString();
		}
	}
}
