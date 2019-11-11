package caceresenzo.apps.jnorminette.rules.errors;

import caceresenzo.apps.jnorminette.rules.IRule;

public class NorminetteError implements INorminetteErrorFormatter {
	
	private final IRule rule;
	private final String message;
	private final int line, column;
	
	public NorminetteError(IRule rule, String message, int line, int column) {
		this.rule = rule;
		this.message = message;
		this.line = line;
		this.column = column;
	}
	
	public String format() {
		return this.format(this);
	}
	
	public String format(INorminetteErrorFormatter formatter) {
		return formatter.format(this);
	}
	
	@Override
	public String format(NorminetteError norminetteError) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Error");
		switch (rule.getRuleSpecification()) {
			case GLOBAL: {
				break;
			}
			
			case LINE: {
				stringBuilder
						.append(" (line ")
						.append(line)
						.append(")");
				break;
			}
			
			case COLUMN: {
				stringBuilder
						.append(" (line ")
						.append(line)
						.append(", col ")
						.append(column)
						.append(")");
				break;
			}
			
			default: {
				throw new IllegalStateException();
			}
		}
		
		stringBuilder
				.append(": ")
				.append(message);
		return stringBuilder.toString();
	}
	
	public IRule getRule() {
		return rule;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
}