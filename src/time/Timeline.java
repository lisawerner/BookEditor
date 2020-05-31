package time;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import book.Book;
import global.ObjectID;

public class Timeline {
	
	private boolean useGregorianCalendar;
	private TimelineSettings my_timelineSettings; //Sort one time and after that insert new or changed timestamps at right position
	
	private ArrayList<Timestamp> my_times;
	
	public Timeline() {
		useGregorianCalendar = true;
		my_timelineSettings = new TimelineSettings();
		my_times = new ArrayList<Timestamp>();
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

	public ArrayList<Timestamp> getTimelineBySplit() {
		switch(my_timelineSettings.getSplitConditon()){
			case NONE:
				return my_times;
			case YEAR:
				return filterForYears();
			case MONTH:
				return filterForMonth();
			default:
				return my_times;
		}
	}

	private ArrayList<Timestamp> filterForYears(){
		ArrayList<Timestamp> filteredList = new ArrayList<Timestamp>();
		ArrayList<Integer> years = new ArrayList<Integer>();
		
		for(Timestamp timestamp : my_times){
			SpecificDate spec = timestamp.getSpecificDate();
			if(spec != null){				
				int timestampYear =  spec.getYear();
				if(!years.contains(timestampYear)){
					filteredList.add(timestamp);
					years.add(timestampYear);
				}
			}
		}
		
		return filteredList;
	}
	
	private ArrayList<Timestamp> filterForMonth() {
		//TODO: alles wäre einfacher, wenn die times liste immer zeitlich sortiert wäre -.-
		return my_times;
	}
	
	public ArrayList<Timestamp> getTimelinePartBySplit(Timestamp startDate) {
		switch(my_timelineSettings.getSplitConditon()){
			case NONE:
				return my_times;
			case YEAR:
				return getTimelinePartByYear(startDate);
			case MONTH:
				return getTimelinePartByMonth(startDate);
			default:
				return my_times;
		}
	}

	private ArrayList<Timestamp> getTimelinePartByYear(Timestamp startDate){
		ArrayList<Timestamp> filteredList = new ArrayList<Timestamp>();
		
		int year = -1;
		if(startDate == null){
			//TODO get first year in Timeline
		} else {			
			SpecificDate specificStart = startDate.getSpecificDate();
			if(specificStart != null){
				year = specificStart.getYear();
			}
		}
		
		for(Timestamp timestamp : my_times){
			SpecificDate spec = timestamp.getSpecificDate();
			if(spec != null){
				int timestampYear =  spec.getYear();
				if(year == timestampYear){
					filteredList.add(timestamp);
				}
			}
		}
		
		return filteredList;
	}
	
	private ArrayList<Timestamp> getTimelinePartByMonth(Timestamp startDate) {
		//TODO: alles wäre einfacher, wenn die times liste immer zeitlich sortiert wäre -.-
		return my_times;
	}

	public void addTimestamp(Timestamp timestamp) {
		if(my_times == null){my_times = new ArrayList<Timestamp>();}
		my_times.add(timestamp);
//		Book.getInstance().save();
	}

	public Timestamp getTimestamp(ObjectID timestampID) {
		for(Timestamp timestamp : my_times){
			if(timestamp.getID().equals(timestampID)){
				return timestamp;
			}
		}
		return null;
	}

	public ArrayList<Timestamp> getAllTimestamps() {
		return my_times;
	}
	
	public ArrayList<Timestamp> getSplittedAndSortedTimestamps(){
		return sortTimes(getTimelineBySplit());
	}
	
	public ArrayList<Timestamp> getTimeSortedTimestamps(){
		return sortTimes(my_times);
	}
	
	private ArrayList<Timestamp> sortTimes(ArrayList<Timestamp> list){
		List<Timestamp> UNsortedSpecificTimes = new ArrayList<Timestamp>();
		UNsortedSpecificTimes.addAll(list.stream().filter(timestamp -> timestamp.isSpecificDate()).collect(Collectors.toList()));
		
		ArrayList<Timestamp> sortedTimestamp = new ArrayList<Timestamp>();
		while(UNsortedSpecificTimes.size() != 0) {			
			Timestamp smallestDateTimestamp = getSmallestTimestamp(UNsortedSpecificTimes);
			sortedTimestamp.add(smallestDateTimestamp);
			UNsortedSpecificTimes.remove(smallestDateTimestamp);
		}
		
		return sortedTimestamp;
	}
	
	private Timestamp getSmallestTimestamp(List<Timestamp> restList) {
		return restList.stream().reduce(this::getSmallerSection).get();
	}
	
	private Timestamp getSmallerSection(Timestamp timestampA, Timestamp timestampB) {
		if(timestampA.getSpecificDate().isAnnoDomini() && !timestampB.getSpecificDate().isAnnoDomini()) {
			//return sectionB, because sectionB is before christ and sectionA after christ
			return timestampB;
		}
		if(!timestampA.getSpecificDate().isAnnoDomini() && timestampB.getSpecificDate().isAnnoDomini()) {
			//return sectionA, because sectionA is before christ and sectionB after christ
			return timestampA;
		}
		if(!timestampA.getSpecificDate().isAnnoDomini() && !timestampB.getSpecificDate().isAnnoDomini()) {
			//return section which is smaller, because both after christ
			if(timestampB.greaterThen(timestampA)) {
				return timestampB;
			} else {
				return timestampA;
			}
		}
		if(timestampA.getSpecificDate().isAnnoDomini() && timestampB.getSpecificDate().isAnnoDomini()) {
			//return section which is greater, because both before christ
			if(timestampA.greaterThen(timestampB)) {
				return timestampB;
			} else {
				return timestampA;
			}
		}
		return null;
	}

	//TODO: Add filter. but this one was not so clever... maybe add other filter next time...
	
//	public ArrayList<Section> filterTimelineSections() {
//	ArrayList<Section> sectionsSortedByTimestamp = Book.getInstance().getTableOfContent().getTimeSortedSections();
//	boolean filterForMainCharacters = Book.getInstance().getTimeline().getSettings().getMaincharacterFilter();
//	ArrayList<Section> filteredList = new ArrayList<Section>();
//	if(filterForMainCharacters) {
//		filteredList = filterForMainCharacters(sectionsSortedByTimestamp);
//	} else {
//		filteredList = sectionsSortedByTimestamp;
//	}
//	return filteredList;
//}
//
//private ArrayList<Section> filterForMainCharacters(ArrayList<Section> unfilteredList){
//	ArrayList<Section> filteredList = new ArrayList<Section>();
//	for(Section section : unfilteredList) {
//		boolean foundSomething = false;
//		//TODO: Do not filter only for person Tag -> Filter also for Relationship with mainCharacters
//		for(Person person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()) {
//			for(Person taggedPerson : section.getPersonByTag()) {					
//				if(taggedPerson.equals(person)) {
//					foundSomething = true;
//					break;
//				}
//			}
//			if(foundSomething) {
//				break;
//			}
//		}
//		if(foundSomething) {
//			filteredList.add(section);
//		}
//	}
//	return filteredList;
//}
}
