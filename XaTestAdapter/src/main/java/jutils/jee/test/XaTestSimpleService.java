package jutils.jee.test;

import java.util.logging.Logger;

import javax.resource.ResourceException;

public class XaTestSimpleService implements XaTestService {

	private XaTestManagedConnection managedConnection;

	public XaTestSimpleService(XaTestManagedConnection managedConnection) {
		this.managedConnection = managedConnection;
	}

	private static Logger logger = Logger.getLogger(XaTestSimpleService.class
			.getName());

	@Override
	public void execute(XaTestData data) throws ResourceException {
		logger.info(XaTestSimpleService.class.getName() + "#echo()");
		XaTestResource xaResource = (XaTestResource) managedConnection
				.getXAResource();
		xaResource.setData(data);
	}
}
