package virtualpet;

public class VirtualPet {

	private String name;
	private String description;
	private int hunger;
	private int thirst;
	private int boredom;

	public VirtualPet(String name, String description) {
		this(name, description, 50, 45, 60);
	}

	public VirtualPet(String name, String description, int hunger, int thirst, int boredom) {
		this.setName(name);
		this.setDescription(description);
		this.setHunger(hunger);
		this.setThirst(thirst);
		this.setBoredom(boredom);
	}

	public int feed() {
		setHunger(getHunger() - 10);
		return getHunger();
	}
	
	public int water() {
		setThirst(getThirst() - 10);
		return getThirst();
	}
	
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public int getThirst() {
		return thirst;
	}

	public void setThirst(int thirst) {
		this.thirst = thirst;
	}

	public int getBoredom() {
		return boredom;
	}

	public void setBoredom(int boredom) {
		this.boredom = boredom;
	}

}
