package jutils.jee.test;

import javax.resource.cci.Record;

public class XaTestRecord implements Record {

	private static final long serialVersionUID = 1L;

	private String name = "UNDEFINED";
	private String description = "";

	private XaTestData data = new XaTestData();

	@Override
	public String getRecordName() {
		return name;
	}

	@Override
	public String getRecordShortDescription() {
		return description;
	}

	@Override
	public void setRecordName(String name) {
		this.name = name;
	}

	@Override
	public void setRecordShortDescription(String description) {
		this.description = description;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public void setData(XaTestData data) {
		this.data = data;
	}

	public XaTestData getData() {
		return data;
	}
}
