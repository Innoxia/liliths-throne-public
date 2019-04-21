package com.lilithsthrone.test;

/**
 * These constants correspond to the maven-surefire-plugin configuration/groups.
 * @author Zsar
 */
public interface Groups {
  /** test group to be executed by the maven-surefire-plugin during the unit test phase */
	String UNIT = "unit-test";
	/** test group to be executed by the maven-failsafe-plugin during the integration test phase */
	String INTEGRATION = "integration-test";
	/**
	 * test group to be executed only by manual start using the IDE's TestNG plugin <p>
	 * Technically, not using the {@code groups} attribute will have the same effect, but then nobody will be able to tell,
	 * whether it was intentionally left out or merely forgotten. <p>
	 * Document your intent by explicitely using this group for tests not meant to be run automatically!
	 */
	String MANUAL = "manual-test";
}
