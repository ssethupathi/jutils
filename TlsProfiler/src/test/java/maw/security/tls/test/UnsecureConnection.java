package maw.security.tls.test;

import maw.security.tls.client.UnsecureClient;
import maw.security.tls.server.Server;
import maw.security.tls.server.UnsecureServer;

import org.junit.Test;

public class UnsecureConnection {

	@Test
	public void test() {
		Server server = new UnsecureServer();
		server.start();
		UnsecureClient client = new UnsecureClient();
		String reply = client.send("Hi");
		System.out.println(reply);
		server.stop();
		client.send("bye");
	}

}
