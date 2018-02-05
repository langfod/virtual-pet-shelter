/*
 * VirtualPetShelterTest:
 * 
 * Junit test methods to test the VirtualPetShelter class 
 *
 * 
 * Author: David Langford
 * Date  : Feb 02, 2018
 * 
 * 
 * Depends on:
 * 
 * VirtualPetShelter
 * VirtualPet
 */

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
	public void shouldAllowIntakeOfMultiplePetsandRetrieveCollection() {
		virtualShelterUnderTest.addPet(testPet1);
		virtualShelterUnderTest.addPet(new VirtualPet("Second Pet Name", "Second Description"));
		virtualShelterUnderTest.addPet(new VirtualPet("Third Pet Name", "Second Description"));
		Collection<VirtualPet> retrievedCollection = virtualShelterUnderTest.getAllPets();
		assertThat(3, is(retrievedCollection.size()));
	}

	@Test
	public void shouldAllowAdoptionOfPetByName() {
		virtualShelterUnderTest.addPet(testPet1);
		virtualShelterUnderTest.adopt("Test Name");
		Collection<VirtualPet> retrievedCollection = virtualShelterUnderTest.getAllPets();
		assertThat(0, is(retrievedCollection.size()));
	}

	@Test
	public void feedAllPetsInShelter() {
		virtualShelterUnderTest.addPet(testPet1);
		virtualShelterUnderTest.addPet(new VirtualPet("Second Pet Name", "Second Description"));
		virtualShelterUnderTest.addPet(new VirtualPet("Third Pet Name", "Second Description"));
		virtualShelterUnderTest.feedAllPets();
		VirtualPet retrievedPet = virtualShelterUnderTest.getPetByName("Test Name");
		assertThat(retrievedPet.getHunger(), is(30));
		retrievedPet = virtualShelterUnderTest.getPetByName("Second Pet Name");
		assertThat(retrievedPet.getHunger(), is(30));
		retrievedPet = virtualShelterUnderTest.getPetByName("Third Pet Name");
		assertThat(retrievedPet.getHunger(), is(30));
	}

	@Test
	public void waterAllPetsInShelter() {
		virtualShelterUnderTest.addPet(testPet1);
		virtualShelterUnderTest.addPet(new VirtualPet("Second Pet Name", "Second Description"));
		virtualShelterUnderTest.addPet(new VirtualPet("Third Pet Name", "Second Description"));
		virtualShelterUnderTest.waterAllPets();
		VirtualPet retrievedPet = virtualShelterUnderTest.getPetByName("Test Name");
		assertThat(retrievedPet.getThirst(), is(25));
		retrievedPet = virtualShelterUnderTest.getPetByName("Second Pet Name");
		assertThat(retrievedPet.getThirst(), is(25));
		retrievedPet = virtualShelterUnderTest.getPetByName("Third Pet Name");
		assertThat(retrievedPet.getThirst(), is(25));
	}

	@Test
	public void PlayWithAllPetsInShelter() {
		virtualShelterUnderTest.addPet(testPet1);
		virtualShelterUnderTest.addPet(new VirtualPet("Second Pet Name", "Second Description"));
		virtualShelterUnderTest.addPet(new VirtualPet("Third Pet Name", "Second Description"));
		virtualShelterUnderTest.playWithAllPets();
		VirtualPet retrievedPet = virtualShelterUnderTest.getPetByName("Test Name");
		assertThat(retrievedPet.getBoredom(), is(40));
		retrievedPet = virtualShelterUnderTest.getPetByName("Second Pet Name");
		assertThat(retrievedPet.getBoredom(), is(40));
		retrievedPet = virtualShelterUnderTest.getPetByName("Third Pet Name");
		assertThat(retrievedPet.getBoredom(), is(40));
	}

	@Test
	public void checkDefaulttoString() {
		assertThat(virtualShelterUnderTest.toString(), is("Acme Pet Shelter"));
	}

	@Test
	public void checkDescritpionConstructorToString() {
		String testDescription = "New Description";
		virtualShelterUnderTest = new VirtualPetShelter(testDescription);
		assertThat(virtualShelterUnderTest.toString(), is(testDescription));
	}

	@Test
	public void tickShouldupdateallPetsByThree() {
		virtualShelterUnderTest.addPet(testPet1);
		virtualShelterUnderTest.addPet(new VirtualPet("Second Pet Name", "Second Description", 70, 80, 90));
		virtualShelterUnderTest.tick();
		VirtualPet retrievedPet = virtualShelterUnderTest.getPetByName("Test Name");
		assertThat(retrievedPet.getHunger(), is(55));
		assertThat(retrievedPet.getThirst(), is(50));
		assertThat(retrievedPet.getBoredom(), is(65));
		retrievedPet = virtualShelterUnderTest.getPetByName("Second Pet Name");
		assertThat(retrievedPet.getHunger(), is(75));
		assertThat(retrievedPet.getThirst(), is(85));
		assertThat(retrievedPet.getBoredom(), is(95));
	}

}
