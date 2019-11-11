package caceresenzo.apps.jnorminette.rules;

import java.util.ArrayList;
import java.util.List;

import caceresenzo.apps.jnorminette.rules.errors.NorminetteError;

public abstract class AbstractRule implements IRule {
	
	/* Variable */
	private final String name;
	private List<NorminetteError> session;
	
	/* Constructor */
	public AbstractRule(String name) {
		this.name = name;
	}
	
	@Override
	public List<NorminetteError> process(List<String> lines, String rawLines) {
		List<NorminetteError> errors = session = new ArrayList<>();
		
		doProcess(lines, rawLines);
		
		session = null;
		return errors;
	}
	
	/**
	 * Do the process of applying the rule checking.<br>
	 * To notify an error, call {@link #notifyError(String, int, int) notifyError(message, line (if any), column (if any))}.
	 * 
	 * @param lines
	 *            {@link List} of lines to work one-by-one.
	 * @param rawLines
	 *            A {@link String} containing all lines.
	 */
	protected abstract void doProcess(List<String> lines, String rawLines);
	
	/**
	 * Notify an error with only a message.
	 * 
	 * @param message
	 *            Message of the error.
	 * @see #notifyError(String, int) Notify an error with a message and a line.
	 * @see #notifyError(String, int, int) Notify an error with a message, a line and a column.
	 * @throws IllegalStateException
	 *             If there are no session currently being done.
	 */
	protected void notifyError(String message) {
		notifyError(message, -1);
	}
	
	/**
	 * Notify an error with a message and a line number.
	 * 
	 * @param message
	 *            Message of the error.
	 * @param line
	 *            Line number where the error is.
	 * @see #notifyError(String) Notify an error with only a message.
	 * @see #notifyError(String, int, int) Notify an error with a message, a line and a column.
	 * @throws IllegalStateException
	 *             If there are no session currently being done.
	 */
	protected void notifyError(String message, int line) {
		notifyError(message, line, -1);
	}
	
	/**
	 * Notify an error with a message, a line number and a column.
	 * 
	 * @param message
	 *            Message of the error.
	 * @param line
	 *            Line number where the error is.
	 * @param column
	 *            Column where the error begin.
	 * @see #notifyError(String) Notify an error with only a message.
	 * @see #notifyError(String, int) Notify an error with a message and a line.
	 * @throws IllegalStateException
	 *             If there are no session currently being done.
	 */
	protected void notifyError(String message, int line, int column) {
		if (session == null) {
			throw new IllegalStateException("Cannot notify any error if no session is currently being done.");
		}
		
		session.add(new NorminetteError(this, message, line, column));
	}
	
	@Override
	public String getName() {
		return name;
	}
	
}