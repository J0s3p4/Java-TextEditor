import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;



public class PresetLoader{


	
										//Type			//Name
		public static void Preset(String PresetType, String Name, JTextArea Page) {
			
			String FileName = Name +" "+ PresetType;
			
										//Loads presets from directory 
			File TextFile = new File("src/Templates/" + FileName);

			try {
				// String
				String Line = "", NextLine = ""; 

				// File reader
				FileReader FReader = new FileReader(TextFile);

				// Buffered reader
				BufferedReader BReader = new BufferedReader(FReader);

				// Initialize NextLine
				NextLine = BReader.readLine();

				// Take the input from the file
				while ((Line = BReader.readLine()) != null) {
					NextLine = NextLine + "\n" + Line;
				}

				// Set the text
				Page.setText(NextLine);
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(Page, evt.getMessage());
			}
			
			
		}
		
	
			
			
		}
	
	
		
	

