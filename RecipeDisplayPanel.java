import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * displays a recipe to the user
 * after they have entered their ingredients and selected a recipe
 * or after they have clicked surprise me 
 * @author tylerchang 
 *
 */
public class RecipeDisplayPanel {
	
	private JPanel displayPanel;
	private JLabel recipeNameLabel;
	private JLabel ingredientsLabel;
	private JLabel instructionsLabel;
	private JLabel whiteSpaceLabel;
	private int panelWidth;
	private int panelHeight;

	
	/**
	 * GUI components setup
	 * @param spoonful
	 * @param recipe
	 */
	public RecipeDisplayPanel(Spoonful spoonful, Recipe recipe) {
				
		panelWidth = spoonful.getFrameWidth();
		panelHeight = spoonful.getFrameHeight(); 
		
		
		displayPanel = new JPanel();
		displayPanel.setSize(new Dimension(panelWidth, panelHeight));
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		

		recipeNameLabel = new JLabel();
		ingredientsLabel = new JLabel();
		instructionsLabel = new JLabel();
		whiteSpaceLabel = new JLabel(" ");
		whiteSpaceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Displaying the title
		
		recipeNameLabel.setText(recipe.getName());
		recipeNameLabel.setFont(new Font(Spoonful.FONT, Font.BOLD, 20));
		recipeNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		displayPanel.add(recipeNameLabel);
		
		// Displaying the ingredients
		ingredientsLabel.setText("Ingredients");
		ingredientsLabel.setFont(new Font(Spoonful.FONT, Font.BOLD, 20));
		ingredientsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		  
		displayPanel.add(whiteSpaceLabel);
		displayPanel.add(ingredientsLabel);

		String[] ingredientsArray = cleanIngredientsString(recipe.getIngredients());
		
		//adds a a label with one ingredient to main screen
		for(String ingredient: ingredientsArray) {
			JLabel ingredientLabel = new JLabel(ingredient);
			ingredientLabel.setFont(new Font(Spoonful.FONT, Font.PLAIN, 16));
			ingredientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			displayPanel.add(ingredientLabel);
		}
		
		// Displaying the instructions
		instructionsLabel.setText("Instructions");
		instructionsLabel.setFont(new Font(Spoonful.FONT, Font.BOLD, 20));
		instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		displayPanel.add(whiteSpaceLabel);
		displayPanel.add(instructionsLabel);
		String[ ] instructionsArray = cleanInstructionsString(recipe.getInstructions());
		
		for(String instruction : instructionsArray) {
			JLabel instructionLabel = new JLabel(instruction);
			instructionLabel.setFont(new Font(Spoonful.FONT, Font.PLAIN, 16));
			instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			displayPanel.add(instructionLabel);
		}
		
	}
	
	public JPanel getPanel() {
		return displayPanel;
	}
	
	
	/**
	 * cleans the ingredients string read from JSON file so 
	 * instructions can be correctly displayed on screen 
	 * @param ingredients
	 * @return an array of ingredient Strings which have been cleaned 
	 */
	public String[] cleanIngredientsString(String ingredients) {
		
		String [] ingredientsArray = ingredients.split("ADVERTISEMENT,");
		
		String [] withoutLastElement = new String [ingredientsArray.length-1];
		
		for(int i = 0; i < withoutLastElement.length; i++) {
			withoutLastElement[i] = ingredientsArray[i];
		}
		
		return withoutLastElement;
		
	}
	

	/**
	 * cleans the instructions string read from JSON file so 
	 * instructions can be correctly displayed on screen 
	 * @param instructions   
	 * @return an array of ingredient Strings which have been cleaned 
	 */
	public String[] cleanInstructionsString(String instructions) {
		String[] cleanedInstructions = instructions.split("\\.");
		
		// Clean whitespaces
		for(int i = 0; i < cleanedInstructions.length; i++) {
			cleanedInstructions[i] = i+1 + ". " + cleanedInstructions[i].strip(); 
		}
		
		return cleanedInstructions;
	}
			

	
	
	
	
	
}