package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;

public class ReturnParenthesesRule extends AbstractRule {
	
	/* Patterns */
	public static final Pattern RETURN_PATTERN = Pattern.compile("^[\\s\\t]+return(.*?);");
	
	/* Constructor */
	public ReturnParenthesesRule() {
		super("CheckReturnParentheses");
	}
	
	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		for (int index = 0; index < lines.size(); index++) {
			int lineNumber = index + 1;
			String line = lines.get(index);
			
			Matcher matcher = RETURN_PATTERN.matcher(line);
			
			if (matcher.find()) {
				String returnToken = matcher.group(1);
				String token = returnToken.trim();
				
				if (token.length() == 0) {
					continue;
				}
				
				if (!token.startsWith("(") || !token.endsWith(")")) {
					notifyError("missing parentheses in return statement", lineNumber);
				}
			}
		}
	}
	
	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.LINE;
	}
	
}