package maw.security.tls.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import maw.security.tls.demo.app.EchoService;

public class UnsecureServer implements Server {

	private AtomicBoolean running = new AtomicBoolean(false);
	private ServerSocket server;
	private List<Executor> executors = new ArrayList<Executor>();

	public void start() {
		System.out.println("Server start requested");
		running.compareAndSet(false, true);
		try {
			server = new ServerSocket(9000);
			System.out.println("Server socket created");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		new Thread(new Runnable() {

			public void run() {
				System.out.println("Server thread invoked");
				try {
					while (running.get()) {
						System.out.println("Server listen and fork being invoked");
						listenAndFork();
						System.out.println("Server listen and fork returned");
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}, "Server").start();
	}

	public void stop() {
		System.out.println("Server stop called");
		running.compareAndSet(true, false);
		for (Executor executor : executors) {
			System.out.println("Executor stop being called");
			executor.stop();
		}
		System.out.println("Server stop executed");
	}

	private void listenAndFork() throws Exception {
		Socket socket = server.accept();
		System.out.println("Server listen connected");
		Executor executor = new Executor(socket, new EchoService());
		executors.add(executor);
		System.out.println("Server forking a new executor");
		new Thread(executor, "Executor").start();
		System.out.println("Server forking completed");
	}

}
