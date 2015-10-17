package jutils.test;

import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

public class XaTestResourceAdapter implements ResourceAdapter {

	private static Logger logger = Logger.getLogger(XaTestResourceAdapter.class
			.getName());

	public void endpointActivation(MessageEndpointFactory arg0,
			ActivationSpec arg1) throws ResourceException {
		// no end point to activate
		logger.fine(this.getClass().getName() + "#endpointActivation()");
	}

	public void endpointDeactivation(MessageEndpointFactory arg0,
			ActivationSpec arg1) {
		// no end point to deactivate
		logger.fine(this.getClass().getName() + "#endpointDeactivation()");
	}

	public XAResource[] getXAResources(ActivationSpec[] arg0)
			throws ResourceException {
		logger.fine(this.getClass().getName() + "#getXAResources()");
		return new XAResource[] {};
	}

	public void start(BootstrapContext arg0)
			throws ResourceAdapterInternalException {
		logger.fine(this.getClass().getName() + "#start()");
	}

	public void stop() {
		logger.fine(this.getClass().getName() + "#stop()");
	}

}
