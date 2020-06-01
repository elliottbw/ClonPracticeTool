import java.util.ArrayList;
import java.util.List;

public class Track {
	private List<Note> notes = new ArrayList<>();
	private final String difficulty;

	public Track(String rawTrack) {
		final String DIFFICULTY_PATTERN = "(?<=[)(Easy|Medium|Hard|Expert)(?=Single])";
		final String DIFFICULTY_REPLACEMENT_PATTERN = "\\[(Easy|Medium|Hard|Expert)Single](\n|\r\n)\\{";

		difficulty = Finder.getFirstMatch(DIFFICULTY_PATTERN, rawTrack);
		rawTrack = rawTrack.replaceAll(DIFFICULTY_REPLACEMENT_PATTERN, "");

		for (String line : rawTrack.split("(\n|\r\n)")) {
			notes.add(new Note(line));
		}
	}

	public String getDifficulty() {
		return difficulty;
	}

	public List<Note> getNotes() {
		return notes;
	}
}
