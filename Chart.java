import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

public class Chart {

	private String name;
	private String artist;
	private String album;
	private String charter;
	private float offset;
	private int resolution;
	private int difficulty;
	private String genre;
	private int length;

	private List<String> sections;

	public static void main(String[] args) throws IOException {
		Chart chart = new Chart("C:\\Games\\Clone Hero\\Songs\\Other\\Prevail\\notes.chart");
		System.out.println(chart.getSections());

	}

	public Chart(String pathToChart) throws IOException {
		Path file = Paths.get(pathToChart);

		if (!Files.isRegularFile(file)) {
			throw new FileNotFoundException("Chart not found at specified path");
		}

		String contents = new String(Files.readAllBytes(file));
		setUpProperties(contents);

		final String SECTION_NAMES_MATCH_PATTERN = "(?<=E \"section ).*?(?=\")";
		final String SECTION_TIMINGS_MATCH_PATTERN = "[0-9]+?(?= \\= E \"section )";


		List<String> sectionNames = Finder.getMatches(SECTION_NAMES_MATCH_PATTERN, contents);
		List<Integer> sectionTimings = Finder.getMatches(SECTION_TIMINGS_MATCH_PATTERN, contents).stream()
																						  .map(Integer::parseInt)
																						  .collect(Collectors.toList());

		if (sectionNames.size() != sectionTimings.size()) {
			throw new InvalidChartException("Section timings and names do not match.");
		}


	}

	private void setUpProperties(String contents) {
		final String NAME_MATCH_PATTERN = "(?<=[Nn]ame = \").+?(?=\")";
		final String ARTIST_MATCH_PATTERN = "(?<=[Aa]rtist = \").+?(?=\")";
		final String ALBUM_MATCH_PATTERN = "(?<=[Aa]lbum = \").+?(?=\")";
		final String CHARTER_MATCH_PATTERN = "(?<=[Cc]harter = \").+?(?=\")";
		final String OFFSET_MATCH_PATTERN = "(?<=[Oo]ffset = ).+?((?=\n)|(?=\r\n))";
		final String RESOLUTION_MATCH_PATTERN = "(?<=[Rr]esolution = )[0-9]+?((?=\n)|(?=\r\n))";
		final String DIFFICULTY_MATCH_PATTERN = "(?<=[Dd]ifficulty = )[0-9]+?((?=\n)|(?=\r\n))";
		final String GENRE_MATCH_PATTERN = "(?<=[Gg]enre = \").+?(?=\")";

		name = Finder.getFirstMatch(NAME_MATCH_PATTERN, contents);
		artist = Finder.getFirstMatch(ARTIST_MATCH_PATTERN, contents);
		album = Finder.getFirstMatch(ALBUM_MATCH_PATTERN, contents);
		charter = Finder.getFirstMatch(CHARTER_MATCH_PATTERN, contents);
		offset = Float.parseFloat(Finder.getFirstMatch(OFFSET_MATCH_PATTERN, contents));
		resolution = Integer.parseInt(Finder.getFirstMatch(RESOLUTION_MATCH_PATTERN, contents));
		difficulty = Integer.parseInt(Finder.getFirstMatch(DIFFICULTY_MATCH_PATTERN, contents));
		genre = Finder.getFirstMatch(GENRE_MATCH_PATTERN, contents);
	}

	private HashMap<String, Timing> calculateTimings(List<String> names, List<Integer> timings) {
		for (int i = 0; i < names.size() ; i++) {
			assert true;
		}
		return new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

	public String getCharter() {
		return charter;
	}

	public String getAlbum() {
		return album;
	}

	public float getOffset() {
		return offset;
	}

	public int getResolution() {
		return resolution;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public String getGenre() {
		return genre;
	}

	public List<String> getSections() {
		return sections;
	}
}
