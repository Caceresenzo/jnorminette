package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;
import caceresenzo.apps.jnorminette.utils.LineUtils;

public class NoArgFunctionRule extends AbstractRule {

	/* Pattern */
	public static final Pattern PROTOTYPE_ARGUMENTS_PATTERN = Pattern.compile("^(?:static )*(?:[\\w\\d_]+)(?:[ \\t\\n]+)(?:[*]*)([\\w\\d_]+)\\(([\\n\\t \\w\\d_*,()]*?)\\)", Pattern.MULTILINE);
	
	/* Constructor */
	public NoArgFunctionRule() {
		super("CheckNoArgFunction");
	}
	
	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		Matcher matcher = PROTOTYPE_ARGUMENTS_PATTERN.matcher(rawLines);
		
		while (matcher.find()) {
			String function = matcher.group(1);
			String arguments = matcher.group(2);
			
			if (arguments.trim().isEmpty()) {
				int start = matcher.start();
				int lineNumber = LineUtils.computeLineNumberByOffset(rawLines, start);
				
				notifyError(String.format("missing void in function %s", function), lineNumber);
			}
		}
	}

	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.LINE;
	}
	
}