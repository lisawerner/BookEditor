package time;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
		if(my_times == null){
			my_times = new ArrayList<Timestamp>();
		}
		if(my_times.isEmpty()){
			my_times.add(newTimestamp);			
		} else {			
			for(Timestamp timestamp : my_times){
				if(newTimestamp.getDate().isAfter(timestamp.getDate())){					
					my_times.add(my_times.indexOf(timestamp), newTimestamp);
					break;
				}
			}
		}
		if(!my_times.contains(newTimestamp)){
			my_times.add(newTimestamp);
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

	public void removeTimestamp(ObjectID timestampID) {
		my_times.stream().filter(timestamp -> timestamp.getID() != timestampID).collect(Collectors.toList());
		Book.getInstance().save();
	}

//	public void fix() {
//		for(Timestamp time : my_times){
//			time.fix(my_times);
//		}	
//	}

}
