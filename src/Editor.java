
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Editor extends JFrame implements ActionListener {

		//Text area
		JTextArea Page;
		//Scroll bar
		JScrollPane PageScrollBar;
		
		//Toolbar
		JMenuBar ToolBar = new JMenuBar();
					
		//FileMenu	
		JMenu FileMenu = new JMenu("File");
			JMenuItem SaveItem = new JMenuItem("Save");
				
			JMenuItem LoadItem = new JMenuItem("Load");
			
			JMenuItem NewFileItem = new JMenuItem("New");
			
			JMenuItem ExitItem = new JMenuItem("Exit");
				
		
		//EditMenu
		JMenu EditMenu = new JMenu("Edit");
			JMenuItem UndoItem = new JMenuItem("Undo");
					
			JMenuItem RedoItem = new JMenuItem("Redo");
			
			JMenuItem CopyItem = new JMenuItem("Copy");
	     
			JMenuItem CutItem = new JMenuItem("Cut");
			
			JMenuItem PasteItem = new JMenuItem("Paste");
			 
		
		//FormatMenu
		JMenu FormatMenu = new JMenu("Format");
			JMenuItem TextItem = new JMenuItem("Text");
			
			JMenuItem TemplatesItem = new JMenuItem("Template");
			
			
		//CloseMenu	
		JMenu CloseMenu = new JMenu("Close");
		
		
		
		
		
		//Clipboard
		Clipboard clipboard = getToolkit().getSystemClipboard();
		
		//Has Document been saved
		public Boolean Saved = false;

		//Undo manager
		UndoManager Undo = new UndoManager();
		
		
	//Editor Window
	public Editor(){
		
		//WindowDefaultLayout
		this.setSize(700,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Joseph's Text Editor");
		this.setLocationRelativeTo(null); //Opens in centre of screen
		this.setLayout(null);
		
	
		
		//Page	
		Page = new JTextArea();		
		Page.setFont(new Font("Arial",Font.PLAIN,15));
		//Set page size and spacing
			//Page properties		
		//Spacing and minimum size 
			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(Page, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(Page, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
	                .addContainerGap())
	        );
		
   

		//Add ToolBar's Options
	        //FileMenu
			ToolBar.add(FileMenu);
				FileMenu.add(SaveItem); //Save
					SaveItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png")));
						SaveItem.addActionListener(this);
				FileMenu.add(LoadItem); //Load
					LoadItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Load.png")));	
						LoadItem.addActionListener(this);
				FileMenu.add(NewFileItem); //New File
					NewFileItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New.png")));
						NewFileItem.addActionListener(e ->{					
							//if not saved
							if (Saved == false) {
									//ask if they are sure they want to clear the page
								int Confirm = JOptionPane.showOptionDialog(null, "Changes haven't been saved, are you sure you want to clear the page?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
								if (Confirm == 0) {
									Page.setText("");
								}
								else {			
								}
							}
							else {							
								
							}
						});
						
				FileMenu.add(ExitItem);	//Exit
					ExitItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit.png")));
					//EXIT SAVED CHECK
						ExitItem.addActionListener(e ->{					
							//if not saved
							if (Saved == false) {
									//ask if they are sure they want to exit
								int Confirm = JOptionPane.showOptionDialog(null, "Changes haven't been saved, are you sure you want to Exit?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
								if (Confirm == 0) {
									this.dispose();	//If Yes chosen, exit
								}
								else {			
								}
							}
							else {							
							
							}
						});
						
			ToolBar.add(EditMenu); //Edit
				EditMenu.add(UndoItem); //Undo
					UndoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Undo.png")));
				EditMenu.add(RedoItem); //Redo
					RedoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Redo.png")));
				EditMenu.add(CopyItem); //Copy
			   		CopyItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Copy.png")));
			   			CopyItem.addActionListener(this);
				EditMenu.add(CutItem); //Cut
					CutItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cut.png")));
						CutItem.addActionListener(this);
				EditMenu.add(PasteItem); //Paste
					PasteItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Paste.png")));
						PasteItem.addActionListener(this);
						
						
			ToolBar.add(FormatMenu); //Format
				FormatMenu.add(TextItem); //Text
					TextItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Text.png")));
						TextItem.addActionListener(this);
				FormatMenu.add(TemplatesItem);	//Templates
					TemplatesItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Templates.png")));
					TemplatesItem.addActionListener(this);
			
					
					
					
			//Char and wordcount

			JMenu CharCountMenu = new JMenu ("Characters: "); 
				ToolBar.add(CharCountMenu); 
							
			
			JMenu WordCountMenu = new JMenu ("Words: ");	
				ToolBar.add(WordCountMenu);
				
				
				//Listener for changes made on page
			Page.addKeyListener(new KeyListener() {
				    @Override
				    public void keyPressed(KeyEvent arg0) {
				    	 //letting saved check know its had changes made
				    }
					@Override
					public void keyTyped(KeyEvent e) {		
						
					}
					@Override
					public void keyReleased(KeyEvent e) {
						//Lets save checks know changes have been made
						Saved = false;  
						
						String Text = Page.getText();	//gets text on page
						String Words[] = Text.split("\\s"); //creates an array of words on page (Split by spaces) to be counted
						
						int CharacterCount = Text.length(); //character count 
						int WordCount = Words.length; //word count
						
						CharCountMenu.setText("Characters: " + CharacterCount); //sets text of menu objects
						WordCountMenu.setText("Words: " + WordCount);
					}
				});
				
				
			//Add menubar and add page
			
			this.setJMenuBar(ToolBar);
			
			this.add(Page);
			
			this.setVisible(true);
			
			
		}
	
	//Copy
	public void Copy() {
		String CopyString = Page.getSelectedText();
		StringSelection CopySelection = new StringSelection(CopyString);
		clipboard.setContents(CopySelection, CopySelection);
	}


	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		

		
		//Format > Text
		if(e.getSource()==TextItem) {			
			TextWindow newTextWindow = new TextWindow(Page);
		}
		
		//Format > Template
				if(e.getSource()==TemplatesItem) {			
					TemplateWindow newTemplateWindow = new TemplateWindow(Page);
				}
				
				
		//creates an undo manager
		UndoManager undoManager = new UndoManager();
		
		Page.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
			    undoManager.addEdit(e.getEdit());
			}		
		});
				
		//Edit > Undo
		if(e.getSource()==UndoItem) {			
			try {
			    undoManager.undo();
			  } catch (CannotUndoException ex) {
			    // handle error
			  }
		}		
		
		//Edit > Redo
		if(e.getSource()==RedoItem) {			
			 try {
				    undoManager.redo();
				  } catch (CannotRedoException ex) {
				    // handle error
				  }
		}		
				
				
		
		
		
		
		//Edit > Copy
		if(e.getSource()==CopyItem) {			
			Copy();			//call copy function
		}
		
		//Edit > Cut
		if(e.getSource()==CutItem) {			
			Copy();
			Page.replaceRange("", Page.getSelectionStart(), Page.getSelectionEnd()); //call copy then replace selection with ""
		}
		
		//Edit > Paste
		if(e.getSource()==PasteItem) {
			//gets what is saved to clipboard and tries to paste it to where is selected 
			Transferable PasteText = clipboard.getContents(Editor.this);
			try {
				String Selection = (String) PasteText.getTransferData(DataFlavor.stringFlavor);
				Page.replaceRange(Selection, Page.getSelectionStart(), Page.getSelectionEnd());
			} catch (UnsupportedFlavorException e1) {
				// TODO Auto-generated catch block
			} catch (IOException e1) {
				// TODO Auto-generated catch block				
			}
			
		}
		
		
		//File > Save
		if(e.getSource()==SaveItem) {
			JFileChooser FileChooser = new JFileChooser() {				//https://stackoverflow.com/questions/3651494/jfilechooser-with-confirmation-dialog
			    @Override
			    public void approveSelection(){
			        File f = getSelectedFile();
			        if(f.exists() && getDialogType() == SAVE_DIALOG){
			            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
			            switch(result){
			                case JOptionPane.YES_OPTION:
			                    super.approveSelection();
			                    return;
			                case JOptionPane.NO_OPTION:
			                    return;
			                case JOptionPane.CLOSED_OPTION:
			                    return;
			                case JOptionPane.CANCEL_OPTION:
			                    cancelSelection();
			                    return;
			            }
			        }
			        super.approveSelection();
			    }        		
			};
			FileChooser.setCurrentDirectory(new File("."));
			
			int response = FileChooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File TextFile;
				PrintWriter FileOut = null;
				
				TextFile = new File(FileChooser.getSelectedFile().getAbsolutePath());			
					try {
						FileOut = new PrintWriter(TextFile);
						FileOut.println(Page.getText());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally {
						FileOut.close();
						
						Saved = true;
					}
				
			}
		}
		
		//File > Load
		if(e.getSource()==LoadItem) {

						JFileChooser FileChooser = new JFileChooser(".");

						//  show the save dialog
						int Response = FileChooser.showOpenDialog(null);

						// If the user selects a file
						if (Response == JFileChooser.APPROVE_OPTION) {
							// Set the label to the path of the selected directory
							File TextFile = new File(FileChooser.getSelectedFile().getAbsolutePath());

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
				
	}
	
	
}

