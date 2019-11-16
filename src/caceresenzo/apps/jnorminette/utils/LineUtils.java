package caceresenzo.apps.jnorminette.utils;

public class LineUtils {
	
	public static final int computeLineNumberByOffset(String allLines, int offset) {
		int lineNumber = 1;
		
		for (int index = 0; index < allLines.length(); index++) {
			if (allLines.charAt(index) == '\n') {
				lineNumber++;
			}
			
			if (index == offset) {
				break;
			}
		}
		
		return lineNumber;
	}
	
}