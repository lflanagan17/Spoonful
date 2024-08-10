

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * tests to see if a given ingredient belongs to the correct category
 * @author lucyflanagan
 */


public class TestCategoryList {
	
	// Setup initialization of needed ingredients
	
	IngredientsCategories ingredients; 
	String blueberries;
	String cilantro;
	String cornstarch;
	String walnut;
	String bacon;
	String provoloneCheese;
	String soySauce;
	
	@Before 
	public void setUp() throws Exception {
		
		//Initial definition
		
		ingredients = new IngredientsCategories(); 
		blueberries = "Blueberries"; 
		cilantro = "Cilantro"; 
		cornstarch = "Cornstarch"; 
		walnut = "Walnut"; 
		bacon = "Bacon"; 
		provoloneCheese = "Provolone Cheese"; 
		soySauce = "Soy sauce";
	}
	
	/**
	 * Test for category using a fruit
	 */
	@Test 
	public void testFruits() {
		assertEquals("fruit", ingredients.getCategory(blueberries)); 
	}
	
	/**
	 * Test for category using a vegetable
	 */
	@Test 
	public void testVegetables() {
		assertEquals("vegetables", ingredients.getCategory(cilantro));  
	}
	
	/**
	 * Test for category using a grain
	 */
	@Test 
	public void testGrains() {
		assertEquals("grains", ingredients.getCategory(cornstarch)); 
	}
	
	/**
	 * Test for category using a protein without meat
	 */
	@Test 
	public void testProteinWithoutMeat() {
		assertEquals("proteins without meat", ingredients.getCategory(walnut)); 
	}  
	
	/**
	 * Test for category using a meat
	 */
	@Test 
	public void testMeats() {
		assertEquals("meat", ingredients.getCategory(bacon)); 
	}
	
	/**
	 * Test for category using a dairy ingredient
	 */
	@Test 
	public void testDairy() {
		assertEquals("dairy", ingredients.getCategory(provoloneCheese)); 
	}
	
	/**
	 * Test for category using a miscellaneous ingredient
	 */
	@Test 
	public void testMisc() {
		assertEquals("misc", ingredients.getCategory(soySauce)); 
	}

}
