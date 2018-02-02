package virtualpet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VirtualPetShelterTest {

	VirtualPetShelter virtualShelterUnderTest = new VirtualPetShelter();
	VirtualPet testPet1 = new VirtualPet("Test Name", "Test Description");

	@Test
	public void shouldAllowIntakeOfPet() {
		virtualShelterUnderTest.addPet(testPet1);
		VirtualPet retrievedPet = virtualShelterUnderTest.getPetByName("Test Name");
		assertThat(retrievedPet, is(testPet1));
	}

}
