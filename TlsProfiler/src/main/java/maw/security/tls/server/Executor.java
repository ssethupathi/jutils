package maw.security.tls.server;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import maw.security.tls.demo.app.Service;

import org.apache.commons.io.IOUtils;

public class Executor implements Runnable {
	private Service service;

	private InputStream in;
	private OutputStream out;

	private AtomicBoolean running = new AtomicBoolean(true);

	public Executor(Socket socket, Service service) {
		this.service = service;
		try {
			this.in = socket.getInputStream();
			this.out = socket.getOutputStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void run() {
		handle();
	}

	public void stop() {
		System.out.println("Server stop requested");
		running.compareAndSet(true, false);
		close(in);
		close(out);
		System.out.println("Server stop completed");
	}

	private void close(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handle() {
		try {
			while (running.get()) {
				String message = readInput(in);
				String result = service.execute(message);
				IOUtils.write(result, out, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String readInput(InputStream in) throws IOException {
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(
				in));
		return inFromClient.readLine();
	}
}
