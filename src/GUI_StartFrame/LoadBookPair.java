package GUI_StartFrame;

public class LoadBookPair {
	
	private final String bookTitle;
	private final String filename;

	public LoadBookPair(String loadedFilename, String loadedBookTitle) {
		bookTitle = loadedBookTitle;
		filename = loadedFilename;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getFilename() {
		return filename;
	}

}
