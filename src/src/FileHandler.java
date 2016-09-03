package src;


// Import Resources
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.*;






/**
 * File menu Handler for the TextEditor object.
 * Handles opening, saving of files, exiting etc.
 * Static to allow calls without instantiating an object.
 * 
 * @author RichardFlanagan - A00193644
 * 
 * @version v0.1: 17-03-2014
 */
public class FileHandler {
	
	
	/**
	 * Clears the display and text fields, after a saving prompt.
	 * 
	 * @param display - The textArea that holds the text to save.
	 * @param logging - Check whether debug mode is active. Prints exceptions.
	 */
	public static void newFile(JTextArea display, boolean logging){
		int n = savePrompt(display);
		if (n == 0){
			saveFile(display, logging);
		}
		else if (n == 1){
			display.setText("");
		}
	}

	
	
	
	/**
	 * Save the current work to a specified file
	 * 
	 * @param display - The textArea that holds the text to save.
	 * @param logging - Check whether debug mode is active. Prints exceptions.
	 */
	public static void saveFile(JTextArea display, boolean logging) {
		JFileChooser fileChooser = new JFileChooser();
		int x = fileChooser.showSaveDialog(display);
		
		if(x == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter in = new FileWriter(fileChooser.getSelectedFile());
				in.write(display.getText());
				in.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error saving file!");
				if (logging) { e.printStackTrace(); }
			} catch (IOException e) {
				System.out.println("Error saving file!");
				if (logging) { e.printStackTrace(); }
			}
		}
	}
	
	
	
	
	/**
	 * Prompts the user to save and then opens the file.
	 * 
	 * @param display - The textArea that holds the text to save.
	 * @param logging - Check whether debug mode is active. Prints exceptions.
	 */
	public static void openFile(JTextArea display, boolean logging){
		
		if (display.getText().equals("")){
			open(display, logging);
		}
		else{
			int n = savePrompt(display);
			if (n == 0){
				saveFile(display, logging);
			}
			else if (n == 1){
				open(display, logging);
			}
		}
	}
	
	
	
	
	/**
	 * Opens an existing file.
	 * 
	 * @param display - The textArea that holds the text to save.
	 * @param logging - Check whether debug mode is active. Prints exceptions.
	 */
	private static void open(JTextArea display, boolean logging){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BTE, TXT, XML, FILE, JAVA, NFO, HTML, CSS", 
				"bte", "txt", "xml", "file", "java", "nfo", "html", "css");
		fileChooser.setFileFilter(filter);
		int x = fileChooser.showOpenDialog(display);
		
		if(x == JFileChooser.APPROVE_OPTION) {
			display.setText("");
			try {
				Scanner in = new Scanner(fileChooser.getSelectedFile());
				String t = "";
				while (in.hasNextLine()){
					t += in.nextLine() + "\r\n";
				}
				display.setText(t);
				in.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error opening file!");
				if (logging) { e.printStackTrace(); }
			}
		}
	}
	
	
	
	
	/**
	 * Prompts the user to save when exiting, then exits.
	 * 
	 * @param display - The textArea that holds the text to save.
	 * @param logging - Check whether debug mode is active. Prints exceptions.
	 */
	public static void exit(JTextArea display, boolean logging){
		
		if (display.getText().equals("")){
			System.exit(0);
		}
		else{
			int n = savePrompt(display);
			if (n == 0){
				saveFile(display, logging);
			}
			else if (n == 1){
				System.exit(0);
			}
		}
	}
	
	
	
	
	/**
	 * Prompts the user to save.
	 * 
	 * @param display - The textArea that holds the text to save.
	 * @return n - The users decision as an int.
	 */
	private static int savePrompt(JTextArea display){
		Object[] options = {"Save", "Do Not Save", "Cancel"};
		int n = JOptionPane.showOptionDialog(display, "Do you want to save first?", 
				"Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, options, options[2]);
		return n;
	}
}