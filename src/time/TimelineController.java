package time;

import java.util.ArrayList;

import book.Book;

public class TimelineController {

	public static ArrayList<Timestamp> getTimelineWithSplit() {
		ArrayList<Timestamp> timeline = Book.getInstance().getTimeline().getAllTimestamps();
		switch(Book.getInstance().getTimeline().getSplitConditon()){
			case NONE:
				return timeline;
			case YEAR:
				return TimelineController.getTimelineHeaderFilteredByYear(timeline);
			case MONTH:
				return TimelineController.getTimelineChapterFilteredByMonth(timeline);
			default:
				return timeline;
		}
	}
	
	public static ArrayList<Timestamp> getPartOfTimelineWithSplit(Timestamp startDate) {
		ArrayList<Timestamp> timeline = Book.getInstance().getTimeline().getAllTimestamps();
		switch(Book.getInstance().getTimeline().getSplitConditon()){
			case NONE:
				return timeline;
			case YEAR:
				return TimelineController.getPartOfTimelineFilteredByYear(timeline, startDate);
			case MONTH:
				return TimelineController.getTimelinePartByMonth(timeline, startDate);
			default:
				return timeline;
		}
	}
	
	private static ArrayList<Timestamp> getTimelineHeaderFilteredByYear(ArrayList<Timestamp> timelist){
		ArrayList<Timestamp> filteredList = new ArrayList<Timestamp>();
		ArrayList<Integer> years = new ArrayList<Integer>();
		
		for(Timestamp timestamp : timelist){
			if(timestamp.getDate() != null){				
				int timestampYear =  timestamp.getYear();
				if(!years.contains(timestampYear)){
					filteredList.add(timestamp);
					years.add(timestampYear);
				}
			}
		}
		
		return filteredList;
	}
	
	private static ArrayList<Timestamp> getTimelineChapterFilteredByMonth(ArrayList<Timestamp> timelist) {
		//TODO: alles wäre einfacher, wenn die times liste immer zeitlich sortiert wäre -.-
		//TODO: maybe it will be easyer if the timeline is splittet in years in object structure
		return timelist;
	}
	
	private static ArrayList<Timestamp> getPartOfTimelineFilteredByYear(ArrayList<Timestamp> timelist, Timestamp startDate){
		ArrayList<Timestamp> filteredList = new ArrayList<Timestamp>();
		
		int year = -1;
		if(startDate == null){
			//TODO get first year in Timeline
		} else {			
			year = startDate.getYear();
		}
		
		for(Timestamp timestamp : timelist){
			if(timestamp.getDate() != null){				
				int timestampYear =  timestamp.getYear();
				if(year == timestampYear){
					filteredList.add(timestamp);
				}
			}
		}
		
		return filteredList;
	}
	
	private static ArrayList<Timestamp> getTimelinePartByMonth(ArrayList<Timestamp> timelist, Timestamp startDate) {
		//TODO: see other month-filter
		return timelist;
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
