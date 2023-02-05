import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class TemplateWindow  extends JFrame implements ActionListener{
	
	//Labels
	JLabel TemplateLabel; 
	JLabel ArtLabel;
	//Combo Boxes
	JComboBox<String> TemplateCBox;	
	JComboBox<String> ArtCBox;	
	
	//Format Window						// Calls Page
	public TemplateWindow(JTextArea Page){

		//Window sizing and formatting
		this.setLayout(new FlowLayout()); //sets layout of window
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Add Template");
		this.setLocationRelativeTo(null); //Opens in centre of screen
		

		//Template Label
		TemplateLabel = new JLabel("Templates:");
			TemplateLabel.setPreferredSize(new Dimension(100,30));
			
		//Template Label
		ArtLabel = new JLabel("ASCII Art:");
			ArtLabel.setPreferredSize(new Dimension(100,30));
				
			//Template Combo Box
				//creates array of templates
			String[] Templates = {"Letter","The Raven","OOP WordSearch"};				
			TemplateCBox = new JComboBox<String>(Templates);				
				TemplateCBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent arg0) {
						
						PresetLoader.Preset("Template",TemplateCBox.getSelectedItem().toString(),Page);
						
						
						}});
				
			
					
				//Template Combo Box
					//creates array of ascii art
				String[] ArtTemplates = {"Pig","Flower","Girl","Wolf"};				
				ArtCBox = new JComboBox<String>(ArtTemplates);				
					ArtCBox.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent arg0) {
							
							PresetLoader.Preset("Art",ArtCBox.getSelectedItem().toString(),Page);
							
							
							}});
				
		this.add(TemplateLabel);
		this.add(TemplateCBox);
		this.add(ArtLabel);
		this.add(ArtCBox);
		this.setVisible(true);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		
	}
	
	
	
}
