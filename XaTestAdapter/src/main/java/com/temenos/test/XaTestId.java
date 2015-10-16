package com.temenos.test;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.transaction.xa.Xid;

public class XaTestId implements Xid {

	private static byte[] branchQualifier;
	private byte[] globalTxnId;

	static {
		try {
			branchQualifier = "XaTestAdapter".getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// won't happen
		}
	}

	public byte[] getBranchQualifier() {
		return branchQualifier;
	}

	public int getFormatId() {
		return 0;
	}

	public byte[] getGlobalTransactionId() {
		if (globalTxnId == null) {
			globalTxnId = new byte[64];
			new Random().nextBytes(globalTxnId);
		}
		return globalTxnId;
	}
}
