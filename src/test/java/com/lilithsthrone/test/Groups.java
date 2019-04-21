package com.lilithsthrone.test;

/** These constants correspond to the maven-surefire-plugin configuration/groups. */
public interface Groups {
  /** test group to be executed by the maven-surefire-plugin during the unit test phase */
	String UNIT = "unit-test";
	/** test group to be executed by the maven-failsafe-plugin during the integration test phase */
	String INTEGRATION = "integration-test";
	/** test group to be executed only by manual start using the IDE's TestNG plugin */
	String MANUAL = "manual-test";
}
