

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The panel that displays the ingredient categories available
 * @author tylerchang
 *
 */
public class IngredientsCategoryPanel {
	
	// Setup of needed GUI components and constants
	public static final String FRUITS = "Fruit";
	public static final String VEGETABLES = "Vegetable";
	public static final String GRAINS = "Grains";
	public static final String PROTEIN_WITHOUT_MEAT = "Protein Without Meat";
	public static final String MEAT = "Meat";
	public static final String DAIRY = "Dairy";
	public static final String MISC = "Misc.";
	
	private JPanel categoriesPanel;
	private GridLayout gridLayout;
	private int categoriesPanelWidth;
	private int categoriesPanelHeight; 
	private JButton backButton;
	private JButton dairyProductsButton;
	private JButton proteinWithoutMeatsButton;
	private JButton meatsButton;
	private JButton grainsButton;
	private JButton fruitsButton;
	private JButton vegetablesButton;
	private JButton miscButton;
	private JButton findRecipesButton;
	
	/**
	 * Defintion of GUI components and button action listeners
	 * @param spoonful 
	 * @param displayer 
	 */
	public IngredientsCategoryPanel(Spoonful spoonful, IngredientsDisplayerPanel displayer, MyListPanel myListInstance) {
		
		// Panel setup
		
		categoriesPanelWidth = spoonful.getFrameWidth() / 3;
		categoriesPanelHeight = spoonful.getFrameHeight();
		
		gridLayout = new GridLayout(9, 0);
	
		categoriesPanel = new JPanel();
		categoriesPanel.setSize(new Dimension(categoriesPanelWidth, categoriesPanelHeight));
		categoriesPanel.setLayout(gridLayout);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font(spoonful.FONT, Font.PLAIN, 23));
		backButton.setOpaque(true); 
		backButton.addActionListener(new ActionListener() {
			
			/**
			 * When back button is clicked, navigate back to home
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				spoonful.goToHomeFromPickingIngredients();
				
			}
		});
		
		
		/**
		 * setting up all the buttons and their cooresponding functionalities
		 */

		dairyProductsButton = new JButton(DAIRY);
		dairyProductsButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		dairyProductsButton.setOpaque(true); 
		dairyProductsButton.setBackground(Spoonful.THEME_COLOR);
		dairyProductsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayer.showIngredients(DAIRY);
				
			}
		});
				
		proteinWithoutMeatsButton = new JButton(PROTEIN_WITHOUT_MEAT);
		proteinWithoutMeatsButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		proteinWithoutMeatsButton.setOpaque(true); 
		proteinWithoutMeatsButton.setBackground(Spoonful.THEME_COLOR);
		proteinWithoutMeatsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayer.showIngredients(PROTEIN_WITHOUT_MEAT);
				
			}
		});
		
		grainsButton = new JButton(GRAINS);
		grainsButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		grainsButton.setOpaque(true); 
		grainsButton.setBackground(Spoonful.THEME_COLOR);
		grainsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayer.showIngredients(GRAINS);
				
			}
		});
		
		fruitsButton = new JButton(FRUITS);
		fruitsButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		fruitsButton.setOpaque(true); 
		fruitsButton.setBackground(Spoonful.THEME_COLOR);
		fruitsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayer.showIngredients(FRUITS);
				
			}
		});
		
		vegetablesButton = new JButton(VEGETABLES);
		vegetablesButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		vegetablesButton.setOpaque(true); 
		vegetablesButton.setBackground(Spoonful.THEME_COLOR);
		vegetablesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayer.showIngredients(VEGETABLES);
				
			}
		});
		
		meatsButton = new JButton(MEAT);
		meatsButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		meatsButton.setOpaque(true); 
		meatsButton.setBackground(Spoonful.THEME_COLOR);
		meatsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayer.showIngredients(MEAT);
				
			}
		});
		
		miscButton = new JButton(MISC);
		miscButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		miscButton.setOpaque(true); 
		miscButton.setBackground(Spoonful.THEME_COLOR);
		miscButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayer.showIngredients(MISC);
				
			}
		});
		
		
		//create the find recipes button
		findRecipesButton = new JButton("Find Recipes!");
		findRecipesButton.setFont(new Font(Spoonful.FONT, Font.BOLD, 37));
		findRecipesButton.setOpaque(true); 
		findRecipesButton.setBackground(Spoonful.THEME_COLOR);
		findRecipesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				HashMap<String, JLabel> listOfList = myListInstance.getListOfItems(); 
				ArrayList<String> ingredientsList = new ArrayList<String>(listOfList.keySet());
				
			spoonful.goToSearchResult(ingredientsList);
				
			}  
		});
		

		//add all the buttons to each cell in the layout 
		categoriesPanel.add(backButton);
		categoriesPanel.add(dairyProductsButton);
		categoriesPanel.add(proteinWithoutMeatsButton);
		categoriesPanel.add(meatsButton);
		categoriesPanel.add(grainsButton);
		categoriesPanel.add(fruitsButton);
		categoriesPanel.add(vegetablesButton);
		categoriesPanel.add(miscButton);
		categoriesPanel.add(findRecipesButton); 
		
		categoriesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	}
	
	/**
	 * @return this JPanel
	 */
	public JPanel getPanel() {
		return categoriesPanel;
	}
	

}
