package jutils.jee.test;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.ConnectionSpec;
import javax.resource.cci.RecordFactory;
import javax.resource.cci.ResourceAdapterMetaData;

public class XaTestConnectionFactory implements ConnectionFactory {

	private static final long serialVersionUID = 1L;

	public void setReference(Reference arg0) {
	}

	public Reference getReference() throws NamingException {
		return null;
	}

	public Connection getConnection() throws ResourceException {
		return null;
	}

	public Connection getConnection(ConnectionSpec arg0)
			throws ResourceException {
		return null;
	}

	public ResourceAdapterMetaData getMetaData() throws ResourceException {
		return null;
	}

	public RecordFactory getRecordFactory() throws ResourceException {
		return null;
	}
}
