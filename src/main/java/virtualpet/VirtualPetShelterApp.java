/*
 * VirtualPetShelter:
 * user interface to the VirtualPetShelter.
 * Have some fun with the shelter pet
 * 
 * Author: David Langford
 * Date  : Feb 02, 2018
 * 
 * 
 * Depends on:
 * 
 * VirtualPetShelter
 * VirtualPet
 * Menu
 * 
 * 
 */

package virtualpet;

import java.util.Collection;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VirtualPetShelterApp {

	private static Scanner input = new Scanner(System.in);
	private static VirtualPetShelter ArthursPetShelter = VirtualPetShelterFactory();

	public static void main(String[] args) {

		Menu mainMenu = buildMainMenu();

		System.out.println(createBanner(ArthursPetShelter));

		while (true) {
			System.out.println(createPetStatusReportString(ArthursPetShelter));
			System.out.println(mainMenu.genMenuString(1));
			mainMenu.getOrdinalMenuChoice(input).run();
			ArthursPetShelter.tick();
			doCheckForStiffies();
		}

	}

	private static void doCheckForStiffies() {
		boolean stiffiesFound = false;
		Collection<VirtualPet> petShelterList = ArthursPetShelter.getAllPets();
		VirtualPet[] petsInShelter = new VirtualPet[petShelterList.size()];
		petsInShelter = petShelterList.toArray(petsInShelter);

		for (VirtualPet pet : petsInShelter) {
			if (pet.getBoredom() > 100 || pet.getHunger() > 100 || pet.getThirst() > 100) {
				System.out.println("Oops. " + pet.getName() + " has passed on.");
				ArthursPetShelter.adopt(pet.getName());
				stiffiesFound = true;
			}
		}
		if (stiffiesFound) {
			System.out.println("Their mortal remains have been sent to the incenerator.\n");
		} else {
			System.out.println("\nFeel the sun shining and the birds a chirpin!\n");
		}
	}

	private static Menu buildMainMenu() {
		Menu fpMenu = new Menu("Main Menu") {
			{
				addItem("Feed the pets", () -> doFeedPets(ArthursPetShelter));
				addItem("Water the pets", () -> doWaterPets(ArthursPetShelter));
				addItem("Play with a pet", () -> doPlayWithaPet(ArthursPetShelter));
				addItem("Adopt a pet", () -> doAdoptaPet(ArthursPetShelter));
				addItem("Admit a pet", () -> doAdmissions(ArthursPetShelter));
				addItem("Quit", () -> goodBye());
			}
		};
		return fpMenu;
	}

	private static Menu buildPetChoiceMenu() {
		Menu pcMenu = new Menu("Choose Pet", "splitkeyname");
		ArthursPetShelter.getAllPets()
				.forEach(p -> pcMenu.addItem(p.getName() + "," + p.getDescription(), () -> p.playWith()));
		return pcMenu;
	}

	private static void doFeedPets(VirtualPetShelter vps) {
		System.out.println("\nYou toss in scoops of chow, and run!\n");
		vps.feedAllPets();
	}

	@SuppressWarnings("serial")
	private static void doAdmissions(VirtualPetShelter vps) {
		System.out.println("\nAdmissions->");
		System.out.println("To admit a pet that has lost it's way, please fill out this form:\n");
		String intakeName;

		while (true) {
			System.out.println("What name does this pet identify as?");
			intakeName = input.nextLine();
			if (vps.getPetByName(intakeName) != null) {
				System.out.println("We already have a " + intakeName + ". This pet will have to be someone else.");
			} else {
				break;
			}
		}

		System.out.println("\nShort description of this pet:");
		String intakeDescription = input.nextLine();
		Map<String, Integer> attributeMap = new HashMap<String, Integer>() {
			{
				put("Hunger", 0);
				put("Thirst", 0);
				put("Boredom", 0);
			}
		};
		for (String attribute : attributeMap.keySet()) {
			boolean inputOkay = false;
			do {
				System.out.println("\nOn a scale of 0 to 100 please rate on this pets " + attribute + ":");
				int userInputNum;
				try {
					userInputNum = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Please use value of 0 to 100.");
					continue;
				}
				if (userInputNum < 0 || userInputNum > 100) {
					System.out.println("Please use value of 0 to 100.");
				} else {
					inputOkay = true;
				}
			} while (!inputOkay);
		}
		VirtualPet newPet = new VirtualPet(intakeName, intakeDescription, attributeMap.get("Hunger"),
				attributeMap.get("Hunger"), attributeMap.get("Hunger"));
		vps.addPet(newPet);
	}

	private static void doAdoptaPet(VirtualPetShelter vps) {
		String petName = getPetNameByUserChoice();
		System.out.println("\nOops. We accidentally incenerated " + petName + "!\n");
		vps.adopt(petName);
	}

	private static String getPetNameByUserChoice() {
		String petName = "";
		while (petName.isEmpty()) {
			Menu pcMenu = buildPetChoiceMenu();
			System.out.println(pcMenu.genMenuString(2, "Which Pet would you like?"));
			petName = pcMenu.getSplitKeyMenuChoice(input);
		}

		return petName;
	}

	private static void doPlayWithaPet(VirtualPetShelter vps) {
		String petName = getPetNameByUserChoice();
		System.out.println("\nYou have a ball playing with " + petName + "!\n");
		vps.getPetByName(petName).playWith();
	}

	private static void doWaterPets(VirtualPetShelter vps) {
		System.out.println("\nYou turn the hose on full and really soak the poor things.\n");
		vps.waterAllPets();
	}

	public static String createPetStatusReportString(VirtualPetShelter vps) {
		StringBuffer output = new StringBuffer();
		Formatter formatter = new Formatter(output);

		formatter.format("%31s%n", "Pet Status Report");
		formatter.format("%9s | %8s | %8s | %8s%n", "Name", "Hunger", "Thirst", "Boredom");
		formatter.format("%9s | %8s | %8s | %8s%n", "---------", "--------", "--------", "--------");
		vps.getAllPets().forEach(s -> {
			formatter.format("%-9s | %8s | %8s | %8s%n", s.getName(), s.getHunger(), s.getThirst(), s.getBoredom());
		});

		formatter.close();
		return output.toString();
	}

	public static String createBanner(VirtualPetShelter vps) {
		return "Welcome to " + vps + "!\n";
	}

	private static VirtualPetShelter VirtualPetShelterFactory() {
		return new VirtualPetShelter("Arthurs Wholistic Pet Shelter and Sasauge Factory") {
			{
				addPet(new VirtualPet("Spot", "One eyed mutt", 30, 40, 50));
				addPet(new VirtualPet("Muffy", "100 pound Terrier", 80, 60, 20));
				addPet(new VirtualPet("Rex", "Feisty Gecko", 50, 10, 60));
				addPet(new VirtualPet("Capn Jack", "Bald Parrot", 20, 34, 50));
				addPet(new VirtualPet("Snappy", "Introverted Turtle", 20, 40, 10));
			}
		};
	}

	private static void goodBye() {
		// TODO println must die! (somehow?)
		System.out.println("\nGoodBye!\n");
		input.close();
		System.exit(0);
	}

}
