package src;


// Import Resources
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;






/**
 * Text Editor Application. Can open and edit the following files:
 * 	(.txt, .xml, .file, .java, .nfo, .html, .css)
 * Includes a file chooser function, save, new, exit, and change text style options.
 * 
 * (version v0.1: 17-03-2014) : Created Basic Version(File/Edit/Help Menu, Text) 
 * (version v0.2: 21-03-2014) : Added Icons, Color Themes, Tools
 * (version v0.3: 22-03-2014) : Updated Time&Date
 * 								Added Encryption(.bte), Key combinations, and updated Help Menu text
 * 
 * @author RichardFlanagan - A00193644
 * @version v0.3: 22-03-2014
 */
public class TextEditor extends JFrame{
	
	// Default Serial Version UID to remove warning
	private static final long serialVersionUID = 1L;
	
	
	
	
// =============(VARIABLES)=============
	// JFrame
	private final String TITLE = "BetterText Editor";
	private final int FRAME_WIDTH = 1024;
	private final int FRAME_HEIGHT = 768;
	public boolean logging = false;
	
	// Main Display
	private JTextArea display;
	private JScrollPane scrollPane;
	
	// Menu Bar
	private JMenuBar menuBar;
	JMenu fileMenu;
	JMenu editMenu;
	JMenu toolMenu;
	JMenu prefMenu;
	JMenu helpMenu;
	
	private String keyShortcuts = "<html> Enter the following commands to get the desired results: <br/>"
			+ "<br/> - New :: CTRL+N "
			+ "<br/> - Open :: CTRL+O "
			+ "<br/> - Save :: CTRL+S "
			+ "<br/> - Quit :: CTRL+Q "
			+ "</html>";
	
	private String manual = "<html> <h1>BetterText Editor Manual</h1> <br/>"
			+ "<br/> -This is a basic text editor. Supported formats: "
			+ "<br/> (.txt, .xml, .file, .java, .nfo, .html, .css) <br/>"
			+ "<br/> -Open, Save, and New functions are included, as well as "
			+ "<br/> the option to change text display size and face. "
			+ "<br/> The application also has several GUI Theme options. <br/>"
			+ "<br/> -Keyboard shortcuts are outlined in: Help->Key Shortcuts. "
			+ "<br/><br/> <h2> 2-step Encryption </h2> "
			+ "<br/> BetterText's custom encryption algorithm will encrypt your "
			+ "<br/> text and numericals based on a seed number of your choice. "
			+ "<br/> An encryption file is generated for your file, and saved as a "
			+ "<br/>  .bte file (BetterTextEncryption file). "
			+ "<br/> You need both the encryption file and the seed to decrypt. "
			+ "<br/> Without one or both, you will lose access to your data. "
			+ "<br/> <br/><h3>Step 1:</h3> Generate an encryption key which holds info on what "
			+ "<br/> characters were encrypted and what were not. "
			+ "<br/> <br/><h3>Step 2:</h3> Offset each encrypted char based on a number generated "
			+ "<br/> from the user's seed number. "
			+ "<br/> <br/>This method prevents someone from reversing the offset reliably "
			+ "<br/> or from using frequency analysis on the file. It is "
			+ "<br/> possible that a string Ki@opdrrq# decrypts to Hello World "
			+ "<br/> <br/>A test file is supplied in the root project folder "
			+ "<br/> to help with testing Encryption. (TestFile.txt) "
			+ "</html>";
	
	private String credits = "<html> Made by: <br/>"
			+ "<br/> Richard Flanagan - A00193644"
			+ "<br/> Software Development: Semester 2 Assignment"
			+ "<br/> \u00a9 2014 <br/>"
			+ "<br/> Made using eclipse, with help from:"
			+ "<br/> http://docs.oracle.com/javase/tutorial/"
			+ "<br/> <br/> Icon art by Nigel Flanagan \u00a9 2014 "
			+ "</html>";
	
	// Font Options
	final int DEFAULT_SIZE = 14;
	private JRadioButtonMenuItem mediumButton;
	
	final String SERIF = "Serif";
	final String SANS_SERIF = "SansSerif";
	final String MONOSPACE = "Monospaced";
	final String DIALOG = "Dialog";
	final String DIALOG_INPUT = "DialogInput";
	
	private int fontSize = DEFAULT_SIZE;
	private String fontFace = "SansSerif";
	private int fontStyle = Font.PLAIN;
	public Font font = new Font(fontFace, fontStyle, fontSize);
	
	
	
	
	
	
// =============(CONSTRUCTOR)=============
	
	
	/**
	 * (Constructor) 
	 */
	public TextEditor(){
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    setResizable(true);
	    setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	    setVisible(true);
	    
	    setIcon();

	    createComponents();
		
		setColorTheme(new Color(255,255,255), new Color(0,0,0), new Color(255,255,255));
	}
	
	
	
	
	
	
// =============(GUI)=============

	
	/** 
	 * (GUI) Creates the components requires to build the application.
	 */
	private void createComponents(){
		
		createMenuBar();
		createDisplay();
		
		pack();		// Sets the frame size to the preferred size of all the components
		display.requestFocus();
	}
	
	
	
	
	/**
	 * (GUI) Creates the main display area.
	 */
	private void createDisplay(){
		display = new JTextArea();
		display.setFont(font);
		display.setText("");
		display.setMargin(new Insets(10, 10, 10, 10));
		display.setLineWrap(true);
		display.setEditable(true);
		
		scrollPane = new JScrollPane(display);
		scrollPane.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension(740, 500));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	
	
	/**
	 * (GUI) Creates the top menu bar and calls menu creation functions.
	 */
	private void createMenuBar(){
		menuBar = new JMenuBar();
		
		createFileMenu();
		createEditMenu();
		createToolMenu();
		createPrefMenu();
		createHelpMenu();

		setJMenuBar(menuBar);
	}
	
	
	
	
	/**
	 * (GUI) Create the File menu on the MenuBar.
	 */
	private void createFileMenu(){
		// File
		fileMenu = new JMenu("File");
		
			// New
			class NewItemListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					FileHandler.newFile(display, logging);
				}
			}
			JMenuItem newItem = new JMenuItem("New");
			newItem.addActionListener(new NewItemListener());
			fileMenu.add(newItem);
			
			// CTRL+N = new file
			KeyStroke newKey = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
			newItem.registerKeyboardAction(new NewItemListener(), newKey, JComponent.WHEN_IN_FOCUSED_WINDOW);
			
			// Open
			class OpenItemListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					FileHandler.openFile(display, logging);
				}
			}
			JMenuItem openItem = new JMenuItem("Open");
			openItem.addActionListener(new OpenItemListener());
			fileMenu.add(openItem);
			
			// CTRL+O = open file
			KeyStroke openKey = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
			openItem.registerKeyboardAction(new OpenItemListener(), openKey, JComponent.WHEN_IN_FOCUSED_WINDOW);
		
			// Save 
			class SaveItemListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					FileHandler.saveFile(display, logging);
				}
			}
			JMenuItem saveItem = new JMenuItem("Save");
			saveItem.addActionListener(new SaveItemListener());
			fileMenu.add(saveItem);
			
			// CTRL+S = save file
			KeyStroke saveKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
			saveItem.registerKeyboardAction(new SaveItemListener(), saveKey, JComponent.WHEN_IN_FOCUSED_WINDOW);
			
			// Exit 
			class ExitItemListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					FileHandler.exit(display, logging);
				}
			}
			JMenuItem exitItem = new JMenuItem("Exit");
			exitItem.addActionListener(new ExitItemListener());
			fileMenu.add(exitItem);
			
			// CTRL+Q = exit
			KeyStroke exitKey = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
			exitItem.registerKeyboardAction(new ExitItemListener(), exitKey, JComponent.WHEN_IN_FOCUSED_WINDOW);
			
		menuBar.add(fileMenu);
	}
	
	
	
	
	/**
	 * (GUI) Create the Edit menu on the MenuBar.
	 */
	private void createEditMenu(){
		// Edit
		editMenu = new JMenu("Edit");
			
			// Set Face
			JMenu faceMenu = new JMenu("Set Face");
				ButtonGroup faceGroup = new ButtonGroup();
				// Serif
				JRadioButtonMenuItem serifButton = createFaceItem("Serif", SERIF);
				faceGroup.add(serifButton);
				faceMenu.add(serifButton);
				// SansSerif
				JRadioButtonMenuItem sansSerifButton = createFaceItem("SansSerif", SANS_SERIF);
				sansSerifButton.setSelected(true);
				faceGroup.add(sansSerifButton);
				faceMenu.add(sansSerifButton);
				// Monospaced
				JRadioButtonMenuItem monospacedButton = createFaceItem("Monospaced", MONOSPACE);
				faceGroup.add(monospacedButton);
				faceMenu.add(monospacedButton);
				// Dialog
				JRadioButtonMenuItem dialogButton = createFaceItem("Dialog", DIALOG);
				faceGroup.add(dialogButton);
				faceMenu.add(dialogButton);
				// DialogInput
				JRadioButtonMenuItem dialogInputButton = createFaceItem("DialogInput", DIALOG_INPUT);
				faceGroup.add(dialogInputButton);
				faceMenu.add(dialogInputButton);
			editMenu.add(faceMenu);
			
			
			// Set Size
			JMenu sizeMenu = new JMenu("Set Size");
				ButtonGroup sizeGroup = new ButtonGroup();
				// Smallest
				JRadioButtonMenuItem smallestButton = createSizeItem("10", 10);
				sizeGroup.add(smallestButton);
				sizeMenu.add(smallestButton);
				// Small
				JRadioButtonMenuItem smallButton = createSizeItem("12", 12);
				sizeGroup.add(smallButton);
				sizeMenu.add(smallButton);
				// Medium
				mediumButton = createSizeItem("14 (default)", DEFAULT_SIZE);
				mediumButton.setSelected(true);
				sizeGroup.add(mediumButton);
				sizeMenu.add(mediumButton);
				// Large
				JRadioButtonMenuItem largeButton = createSizeItem("16", 16);
				sizeGroup.add(largeButton);
				sizeMenu.add(largeButton);
				// Largest
				JRadioButtonMenuItem largestButton = createSizeItem("18", 18);
				sizeGroup.add(largestButton);
				sizeMenu.add(largestButton);
			editMenu.add(sizeMenu);
			
		menuBar.add(editMenu);	
	}
	
	
	
	
	/**
	 * (GUI) Creates a radio button for sizeItem, creates and assigns an ActionListener
	 * 
	 * @param name - The label to appear on the button
	 * @param size - The size of the text
	 * @return The buttonMenuItem
	 */
	private JRadioButtonMenuItem createSizeItem(String name, final int size){
		class SizeItemListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				fontSize = size;
				font = new Font(fontFace, fontStyle, fontSize);
				display.setFont(font);
			}
		}
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);
		ActionListener listener = new SizeItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	
	
	
	/**
	 * (GUI) Creates a radio button for faceItem, creates and assigns an ActionListener
	 * 
	 * @param name - The label to appear on the button
	 * @param face - The face style of the text
	 * @return The buttonMenuItem
	 */
	private JRadioButtonMenuItem createFaceItem(String name, final String face){
		class FaceItemListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				fontFace = face;
				font = new Font(fontFace, fontStyle, fontSize);
				display.setFont(font);
			}
		}
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);
		ActionListener listener = new FaceItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	
	
	
	/**
	 * (GUI) Create the Tools menu on the MenuBar.
	 */
	private void createToolMenu(){
		// Tools
		toolMenu = new JMenu("Tools");
			
			// Line Counter
			JMenuItem lineCountItem = new JMenuItem("Line Counter");
			
			class lineCountListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					JOptionPane.showMessageDialog(display,
						    "Word Count: " + ToolHandler.getLineCount(display),
						    "BetterText - Word Count",
						    JOptionPane.INFORMATION_MESSAGE,
						    new ImageIcon("res/Icon32Blue.jpg"));
				}
			}
			lineCountItem.addActionListener(new lineCountListener());
			toolMenu.add(lineCountItem);
			
			// Word Counter
			JMenuItem wordCountItem = new JMenuItem("Word Counter");
			
			class wordCountListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					JOptionPane.showMessageDialog(display,
						    "Word Count: " + ToolHandler.getWordCount(display),
						    "BetterText - Word Count",
						    JOptionPane.INFORMATION_MESSAGE,
						    new ImageIcon("res/Icon32Orange.jpg"));
				}
			}
			wordCountItem.addActionListener(new wordCountListener());
			toolMenu.add(wordCountItem);
			
			toolMenu.addSeparator();
			
			// Add Time
			JMenuItem addTimeItem = new JMenuItem("Add Time");
			
			class addTimeListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					display.insert(ToolHandler.getTime(), display.getCaretPosition());
				}
			}
			addTimeItem.addActionListener(new addTimeListener());
			toolMenu.add(addTimeItem);
			
			// Add Date
			JMenuItem addDateItem = new JMenuItem("Add Date");
			
			class addDateListener implements ActionListener{
				public void actionPerformed(ActionEvent event){ 
					display.insert(ToolHandler.getDate(), display.getCaretPosition());
				}
			}
			addDateItem.addActionListener(new addDateListener());
			toolMenu.add(addDateItem);
			
			toolMenu.addSeparator();
			
			// To Uppercase
			JMenuItem uppercaseItem = new JMenuItem("To Uppercase");
			
			class UppercaseListener implements ActionListener{
				public void actionPerformed(ActionEvent event){ 
					display.setText(ToolHandler.toUppercase(display, display.getSelectedText()));
				}
			}
			uppercaseItem.addActionListener(new UppercaseListener());
			toolMenu.add(uppercaseItem);
			
			// To Lowercase
			JMenuItem lowercaseItem = new JMenuItem("To Lowercase");
			
			class LowercaseListener implements ActionListener{
				public void actionPerformed(ActionEvent event){ 
					display.setText(ToolHandler.toLowercase(display, display.getSelectedText()));
				}
			}
			lowercaseItem.addActionListener(new LowercaseListener());
			toolMenu.add(lowercaseItem);
						
			toolMenu.addSeparator();
			
			// Encrypt
			JMenuItem encryptItem = new JMenuItem("Encrypt file");
			
			class encryptListener implements ActionListener{
				public void actionPerformed(ActionEvent event){ 
					int seed = Integer.parseInt((String)JOptionPane.showInputDialog(
		                    display,
		                    "Please choose a seed:\n",
		                    "BetterText - Encrypt File",
		                    JOptionPane.PLAIN_MESSAGE,
		                    new ImageIcon("res/Icon32Grey.jpg"),
		                    null, new Random().nextInt(1000)));
					try {
						display.setText(EncryptionHandler.encrypt(display.getText(), seed));
					} catch (FileNotFoundException e) {
						if (logging) { e.printStackTrace(); }
					}
				}
			}
			encryptItem.addActionListener(new encryptListener());
			toolMenu.add(encryptItem);
			
			// Decrypt
			JMenuItem decryptItem = new JMenuItem("Decrypt file");
			
			class decryptListener implements ActionListener{
				public void actionPerformed(ActionEvent event){ 
					int seed = Integer.parseInt((String)JOptionPane.showInputDialog(
		                    display,
		                    "What was the encryption seed?\n",
		                    "BetterText - Decrypt File",
		                    JOptionPane.PLAIN_MESSAGE,
		                    new ImageIcon("res/Icon32Grey.jpg"),
		                    null, new Random().nextInt(1000)));
					try {
						display.setText(EncryptionHandler.decrypt(display.getText(), seed));
					} catch (FileNotFoundException e) {
						if (logging) { e.printStackTrace(); }
					}
				}
			}
			decryptItem.addActionListener(new decryptListener());
			toolMenu.add(decryptItem);
				
		menuBar.add(toolMenu);
	}
	
	
	
	
	/**
	 * (GUI) Create the Preferences menu on the MenuBar
	 */
	private void createPrefMenu(){
		// Preferences
		prefMenu = new JMenu("Preferences");
		
			// Set Colour Theme
			JMenu themeMenu = new JMenu("Set Colour Theme");
			ButtonGroup themeGroup = new ButtonGroup();
			
			JRadioButtonMenuItem brightButton = createThemeItem("Bright Theme", new Color(255,255,255), new Color(0,0,0), new Color(255,255,255));
			brightButton.setSelected(true);
			themeGroup.add(brightButton);
			themeMenu.add(brightButton);
			
			JRadioButtonMenuItem darkButton = createThemeItem("Dark Theme", new Color(0,0,0), new Color(255,255,255), new Color(50,50,50));
			themeGroup.add(darkButton);
			themeMenu.add(darkButton);
			
			JRadioButtonMenuItem matrixButton = createThemeItem("Matrix Theme", new Color(0,0,0), new Color(0,255,0), new Color(0,0,0));
			themeGroup.add(matrixButton);
			themeMenu.add(matrixButton);
			
			prefMenu.add(themeMenu);
			
			// Toggle Debug Mode
			JMenu debugMenu = new JMenu("Debug Mode");
			ButtonGroup debugGroup = new ButtonGroup();
				JRadioButtonMenuItem onButton = createDebugItem("On");
				debugGroup.add(onButton);
				debugMenu.add(onButton);
				
				JRadioButtonMenuItem offButton = createDebugItem("Off");
				offButton.setSelected(true);
				debugGroup.add(offButton);
				debugMenu.add(offButton);
			prefMenu.add(debugMenu);
			
			
			// Line Wrap
			JMenuItem lineWrapItem = new JMenuItem("Line Wrap");
			
			class lineWrapListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					ToolHandler.toggleWordWrap(display);
				}
			}
			lineWrapItem.addActionListener(new lineWrapListener());
			prefMenu.add(lineWrapItem);
			
		menuBar.add(prefMenu);
	}
	
	
	
	
	/**
	 * (GUI) Creates a radio button for themeItem, creates and assigns an ActionListener
	 * 
	 * @param name - The label to appear on the button.
	 * @param back - The theme's background color.
	 * @param front - The theme's foreground color.
	 * @param displayColor - The theme's JTextArea Background color.
	 * @return The buttonMenuItem
	 */
	private JRadioButtonMenuItem createThemeItem(String name, final Color back, final Color front, final Color displayColor){
		class ThemeItemListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				setColorTheme(back, front, displayColor);
			}
		}
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);
		item.addActionListener(new ThemeItemListener());
		return item;
	}
	
	
	
	
	/**
	 * (GUI) Creates a radio button for debugItem, creates and assigns an ActionListener
	 * 
	 * @param name - The label to appear on the button..
	 * @return The buttonMenuItem
	 */
	private JRadioButtonMenuItem createDebugItem(String name){
		class DebugItemListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				logging = !logging;
			}
		}
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);
		item.addActionListener(new DebugItemListener());
		return item;
	}
	
	
	
	
	/**
	 * (GUI) Create the Help menu on the MenuBar.
	 */
	private void createHelpMenu(){
		// Help
		helpMenu = new JMenu("Help");
			// Show Key Shortcuts
			JMenuItem showKeyShortcuts = new JMenuItem("Key Shortcuts");
			
			class showKeyShortcutsListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					JOptionPane.showMessageDialog(null, keyShortcuts, "Key Shortcuts ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			showKeyShortcuts.addActionListener(new showKeyShortcutsListener());
			helpMenu.add(showKeyShortcuts);
			
			// Manual
			JMenuItem showManual = new JMenuItem("Manual");
			
			class ShowManualListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					JOptionPane.showMessageDialog(null, manual, "Manual ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			showManual.addActionListener(new ShowManualListener());
			helpMenu.add(showManual);
			
			// Credits
			JMenuItem showCredits = new JMenuItem("Credits");
			
			class ShowCreditsListener implements ActionListener{
				public void actionPerformed(ActionEvent event){
					JOptionPane.showMessageDialog(null, credits, "Credits ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			showCredits.addActionListener(new ShowCreditsListener());
			helpMenu.add(showCredits);
			
		menuBar.add(helpMenu);
	}
	
	
	
	
	/**
	 * (GUI) Changes the GUI to a specified color scheme.
	 */
	private void setColorTheme(Color back, Color front, Color displayColor){
		menuBar.setBackground(back);
		menuBar.setBorderPainted(false);
		
		fileMenu.setForeground(front);
		fileMenu.setBorderPainted(false);
		
		editMenu.setForeground(front);
		editMenu.setBorderPainted(false);
		
		toolMenu.setForeground(front);
		toolMenu.setBorderPainted(false);
		
		prefMenu.setForeground(front);
		prefMenu.setBorderPainted(false);
		
		helpMenu.setForeground(front);
		helpMenu.setBorderPainted(false);
		
		display.setBackground(displayColor);
		display.setForeground(front);
		display.setCaretColor(front);
	}
	
	
	
	
	/**
	 * (GUI) Sets the JFrame icons based on a random number.
	 */
	private void setIcon(){
		List<Image> icons  = new ArrayList<Image>();
		
	    int iconChoice = new Random().nextInt(6);
	    if (iconChoice == 0){
	    	icons.add(new ImageIcon("res/Icon256Purple.jpg").getImage());
		    icons.add(new ImageIcon("res/Icon32Purple.jpg").getImage());
	    }
	    else if (iconChoice == 1){
	    	icons.add(new ImageIcon("res/Icon256Green.jpg").getImage());
		    icons.add(new ImageIcon("res/Icon32Green.jpg").getImage());
	    }
	    else if (iconChoice == 2){
	    	icons.add(new ImageIcon("res/Icon256Blue.jpg").getImage());
		    icons.add(new ImageIcon("res/Icon32Blue.jpg").getImage());
	    }
	    else if (iconChoice == 3){
	    	icons.add(new ImageIcon("res/Icon256Orange.jpg").getImage());
		    icons.add(new ImageIcon("res/Icon32Orange.jpg").getImage());
	    }
	    else if (iconChoice == 4){
	    	icons.add(new ImageIcon("res/Icon256Grey.jpg").getImage());
		    icons.add(new ImageIcon("res/Icon32Grey.jpg").getImage());
	    }
	    else {
	    	icons.add(new ImageIcon("res/Icon256Red.jpg").getImage());
		    icons.add(new ImageIcon("res/Icon32Red.jpg").getImage());
	    }
	    
	    setIconImages(icons);
	}

	
}