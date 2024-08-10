

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Panel that displays the welcome screen
 * @author lucyflanagan
 *
 */
public class WelcomePage {
	
	private JPanel mainPanel; 
	private JPanel buttonPanel; 
	private int mainPanelWidth;
	private int mainPanelHeight;
	private int buttonPanelWidth;
	private int buttonPanelHeight;
	private JButton surpriseButton; 
	private JButton enterButton; 
	private JLabel welcome; 
	private ImageIcon image; 
	private JLabel labelWithImage; 
	
	/**
	 * GUI setup of panel and adding buttons to the screen along with functionality
	 * @param spoonful
	 */
	public WelcomePage(Spoonful spoonful) {
		//main panel will have 3 rows for the label, image, and buttons 
		mainPanelWidth = spoonful.getFrameWidth(); 
		mainPanelHeight = spoonful.getFrameHeight();
		mainPanel = new JPanel(); 
		mainPanel.setSize(new Dimension(mainPanelWidth, mainPanelHeight));
		GridLayout layout = new GridLayout(3,0, 0, 25); 
		mainPanel.setLayout(layout); 
		
		//add the welcome label to the panel 
		welcome = new JLabel("Welcome to Spoonful!"); 
		welcome.setFont(new Font(spoonful.FONT, Font.PLAIN, 40));
		welcome.setHorizontalAlignment(JLabel.CENTER);
//	    welcome.setVerticalAlignment(JLabel.CENTER); 
		mainPanel.add(welcome); 
		
		//add the image to the next column in the main panel
		image = new ImageIcon("chef.jpg");
		labelWithImage = new JLabel(image); 
		mainPanel.add(labelWithImage);  
		
		//create the button panel
		buttonPanel = new JPanel(); 
		buttonPanelWidth=spoonful.getFrameWidth();
		buttonPanelHeight=spoonful.getFrameHeight()/3;
		buttonPanel.setSize(new Dimension(buttonPanelWidth, buttonPanelHeight));
		buttonPanel.setBackground(Color.WHITE);
	
		//create buttons and add action listeners to them 
		enterButton = new JButton("Enter What I Have"); 
		enterButton.setOpaque(true); 
		enterButton.setBackground(spoonful.THEME_COLOR);
		enterButton.setFont(new Font(spoonful.FONT, Font.PLAIN, 40));
		
		enterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spoonful.goToPickIngredients();
				
				
			}
		});
		surpriseButton = new JButton("Surpise Me"); 
		surpriseButton.setOpaque(true); 
		surpriseButton.setBackground(spoonful.THEME_COLOR);
		surpriseButton.setFont(new Font(spoonful.FONT, Font.PLAIN, 40));
		surpriseButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spoonful.goToSurpriseButtons();
				
				
			} 
		});
		
		//center the buttons in the panel
		
		//add buttons to the button panel
		buttonPanel.add(enterButton);  
		buttonPanel.add(surpriseButton); 													
		
		//add button panel to mainPanel 
		mainPanel.add(buttonPanel); 
		
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}   
	
	public JPanel getWelcomePanel() {
		return mainPanel; 
		
	}
	
}
