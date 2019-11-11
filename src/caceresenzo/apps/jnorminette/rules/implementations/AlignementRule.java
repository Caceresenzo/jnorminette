package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;
import caceresenzo.apps.jnorminette.utils.ColumnUtils;
import caceresenzo.apps.jnorminette.utils.RegexUtils;

public class AlignementRule extends AbstractRule {
	
	/* Patterns */
	public static final Pattern PROTOTYPES_RETURN_TYPES_PATTERN = Pattern.compile("^(?:static )*(?:[\\w\\d_]+)([ \\t]+)", Pattern.MULTILINE);

	public AlignementRule() {
		super("CheckAlignement");
	}

	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		List<Integer> columns = new ArrayList<>();
		
		for (int index = 0; index < lines.size(); index++) {
			String line = lines.get(index);
			
			RegexUtils.forAllMatches(PROTOTYPES_RETURN_TYPES_PATTERN, line, (matcher) -> {
				int column = ColumnUtils.computeLength(line, matcher.end(), 0);
				
				columns.add(column);
			});
		}
		
		if (!columns.isEmpty()) {
			int firstValue = columns.get(0);
			
			for (int index = 1; index < columns.size(); index++) {
				int aValue = columns.get(index);
				
				if (firstValue != aValue) {
					notifyError("global scope bad align");
					System.out.println(columns);
					break ;
				}
			}
		}
	}

	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.GLOBAL;
	}
	
}