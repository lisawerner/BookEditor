package book.time;

import java.util.ArrayList;

import book.content.Book;

public class TimelineController {

	public static ArrayList<Timestamp> getTimelineWithSplit() {
		ArrayList<Timestamp> timeline = Book.getInstance().getTimeline().getAllTimestamps();
		switch(Book.getInstance().getTimeline().getSplitCondition()){
			case YEAR:
				return TimelineController.getTimelineHeaderFilteredByYear(timeline);
			case MONTH:
				return TimelineController.getTimelineChapterFilteredByMonth(timeline);
			case NONE:
				// As default
			default:
				return timeline;
		}
	}
	
	public static ArrayList<Timestamp> getPartOfTimelineWithSplit(Timestamp startDate) {
		ArrayList<Timestamp> timeline = Book.getInstance().getTimeline().getAllTimestamps();
		switch(Book.getInstance().getTimeline().getSplitCondition()){
			case YEAR:
				return TimelineController.getPartOfTimelineFilteredByYear(timeline, startDate);
			case MONTH:
				return TimelineController.getTimelinePartByMonth(timeline, startDate);
			case NONE:
				// As default
			default:
				return timeline;
		}
	}
	
	private static ArrayList<Timestamp> getTimelineHeaderFilteredByYear(ArrayList<Timestamp> timeList){
		ArrayList<Timestamp> filteredList = new ArrayList<>();
		ArrayList<Integer> years = new ArrayList<>();
		
		for(Timestamp timestamp : timeList){
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
	
	private static ArrayList<Timestamp> getTimelineChapterFilteredByMonth(ArrayList<Timestamp> timeList) {
		//TODO: everything would be easier if the book.time list is always sorted chronological -.-
		//TODO: maybe it will be easier if the timeline is splitter in years in object structure
		return timeList;
	}
	
	private static ArrayList<Timestamp> getPartOfTimelineFilteredByYear(ArrayList<Timestamp> timeList, Timestamp startDate){
		ArrayList<Timestamp> filteredList = new ArrayList<>();
		
		int year = -1;
		if(startDate == null){
			//TODO get first year in Timeline
		} else {			
			year = startDate.getYear();
		}
		
		for(Timestamp timestamp : timeList){
			if(timestamp.getDate() != null){				
				int timestampYear =  timestamp.getYear();
				if(year == timestampYear){
					filteredList.add(timestamp);
				}
			}
		}
		
		return filteredList;
	}
	
	private static ArrayList<Timestamp> getTimelinePartByMonth(ArrayList<Timestamp> timeList, Timestamp startDate) {
		//TODO: see other month-filter
		return timeList;
	}
	
	//TODO: Add filter. but this one was not so clever... maybe add other filter next book.time...
	
//	public ArrayList<Section> filterTimelineSections() {
//	ArrayList<Section> sectionsSortedByTimestamp = Book.getInstance().getTableOfContent().getTimeSortedSections();
//	boolean filterForMainCharacters = Book.getInstance().getTimeline().getSettings().getMainCharacterFilter();
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
//		//TODO: Do not filter only for book.person Tag -> Filter also for Relationship with mainCharacters
//		for(Person book.person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()) {
//			for(Person taggedPerson : section.getPersonByTag()) {					
//				if(taggedPerson.equals(book.person)) {
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
