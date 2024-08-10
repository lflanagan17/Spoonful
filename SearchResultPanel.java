
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The search results page for displaying recipes that can be made
 * after a user has entered ingredients 
 * 
 * @author ronak, tyler
 *
 */
public class SearchResultPanel {
	
	
	private Spoonful spoonful;
	private JPanel searchResultPanel;
	private JPanel recipes;
	private JLabel noMatchesLabel;
	private JLabel exactMatchesLabel;
	private JLabel missingOneLabel;
	private JLabel missingTwoLabel;
	private JButton backButton;
	private MyListPanel myList;
	private GridLayout grid;
	private int panelWidth;
	private int panelHeight;
	
	private ArrayList <JButton> allButtons;
	private ArrayList <ArrayList<Recipe>> searchResults;
	private SearchResultPanel thisClass;
	
	
	/** 
	 * Setup required GUI components
	 * @param spoonful
	 */  
	public SearchResultPanel(Spoonful spoonful) {
		 
		thisClass = this;
		this.spoonful = spoonful;
		
		panelWidth = spoonful.getFrameWidth();
		panelHeight = spoonful.getFrameHeight();
		
		grid = new GridLayout(0,2);
		
		noMatchesLabel =  new JLabel("Sorry, no results were found.");
		noMatchesLabel.setFont(new Font(Spoonful.FONT, Font.BOLD, 20));
		noMatchesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		exactMatchesLabel = new JLabel("You Can Make: ");
		exactMatchesLabel.setFont(new Font(Spoonful.FONT, Font.BOLD, 20));
		exactMatchesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		missingOneLabel = new JLabel("Missing One Ingredient For: ");
		missingOneLabel.setFont(new Font(Spoonful.FONT, Font.BOLD, 20));
		missingOneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		missingTwoLabel = new JLabel("Missing Two Ingredients For: ");
		missingTwoLabel.setFont(new Font(Spoonful.FONT, Font.BOLD, 20));
		missingTwoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		searchResultPanel = new JPanel();
		searchResultPanel.setLayout(grid);
		searchResultPanel.setSize(new Dimension(panelWidth, panelHeight));
		
		recipes = new JPanel();
		recipes.setSize(new Dimension(this.panelWidth/2, this.panelHeight));
		recipes.setLayout(new BoxLayout(recipes, BoxLayout.Y_AXIS));
		
		
		allButtons = new ArrayList<>();
		
		backButton = new JButton("Back");
		backButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 16));
		backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				spoonful.goToPickIngredients();
				recipes.remove(noMatchesLabel);
				recipes.remove(exactMatchesLabel);
				recipes.remove(missingOneLabel);
				recipes.remove(missingTwoLabel);
				clearButtons();
				searchResults.clear();
				myList.clearList();
				searchResultPanel.repaint();
				searchResultPanel.revalidate();
			
				
			}
		});
		
		recipes.add(backButton);
		
		myList = new MyListPanel(spoonful, false);
		myList.setPanelWidth(this.panelWidth/2);
		myList.setPanelHeight(this.panelHeight);

		myList.setShowButtons(false); 


	}
	
	/**
	 * Given a list of recipes and list of selected ingredients, display them as 2 components on the screen
	 * @param selectedIngredientsList
	 * @param searchResults
	 */
	public void displayResults(ArrayList <String> selectedIngredientsList, ArrayList <ArrayList<Recipe>> searchResults) {
		
		this.searchResults = searchResults;  
		
		// Make the recipe buttons
		ArrayList<Recipe> exactMatches = searchResults.get(0);
		ArrayList<Recipe> missingOneMatches = searchResults.get(1);
		ArrayList<Recipe> missingTwoMatches = searchResults.get(2);
  
		// If nothing was found
		if(exactMatches.size() == 0 && missingOneMatches.size() == 0 && missingTwoMatches.size() == 0) {
			recipes.add(noMatchesLabel);
		}
		
		//if an exact match between recipe and input ingredients was found 
		if(exactMatches.size() > 0) {
			recipes.add(exactMatchesLabel);
			for(int i = 0; i<exactMatches.size(); i++) {
				
				Recipe currentRecipe = exactMatches.get(i);
				JButton recipe = new JButton(currentRecipe.getName());
				recipe.setFont(new Font(Spoonful.FONT, Font.PLAIN, 20));
				recipe.setAlignmentX(Component.CENTER_ALIGNMENT);
				recipe.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						spoonful.goToRecipePage(currentRecipe);
						
					}
				});
				recipes.add(recipe);
				allButtons.add(recipe);
				
			}
			
		}
		
		//if a recipe was found that is missing just one ingredient from the input list 
		if(missingOneMatches.size() > 0) {
			recipes.add(missingOneLabel);
			for(int i = 0; i<missingOneMatches.size(); i++) {
				Recipe currentRecipe = missingOneMatches.get(i);
				JButton recipe = new JButton(missingOneMatches.get(i).getName());
				recipe.setFont(new Font(Spoonful.FONT, Font.PLAIN, 20));
				recipe.setAlignmentX(Component.CENTER_ALIGNMENT);
				recipe.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					
						spoonful.goToRecipePage(currentRecipe);
						
					}
				});
				recipes.add(recipe);
				allButtons.add(recipe);

			}
			
		}
		
		//if a recipe was found that is missing two ingredients from the input list 
		if(missingTwoMatches.size() > 0) {
			recipes.add(missingTwoLabel);
			for(int i = 0; i<missingTwoMatches.size(); i++) {
				Recipe currentRecipe = missingTwoMatches.get(i);
				JButton recipe = new JButton(missingTwoMatches.get(i).getName());
				recipe.setFont(new Font(Spoonful.FONT, Font.PLAIN, 20));
				recipe.setAlignmentX(Component.CENTER_ALIGNMENT);
				recipe.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						spoonful.goToRecipePage(currentRecipe);
						
						
					}
				});
				recipes.add(recipe);
				allButtons.add(recipe);

			}
			
		}
		
		
		//Populate my list
		for(int i = 0; i<selectedIngredientsList.size(); i++) {
			myList.addToList(selectedIngredientsList.get(i));
		}
		searchResultPanel.add(recipes);
		searchResultPanel.add(myList.getPanel());
		searchResultPanel.revalidate();

		
	}
	
	/**
	 * Removes all the buttons from the screen
	 */
	public void clearButtons() {
		for(JButton button : allButtons) {
			recipes.remove(button);
		}
		allButtons.clear();
	}
	
	/**
	 * Clears the stored list's data
	 */
	public void clearList() {
		myList.clearList();
	}
	
	public JPanel getPanel() {
		return searchResultPanel;
	}


}