package maw.security.tls;

import java.io.StringWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.cert.X509Certificate;
import java.util.Base64;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

public class KeyHandler {

	public static KeyPair generateKeys(String alg) {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance(alg);
			generator.initialize(1024);
			return generator.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String encode(Key key) {
		Base64.Encoder encoder = Base64.getEncoder();
		StringWriter writer = new StringWriter();
		writer.write("-----BEGIN RSA PRIVATE KEY-----\n");
		writer.write(encoder.encodeToString(key.getEncoded()));
		writer.write("\n-----END RSA PRIVATE KEY-----\n");
		writer.flush();
		return writer.getBuffer().toString();
	}

	public static X509Certificate generateCertificate() {
		try {
			CertAndKeyGen keyGen = new CertAndKeyGen("RSA", "SHA1WithRSA", null);
			keyGen.generate(1024);
			return keyGen.getSelfCertificate(new X500Name("CN=ROOT"),
					(long) 365 * 24 * 3600);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
