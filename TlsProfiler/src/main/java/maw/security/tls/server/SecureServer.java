package maw.security.tls.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

public class SecureServer {

	private void listen() throws IOException {
		int port = 443;
		ServerSocketFactory ssocketFactory = SSLServerSocketFactory
				.getDefault();
		ServerSocket ssocket = ssocketFactory.createServerSocket(port);

		Socket socket = ssocket.accept();

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		// Read from in and write to out...

		in.close();
		out.close();
	}

	public static void main(String[] args) {
		SecureServer server = new SecureServer();
		try {
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
