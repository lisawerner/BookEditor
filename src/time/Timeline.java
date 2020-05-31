package time;

import java.util.ArrayList;
import book.Book;
import global.ObjectID;

public class Timeline {
	
	private boolean useGregorianCalendar;
	private SplitCondition my_splitCondition;
	
	private ArrayList<Timestamp> my_times;
	
	public Timeline() {
		useGregorianCalendar = true;
		my_splitCondition = SplitCondition.NONE;
		my_times = new ArrayList<Timestamp>();
	}
	
	public boolean useGregorianCalendar() {
		return useGregorianCalendar;
	}
	
	public void changeCalendarSettings(boolean newGregorainCalendarSettings) {
		useGregorianCalendar = newGregorainCalendarSettings;
		Book.getInstance().save();
	}
	
	public void addTimestamp(Timestamp newTimestamp) {
		if(my_times == null){my_times = new ArrayList<Timestamp>();}
		SpecificDate spec = newTimestamp.getSpecificDate();
		if(spec == null){
			my_times.add(newTimestamp);
		} else {			
			for(Timestamp timestamp : my_times){
				if(spec.getDate().after(timestamp.getSpecificDate().getDate())){					
					my_times.add(my_times.indexOf(timestamp), newTimestamp);
					break;
				}
			}
		}
		Book.getInstance().save();
	}

	public Timestamp getTimestamp(ObjectID timestampID) {
		if(timestampID != null){
			for(Timestamp timestamp : my_times){
				if(timestamp.getID() != null){				
					if(timestamp.getID().equals(timestampID)){
						return timestamp;
					}
				}
			}			
		}
		return null;
	}

	public ArrayList<Timestamp> getAllTimestamps() {
		return my_times;
	}
	

	public void setSplitCondition(SplitCondition newCondition){
		my_splitCondition = newCondition;
		Book.getInstance().save();
	}
	
	public SplitCondition getSplitConditon(){
		return my_splitCondition;
	}

}
