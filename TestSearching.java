
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

public class TestSearching {
	
	//Change this if your port number is different otherwise you will not be able to connect to the database
	public static final String PORT_NUMBER = RecipesDataBase.PORT_NUMBER;

	@Test
	/*
	 * Test to see that given a list of ingredients will it return the recipe associated with that recipe 
	 */
	public void testSearchingForRecipe() {
		RecipesDataBase databaseInstance = RecipesDataBase.getDatabaseInstance(); 
		ArrayList<String> ingredientForRecipe = new ArrayList<String>();
		ingredientForRecipe.add("Butter");
		ingredientForRecipe.add("Eggs");
		ingredientForRecipe.add("Flour");
		ingredientForRecipe.add("Baking Powder");					//Set up the arrayList for searching
		ingredientForRecipe.add("Salt");
		ingredientForRecipe.add("Sugar");
		ingredientForRecipe.add("Vanilla Extract");
		
		Recipe recipe = null;
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
						
					String sql = "SELECT * FROM Recipes WHERE id = 28;";		//Make the connection to the database and 
					ResultSet rset = stmt.executeQuery(sql);
					
					while(rset.next()) {
				
						String recipeName = rset.getString("name");
						String recipeIngredients = rset.getString("ingredients");
						String recipeInstructions = rset.getString("instructions");
						
						recipe = new Recipe(28, recipeName, recipeIngredients, recipeInstructions);		//Create the recipe out of the database
					}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		//Test to make sure that each field of the recipe equals the expected object 
		assertEquals(recipe.getID(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(0).get(0).getID() );
		assertEquals(recipe.getName(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(0).get(0).getName() );
		assertEquals(recipe.getIngredients(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(0).get(0).getIngredients() );
		assertEquals(recipe.getInstructions(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(0).get(0).getInstructions() );
	
	}
		
	@Test
	/*
	 * Test to see that given a list of ingredients will it return the recipe associated with that recipe, minus one ingredient in the list
	 */
	public void testSearchingForRecipeMinusOneIngredient() {
		RecipesDataBase databaseInstance = RecipesDataBase.getDatabaseInstance(); 
		ArrayList<String> ingredientForRecipe = new ArrayList<String>();
		ingredientForRecipe.add("Butter");
		ingredientForRecipe.add("Eggs");
		ingredientForRecipe.add("Flour");
		ingredientForRecipe.add("Baking Powder");					//Set up the arrayList for searching
		ingredientForRecipe.add("Salt");
		ingredientForRecipe.add("Sugar");
		
		Recipe recipe = null;
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
						
					String sql = "SELECT * FROM Recipes WHERE id = 28;";		//Make the connection to the database and 
					ResultSet rset = stmt.executeQuery(sql);
					
					while(rset.next()) {
				
						String recipeName = rset.getString("name");
						String recipeIngredients = rset.getString("ingredients");
						String recipeInstructions = rset.getString("instructions");
						
						recipe = new Recipe(28, recipeName, recipeIngredients, recipeInstructions);		//Create the recipe out of the database
					}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}
				
		//Test to make sure that each field of the recipe equals the expected object 
		assertEquals(recipe.getID(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(1).get(0).getID() );
		assertEquals(recipe.getName(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(1).get(0).getName() );
		assertEquals(recipe.getIngredients(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(1).get(0).getIngredients() );
		assertEquals(recipe.getInstructions(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(1).get(0).getInstructions() );
	
	}
	
	@Test
	/*
	 * Test to see that given a list of ingredients will it return the recipe associated with that recipe, minus two ingredient in the list
	 */
	public void testSearchingForRecipeMinusTwoIngredient() {
		RecipesDataBase databaseInstance = RecipesDataBase.getDatabaseInstance(); 
		ArrayList<String> ingredientForRecipe = new ArrayList<>();
		ingredientForRecipe.add("Butter");
		ingredientForRecipe.add("Eggs");
		ingredientForRecipe.add("Flour");
		ingredientForRecipe.add("Baking Powder");					//Set up the arrayList for searching
		ingredientForRecipe.add("Salt");
		
		Recipe recipe = null;
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
						
					String sql = "SELECT * FROM Recipes WHERE id = 28;";		//Make the connection to the database and 
					ResultSet rset = stmt.executeQuery(sql);
					
					while(rset.next()) {
				
						String recipeName = rset.getString("name");
						String recipeIngredients = rset.getString("ingredients");
						String recipeInstructions = rset.getString("instructions");
						
						recipe = new Recipe(28, recipeName, recipeIngredients, recipeInstructions);		//Create the recipe out of the database
					}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}
				
		//Test to make sure that each field of the recipe equals the expected object 
		assertEquals(recipe.getID(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(2).get(0).getID() );
		assertEquals(recipe.getName(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(2).get(0).getName() );
		assertEquals(recipe.getIngredients(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(2).get(0).getIngredients() );
		assertEquals(recipe.getInstructions(), databaseInstance.searchRecipesWithIngredients(ingredientForRecipe).get(2).get(0).getInstructions() );
	
	}
	
	@Test
	/*
	 * This method test the search for ingredients inside the database method, testing to see that if given a string it returns the execte results 
	 */
	public void testSearchingForIngredients() {
		RecipesDataBase databaseInstance = RecipesDataBase.getDatabaseInstance(); 
		ArrayList<String> fromDatabase = databaseInstance.searchIngredientDatabase("Salt");		//Searches the database 
		
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("Saltine Craker");
		expectedResults.add("Salt");
		expectedResults.add("Celery Salt");					//Set up the test string 
		expectedResults.add("Garlic Salt");
		
		assertEquals(expectedResults, fromDatabase);		//Test to see ig they are equal 
	}
	
	
}
