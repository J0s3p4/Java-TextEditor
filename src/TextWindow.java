import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Dimension;

public class TextWindow extends JFrame implements ActionListener{

	JSpinner FontSizeSpinner; //Font spinner
	JLabel FontSizeLabel; //Spinner Label
	JButton FontColourButton; //ColourButton
	JButton BackGColourButton; //ColourButton
	JComboBox<String> FontCBox;
	
	
	//Format Window						// Calls Page
	public TextWindow(JTextArea page){
		//Window sizing and formatting
		this.setLayout(new FlowLayout()); //sets layout of window
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Text Format");
		this.setLocationRelativeTo(null); //Opens in centre of screen
		
		//Font Size Spinner
		FontSizeSpinner = new JSpinner();
			FontSizeSpinner.setPreferredSize(new Dimension(100,30));
			//FontSizeSpinner.setBounds(10,10,10,10);
			FontSizeSpinner.setValue(page.getFont().getSize()); //gets font size from page
			FontSizeSpinner.addChangeListener(new ChangeListener() {		
				@Override
				public void stateChanged(ChangeEvent e) {
					//Sets font size to font size on the font spinner
					page.setFont(new Font(page.getFont().getFamily(),Font.PLAIN,(int) FontSizeSpinner.getValue()));				
					}			
				});
		//FontSize Label
		FontSizeLabel = new JLabel("Font Size:");
			FontSizeLabel.setPreferredSize(new Dimension(100,30));
			
		//FontColorButton
		FontColourButton = new JButton("Text Colour");
			FontColourButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
						//Opens JColorChooser
						JColorChooser ColourWindow = new JColorChooser();
						
						Color Colour = ColourWindow.showDialog(null, "Text Colour", Color.black);
						
						page.setForeground(Colour);
					}});
			
			//BackGroundColorButton
			BackGColourButton = new JButton("Page Colour");
				BackGColourButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
							//Opens JColorChooser
							JColorChooser ColourWindow = new JColorChooser();
							
							Color Colour = ColourWindow.showDialog(null, "Page Colour", Color.white);
							
							page.setBackground(Colour);
						}});	
				
			//Font Combo Box
				//creates array of Fonts
			String[] Fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();				
			FontCBox = new JComboBox<String>(Fonts);				
			FontCBox.setSelectedItem(page.getFont());
				FontCBox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent arg0) {
						
							page.setFont(new Font((String) FontCBox.getSelectedItem(),Font.PLAIN,page.getFont().getSize()));
							System.out.println(page.getFont());
						//Does not change font on page?? but says it is selected font??
						}});
				
		this.add(FontSizeLabel);
		this.add(FontSizeSpinner);
		this.add(FontColourButton);
		this.add(BackGColourButton);
		this.add(FontCBox);
		this.setVisible(true);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
	
	
	
}
