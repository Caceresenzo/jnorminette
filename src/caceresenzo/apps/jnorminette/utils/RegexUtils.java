package caceresenzo.apps.jnorminette.utils;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	public static final void forAllMatches(Pattern pattern, String source, Consumer<Matcher> consumer) {
		Matcher matcher = pattern.matcher(source);
		
		while (matcher.find()) {
			consumer.accept(matcher);
		}
	}
	
}