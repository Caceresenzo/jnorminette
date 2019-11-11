package caceresenzo.apps.jnorminette.rules;

import java.util.List;

import caceresenzo.apps.jnorminette.rules.errors.NorminetteError;

public interface IRule {
	
	/**
	 * Check the rule on the provided lines.
	 * 
	 * @param lines
	 *            {@link List} of lines to work one-by-one.
	 * @param rawLines
	 *            A {@link String} containing all lines.
	 * @return A {@link List} of found {@link NorminetteError error}.
	 */
	public List<NorminetteError> process(List<String> lines, String rawLines);
	
	/** @return {@link IRule Rule}'s name. */
	public String getName();
	
	/** @return {@link IRule Rule}'s {@link RuleSpecification specification}, or in other words, where does the rule applies. */
	public RuleSpecification getRuleSpecification();
	
}