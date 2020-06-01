import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
	public static List<String> getMatches(String patternText, String textToCheck) {
		Matcher matcher = Pattern.compile(patternText).matcher(textToCheck);
		List<String> matches = new ArrayList<>();
		while (matcher.find()) {
			matches.add(matcher.group(0));
		}

		return matches;
	}

	public static String getFirstMatch(String patternText, String textToCheck) {
		return getMatches(patternText, textToCheck).get(0);
	}
}
