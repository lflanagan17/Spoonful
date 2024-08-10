import java.util.Arrays;

/**
 * Represents a Recipe
 * @author lucy, tyler
 *
 */

public class Recipe {

	private int id;

	private String name;

	private String ingredients;

	private String instructions;
	
	/**
	 * Define the attributes to the input parameters
	 * @param name
	 * @param ingredients
	 * @param instructions
	 */
	public Recipe(int id, String name, String ingredients, String instructions) {
		this.id = id;

		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	public int getID() {
		return id; 
	}
	
	public String getName() {
		return name;
	}
	
	public String getIngredients() {
		return ingredients;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	/**
	 * Prints the recipe as its name and ingredients
	 */
	public String toString() {

		//return name + ": " + Arrays.toString(ingredients);
		return name + ": " + ingredients;
	}
	
}
