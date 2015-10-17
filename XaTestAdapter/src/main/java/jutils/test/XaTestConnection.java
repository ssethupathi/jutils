package jutils.test;

import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionMetaData;
import javax.resource.cci.Interaction;
import javax.resource.cci.LocalTransaction;
import javax.resource.cci.ResultSetInfo;

public class XaTestConnection implements Connection {
	
	private XaTestManagedConnection managedConnection;
	
	private static Logger logger = Logger.getLogger(XaTestConnection.class
			.getName());
	
	public XaTestConnection(XaTestManagedConnection managedConnection) {
		this.managedConnection = managedConnection;
	}
	
	public void close() throws ResourceException {
		logger.info("XaTestConnection#close()");
	}

	public Interaction createInteraction() throws ResourceException {
		logger.info("XaTestConnection#createInteraction()");
		return new XaTestInteraction(this);
	}

	public LocalTransaction getLocalTransaction() throws ResourceException {
		logger.info("XaTestConnection#getLocalTransaction()");
		return null;
	}

	public ConnectionMetaData getMetaData() throws ResourceException {
		logger.info("XaTestConnection#getMetaData()");
		return null;
	}

	public ResultSetInfo getResultSetInfo() throws ResourceException {
		logger.info("XaTestConnection#getResultSetInfo()");
		return null;
	}
	
	public XaTestManagedConnection getManagedConnection() {
		return managedConnection;
	}
}
