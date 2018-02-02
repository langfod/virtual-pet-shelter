package virtualpet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class VirtualPetShelterTest {

	VirtualPetShelter virtualShelterUnderTest = new VirtualPetShelter();
	VirtualPet testPet1 = new VirtualPet("Test Name", "Test Description");

	@Test
	public void shouldAllowIntakeOfPetandRetrieveByName() {
		virtualShelterUnderTest.addPet(testPet1);
		VirtualPet retrievedPet = virtualShelterUnderTest.getPetByName("Test Name");
		assertThat(retrievedPet, is(testPet1));
	}
	
	@Test 
	public void shouldAllowIntakeOfPetandRetrieveCollection() {
		virtualShelterUnderTest.addPet(testPet1);
		Collection<VirtualPet> retrievedCollection = virtualShelterUnderTest.getAllPets();
		assertThat(1, is(retrievedCollection.size()));
	}
	
	
	@Test
	public void shouldAllowAdoptionOfPetByName() {
		virtualShelterUnderTest.addPet(testPet1);
		virtualShelterUnderTest.adopt("Test Name");
		Collection<VirtualPet> retrievedCollection = virtualShelterUnderTest.getAllPets();
		assertThat(0, is(retrievedCollection.size()));
	}
	

}
