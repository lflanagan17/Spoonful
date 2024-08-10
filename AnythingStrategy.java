import java.util.Random;

/**
 * Strategy for choosing a random dairy free recipe 
 * when Surprise Me button is selected 
 * @author lucyflanagan
 */
public class AnythingStrategy implements SurpriseStrategy {
	
	/**
	 * Method signature for choosing a signature recipe
	 */
	public Recipe chooseSurpriseRecipe() {
		
		RecipesDataBase db = RecipesDataBase.getDatabaseInstance(); 
		return db.chooseAnyRecipe();
	}

}
