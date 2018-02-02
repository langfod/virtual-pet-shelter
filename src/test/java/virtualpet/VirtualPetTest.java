package virtualpet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VirtualPetTest {

	// VirtualPet petUnderTest = new VirtualPet();

	@Test
	public void constructorNameAndDescription() {
		assertNotNull(new VirtualPet("Pet Name", "Pet Desciption"));
	}

	@Test
	public void constructorNameAndDescriptionAndAttributes() {
		assertNotNull(new VirtualPet("Pet Name", "Pet Desciption", 50, 50, 50));
	}

	@Test
	public void checkInitialValues() {
		VirtualPet petUnderTest = new VirtualPet("Pet Name", "Pet Desciption");
		assertThat(petUnderTest.getHunger(), is(50));
		assertThat(petUnderTest.getThirst(), is(50));
		assertThat(petUnderTest.getBoredom(), is(50));

	}
}
