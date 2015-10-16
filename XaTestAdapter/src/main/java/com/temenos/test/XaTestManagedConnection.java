package com.temenos.test;

import java.io.PrintWriter;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

public class XaTestManagedConnection implements ManagedConnection {

	public void addConnectionEventListener(ConnectionEventListener arg0) {

	}

	public void associateConnection(Object arg0) throws ResourceException {

	}

	public void cleanup() throws ResourceException {

	}

	public void destroy() throws ResourceException {

	}

	public Object getConnection(Subject arg0, ConnectionRequestInfo arg1)
			throws ResourceException {
		return null;
	}

	public LocalTransaction getLocalTransaction() throws ResourceException {
		return null;
	}

	public PrintWriter getLogWriter() throws ResourceException {
		return null;
	}

	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		return null;
	}

	public XAResource getXAResource() throws ResourceException {
		return new XaTestResource();
	}

	public void removeConnectionEventListener(ConnectionEventListener arg0) {

	}

	public void setLogWriter(PrintWriter arg0) throws ResourceException {

	}

}
