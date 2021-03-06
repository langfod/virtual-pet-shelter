/*
 * VirtualPetTest:
 * 
 * Junit test methods to test the VirtualPet class
 *
 * 
 * Author: David Langford
 * Date  : Feb 02, 2018
 * 
 * 
 * Depends on:
 * 
 * VirtualPet
 * 
 */
package virtualpet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VirtualPetTest {

	private VirtualPet petUnderTest = new VirtualPet("Pet Name", "Pet Desciption");

	// VirtualPet petUnderTest = new VirtualPet();

	@Test
	public void constructorNameAndDescription() {
		assertNotNull(new VirtualPet("Pet Name", "Pet Desciption"));
	}

	@Test
	public void constructorNameAndDescriptionAndAttributes() {
		VirtualPet newPet = new VirtualPet("Pet Name", "Pet Desciption", 10, 20, 99);
		assertEquals(99, newPet.getBoredom());
	}

	@Test
	public void checkInitialValues() {
		assertThat(petUnderTest.getHunger(), is(50));
		assertThat(petUnderTest.getThirst(), is(45));
		assertThat(petUnderTest.getBoredom(), is(60));

	}

	@Test
	public void testFeed() {
		petUnderTest.feed();
		assertThat(petUnderTest.getHunger(), is(30));
	}

	@Test
	public void testWater() {
		petUnderTest.water();
		assertThat(petUnderTest.getThirst(), is(25));
	}

	@Test
	public void testPlayWith() {
		petUnderTest.playWith();
		assertThat(petUnderTest.getBoredom(), is(40));
	}

	@Test
	public void tickShouldUpdateValuesByThree() {
		petUnderTest.tick();
		assertThat(petUnderTest.getHunger(), is(55));
		assertThat(petUnderTest.getThirst(), is(50));
		assertThat(petUnderTest.getBoredom(), is(65));

	}
}
