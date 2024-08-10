import java.util.ArrayList;

public class IngredientsList {
	
	private ArrayList fruits;
	private ArrayList vegetables;
	private ArrayList grains;
	private ArrayList proteinsWithoutMeat;
	private ArrayList meat;
	private ArrayList dairy;
	private ArrayList misc;

	
	public IngredientsList() {
		this.fruits = new ArrayList<String>();
		this.vegetables = new ArrayList<String>();
		this.grains = new ArrayList<String>();
		this.proteinsWithoutMeat = new ArrayList<String>();
		this.meat = new ArrayList<String>();
		this.dairy =  new ArrayList<String>();
		this.misc =  new ArrayList<String>();
		
		addMeats();
				
	}
	
	public static void main(String[] args) {
		IngredientsList test = new IngredientsList();
		System.out.println(test.meat.get(0));
	}
	
	public void addFruits() {
		
	}
	
	public void addVegetables() {
		
	}
	
	public void addGrains() {
		
	}
	
	public void addProteinsWithoutMeat() {
		
	}
	
	public void addMeats() {
		meat.add("Chicken breast");
	}
	
	public void addDairy() {
		
	}
	
	public void addMisc() {
		
	}
	
}
