import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class TestNoDairyStrategy {

	NoDairyStrategy stratInstance;
	Recipe recipe; 
	boolean containsDairy; 
	
	
	@Before 
	public void setUp() throws Exception {
		stratInstance = new NoDairyStrategy();
		recipe = null; 
		containsDairy = true; 
	}
	
	/**
	 * this one test case calls the chooseNoDairy class method
	 * this method returns a new, random Dairy free recipe every time it is called
	 * so we do not need multiple test cases (we can just run this one every time). 
	 * first we iterate through every ingredient in the randomly generated recipe
	 * if this ingredient has dairy, then we increment the number of dairy ingredients 
	 * at the end of the iteration, we check if number of dairy ingredients is 0
	 * if this equals 0, then global variable hasDairy is set to false (we started by assuming the Recipe did have dairy) 
	 * finally assert checks that this variable is false every time 
	 */
	@Test
	public void testRandomVegRecipe() {
		recipe = stratInstance.chooseSurpriseRecipe(); 
		int recipeID = recipe.getID(); 
		int ingredientID = 0; 
		int numDairyIngredients = 0; 
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + RecipesDataBase.PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement getIngredientsStatement = conn.createStatement();
				) {
  
			String getIngredientsSql = "SELECT ingredientID FROM RecipeIngredientAssociation WHERE recipeID=" + recipeID + ";"; 
			ResultSet ingredients = getIngredientsStatement.executeQuery(getIngredientsSql); 
			
			while(ingredients.next()) {
				ingredientID = ingredients.getInt("ingredientID"); 
				Statement hasDairyStatament = conn.createStatement();
				String hasVegetarianSql = "SELECT containsDairy FROM Ingredients WHERE id=" + ingredientID + ";"; 
				ResultSet hasDairy = hasDairyStatament.executeQuery(hasVegetarianSql); 
				while(hasDairy.next()) {
					String yesOrNo = hasDairy.getString("containsDairy"); 
					if(yesOrNo.equals("Yes")) {
						numDairyIngredients++; 
					}
				}
			}
			if(numDairyIngredients==0) {
				containsDairy=false; 
			}

		} catch(SQLException ex) {   
			ex.printStackTrace();  
		}   
		assertFalse(containsDairy);  
	}

}
