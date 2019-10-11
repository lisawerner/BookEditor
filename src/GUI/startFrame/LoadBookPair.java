package GUI.startFrame;

public class LoadBookPair {
	
	private String booktitle;
	private String filename;

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
