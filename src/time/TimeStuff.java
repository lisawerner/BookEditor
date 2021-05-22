package time;

import book.Book;

public class TimeStuff {
	
	private boolean useGregorianCalendar;
	private final TimelineSettings my_timelineSettings;
	
	public TimeStuff() {
		useGregorianCalendar = true;
		my_timelineSettings = new TimelineSettings();
	}
	
	public boolean useGregorianCalendar() {
		return useGregorianCalendar;
	}
	
	public void changeCalendarSettings(boolean newGregorianCalendarSettings) {
		useGregorianCalendar = newGregorianCalendarSettings;
		Book.getInstance().save();
	}
	
	public TimelineSettings getSettings() {		
		return my_timelineSettings;
	}

}
