package caceresenzo.apps.jnorminette.utils;

public class ColumnUtils {
	
	public static final int TAB_SIZE = 4;
	
	public static final int computeLength(String line, int until, int offset) {
		int column = 0;
		
		for (int index = 0; index < until && index < line.length(); index++) {
			char current = line.charAt(index);
			
			if (current == '\t') {
				int modulo = column % TAB_SIZE;
				
				if (modulo == 0) {
					column += TAB_SIZE;
				} else {
					column += TAB_SIZE - modulo;
				}
			} else {
				column++;
			}
		}
		
		return column + 1 + offset;
	}
	
}