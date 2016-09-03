package src;


// Import Resources
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;






/**
 * 
 * @author RichardFlanagan - A00193644
 * 
 * @version v0.1: 21-03-2014
 */
public class ToolHandler {

	
	/**
	 * Counts the amount of lines in the current document.
	 * 
	 * @param display - The textArea that holds the text to process.
	 * @return The amount of lines in the file.
	 */
	public static int getLineCount(JTextArea display){
		String doc = display.getText();
		Scanner in = new Scanner(doc);
		int lineCount = 0;
		
		while (in.hasNextLine()){
			in.nextLine();
			lineCount++;
		}
		
		in.close();
		return lineCount;
	}
	
	
	
	
	/**
	 * Counts the amount of words in the current document.
	 * 
	 * @param display - The textArea that holds the text to process.
	 * @return The amount of words in the file.
	 */
	public static int getWordCount(JTextArea display){
		String doc = display.getText();
		Scanner in = new Scanner(doc);
		int wordCount = 0;
		
		while (in.hasNext()){
			in.next();
			wordCount++;
		}
		
		in.close();
		return wordCount;
	}
	
	
	
	
	/**
	 * Prints the current time to the file.
	 * 
	 * @return The current Time.
	 */
	public static String getTime(){
		SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss a zzz");
		String time = ft.format(new Date());
		return time;
	}
	
	
	
	
	/**
	 * Prints the current date to the file.
	 * 
	 * @return The current Date.
	 */
	public static String getDate(){
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
		String date = ft.format(new Date());
		return date;
	}
	
	
	
	
	/**
	 * Toggles Line wrap in the JTextArea
	 * 
	 * @param display - The textArea to toggle line wrap on.
	 */
	public static void toggleWordWrap(JTextArea display){
		if (display.getLineWrap()){
			display.setLineWrap(false);
		}else{
			display.setLineWrap(true);
		}
	}
	
	
	
	
	/**
	 * Converts the selected text to uppercase.
	 * 
	 * @param display - The textArea which holds the text to process
	 * @param text - The selected text to convert.
	 * @return The converted text
	 */
	public static String toUppercase(JTextArea display, String text){
		String t = text;
		String dt = display.getText();
		if (t != null && t.length() > 0){
			t = t.toUpperCase();
			return (dt.substring(0, display.getSelectionStart())+ t + dt.substring(display.getSelectionEnd(), dt.length()));
		}
		return display.getText().toUpperCase();
	}
	
	
	
	
	/**
	 * Converts the selected text to lowercase.
	 * 
	 * @param display - The textArea which holds the text to process
	 * @param text - The selected text to convert.
	 * @return The converted text
	 */
	public static String toLowercase(JTextArea display, String text){
		String t = text;
		String dt = display.getText();
		if (t != null && t.length() > 0){
			t = t.toLowerCase();
			return (dt.substring(0, display.getSelectionStart())+ t + dt.substring(display.getSelectionEnd(), dt.length()));
		}
		return display.getText().toLowerCase();
	}
	
	
}
