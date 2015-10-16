package com.temenos.test;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.Interaction;
import javax.resource.cci.InteractionSpec;
import javax.resource.cci.Record;
import javax.resource.cci.ResourceWarning;

public class XaTestInteraction implements Interaction {
	
	private XaTestConnection connection;
	
	public XaTestInteraction(XaTestConnection connection) {
		this.connection = connection;
	}

	public void clearWarnings() throws ResourceException {

	}

	public void close() throws ResourceException {

	}

	public Record execute(InteractionSpec interactionSpec, Record record)
			throws ResourceException {
		return null;
	}

	public boolean execute(InteractionSpec arg0, Record arg1, Record arg2)
			throws ResourceException {
		return false;
	}

	public Connection getConnection() {
		return null;
	}

	public ResourceWarning getWarnings() throws ResourceException {
		return null;
	}

}
