package time;

import book.Book;

public class TimelineSettings {
	
	private boolean filter_mainCharacters;
	
	public TimelineSettings() {
		filter_mainCharacters = false;
	}

	public void setMainCharacterFilter(boolean newFilter) {
		filter_mainCharacters = newFilter;
		Book.getInstance().save();
	}
	
	public boolean getMainCharacterFilter() {
		return filter_mainCharacters;
	}
}
