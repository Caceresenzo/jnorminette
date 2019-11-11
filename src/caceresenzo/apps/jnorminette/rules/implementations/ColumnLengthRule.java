package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.List;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;
import caceresenzo.apps.jnorminette.utils.ColumnUtils;

public class ColumnLengthRule extends AbstractRule {
	
	/* Constants */
	public static final int MAX_COLUMN_LENGTH = 80;
	
	/* Constructor */
	public ColumnLengthRule() {
		super("CheckColumnLength");
	}
	
	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		for (int index = 0; index < lines.size(); index++) {
			int lineNumber = index + 1;
			String line = lines.get(index);
			int columnLength = ColumnUtils.computeLength(line, Integer.MAX_VALUE, -1);
			
			if (columnLength > MAX_COLUMN_LENGTH) {
				notifyError("line is " + columnLength + " characters long", lineNumber);
			}
		}
	}

	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.LINE;
	}
	
}