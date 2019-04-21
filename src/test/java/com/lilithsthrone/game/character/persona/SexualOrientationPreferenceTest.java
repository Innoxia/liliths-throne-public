package com.lilithsthrone.game.character.persona;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import com.lilithsthrone.test.Groups;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

public class SexualOrientationPreferenceTest {
	/** For demonstration purposes only. Remove once the first proper integration test exists! */
	@Test(groups = Groups.INTEGRATION)
	public void anIntegrationTest() {
		System.err.println("I am an integration test.");
	}
	/** For demonstration purposes only. Remove once the first proper integration test exists! */
	@Test(groups = Groups.MANUAL)
	public void aManualTest() {
		System.err.println("I am a manual test.");
	}
	/**
	 * No one can know whether this was supposed to be a unit test, supposed to be an integration test <p>
	 * - use {@link com.lilithsthrone.test.Groups#MANUAL} so people <i>know</i> you meant it to be manual-only!
	 */
	@Test
	public void aTestWithoutGroup() {
		System.err.println("Someone forgot to annotate me. I am alone and miserable.");
	}

	@Test(groups = Groups.UNIT)
	public void testNameSet() {
		assertThat(SexualOrientationPreference.values())
				.extracting(SexualOrientationPreference::getName)
				.noneMatch(StringUtils::isBlank);
	}
}
