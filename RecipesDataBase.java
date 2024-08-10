

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;


/**
 * Database management class
 * instance of this class is used whenever we need 
 * contains any and all sql commands
 * @author tylerchang, fremont
 *
 */
public class RecipesDataBase {
	
	// DIFFERENT FOR EVERYONE, MAKE SURE THE RIGHT PORT NUMBER IS ENTERED
	public static final String PORT_NUMBER = "8889";
	
	//number of recipes in the database 
	private static final int NUM_RECIPES = 81; 
	
	// List for storing the found results that match exactly the type and number of entered ingredients
	private ArrayList<Recipe> exactFoundRecipes;
	
	// List for storing the found results that match exactly the type and number of entered ingredients
	private ArrayList<Recipe> missingOneFoundRecipes;
		
	// List for storing the found results that match exactly the type and number of entered ingredients
	private ArrayList<Recipe> missingTwoFoundRecipes;
		
	// Initalize public instance when first loading the class, singleton design principle
	private static final RecipesDataBase databaseInstance = new RecipesDataBase();

	/**
	 * Private constructor based on Singleton design pattern
	 */
	private RecipesDataBase(){
		 
	}
	
	/**
	 * Gets the instance of the database through static reference
	 * @return RecipesDataBase instance
	 * Singleton design pattern
	 */
	public static RecipesDataBase getDatabaseInstance() {
		return databaseInstance;
	}
	
	/**
	 * Searches the database for recipes that use the selected ingredients, are missing one ingredients, and are missing two ingredients
	 * @param selectedIngredients - input list of ingredients
	 * @return an ArrayList containing arraylists of the recipes based on how many ingredients are missing
	 */
	public ArrayList<ArrayList<Recipe>> searchRecipesWithIngredients(ArrayList<String> selectedIngredients){
		
		// List for storing the found results that match exactly the type and number of entered ingredients
		ArrayList<Recipe> exactFoundRecipes = new ArrayList<>();
		// List for storing the found results' IDs that match exactly the type and number of entered ingredients
		ArrayList<Integer> exactFoundRecipesID = new ArrayList<>();
	
		// List for storing the found results that are missing one ingredient
		ArrayList<Recipe> missingOneFoundRecipes = new ArrayList<>();
		// List for storing the found results' IDs that are missing one ingredient
		ArrayList<Integer> missingOneFoundRecipesID = new ArrayList<>();
			
		// List for storing the found results that are missing two ingredients
		ArrayList<Recipe> missingTwoFoundRecipes = new ArrayList<>();
		// List for storing the found results' IDs that are missing two ingredients
		ArrayList<Integer> missingTwoFoundRecipesID = new ArrayList<>();
		
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
			
			// IDs of the ingredients selected by the user
			HashSet <Integer> selectedIngredientsID = new HashSet<Integer>();
			
			//Loop through the selected ingredients to get their IDs			
			for(String ingredient : selectedIngredients) {
				
				String sql = "SELECT id FROM Ingredients WHERE ingredientName = '" + ingredient + "';";
				ResultSet rset = stmt.executeQuery(sql);
				int ingredientID = -1;
				
				while(rset.next()) {
					ingredientID = rset.getInt("id");
					selectedIngredientsID.add(ingredientID);
				}
			}
			
			// IDs of the recipes that use the selected ingredients by the user
			HashSet <Integer> selectedRecipesID = new HashSet<Integer>(); 
			
			// Go into the relational table and get all the ids of all the recipes that use ingredients in the set
			for(int ingredientID : selectedIngredientsID) {
				String sql = "SELECT recipeID FROM RecipeIngredientAssociation WHERE ingredientID = " + ingredientID + ";";
				ResultSet rset = stmt.executeQuery(sql);
				int recipeID = -1;
				
				while(rset.next()) {
					recipeID = rset.getInt("recipeID");
					selectedRecipesID.add(recipeID);
				}
			}
			
			
			// Go through the selected recipes, for each recipe, examine the ingredients that it uses
			for(int currentRecipeID : selectedRecipesID) {
				
				ArrayList <Integer> matchingIngredientsID = new ArrayList<>();
				ArrayList <Integer> missingIngredientsID = new ArrayList<>();
				
				String sql = "SELECT ingredientID FROM RecipeIngredientAssociation WHERE recipeID = " + currentRecipeID + ";";
				ResultSet rset = stmt.executeQuery(sql);
				
				// Loops through ingredients
				while(rset.next()) {
					int currentIngredientID = rset.getInt("ingredientID");
					
					if(selectedIngredientsID.contains(currentIngredientID)) {
						matchingIngredientsID.add(currentIngredientID);
					}else {
						missingIngredientsID.add(currentIngredientID);
					}
					
					
				}
				
				// This case means the recipe was an exact match of the selected ingredients
				if(missingIngredientsID.size() == 0) {
					exactFoundRecipesID.add(currentRecipeID);
				}
				else if(missingIngredientsID.size() == 1) {
					// This case means the recipe was missing only one ingredient
					missingOneFoundRecipesID.add(currentRecipeID);
				}
				else if(missingIngredientsID.size() == 2) {
					// This case means the recipe was missing two ingredients
					missingTwoFoundRecipesID.add(currentRecipeID);
				}
				
			}
			
			
			// Loop through each ID list and make a Recipe object for each and populate the corresponding array
			
			for(int recipeID : exactFoundRecipesID) {
				
				String sql = "SELECT * FROM Recipes WHERE id = " + recipeID + ";";
				ResultSet rset = stmt.executeQuery(sql);
				while(rset.next()) {
					
					String recipeName = rset.getString("name");
					String recipeIngredients = rset.getString("ingredients");
					String recipeInstructions = rset.getString("instructions");
					
					Recipe newRecipe = new Recipe(recipeID, recipeName, recipeIngredients, recipeInstructions);
					
					exactFoundRecipes.add(newRecipe);
				}
			}
			
			for(int recipeID : missingOneFoundRecipesID) {
				
				String sql = "SELECT * FROM Recipes WHERE id = " + recipeID + ";";
				ResultSet rset = stmt.executeQuery(sql);
				while(rset.next()) {
					
					String recipeName = rset.getString("name");
					String recipeIngredients = rset.getString("ingredients");
					String recipeInstructions = rset.getString("instructions");
					
					Recipe newRecipe = new Recipe(recipeID, recipeName, recipeIngredients, recipeInstructions);
					
					missingOneFoundRecipes.add(newRecipe);
				}
			}
			
			for(int recipeID : missingTwoFoundRecipesID) {
				
				String sql = "SELECT * FROM Recipes WHERE id = " + recipeID + ";";
				ResultSet rset = stmt.executeQuery(sql);
				while(rset.next()) {
					
					String recipeName = rset.getString("name");
					String recipeIngredients = rset.getString("ingredients");
					String recipeInstructions = rset.getString("instructions");
					
					Recipe newRecipe = new Recipe(recipeID, recipeName, recipeIngredients, recipeInstructions);
					
					missingTwoFoundRecipes.add(newRecipe);
				}
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		// Returns 3 ArrayLists
		
		ArrayList<ArrayList<Recipe>> results = new ArrayList<ArrayList<Recipe>>();
		results.add(exactFoundRecipes);
		results.add(missingOneFoundRecipes);
		results.add(missingTwoFoundRecipes);
		
		
		return results;

	}

	/**
	 * Add a recipe to the database given required attributes
	 * @param id
	 * @param name
	 * @param ingredients
	 * @param instructions
	 */
	public void addRecipeToDatabase(int id, String name, String ingredients, String instructions) {
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {			
			String sql = "INSERT IGNORE INTO Recipes values ('" + id + "', '" + name + "', '" + ingredients + "', '" + instructions + "');";	
			stmt.execute(sql);
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/*
	 * This method searches through the ingredient database comparing the given string to the ingredients name
	 * @param itemSearch, the user given string that see if their any ingredients that hold that string using the compare method 
	 * @return an arrayList of strings that represent the items found in the search 
	 */
	public ArrayList<String> searchIngredientDatabase(String itemSearch) {
		
		ArrayList<String> categoryItems = new ArrayList<String>();
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();		//Establish a connection with the database
				) {
			
			String sql = "SELECT ingredientName FROM Ingredients";
			
			ResultSet rset = stmt.executeQuery(sql);
			
			while (rset.next()) {								//While the databse contains more ingredients 
				
				String ingredientFromDatabase = rset.getString("ingredientName");	//Get the ingredient name 
				
				String ingredientFromDatabaseLowerCase = ingredientFromDatabase.toLowerCase();
				String itemSearchLowerCase = itemSearch.toLowerCase();
				
				if ( ingredientFromDatabaseLowerCase.contains(itemSearchLowerCase) ) {			//If the databse item contains the given string, add it to the arraylist 
					categoryItems.add(ingredientFromDatabase);
					
				}
				
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return categoryItems;				//Return the arraylist of matching strings 
	}
	
	/**
	 * Creates the Recipes table in the database if there already isn't one
	 */
	public void createRecipesTable() {
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
			//Make a table
			String sql = "create table if not exists Recipes ("
					+ "id mediumint auto_increment, "
					+ "name varchar(50), "
					+ "ingredients longtext, "
					+ "instructions longtext, "
					+ "primary key (id));";
			stmt.execute(sql);
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Get a list of ingredients in a specified category from the database 
	 * @param category
	 * @return list of ingredients in that category
	 */
	public ArrayList<String> getDatabaseCategory(String category) {
		ArrayList<String> categoryItems = new ArrayList<String>();
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
			
			String sql = "SELECT ingredientName, category FROM Ingredients";
			
			ResultSet rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				
				String categoryFromDatabase = rset.getString("category");
				
				if ( category.equals(categoryFromDatabase) ) {
					String ingredient = rset.getString("ingredientName");
					categoryItems.add(ingredient);
				}
				
			}
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return categoryItems;
	}
	
	/**
	 * Creates an Ingredients table if there already isn't
	 * Populates it with ingredients found in recipes 
	 */
	public void createAndFillIngredientsTable() {
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
			//Make a table
			String sql = "create table if not exists Ingredients ("
					+ "id mediumint auto_increment, "
					+ "ingredientName varchar(50), "
					+ "isVegetarian varchar(50), "
					+ "containsDairy varchar(50), "
					+ "category varchar(50), "
					+ "primary key (id));";
			stmt.execute(sql);
			
			IngredientsCategories ingredientCategories = new IngredientsCategories();
			ArrayList<String> dairy = ingredientCategories.getDairy();
			ArrayList<String> meat = ingredientCategories.getMeat();
			ArrayList<String> proteinWithoutMeat = ingredientCategories.getProteinsWithoutMeat();
			ArrayList<String> vegetables = ingredientCategories.getVegetables();
			ArrayList<String> fruits = ingredientCategories.getFruits();
			ArrayList<String> grains = ingredientCategories.getGrains();
			ArrayList<String> miscList = ingredientCategories.getMisc();

			int id = 1;
			
			// Dairy ingredients
			for(String dairyIngredient : dairy) {
				
				String query = "INSERT IGNORE INTO Ingredients values ('" + id + "', '" + dairyIngredient + "', 'Yes', 'Yes', 'Dairy');";	
				stmt.execute(query);
				id++;
			}
			
			// Meat ingredients
			for(String meatIngredient : meat) {
				
				String query = "INSERT IGNORE INTO Ingredients values ('" + id + "', '" + meatIngredient + "', 'No', 'No', 'Meat');";	
				stmt.execute(query);
				id++;
			}
			
			// Protein without meat ingredients
			for(String proteinNoMeat : proteinWithoutMeat) {
				
				String query = "INSERT IGNORE INTO Ingredients values ('" + id + "', '" + proteinNoMeat + "', 'Yes', 'No', 'Protein Without Meat');";	
				stmt.execute(query);
				id++;
			}
			// Vegetable ingredients
			for(String vegetable : vegetables) {
				
				String query = "INSERT IGNORE INTO Ingredients values ('" + id + "', '" + vegetable + "', 'Yes', 'No', 'Vegetable');";	
				stmt.execute(query);
				id++;
			}
			
			// Fruits ingredients
			for(String fruit : fruits) {
				
				String query = "INSERT IGNORE INTO Ingredients values ('" + id + "', '" + fruit + "', 'Yes', 'No', 'Fruit');";	
				stmt.execute(query);
				id++;
			}
			
			// Grains ingredients
			for(String grain : grains) {
				
				String query = "INSERT IGNORE INTO Ingredients values ('" + id + "', '" + grain + "', 'Yes', 'No', 'Grains');";	
				stmt.execute(query);
				id++;
			}	
			// Misc ingredients
			for(String misc : miscList) {
				
				String query = "INSERT IGNORE INTO Ingredients values ('" + id + "', '" + misc + "', 'Yes', 'No', 'Misc.');";	
				stmt.execute(query);
				id++;
			}	
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Creates an association table in the database connecting the relationship between ingredients and recipes
	 */
	public void createAndFillAssociationsTable() {
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement mainStatement = conn.createStatement();
				Statement getRecipesStatement = conn.createStatement();
				) {
			
			//Make the association table
			String sql = "create table if not exists RecipeIngredientAssociation ("
					+ "ingredientID int, "
					+ "recipeID int);";
			
			mainStatement.execute(sql);

			String selectIngredientsAndID = "SELECT ingredientName, id FROM Ingredients;";
			String selectRecipeIngredientsAndID = "SELECT ingredients, id FROM Recipes;";
			
			ResultSet ingredients = mainStatement.executeQuery(selectIngredientsAndID);		
						
			while(ingredients.next()) {
				
				ResultSet recipes = getRecipesStatement.executeQuery(selectRecipeIngredientsAndID);

				// Split the ingredients string into a comma separated array
				String ingredientName = ingredients.getString("ingredientName");
				int ingredientID = ingredients.getInt("id");
				
				
				while(recipes.next()) {
					

					
					String recipeIngredients = recipes.getString("ingredients");
					int recipieIngredientID = recipes.getInt("id");
					
					
					if(recipeIngredients.toLowerCase().contains(ingredientName.toLowerCase())) {
						String addSQL =  "INSERT INTO RecipeIngredientAssociation values(" + ingredientID + ", " + recipieIngredientID + ");";
						Statement addStatement = conn.createStatement();
						addStatement.execute(addSQL);
					}					
					
				}
				
				
			}	
				
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
	}
	/**
	 * Creates a database for the program on your local machine
	 */
	public void createDatabase() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/?serverTimezone=UTC", 
						"root", "root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			// Step 3 - create our database
			String sql = "create database if not exists SpoonfulDataBase";
			stmt.execute(sql);

		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	/**
	 * Implementation for the AnythingStrategy
	 * @return a random recipe 
	 */
	public Recipe chooseAnyRecipe() {
		Random random = new Random(); 
		int randChoice = random.nextInt(NUM_RECIPES+1); 
		
		try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + RecipesDataBase.PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
  
			String sql = "SELECT id, name, ingredients, instructions FROM Recipes WHERE id=" + randChoice + ";"; 
			ResultSet rset = stmt.executeQuery(sql); 
			
			while(rset.next()) {
				int id = rset.getInt("id"); 
				String ingredients = rset.getString("ingredients");
				String instructions = rset.getString("instructions");
				String name = rset.getString("name");

				return new Recipe(id, name, ingredients, instructions); 
			}

		} catch(SQLException ex) {   
			ex.printStackTrace();  
		}  
		return null; 
	}

	/**
	 * implementation for the VegetarianStrategy
	 * @return a random recipe 
	 */
	public Recipe chooseVegetarianRecipe() {
		String ingredients = ""; 
		String instructions = ""; 
		String name = ""; 
		int id = 0; 
		boolean allVeg=false;  
		
		try (  
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + RecipesDataBase.PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement randRecipeStmt = conn.createStatement();
				) {
  
			
			while(!allVeg) {
				
				Random random = new Random(); 
				int randChoice = random.nextInt(NUM_RECIPES+1);  
				//select a random recipe from the Recipe table 
				String randRecipeSql = "SELECT id, name, ingredients, instructions FROM Recipes WHERE id=" + randChoice + ";"; 
				ResultSet randRecipe = randRecipeStmt.executeQuery(randRecipeSql); 
				
				while(randRecipe.next()) {
					id = randRecipe.getInt("id"); 
					ingredients = randRecipe.getString("ingredients");
					instructions = randRecipe.getString("instructions");
					name = randRecipe.getString("name"); 
					int recipeID = randRecipe.getInt("id"); //this is the same as randChoice but just check for now

					Statement ingredientStmt = conn.createStatement();
					//now get all the ingredients in that recipe from the Association table 
					String ingredienteSql = "SELECT ingredientID FROM RecipeIngredientAssociation WHERE recipeID=" + recipeID + ";"; 
					ResultSet ingredientsInRecipe = ingredientStmt.executeQuery(ingredienteSql); 
					int count=0; 
					//loop through every ingredient in that recipe 
					while(ingredientsInRecipe.next()) {
						int ingredientsID = ingredientsInRecipe.getInt("ingredientID"); 
						Statement isVegStmt = conn.createStatement();
						//get whether or not that particular was vegetarian 
						String isVegSql = "SELECT isVegetarian FROM Ingredients WHERE id=" + ingredientsID + ";"; 
						ResultSet isVeg = isVegStmt.executeQuery(isVegSql); 
						while(isVeg.next()) {
							String isVegetarian = isVeg.getString("isVegetarian");
							int compare = isVegetarian.compareTo("No"); 
							if(compare==0) {
								count++; //increment count if this ingredient is not vegetarian 
							}
						}
						//if the count was never incremented, then every ingredient in this recipe was vegetarian, so terminate the loop 
					}
					if(count==0) { 
						allVeg=true; 
					}
				}
			}

		} catch(SQLException ex) {   
			ex.printStackTrace();  
		}  
		return new Recipe(id, name, ingredients, instructions); 
	}
	
	/**
	 * Get the number of rows of the ingredient to recipe associations
	 * @return
	 */
	public int seeNumberOfAssociationRows() {
		
		int count = 0;
		
	    try ( 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement stmt = conn.createStatement();
				) {
			
	    	String getRowNumber = "select count(*) from RecipeIngredientAssociation";
			ResultSet rowNumber =  stmt.executeQuery( getRowNumber);	
			rowNumber.next();
		    count = rowNumber.getInt(1);		    
				
			} catch(SQLException ex) {
			//Got rid of the stack trace error so that it would not throw the error if you ran it the first time, cause you call a database that does not exist yet
		}
	    
	    return count;
	    
	}

	/**
	 * implementation for the NoDairyStrategy
	 * @return a random recipe 
	 */
	public Recipe chooseNoDairyRecipe() {
		int id=0; 
		String ingredients = ""; 
		String instructions = ""; 
		String name = ""; 
		boolean containsDairy=true;  
		
		try (  
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + RecipesDataBase.PORT_NUMBER + "/SpoonfulDatabase?serverTimezone=UTC", 
						"root", "root");
				Statement randRecipeStmt = conn.createStatement();
				) {
  
			
			while(containsDairy) {
				
				Random random = new Random(); 
				int randChoice = random.nextInt(NUM_RECIPES+1);  
				//select a random recipe from the Recipe table 
				String randRecipeSql = "SELECT id, name, ingredients, instructions FROM Recipes WHERE id=" + randChoice + ";"; 
				ResultSet randRecipe = randRecipeStmt.executeQuery(randRecipeSql); 
				
				while(randRecipe.next()) {
					id = randRecipe.getInt("id"); 
					ingredients = randRecipe.getString("ingredients");
					instructions = randRecipe.getString("instructions");
					name = randRecipe.getString("name"); 
					int recipeID = randRecipe.getInt("id"); //this is the same as randChoice but just check for now

					Statement ingredientStmt = conn.createStatement();
					//now get all the ingredients in that recipe from the Association table 
					String ingredienteSql = "SELECT ingredientID FROM RecipeIngredientAssociation WHERE recipeID=" + recipeID + ";"; 
					ResultSet ingredientsInRecipe = ingredientStmt.executeQuery(ingredienteSql); 
					int count=0; 
					//loop through every ingredient in that recipe 
					while(ingredientsInRecipe.next()) {
						int ingredientsID = ingredientsInRecipe.getInt("ingredientID"); 
						Statement hasDairyStmt = conn.createStatement();   
						//get whether or not that particular was vegetarian 
						String hasDairySql = "SELECT containsDairy FROM Ingredients WHERE id=" + ingredientsID + ";"; 
						ResultSet hasDairyOrNo = hasDairyStmt.executeQuery(hasDairySql); 
						while(hasDairyOrNo.next()) {
							String hasDairy = hasDairyOrNo.getString("containsDairy");
							int compare = hasDairy.compareTo("Yes"); 
							if(compare==0) {
								count++; //increment count if this ingredient does have dairy 
							}
						}
						//if the count was never incremented, then every ingredient in this recipe does not have dairy, so terminate the loop 
					}
					if(count==0) { 
						containsDairy=false; 
					}
				}
			}

		} catch(SQLException ex) {   
			ex.printStackTrace();  
		}  
		return new Recipe(id, name, ingredients, instructions); 
	}

}
