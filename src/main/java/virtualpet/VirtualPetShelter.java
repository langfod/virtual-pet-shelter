package virtualpet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {

	Map<String, VirtualPet> petList = new HashMap<>();

	public VirtualPetShelter() {
	}

	public void addPet(VirtualPet pet) {
		petList.put(pet.getName(), pet);
	}

	public VirtualPet getPetByName(String petName) {
		return petList.get(petName);
	}

	public Collection<VirtualPet> getAllPets() {
		return petList.values();
	}

	public void adopt(String petName) {
		petList.remove(petName);
	}

	public void feedAllPets() {
		petList.values().forEach(VirtualPet::feed);
	}
}
