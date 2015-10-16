package com.temenos.test;

import java.util.concurrent.TimeUnit;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class XaTestResource implements XAResource {

	private long delay = 1; // 1ms
	private boolean failStart;
	private boolean failCommit;
	private boolean failPrepare;
	private boolean failRollback;

	public void commit(Xid arg0, boolean arg1) throws XAException {
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {

		}
		if (failCommit) {
			throw new XAException("Commit failed");
		}
	}

	public void end(Xid arg0, int arg1) throws XAException {

	}

	public void forget(Xid arg0) throws XAException {

	}

	public int getTransactionTimeout() throws XAException {
		return 0;
	}

	public boolean isSameRM(XAResource arg0) throws XAException {
		return false;
	}

	public int prepare(Xid arg0) throws XAException {
		return 0;
	}

	public Xid[] recover(int arg0) throws XAException {
		return null;
	}

	public void rollback(Xid arg0) throws XAException {

	}

	public boolean setTransactionTimeout(int arg0) throws XAException {
		return false;
	}

	public void start(Xid arg0, int arg1) throws XAException {

	}

}
