package src;


// Import Resources
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;






/**
 * Encrypts and Decrypts the specified text using the user's seed.
 * Works similar to a Caesar cipher, except the key changes for each letter.
 * 
 * @author RichardFlanagan - A00193644
 * 
 * @version v0.1: 22-03-2014
 */
public class EncryptionHandler {

	
	/**
	 * Encrypts the file.
	 * 
	 * @param s - The string that holds the text to process.
	 * @param seed - The user specified seed to be used in encryption.
	 * @return The encrypted string.
	 * @throws FileNotFoundException 
	 */
	public static String encrypt(String s, int seed) throws FileNotFoundException{
		Random r = new Random(seed);

		String origText = s;
		String encText = "";
		String toFile = "";
		
		for (int i = 0; i < origText.length(); i++){
			String t = origText.substring(i, i+1);
			if (t.matches("[A-Za-z0-9]")){
				toFile += true;
				char c = t.charAt(0);
				int x = r.nextInt((4 - 1) + 1) + 1;
				c += x;
				t = "" + c;
			}else{
				toFile += false;
			} toFile += " ";
			encText += t;
		}
		
		
		PrintWriter out = new PrintWriter(new File("EncryptionKey.bte"));
		out.print(toFile);
		out.close();
		
		return encText;
	}
	
	
	
	
	/**
	 * Decrypts the file.
	 * 
	 * @param s - The string that holds the text to process.
	 * @param seed - The user specified seed which was used in encryption.
	 * @return The decrypted string.
	 * @throws FileNotFoundException 
	 */
	public static String decrypt(String s, int seed) throws FileNotFoundException{
		
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BTE", "bte");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Choose the EncryptionKey.bte file:");
		int rval = fileChooser.showOpenDialog(null);
		
		String fileName = "EncryptionKey.bte";
		
		if(rval == JFileChooser.APPROVE_OPTION) {
			fileName = fileChooser.getSelectedFile().getName();
		}
		
		
		Random r = new Random(seed);
		Scanner in = new Scanner(new File(fileName));
		
		String origText = s;
		String encText = "";
		int x = r.nextInt((4 - 1) + 1) + 1;
		for (int i = 0; i < origText.length(); i++){
			
			String t = origText.substring(i, i+1);
			char c = t.charAt(0);
			c -= x;
			t = "" + c;
			
			if (in.nextBoolean() == true){
				encText += t;
				x = r.nextInt((4 - 1) + 1) + 1;
			}
			else{
				c += x;
				t = "" + c;
				encText += t;
			}
		}

		in.close();
		return encText;
	}

	
}