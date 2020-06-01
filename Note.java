import java.util.Arrays;
import java.util.List;

public class Note {
	private final String depiction;

	private final int tick;
	private final char type;
	private final int fret;
	private final int length;

	private final List<String> NOTE_DEPICTIONS = Arrays.asList("Green", "Red", "Yellow", "Blue", "Orange",
															   "Forced", "Tap", "Open");

	public Note(String chartEntry) {
		if (validate(chartEntry)) {
			depiction = chartEntry;
		} else {
			throw new InvalidChartException(String.format("Incorrect note format %s", chartEntry));
		}

		final String TICK_PATTERN = "[0-9]+(?==[NS][0-7][0-9]+)";
		final String TYPE_PATTERN = "(?<=[0-9]=)[NS](?=[0-7][0-9]+)";
		final String FRET_PATTERN = "(?<=[0-9]=[NS])[0-7](?=[0-9]+)";
		final String LENGTH_PATTERN = "(?<=[0-9]=[NS][0-7])[0-9]+";

		tick = Integer.parseInt(Finder.getFirstMatch(TICK_PATTERN, chartEntry));
		type = Finder.getFirstMatch(TYPE_PATTERN, chartEntry).charAt(0);
		fret = Integer.parseInt(Finder.getFirstMatch(FRET_PATTERN, chartEntry));
		length = Integer.parseInt(Finder.getFirstMatch(LENGTH_PATTERN, chartEntry));
	}

	public static boolean validate(String chartEntry) {
		// No pun intended
		final String NOTE_PATTERN = "[0-9]+=[NS][0-7][0-9]+";

		chartEntry = chartEntry.replaceAll("\\s", "");
		int matchCount = Finder.getMatches(NOTE_PATTERN, chartEntry).size();

		return matchCount == 1;
	}

	@Override
	public String toString() {
		return depiction;
	}

	public int getTick() {
		return tick;
	}

	public char getType() {
		return type;
	}

	public int getLength() {
		return length;
	}

	public int getFret() {
		return fret;
	}

	public String getFretDepiction() {
		return NOTE_DEPICTIONS.get(fret);
	}
}
