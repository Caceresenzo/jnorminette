package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.List;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;

public class MultipleAssignationRule extends AbstractRule {
	
	/* Constructor */
	public MultipleAssignationRule() {
		super("CheckMultipleAssignation");
	}

	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		for (int index = 0; index < lines.size(); index++) {
			int lineNumber = index + 1;
			String line = lines.get(index);
			String[] split = line.replace("==", "").split("=");
			
			if (split.length > 2) {
				notifyError("Multiple affectation", lineNumber);
			}
		}
	}
	
	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.LINE;
	}
	
}