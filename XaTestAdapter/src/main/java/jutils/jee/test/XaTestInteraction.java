package jutils.jee.test;

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
		XaTestConnection connection = (XaTestConnection) getConnection();
		XaTestManagedConnection managedConnection = connection
				.getManagedConnection();
		XaTestService service = new XaTestSimpleService(managedConnection);
		service.execute(buildData((XaTestInteractionSpec) interactionSpec,
				(XaTestRecord) record));
		return record;
	}

	public boolean execute(InteractionSpec arg0, Record arg1, Record arg2)
			throws ResourceException {
		return false;
	}

	public Connection getConnection() {
		return connection;
	}

	public ResourceWarning getWarnings() throws ResourceException {
		return null;
	}

	private XaTestData buildData(XaTestInteractionSpec interactionSpec,
			XaTestRecord record) {
		if (record != null) {
			return record.getData();
		}
		if (interactionSpec == null) {
			interactionSpec = new XaTestInteractionSpec();
		}
		return interactionSpec.getData();
	}
}
