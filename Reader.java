
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


/**
 * JSON parser in Java
 * @author tylerchang
 *
 */
public class Reader {
	
	/**
	 * Given the recipes.json file in the project folder, parse and add each recipe as an entry into the database
	 * @throws ParseException 
	 */
	public void addRecipesFromJSONToDatabase() throws FileNotFoundException, IOException, ParseException{
		
		// Get instance of the database through the Singleton design pattern
		
		RecipesDataBase db = RecipesDataBase.getDatabaseInstance();
		db.createDatabase();
		db.createRecipesTable();
		db.createAndFillIngredientsTable();
		
		
		JSONParser parser = new JSONParser(); 
		JSONObject recipes = (JSONObject) parser.parse(new FileReader("recipes.json"));
		
		Set keys = recipes.keySet();
		int id = 1;
		
		for(Object key : keys) {
			
			// Recipe Object
			JSONObject recipe = (JSONObject) recipes.get(key);
			
			// Title of recipe
			String recipeTitle = recipe.get("title").toString();
			recipeTitle = recipeTitle.replace("\"", "");
			
			// String of ingredients plus formatting
			//String of all ingredients needs a lot of cleaning to be read correctly so we do that here 
			JSONArray ingredientsArray = (JSONArray) recipe.get("ingredients");
			String ingredientsClean = ingredientsArray.toString(); 
			ingredientsClean = ingredientsClean.replace("\"", ""); 
			ingredientsClean = ingredientsClean.replace("[", "");
			ingredientsClean = ingredientsClean.replace("]", "");
			ingredientsClean = ingredientsClean.replace("(", "");
			ingredientsClean = ingredientsClean.replace(")", "");
			ingredientsClean = ingredientsClean.replace(" ,", ", ");
			ingredientsClean = ingredientsClean.replace("'", "");
			ingredientsClean = ingredientsClean.substring(0, ingredientsClean.length()-2);
			ingredientsClean = ingredientsClean.strip();
			ingredientsClean = ingredientsClean.replace("\n", "");
			ingredientsClean = ingredientsClean.replace("\\/", "/");
						
			
			// String of instructions plus formatting
			String instructions = recipe.get("instructions").toString();
			instructions = instructions.replace("\n", "");
			
			//fill the database with each recipe
			db.addRecipeToDatabase(id, recipeTitle, ingredientsClean, instructions);
			id++;

					
		}
		
		if (db.seeNumberOfAssociationRows() >= 787) {
			
		} else {
			db.createAndFillAssociationsTable();
		}
		

		    
	}
		
}
