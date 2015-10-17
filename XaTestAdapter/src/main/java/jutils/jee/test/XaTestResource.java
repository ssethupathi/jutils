package jutils.jee.test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class XaTestResource implements XAResource {

	private long delay = 100; // 100 ms
	private XaTestData data = new XaTestData();

	private static Logger logger = Logger.getLogger(XaTestResource.class
			.getName());

	public void commit(Xid xid, boolean onePhase) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2},{3})", new Object[] {
				XaTestResource.class.getName(), "commit", xid, onePhase });
	}

	public void end(Xid arg0, int arg1) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2},{3})", new Object[] {
				XaTestResource.class.getName(), "end", arg0, arg1 });
	}

	public void forget(Xid arg0) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2})", new Object[] {
				XaTestResource.class.getName(), "forget", arg0 });
	}

	public int getTransactionTimeout() throws XAException {
		logger.log(Level.INFO, "{0}#{1}()",
				new Object[] { XaTestResource.class.getName(),
						"getTransactionTimeout" });
		return 0;
	}

	public boolean isSameRM(XAResource arg0) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2})", new Object[] {
				XaTestResource.class.getName(), "isSameRM", arg0 });
		return false;
	}

	public int prepare(Xid arg0) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2})", new Object[] {
				XaTestResource.class.getName(), "prepare", arg0 });
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			// do-nothing
		}

		if (data.getValue(XaTestInteractionSpec.FAIL_PREPARE)) {
			throw new XAException("Prepare failed");
		}
		return 0;
	}

	public Xid[] recover(int arg0) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2})", new Object[] {
				XaTestResource.class.getName(), "recover", arg0 });
		return null;
	}

	public void rollback(Xid arg0) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2})", new Object[] {
				XaTestResource.class.getName(), "rollback", arg0 });

	}

	public boolean setTransactionTimeout(int arg0) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2})", new Object[] {
				XaTestResource.class.getName(), "setTransactionTimeout", arg0 });
		return false;
	}

	public void start(Xid arg0, int arg1) throws XAException {
		logger.log(Level.INFO, "{0}#{1}({2},{3})", new Object[] {
				XaTestResource.class.getName(), "start", arg0, arg1 });
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setData(XaTestData data) {
		this.data = data;
	}
}
