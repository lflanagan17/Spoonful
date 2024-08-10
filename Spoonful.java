import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;


/**
 * This is the main class of the program
 * @author tylerchang
 *
 */
public class Spoonful {
	
	/**
	 * Initalize GUI panels being used
	 */
	public static final String FONT = "Palatino"; 
	public static final Color THEME_COLOR = new Color(128,0,128); 
	
	private JFrame programFrame; 
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	
	private WelcomePage welcome;
	private JPanel welcomePanel;
	
	private MyListPanel myList;
	private JPanel myListPanel;
	
	private IngredientsDisplayerPanel availableIngredients;
	private JPanel availableIngredientsPanel;
	
	private IngredientsCategoryPanel categories;
	private JPanel categoriesPanel;
	
	private SurprisePanel surprise;
	private JPanel enterWhatIHavePanel;
	
	private JPanel surprisePanel;
	
	private SearchResultPanel results;
	private JPanel resultsPanel;
	
	
	 
	
	/**
	 * Define the needed GUI elements
	 */
	public Spoonful() { 
		
		// Initial definition of GUI components
		
		programFrame = new JFrame();
		programFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		programFrame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		programFrame.setVisible(true);
		
		welcome = new WelcomePage(this); 
		welcomePanel = welcome.getWelcomePanel();
		
		//three panels that are added to the enter what I have page 
		myList = new MyListPanel(this, true);
		myListPanel = myList.getPanel();  
		
		availableIngredients = new IngredientsDisplayerPanel(this, myList);
		availableIngredientsPanel = availableIngredients.getPanel();
		
		categories = new IngredientsCategoryPanel(this, availableIngredients, myList);
		categoriesPanel = categories.getPanel();
		 

		surprise = new SurprisePanel(this);
		surprisePanel = new JPanel();

		//Add these three panels to one panel
		enterWhatIHavePanel = new JPanel(); 
		GridLayout layout = new GridLayout(0,3);
		enterWhatIHavePanel.setLayout(layout);
		enterWhatIHavePanel.add(categoriesPanel); 
		enterWhatIHavePanel.add(availableIngredientsPanel); 
		enterWhatIHavePanel.add(myListPanel);
				
		
		results = new SearchResultPanel(this);
		resultsPanel = results.getPanel();
		
		
	} 
	
	/**
	 * The sole purpose of the main method is to start the program
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		Reader reader = new Reader();
		reader.addRecipesFromJSONToDatabase();		
	
		Spoonful spoonful = new Spoonful();
		spoonful.start();
		 
	}
	
	/**
	 * Starts the program by going to the main page
	 */
	public void start() {
		 
		programFrame.add(welcomePanel);
		programFrame.revalidate();
		
	}
	
	/**
	 * Navigates to the pick ingredients page
	 */
	public void goToPickIngredients() {
		
		
		
		programFrame.remove(welcomePanel);
		programFrame.remove(enterWhatIHavePanel);
		programFrame.remove(surprisePanel);
		programFrame.remove(resultsPanel);	
		
		programFrame.add(enterWhatIHavePanel);
		
		programFrame.repaint();
		programFrame.revalidate();
	}
	
	public void goToHomeFromPickingIngredients() {
		programFrame.remove(enterWhatIHavePanel);
		programFrame.add(welcomePanel);  
		programFrame.repaint();
		programFrame.revalidate();
	}
	
	public void gotToHomeFromSurprise() {
		programFrame.remove(surprisePanel);
		programFrame.add(welcomePanel);
		programFrame.repaint();
		programFrame.revalidate();
	}
	
	/**
	 * changes GUI page after the Surprise Me button has been clicked 
	 */
	public void goToSurpriseButtons() {
		
		programFrame.remove(surprisePanel);
		programFrame.remove(welcomePanel);
		
		surprisePanel = surprise.getPanel();  
		
		programFrame.add(surprisePanel);
		
		programFrame.repaint();
		programFrame.revalidate();
	}
	
	/**
	 * Navigates to the search results page
	 */
	public void goToSearchResult(ArrayList<String> selectedIngredients) {
		
		programFrame.remove(resultsPanel);
		programFrame.remove(enterWhatIHavePanel);
		
		
		programFrame.setLayout(new BorderLayout());
		
		RecipesDataBase database = RecipesDataBase.getDatabaseInstance();
		ArrayList<ArrayList<Recipe>> foundResults = database.searchRecipesWithIngredients(selectedIngredients);
		
  		  
		results.displayResults(selectedIngredients, foundResults);
		
		
		resultsPanel = results.getPanel();

		
		programFrame.add(resultsPanel);
		
		programFrame.repaint();
		programFrame.revalidate();

	}
	
	
	public void recipeDisplayBackButton(String path, ArrayList<String> selectedIngredients, ArrayList<ArrayList<Recipe>> FOUNDRESULTS) {
		
	/**
	 * Navigates to the recipe page from enter screen
	 * @param recipe
	 */
		programFrame.remove(resultsPanel);
		
		 if (path.equals("Search Results")) {
//			programFrame.remove(resultsPanel);
//			programFrame.remove(recipeDisplayPanel);
			programFrame.removeAll();

			programFrame.repaint();
			programFrame.revalidate();	
			
			programFrame.setLayout(new BorderLayout());
			
			RecipesDataBase database = RecipesDataBase.getDatabaseInstance();
			ArrayList<ArrayList<Recipe>> foundResults = database.searchRecipesWithIngredients(selectedIngredients);
			
	  		
			results.displayResults(selectedIngredients, foundResults);
						
			resultsPanel = results.getPanel();

			
			programFrame.add(resultsPanel);
			
			programFrame.repaint();
			programFrame.revalidate();

		 }
		
	}
	
	
	public void goToRecipePage(Recipe recipe) {
		
		RecipeDisplayPanel recipeDisplayPanel = new RecipeDisplayPanel(this, recipe);
		
		
		JFrame frame = new JFrame("Recipe");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.add(recipeDisplayPanel.getPanel());
	    frame.pack();
		frame.setVisible(true);
		frame.revalidate();


	}
	
	/**
	 * Navigates to the recipe page from the surprise screen 
	 * @param recipe
	 */
	public void goToRecipePageFromSurprise(Recipe recipe) {
	RecipeDisplayPanel recipeDisplayPanel = new RecipeDisplayPanel(this, recipe);
		
		
		JFrame frame = new JFrame("Recipe");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.add(recipeDisplayPanel.getPanel());
	    frame.pack();
		frame.setVisible(true);
		frame.revalidate();

	}
	
	/**
	 * Gets the height of the JFrame
	 * @return height
	 */
	public int getFrameHeight() {
		return programFrame.getHeight();
	}
	
	/**
	 * Gets the width of the JFrame
	 * @return width
	 */
	public int getFrameWidth() {
		return programFrame.getHeight();
	}
}
