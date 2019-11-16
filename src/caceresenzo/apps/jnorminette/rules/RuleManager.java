package caceresenzo.apps.jnorminette.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import caceresenzo.apps.jnorminette.rules.errors.NorminetteError;
import caceresenzo.apps.jnorminette.rules.implementations.AlignementRule;
import caceresenzo.apps.jnorminette.rules.implementations.ColumnLengthRule;
import caceresenzo.apps.jnorminette.rules.implementations.CommaRule;
import caceresenzo.apps.jnorminette.rules.implementations.MultipleAssignationRule;
import caceresenzo.apps.jnorminette.rules.implementations.NoArgFunctionRule;
import caceresenzo.apps.jnorminette.rules.implementations.OneInstructionPerLineRule;
import caceresenzo.apps.jnorminette.rules.implementations.TopCommentHeaderRule;

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
	
	/** Initialize the {@link RuleManager}. */
	private void initialize() {
		registerRule(new AlignementRule());
		registerRule(new ColumnLengthRule());
		registerRule(new CommaRule());
		registerRule(new MultipleAssignationRule());
		registerRule(new NoArgFunctionRule());
		registerRule(new OneInstructionPerLineRule());
		registerRule(new TopCommentHeaderRule());
	}
	
	/**
	 * Register a {@link IRule rule}.
	 * 
	 * @param rule {@link IRule Rule} to register.
	 * @return Weather or not the rule registred replaced another rule with the same name.
	 */
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