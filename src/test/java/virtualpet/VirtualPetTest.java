package virtualpet;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VirtualPetTest {

	//VirtualPet petUnderTest = new VirtualPet();
	
	@Test
	public void constructorNameAndDescription() {
		assertNotNull(new VirtualPet("Pet Name", "Pet Desciption"));
	}
	
	@Test
	public void constructorNameAndDescriptionAndAttributes() {
		assertNotNull(new VirtualPet("Pet Name", "Pet Desciption", 50,50,50));
	}
	
	
}
