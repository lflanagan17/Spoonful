import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * This class will test the adding and removing operations of the ingredients list
 * @author tylerchang
 *
 */
public class TestIngredientsList {
	
	Spoonful spoonful;

	@Before
	public void setUp() throws Exception {
		spoonful = new Spoonful();
	}
	
	/**
	 * This will test the add to list feature of the ingredients list
	 */
	@Test
	public void testAddToIngredientsList() {
		
		// Adding list of ingredients to myList
		MyListPanel myListPanel = new MyListPanel(spoonful, true);
		
		myListPanel.addToList("Salmon");
		myListPanel.addToList("Walnuts");
		myListPanel.addToList("Peanut Butter");
		
		// Get the list from the JComponent
		ArrayList<String> myList = myListPanel.getList();
		
		// For comparison, make a list of same ingredients in an Arraylist
		ArrayList <String> comparisonList = new ArrayList<>();
		comparisonList.add("Salmon");
		comparisonList.add("Walnuts");
		comparisonList.add("Peanut Butter");
		
		//Sort both into alphabetical order
		myList.sort(String::compareToIgnoreCase);
		comparisonList.sort(String::compareToIgnoreCase);
		
		// The two lists should equal each other
		assertEquals(comparisonList, myList);		
		
	}
	
	/**
	 * This will test the remomve fromo list feature of the ingredients list
	 */
	@Test
	public void testRemoveIngredientsList() {
		
		// Adding list of ingredients to myList
		MyListPanel myListPanel = new MyListPanel(spoonful, true);
		
		myListPanel.addToList("Salmon");
		myListPanel.addToList("Walnuts");
		myListPanel.addToList("Peanut Butter");
		
		// Remove an ingredient
		myListPanel.removeFromList("Peanut Butter");	
		
		// Get the list from the JComponent
		ArrayList<String> myList = myListPanel.getList();
		
		// For comparison, make a list of the remaining ingredients
		ArrayList <String> comparisonList = new ArrayList<>();
		comparisonList.add("Salmon");
		comparisonList.add("Walnuts");
		
		//Sort both into alphabetical order
		myList.sort(String::compareToIgnoreCase);
		comparisonList.sort(String::compareToIgnoreCase);
		
		// The two lists should equal each other
		assertEquals(comparisonList, myList);		
		
	}

}
