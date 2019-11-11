package caceresenzo.apps.jnorminette.rules.errors;

import caceresenzo.apps.jnorminette.rules.IRule;

public class NorminetteError implements INorminetteErrorFormatter {
	
	/* Variables */
	private final IRule rule;
	private final String message;
	private final int line, column;
	
	/* Constructor */
	public NorminetteError(IRule rule, String message, int line, int column) {
		this.rule = rule;
		this.message = message;
		this.line = line;
		this.column = column;
	}
	
	/** @return A formatted representation of this {@link NorminetteError}. */
	public String format() {
		return this.format(this);
	}
	
	/**
	 * Format this {@link NorminetteError} with a custom {@link INorminetteErrorFormatter formatter}.
	 * 
	 * @param formatter
	 *            Custom formatter.
	 * @return A formatted representation.
	 */
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
	
	/** @return {@link NorminetteError}'s source {@link IRule rule} that created this instance. */
	public IRule getRule() {
		return rule;
	}

	/** @return {@link NorminetteError}'s message. */
	public String getMessage() {
		return message;
	}

	/** @return {@link NorminetteError}'s error line (if any). */
	public int getLine() {
		return line;
	}

	/** @return {@link NorminetteError}'s error column (if any). */
	public int getColumn() {
		return column;
	}
	
}