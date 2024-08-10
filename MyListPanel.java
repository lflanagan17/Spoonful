

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;


/**
 * The panel component that shows what ingredients have been selected
 * @author tylerchang, fremont
 *
 */
public class MyListPanel extends JComponent {
	
	private JPanel myListPanel;
	private int myListWidth;
	private int myListHeight;
	private JButton resetListButton;
	private JLabel listTitle;
	private HashMap<String, JLabel> listOfListItems = new HashMap<String, JLabel>();
	private boolean showButtons;
	private ArrayList<JLabel> allLabels;
	private ArrayList<JCheckBox> checkBoxes;

	
	/**
	 * GUI setup and button functionalities
	 * @param spoonful
	 * @param showButtons
	 */
	public MyListPanel(Spoonful spoonful, boolean showButtons) {
		
		checkBoxes = new ArrayList<JCheckBox>();
		
		  
		setPanelWidth(spoonful.getFrameWidth() / 3);
		setPanelHeight(spoonful.getFrameHeight());
		
		this.showButtons = showButtons;
		
		listTitle = new JLabel("My List:");
		listTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		listTitle.setFont(new Font(Spoonful.FONT, Font.BOLD, 28));
		
		if(showButtons) {
			
			resetListButton = new JButton("Reset List");
			resetListButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 20));
			resetListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			resetListButton.addActionListener(new ActionListener() {
				  
				@Override
				public void actionPerformed(ActionEvent e) {
					
					for ( JLabel label: listOfListItems.values() ) {
						label.setVisible(false);
					}
					
					for (JCheckBox checkBox : checkBoxes) {
						checkBox.setSelected(false);
					}
					
					listOfListItems.clear();
					myListPanel.revalidate();
					 
				}
			});
		}
		allLabels = new ArrayList<>();
		
		myListPanel = new JPanel();
		myListPanel.setLayout(new BoxLayout(myListPanel, BoxLayout.Y_AXIS));
		myListPanel.add(listTitle);
		
		if(showButtons) {
			myListPanel.add(resetListButton);
		}
		
		myListPanel.setSize(new Dimension(myListWidth, myListHeight));
		myListPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
	}

	
	public JPanel getPanel() {
		return myListPanel;
	}
	
	/**
	 * Gets the list of ingredients in an ArrayList
	 * @return
	 */
	public ArrayList<String> getList(){
		ArrayList<String> ingredientsList = new ArrayList<String>(listOfListItems.keySet());
		return ingredientsList;
	}
	
	/**
	 * Clears the list and removes all the label components
	 */
	public void clearList() {
		listOfListItems.clear();
		for(JLabel label : allLabels) {
			myListPanel.remove(label);
		}
	}
	
	public void setPanelWidth(int width) {
		this.myListWidth = width;
	}
	
	public void setPanelHeight(int height) {
		this.myListHeight = height;
	}
	
	public void setShowButtons(boolean status) {
		showButtons = status;
		myListPanel.revalidate();
	}
	
	/**
	 * Adds an ingredient to the list and the screen
	 * @param name of ingredient
	 */
	public void addToList(String name) {
		JLabel ingredient = new JLabel(name);
		ingredient.setFont(new Font(Spoonful.FONT, Font.PLAIN, 20));
		ingredient.setAlignmentX(Component.CENTER_ALIGNMENT);
		ingredient.setVisible(true);
		listOfListItems.put(name,ingredient);
		myListPanel.add(ingredient);
		allLabels.add(ingredient);
		myListPanel.revalidate();
	}
	
	/**
	 * Removes an ingredient from the list and the screen
	 * @param name
	 */
	public void removeFromList(String name) {
		JLabel removedLabel = listOfListItems.get(name);
		listOfListItems.remove(name); 
		removedLabel.setVisible(false);
	}
	
	/**
	 * Gets the list of items
	 * @return hashmap of selected items 
	 */
	public HashMap<String, JLabel> getListOfItems() {
		return listOfListItems; 
	}
	
	/**
	 * Adds a checkbox to the list of checkboxes
	 * @param checkBox
	 */
	public void addCheckBox(JCheckBox checkBox) {
		checkBoxes.add(checkBox);
	}
}
