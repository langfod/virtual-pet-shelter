package virtualpet;

import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {

	Map<String, VirtualPet> petList = new HashMap<>();
	
	public VirtualPetShelter() {
	}
	
	public void addPet(VirtualPet pet) {
		petList.put(pet.getName(),pet);
	}

	public VirtualPet getPetByName(String petName) {
		return petList.get(petName);
	}

}
