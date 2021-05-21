package GUI_StartFrame;

public class LoadBookPair {
	
	private final String booktitle;
	private final String filename;

	public LoadBookPair(String loadedFilename, String loadedBookTitle) {
		booktitle = loadedBookTitle;
		filename = loadedFilename;
	}

	public String getBookTitle() {
		return booktitle;
	}

	public String getFilename() {
		return filename;
	}

}
