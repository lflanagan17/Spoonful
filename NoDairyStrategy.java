/**
 * This is the No Dairy concrete strategy
 * @author lucyflanagan
 *
 */
public class NoDairyStrategy implements SurpriseStrategy {

	/**
	 * Strategy for choosing a random dairy free recipe 
	 * when Surprise Me button is selected 
	 * @author lucyflanagan
	 */
	@Override
	public Recipe chooseSurpriseRecipe() {
		RecipesDataBase db = RecipesDataBase.getDatabaseInstance(); 
		return db.chooseNoDairyRecipe(); 
	}

}
