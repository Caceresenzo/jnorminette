package caceresenzo.apps.jnorminette.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import caceresenzo.apps.jnorminette.rules.errors.NorminetteError;
import caceresenzo.apps.jnorminette.rules.implementations.AlignementRule;
import caceresenzo.apps.jnorminette.rules.implementations.CommaRule;

public class RuleManager {
	
	/* Singleton */
	private static RuleManager INSTANCE;
	
	/* Rules */
	private final Map<String, IRule> rules;
	
	/* Private Constructor */
	private RuleManager() {
		this.rules = new HashMap<>();
		
		initialize();
	}
	
	private void initialize() {
		registerRule(new AlignementRule());
		registerRule(new CommaRule());
	}
	
	public boolean registerRule(IRule rule) {
		boolean replaced = rules.containsKey(rule.getName());
		
		rules.put(rule.getName(), rule);
		
		return replaced;
	}
	
	public List<NorminetteError> findErrors(List<String> lines, String rawLines) {
		List<NorminetteError> errors = new ArrayList<>();
		
		rules.forEach((key, value) -> {
			IRule rule = value;
			
			errors.addAll(rule.process(lines, rawLines));
		});
		
		return errors;
	}
	
	/** @return RuleManager's singleton instance. */
	public static final RuleManager get() {
		if (INSTANCE == null) {
			INSTANCE = new RuleManager();
		}
		
		return INSTANCE;
	}
	
}