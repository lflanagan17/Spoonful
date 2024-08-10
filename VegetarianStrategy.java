/**
 * chooses a random vegetarian recipe after user
 * selects surprise me 
 * @author lucyflanagan
 *
 */
public class VegetarianStrategy implements SurpriseStrategy{

	@Override
	public Recipe chooseSurpriseRecipe() {
		RecipesDataBase db = RecipesDataBase.getDatabaseInstance(); 
		return db.chooseVegetarianRecipe(); 
	}

}
 