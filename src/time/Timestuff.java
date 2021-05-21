package time;

import book.Book;

public class Timestuff {
	
	private boolean useGregorianCalendar;
	private final TimelineSettings my_timelineSettings;
	
	public Timestuff() {
		useGregorianCalendar = true;
		my_timelineSettings = new TimelineSettings();
	}
	
	public boolean useGregorianCalendar() {
		return useGregorianCalendar;
	}
	
	public void changeCalendarSettings(boolean newGregorainCalendarSettings) {
		useGregorianCalendar = newGregorainCalendarSettings;
		Book.getInstance().save();
	}
	
	public TimelineSettings getSettings() {		
		return my_timelineSettings;
	}

}
