package maw.security.tls.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class UnsecureClient {

	public String send(String message) {
		return sendSafely(message);
	}

	private String sendSafely(String message) {
		try {
			System.out.println("Client received a message");
			Socket clientSocket = new Socket("localhost", 9000);
			System.out.println("Connection with server established");
			OutputStream out = clientSocket.getOutputStream();
			InputStream in = clientSocket.getInputStream();
			IOUtils.write(message, out, "UTF-8");
			Thread.sleep(1000);
			return IOUtils.toString(in, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
