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
	
	protected abstract void doProcess(List<String> lines, String rawLines);
	
	protected void notifyError(String message) {
		notifyError(message, -1);
	}
	
	protected void notifyError(String message, int line) {
		notifyError(message, line, -1);
	}
	
	protected void notifyError(String message, int line, int column) {
		checkSession();
		
		session.add(new NorminetteError(this, message, line, column));
	}
	
	private void checkSession() {
		if (session == null) {
			throw new IllegalStateException("Cannot notify any error if no session is currently being done.");
		}
	}
	
	@Override
	public String getName() {
		return name;
	}
	
}