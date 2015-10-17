package jutils.jee.test;

import javax.resource.cci.InteractionSpec;

public class XaTestInteractionSpec implements InteractionSpec {

	public static final String FAIL_PREPARE = "FAIL_PREPARE";

	private static final long serialVersionUID = 1L;
	private XaTestData data = new XaTestData();

	public XaTestInteractionSpec() {
		data.add(FAIL_PREPARE, false);
	}

	public boolean isFailPrepare() {
		return data.getValue(FAIL_PREPARE);
	}

	public void setFailPrepare(boolean failPrepare) {
		data.add(FAIL_PREPARE, failPrepare);
	}

	public XaTestData getData() {
		return data;
	}
}
