# JavaTextEditor
A simple Java text editor

BetterText is a java based plaintext editor. It can edit any plaintext files including, but not limited to: .txt, .java, .html, .csv and many more.


BetterText includes basic file functions such as open, save, and new file. These operations are made easy for the user through the use of the FileChooser object in the Java library. Windows standard Keyboard Commands are also implemented for these commands: 
- Ctrl+n to start a new file.
- Ctrl+s to save the current file.
- Ctrl+o to open an existing file.
- Ctrl+q to quit.


Basic appearance options are also present in the editor. The user can change font size and face (serif, sans-serif, monospaced), as well as change the application’s overall colour theme. Included colour themes are:
- Bright theme (default).
- Dark theme.
- Matrix inspired theme.


A selection of tools is included in BetterText. These include:
- Line Counter and Word Counter – Counts the current file’s words or lines, displaying them to the user in a dialog box.
- Add Time and Add Date – Prints the time or date to the file at the user’s caret (pointer) in the standard ISO8601 format.
- To Uppercase and To Lowercase – Converts the selected text or the entire file to upper or lower case.
- Encrypt and Decrypt – Encrypts or decrypts the current file according to the supplied seed and .bte file (see encryption on next page).
- Line Wrap – Toggles line wrap. When off, text will not wrap to the next line when it passes the text window boundary.
- Debug Mode – When active, prints out all experienced exceptions stack traces. Otherwise, prints short warnings to the console.


BetterText also includes a help section to introduce users to Key Commands and Encryption, and to display the credits.

BetterText uses custom icon files which randomly choose one of six colours upon application start. 

![](http://i.imgur.com/tG2qtp0.png)

![](http://i.imgur.com/43zfZih.png)
