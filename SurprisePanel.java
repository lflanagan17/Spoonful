import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * adds a panel to the GUI when surprise button is selected
 * @author lucyflanagan
 *
 */
public class SurprisePanel {
	
	private Spoonful spoonful;
	
	private JPanel entirePanel;
	private JPanel surprisePanel;
	private JPanel noDietPanel;
	private JPanel vegPanel;
	private JPanel noDairyPanel;
	
	private JLabel noDietLabel;
	private JLabel vegLabel;
	private JLabel noDairyLabel;
	
	private ImageIcon noDietImage;
	private ImageIcon vegImage;
	private ImageIcon noDairyImage;
	
	private JButton noDietButton; 
	private JButton vegButton;
	private JButton noDairyButton; 
	private SurpriseStrategy strategy; 
	private JButton backButton;
	
	public SurprisePanel(Spoonful spoonful) {
		
		this.spoonful = spoonful;
		
		entirePanel = new JPanel();
		entirePanel.setLayout(new BoxLayout(entirePanel, BoxLayout.Y_AXIS));
	
		surprisePanel = new JPanel();
		noDietPanel = new JPanel();
		vegPanel = new JPanel();
		noDairyPanel = new JPanel();
		
		noDietButton = new JButton("<html>No Dietary<br />Restrictions</html>"); 
		vegButton = new JButton("Vegetarian"); 
		noDairyButton = new JButton("Dairy Free"); 
		backButton = new JButton("Back");
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setFont(new Font(Spoonful.FONT, Font.PLAIN, 23));
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spoonful.gotToHomeFromSurprise();
				
				
			}
		});
		
		display();
	}

 
	/**
	 * Configures and adds all components of the surprise panel
	 */
	private void display() {
		
		//creates diet button and links it with Anything strategy
		Font myFont = new Font(Spoonful.FONT, Font.PLAIN, 40);
		noDietButton.setFont(myFont);
		noDietButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				strategy = new AnythingStrategy(); 
				Recipe recipe = strategy.chooseSurpriseRecipe(); 
				spoonful.goToRecipePageFromSurprise(recipe);
			} 
		});
		
	//creates vegetarian button and links it with VegetarianStrategy
		vegButton.setFont(myFont);
		vegButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				strategy = new VegetarianStrategy(); 
				Recipe recipe = strategy.chooseSurpriseRecipe(); 
				spoonful.goToRecipePageFromSurprise(recipe);
			} 
		});

		//creates no dairy button and links it with NoDairyStrategy
		noDairyButton.setFont(myFont);
		noDairyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				strategy = new NoDairyStrategy(); 
				Recipe recipe = strategy.chooseSurpriseRecipe(); 
				spoonful.goToRecipePageFromSurprise(recipe);
				
			} 
		});

		noDietButton.setBackground(Color.ORANGE);
		noDietButton.setOpaque(true);
		vegButton.setBackground(new Color(124,179,66));
		vegButton.setOpaque(true);  
		noDairyButton.setBackground(Color.WHITE);
		noDairyButton.setOpaque(true);
		
		setImage("nodietaryrestrictions.png", noDietImage, noDietLabel, noDietPanel);
		setImage("vegetarian.png", vegImage, vegLabel, vegPanel);
		setImage("dairy-free.png", noDairyImage, noDairyLabel, noDairyPanel);
		
		GridLayout layout = new GridLayout(0,3);
		surprisePanel.setLayout(layout);

		
		surprisePanel.add(noDietPanel);
		surprisePanel.add(vegPanel);
		surprisePanel.add(noDairyPanel);
		
		surprisePanel.add(noDietButton);

		surprisePanel.add(vegButton);
		
		
		entirePanel.add(backButton);
		entirePanel.add(surprisePanel);
		surprisePanel.add(noDairyButton); 
	}
	
	/**
	 * Sets the images in the grid
	 * @param fileName
	 * @param image
	 * @param label
	 * @param panel
	 */
	private void setImage(String fileName, ImageIcon image, JLabel label, JPanel panel) {
		image = new ImageIcon(fileName);
		label = new JLabel(image);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		panel.add(label);
	}
	
	public JPanel getPanel() {
		return entirePanel;
	}
	
}
