package caceresenzo.apps.jnorminette.utils;

public class ColumnUtils {
	
	public static final int TAB_SIZE = 4;
	
	public static final int computeColumn(String line, int matcherStart, int offset) {
		int column = 0;
		
		for (int index = 0; index < matcherStart; index++) {
			char current = line.charAt(index);
			
			if (current == '\t') {
				int modulo = column % TAB_SIZE;
				
				if (modulo == 0) {
					column += TAB_SIZE;
				} else {
					column += modulo;
				}
			} else {
				column++;
			}
		}
		
		return column + offset;
	}
	
}