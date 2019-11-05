package time;

import book.Book;

public class TimelineSettings {
	
	private boolean filter_maincharacters;
	
	public TimelineSettings() {
		filter_maincharacters = false;
	}

	public void setMaincharacterFilter(boolean newFilter) {
		filter_maincharacters = newFilter;
		Book.getInstance().save();
	}
	
	public boolean getMaincharacterFilter() {
		return filter_maincharacters;
	}
}
