package caceresenzo.apps.jnorminette.rules;

import java.util.List;

import caceresenzo.apps.jnorminette.rules.errors.NorminetteError;

public interface IRule {
	
	public List<NorminetteError> process(List<String> lines, String rawLines);
	
	public String getName();
	
	public RuleSpecification getRuleSpecification();
	
}