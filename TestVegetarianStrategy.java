import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestVegetarianStrategy {

	VegetarianStrategy stratInstance;
	Recipe recipe; 
	boolean isVegetarian; 
	
	
	@Before 
	public void setUp() throws Exception {
		stratInstance = new VegetarianStrategy();
		recipe = null; 
		isVegetarian = false; 
	}
	
	/**
	 * this one test case calls the chooseVegetarian class method
	 * this method returns a new, random Vegetarian recipe every time it is called
	 * so we do not need multiple test cases (we can just run this one every time). 
	 * first we iterate through every ingredient in the randomly generated recipe
	 * if this ingredient is vegetarian, then we increment the number of vegetarian ingredients 
	 * at the end of the iteration, we check if the total number of ingredients equals the number of veg ingredients 
	 * if they are equal then isVegetarian is set to true (it was set to false in constructor) 
	 * finally assert checks that this variable is true every time 
	 */
	@Test
	public void testRandomVegRecipe() {
		recipe = stratInstance.chooseSurpriseRecipe(); 
		int recipeID = recipe.getID(); 
		int ingredientID = 0; 
		int numTotalIngredients = 0; 
		int numVegIngredients = 0; 
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + RecipesDataBase.PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement getIngredientsStatement = conn.createStatement();
				) {
  
			String getIngredientsSql = "SELECT ingredientID FROM RecipeIngredientAssociation WHERE recipeID=" + recipeID + ";"; 
			ResultSet ingredients = getIngredientsStatement.executeQuery(getIngredientsSql); 
			
			while(ingredients.next()) {
				numTotalIngredients++; 
				ingredientID = ingredients.getInt("ingredientID"); 
				Statement isVegetarianStatement = conn.createStatement();
				String isVegetarianSql = "SELECT isVegetarian FROM Ingredients WHERE id=" + ingredientID + ";"; 
				ResultSet isVeg = isVegetarianStatement.executeQuery(isVegetarianSql); 
				while(isVeg.next()) {
					String yesOrNo = isVeg.getString("isVegetarian"); 
					if(yesOrNo.equals("Yes")) {
						numVegIngredients++; 
					}
				}
			}
			if(numTotalIngredients==numVegIngredients) {
				isVegetarian=true; 
			}

		} catch(SQLException ex) {   
			ex.printStackTrace();  
		}  
		assertTrue(isVegetarian);  
	}
}
