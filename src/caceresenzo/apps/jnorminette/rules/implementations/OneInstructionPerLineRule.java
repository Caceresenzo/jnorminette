package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;

public class OneInstructionPerLineRule extends AbstractRule {
	
	/* Pattern */
	public static final Pattern SEMICOLON_AFTER_PATTERN = Pattern.compile(";(.*?)$", Pattern.MULTILINE);
	
	/* Constructor */
	public OneInstructionPerLineRule() {
		super("CheckOneInstructionPerLine");
	}
	
	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		for (int index = 0; index < lines.size(); index++) {
			int lineNumber = index + 1;
			String line = lines.get(index);
			
			Matcher matcher = SEMICOLON_AFTER_PATTERN.matcher(line);
			
			if (matcher.find()) {
				String afterColon = matcher.group(1);
				
				if (afterColon.trim().length() != 0) {
					String[] split;
					int instructionCounts = 1;

					while ((split = afterColon.split(";", 2)).length == 2) {
						instructionCounts++;
						afterColon = split[1];
					}
					
					if (instructionCounts > 1) {
						notifyError(String.format("%s instructions", instructionCounts), lineNumber);
					}
				}
			}
		}
	}
	
	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.LINE;
	}
	
}