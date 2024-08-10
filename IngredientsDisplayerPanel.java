
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * The panel that displays what ingredients are available in a category
 * @author tylerchang
 *
 */
public class IngredientsDisplayerPanel {

	private JPanel overallPanel;
	private JPanel searchPanel;
	private JTextField searchText;
	private JButton searchButton;
	private Spoonful spoonful;
	private JPanel ingredientsDisplayerPanel;
	private JScrollPane scrollingPanel;
	private MyListPanel myList;
	private int displayerWidth;
	private int displayerHeight;
	
	
	/**
	 * Setup relevant GUI components
	 * @param spoonful
	 * @param myList
	 */
	public IngredientsDisplayerPanel(Spoonful spoonful, MyListPanel myList) {
		this.myList = myList;
		this.spoonful=spoonful; 
	
		displayerWidth = spoonful.getFrameWidth()/ 3;
		displayerHeight = spoonful.getFrameHeight();
		
		overallPanel = new JPanel();
		overallPanel.setLayout(new BorderLayout());
		overallPanel.setSize(new Dimension(displayerWidth, displayerHeight));
		
		searchPanel = new JPanel();
		searchPanel.setSize(new Dimension(displayerWidth, Math.round(displayerHeight/9)));
		searchText = new JTextField("", 10);
		searchButton = new JButton("Search");
		
		addSearch();
		
		ingredientsDisplayerPanel = new JPanel();
		ingredientsDisplayerPanel.setLayout(new BoxLayout(ingredientsDisplayerPanel, BoxLayout.Y_AXIS));
		ingredientsDisplayerPanel.setSize(new Dimension(displayerWidth, Math.round((displayerHeight * 8)/9)));
		ingredientsDisplayerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		scrollingPanel = new JScrollPane(ingredientsDisplayerPanel);
		scrollingPanel.setSize(new Dimension(displayerWidth, Math.round((displayerHeight * 8)/9)));
		
		overallPanel.add(searchPanel, BorderLayout.NORTH);
		overallPanel.add(scrollingPanel, BorderLayout.CENTER);
		
	}
	
	/**
	 * Adds the search box functionality to the displayer
	 */
	private void addSearch() {
		searchPanel.add(searchText);
		searchPanel.add(searchButton);
		
		RecipesDataBase db = RecipesDataBase.getDatabaseInstance();
		
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> results = new ArrayList<String>();
				results = db.searchIngredientDatabase(searchText.getText());
				ingredientsDisplayerPanel.removeAll();
				ingredientsDisplayerPanel.revalidate();
				ingredientsDisplayerPanel.repaint();
				for (String result : results) {
					createCheckBox(result);
				}
				ingredientsDisplayerPanel.revalidate();
				
			}
			
		});
	}

	/**
	 * Creates a checkbox and adds it to the screen given the ingredient name
	 * @param ingredientName
	 */
	public void createCheckBox(String ingredientName) {
		
		JCheckBox checkBox = new JCheckBox(ingredientName);
		checkBox.setFont(new Font(spoonful.FONT, Font.PLAIN, 20));
		checkBox.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	        	        		 
	        		 if(checkBox.isSelected() && !(myList.getList().contains(ingredientName) ) ) {
	        			 myList.addToList(ingredientName);
	        		 }else {
	        			 
	        			 try {
	        				 myList.removeFromList(ingredientName);
	        			 } catch (Exception a) {
	        				 
	        			 }
	        			
	        		 }    	 
	          }
		});   
		
		ingredientsDisplayerPanel.add(checkBox);
		myList.addCheckBox(checkBox);
	}
	
	
	public JPanel getPanel() {
		return overallPanel;
	}
	
	/**
	 * Shows the ingredients in a category given the category name
	 * @param category - name
	 */
	public void showIngredients(String category) {
		IngredientsCategories categories = new IngredientsCategories();
		RecipesDataBase db = RecipesDataBase.getDatabaseInstance();
		
		if(category.equals(IngredientsCategoryPanel.FRUITS)) {
			ingredientsDisplayerPanel.removeAll();
			ingredientsDisplayerPanel.revalidate();
			ArrayList<String> ingredients = db.getDatabaseCategory(IngredientsCategoryPanel.FRUITS);			
			ingredients.sort(String::compareToIgnoreCase);
			
			for(int i = 0; i < ingredients.size(); i++) {
				String ingredient = ingredients.get(i);
				createCheckBox(ingredient);
			}
			
			ingredientsDisplayerPanel.revalidate();

		}
		else if(category.equals(IngredientsCategoryPanel.DAIRY)) {
			ingredientsDisplayerPanel.removeAll();
			ingredientsDisplayerPanel.revalidate();
			ArrayList<String> ingredients =  db.getDatabaseCategory(IngredientsCategoryPanel.DAIRY);
			ingredients.sort(String::compareToIgnoreCase);


			for(int i = 0; i < ingredients.size(); i++) {
				String ingredient = ingredients.get(i);
				createCheckBox(ingredient);
			}
			
			ingredientsDisplayerPanel.revalidate();
		}
		else if(category.equals(IngredientsCategoryPanel.VEGETABLES)) {
			ingredientsDisplayerPanel.removeAll();
			ingredientsDisplayerPanel.revalidate();
			ArrayList<String> ingredients =  db.getDatabaseCategory(IngredientsCategoryPanel.VEGETABLES);
			ingredients.sort(String::compareToIgnoreCase);


			for(int i = 0; i < ingredients.size(); i++) {
				String ingredient = ingredients.get(i);
				createCheckBox(ingredient);
			}
			
			ingredientsDisplayerPanel.revalidate();
		}
		else if(category.equals(IngredientsCategoryPanel.GRAINS)) {
			ingredientsDisplayerPanel.removeAll();
			ingredientsDisplayerPanel.revalidate();

			ArrayList<String> ingredients = db.getDatabaseCategory(IngredientsCategoryPanel.GRAINS);
			ingredients.sort(String::compareToIgnoreCase);


			for(int i = 0; i < ingredients.size(); i++) {
				String ingredient = ingredients.get(i);
				createCheckBox(ingredient);
			}
			
			ingredientsDisplayerPanel.revalidate();
		}
		else if(category.equals(IngredientsCategoryPanel.PROTEIN_WITHOUT_MEAT)) {
			ingredientsDisplayerPanel.removeAll();
			ingredientsDisplayerPanel.revalidate();

			ArrayList<String> ingredients = db.getDatabaseCategory(IngredientsCategoryPanel.PROTEIN_WITHOUT_MEAT);
			ingredients.sort(String::compareToIgnoreCase);


			for(int i = 0; i < ingredients.size(); i++) {
				String ingredient = ingredients.get(i);
				createCheckBox(ingredient);
			}
			
			ingredientsDisplayerPanel.revalidate();
		}
		else if(category.equals(IngredientsCategoryPanel.MEAT)) {
			ingredientsDisplayerPanel.removeAll();
			ingredientsDisplayerPanel.revalidate();

			ArrayList<String> ingredients = db.getDatabaseCategory(IngredientsCategoryPanel.MEAT);
			ingredients.sort(String::compareToIgnoreCase);


			for(int i = 0; i < ingredients.size(); i++) {
				String ingredient = ingredients.get(i);
				createCheckBox(ingredient);
			}
			
			ingredientsDisplayerPanel.revalidate();
		}
		else if(category.equals(IngredientsCategoryPanel.MISC)) {
			ingredientsDisplayerPanel.removeAll();
			ingredientsDisplayerPanel.revalidate();

			ArrayList<String> ingredients = db.getDatabaseCategory(IngredientsCategoryPanel.MISC);
			ingredients.sort(String::compareToIgnoreCase);


			for(int i = 0; i < ingredients.size(); i++) {
				String ingredient = ingredients.get(i);
				createCheckBox(ingredient);
			}
			
			ingredientsDisplayerPanel.revalidate();
		}
		
		
	
	}
	
}
