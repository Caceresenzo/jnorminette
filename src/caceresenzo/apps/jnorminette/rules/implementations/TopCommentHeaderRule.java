package caceresenzo.apps.jnorminette.rules.implementations;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import caceresenzo.apps.jnorminette.rules.AbstractRule;
import caceresenzo.apps.jnorminette.rules.RuleSpecification;

public class TopCommentHeaderRule extends AbstractRule {
	
	/* Patterns */
	protected static final Pattern PATTERNS[] = {
			Pattern.compile("\\/\\* (?:\\*){74} \\*\\/"),
			Pattern.compile("\\/\\* (?: ){74} \\*\\/"),
			Pattern.compile("\\/\\* (?: ){55}(?::::      ::::::::  ) \\*\\/"),
			Pattern.compile("\\/\\* (?: ){2}([\\w\\d_.]{1,50})(?: ){1,50}(?: :\\+:      :\\+:    :\\+:  ) \\*\\/"),
			Pattern.compile("\\/\\* (?: ){51}(?:\\+:\\+ \\+:\\+         \\+:\\+    ) \\*\\/"),
			Pattern.compile("\\/\\*   (?:By: )(?:[\\w\\d]{1,42}) ([\\w\\d<>@.]{1,42})(?:          \\+#\\+  \\+:\\+       \\+#\\+       ) \\*\\/"),
			Pattern.compile("\\/\\* (?: ){47}\\+#\\+#\\+#\\+#\\+#\\+   \\+#\\+(?: ){9}  \\*\\/"),
			Pattern.compile("\\/\\*   (?:Created: )(?:[\\d]{4}\\/[\\d]{2}\\/[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2} by [\\w\\d]+)(?: ){10}(?:#\\+#    #\\+#)(?: ){12} \\*\\/"),
			Pattern.compile("\\/\\*   (?:Updated: )(?:[\\d]{4}\\/[\\d]{2}\\/[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2} by [\\w\\d]+)(?: ){9}(?:###   ########\\.fr)(?: ){6} \\*\\/"),
			Pattern.compile("\\/\\* (?: ){74} \\*\\/"),
			Pattern.compile("\\/\\* (?:\\*){74} \\*\\/")
	};
	
	/* Constructor */
	public TopCommentHeaderRule() {
		super("CheckTopCommentHeader");
	}
	
	@Override
	protected void doProcess(List<String> lines, String rawLines) {
		Matcher firstLinesMatcher = PATTERNS[0].matcher(rawLines);
		
		if (lines.size() < 11 || !firstLinesMatcher.find()) {
			notifyError("42 header missing");
			return;
		}
		
		for (int index = 0; index < 11; index++) {
			Matcher matcher = PATTERNS[index].matcher(lines.get(index));
			
			if (!matcher.find()) {
				notifyError("42 header badly formatted");
				return;
			}
		}
	}
	
	@Override
	public RuleSpecification getRuleSpecification() {
		return RuleSpecification.GLOBAL;
	}
	
}