package jutils.jee.test;

import java.io.PrintWriter;
import java.util.Set;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

public class XaTestManagedConnectionFactory implements ManagedConnectionFactory {
	
	private static final long serialVersionUID = 1L;

	public Object createConnectionFactory() throws ResourceException {
		return null;
	}

	public Object createConnectionFactory(ConnectionManager arg0)
			throws ResourceException {
		return null;
	}

	public ManagedConnection createManagedConnection(Subject arg0,
			ConnectionRequestInfo arg1) throws ResourceException {
		return null;
	}

	public PrintWriter getLogWriter() throws ResourceException {
		return null;
	}

	public ManagedConnection matchManagedConnections(Set arg0, Subject arg1,
			ConnectionRequestInfo arg2) throws ResourceException {
		return null;
	}

	public void setLogWriter(PrintWriter arg0) throws ResourceException {
	}

}
