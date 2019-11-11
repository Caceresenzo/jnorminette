package caceresenzo.apps.jnorminette.rules.errors;

public interface INorminetteErrorFormatter {
	
	/**
	 * Format a {@link NorminetteError}.
	 * 
	 * @param norminetteError
	 *            Source {@link NorminetteError} instance to format.
	 * @return A formatted representation.
	 */
	public String format(NorminetteError norminetteError);
	
}