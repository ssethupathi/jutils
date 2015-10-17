package jutils.test;

import javax.resource.ResourceException;

public interface XaTestService {

	public void execute(XaTestData data) throws ResourceException;
}
