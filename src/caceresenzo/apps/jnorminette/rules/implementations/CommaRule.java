package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.List;
import java.util.regex.Pattern;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;
import caceresenzo.apps.jnorminette.utils.ColumnUtils;
import caceresenzo.apps.jnorminette.utils.RegexUtils;

public class CommaRule extends AbstractRule {
	
	/* Patterns */
	public static final Pattern COMA_SPACE_BEFORE_PATTERN = Pattern.compile("([ \\t\\n]*),", Pattern.MULTILINE);
	public static final Pattern COMA_SPACE_AFTER_PATTERN = Pattern.compile(",([ \\t\\n]*)", Pattern.MULTILINE);
	
	/* Constructor */
	public CommaRule() {
		super("CheckComma");
	}
	
	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		for (int index = 0; index < lines.size(); index++) {
			int lineNumber = index + 1;
			String line = lines.get(index);
			
			RegexUtils.forAllMatches(COMA_SPACE_BEFORE_PATTERN, line, (matcher) -> {
				String spaces = matcher.group(1);
				int column = ColumnUtils.computeColumn(line, matcher.start(), -spaces.length());
				
				if (spaces.length() != 0) {
					notifyError("space before coma", lineNumber, column);
				}
			});
			
			RegexUtils.forAllMatches(COMA_SPACE_AFTER_PATTERN, line, (matcher) -> {
				String spaces = matcher.group(1);
				int column = ColumnUtils.computeColumn(line, matcher.start(), 2);
				boolean end = matcher.end() == line.length();
				
				switch (spaces.length()) {
					case 0: {
						if (!end) {
							notifyError("no space after a coma", lineNumber, column);
						}
						break;
					}
					
					case 1: {
						if (spaces.charAt(0) == '\t') {
							notifyError("tab after a coma", lineNumber, column);
						}
						break;
					}
					
					default: {
						if (spaces.charAt(0) != '\n') {
							notifyError("too much whitespace after a coma", lineNumber, column);
						}
					}
				}
			});
		}
	}
	
	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.COLUMN;
	}
	
}