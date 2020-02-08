package book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import global.ObjectID;
import person.Person;
import person.Relationship;

public class Content {
	
	private ArrayList<Chapter> my_chapters;
		
	public Content() {
		my_chapters = new ArrayList<Chapter>();
	}
					
	public int getCountChapters() {
		return my_chapters.size();
	}
		
	public ArrayList<Section> getTimeSortedSections(){
		ArrayList<Section> sortedSections = new ArrayList<Section>();
		List<Section> UNsortedCops = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {
			UNsortedCops.addAll(chapter.getSections().stream().filter(section -> section.hasTimestamp()).collect(Collectors.toList()));
		}

		while(UNsortedCops.size() != 0) {			
			Section smallestDateSection = getSectionWithSmallestTimestamp(UNsortedCops);
			sortedSections.add(smallestDateSection);
			UNsortedCops.remove(smallestDateSection);
		}
		
		return sortedSections;
	}
	
	private Section getSectionWithSmallestTimestamp(List<Section> restList) {
		return restList.stream().reduce(this::getSmallerSection).get();
	}
	
	private Section getSmallerSection(Section sectionA, Section sectionB) {
		if(sectionA.getTimestamp().getSpecificDate().isAnnoDomini() && !sectionB.getTimestamp().getSpecificDate().isAnnoDomini()) {
			//return sectionB, because sectionB is before christ and sectionA after christ
			return sectionB;
		}
		if(!sectionA.getTimestamp().getSpecificDate().isAnnoDomini() && sectionB.getTimestamp().getSpecificDate().isAnnoDomini()) {
			//return sectionA, because sectionA is before christ and sectionB after christ
			return sectionA;
		}
		if(!sectionA.getTimestamp().getSpecificDate().isAnnoDomini() && !sectionB.getTimestamp().getSpecificDate().isAnnoDomini()) {
			//return section which is smaller, because both after christ
			if(sectionB.getTimestamp().greaterThen(sectionA.getTimestamp())) {
				return sectionB;
			} else {
				return sectionA;
			}
		}
		if(sectionA.getTimestamp().getSpecificDate().isAnnoDomini() && sectionB.getTimestamp().getSpecificDate().isAnnoDomini()) {
			//return section which is greater, because both before christ
			if(sectionA.getTimestamp().greaterThen(sectionB.getTimestamp())) {
				return sectionB;
			} else {
				return sectionA;
			}
		}
		return null;
	}
	
	public List<Section> getUnfinishedSections(){
		ArrayList<Section> sectionList = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {
			sectionList.addAll(chapter.getSections().stream()
					.filter(section -> section.getDevelopmentStatus() < 4)
					.collect(Collectors.toList()));
		}
		return sectionList;
	}
	
	public List<Section> getEmptySections(){
		ArrayList<Section> sectionList = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {
			sectionList.addAll(chapter.getSections().stream().filter(section -> "".equals(section.getText())).collect(Collectors.toList()));
		}
		return sectionList;
	}
	
	public List<Section> getUnsortedSections(){
		ArrayList<Section> sectionList = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {
			sectionList.addAll(chapter.getSections().stream().filter(section -> section.isUnsorted()).collect(Collectors.toList()));
		}
		return sectionList;
	}
	
	public List<Section> getSectionsByDevStatus(int devStatus, boolean andSmaller){
		ArrayList<Section> sectionList = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {
			sectionList.addAll(chapter.getSections().stream().filter(section -> section.getDevelopmentStatus() == devStatus
					|| (andSmaller && section.getDevelopmentStatus() < devStatus)).collect(Collectors.toList()));
		}
		return sectionList;
	}

	public List<Section> getSectionsByPersons(ArrayList<Person> selectedPersons, boolean intersectionSelect) {
		ArrayList<Section> sectionList = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {
			sectionList.addAll(chapter.getSections().stream()
					.filter(section -> allPersonsTagged(intersectionSelect,selectedPersons,section) || anyPersonTagged(intersectionSelect,selectedPersons,section))
					.collect(Collectors.toList()));
		}
		return sectionList;
	}
	
	private boolean allPersonsTagged(boolean intersectionSelect, List<Person> selectedPersons, Section section) {
		//Select section only if ALL persons are tagged in the section
		return intersectionSelect && selectedPersons.stream().allMatch(person -> section.hasTag(person.getID()));
	}
	
	private boolean anyPersonTagged(boolean intersectionSelect, List<Person> selectedPersons, Section section) {
		//Select section if min one of the persons is tagged in the section
		return selectedPersons.stream().anyMatch(person -> section.hasTag(person.getID()));
	}
			
	public Section getSection(ObjectID sectionID) {
		for(Chapter chapter : my_chapters) {			
			for(Section section : chapter.getSections()) {
				if(section.equals(sectionID)) {
					return section;
				}
			}
		}
		return null;
	}

	public ArrayList<Section> filterTimelineSections() {
		ArrayList<Section> sectionsSortedByTimestamp = Book.getInstance().getTableOfContent().getTimeSortedSections();
		boolean filterForMainCharacters = Book.getInstance().getTimeline().getSettings().getMaincharacterFilter();
		ArrayList<Section> filteredList = new ArrayList<Section>();
		if(filterForMainCharacters) {
			filteredList = filterForMainCharacters(sectionsSortedByTimestamp);
		} else {
			filteredList = sectionsSortedByTimestamp;
		}
		return filteredList;
	}
	
	private ArrayList<Section> filterForMainCharacters(ArrayList<Section> unfilteredList){
		ArrayList<Section> filteredList = new ArrayList<Section>();
		for(Section section : unfilteredList) {
			boolean foundSomething = false;
			//TODO: Do not filter only for person Tag -> Filter also for Relationship with mainCharacters
			for(Person person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()) {
				for(Person taggedPerson : section.getPersonByTag()) {					
					if(taggedPerson.equals(person)) {
						foundSomething = true;
						break;
					}
				}
				if(foundSomething) {
					break;
				}
			}
			if(foundSomething) {
				filteredList.add(section);
			}
		}
		return filteredList;
	}

	public List<Section> getSectionWithoutTaggedMaincharacters() {
		ArrayList<Section> filteredList = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {			
			for(Section section : chapter.getSections()) {
				boolean hasNoMainCharacter = true;
				for(Person sectionPerson : section.getPersonByTag()) {
					if(sectionPerson.getInformation().isSuperMainChar()) {
						hasNoMainCharacter = false;
					}
				}
				if(hasNoMainCharacter) {				
					filteredList.add(section);
				}
			}
		}
		return filteredList;
	}

	public List<Section> getSectionWithoutTimestamp() {
		ArrayList<Section> sectionList = new ArrayList<Section>();
		for(Chapter chapter : my_chapters) {
			sectionList.addAll(chapter.getSections().stream().filter(section -> !section.hasTimestamp()).collect(Collectors.toList()));
		}
		return sectionList;
	}

	public Section getSectionByRelationship(ObjectID relID) {
		for(Chapter chapter : my_chapters) {			
			for(Section section : chapter.getSections()) {
				for(Relationship relship : section.getRelationships()) {
					if(relship.getID().equals(relID)) {
						return section;
					}
				}
			}
		}
		return null;
	}

	public void addChapter(Chapter newChapter) {
		if(my_chapters == null) {my_chapters = new ArrayList<Chapter>();}
		my_chapters.add(newChapter);
	}

	public Chapter getChapter(String name) {
		if(my_chapters == null) {return null;}
		
		for(Chapter chapter : my_chapters) {
			if(name.equals(chapter.getTitle())) {
				return chapter;
			}
		}
		
		return null;
	}

	public ArrayList<Chapter> getChapters() {
		if(my_chapters == null) {return new ArrayList<Chapter>();}
		return my_chapters;
	}

	public boolean isFirstChapter(Chapter chapter) {
		return my_chapters.indexOf(chapter) == 0;
	}

	public void sortChapter(Chapter moveChapter, boolean moveUp) {
		if(my_chapters == null) {my_chapters = new ArrayList<Chapter>();}
		
		int index = my_chapters.indexOf(moveChapter);
		my_chapters.remove(index);
		if(moveUp) {			
			index--;
		} else {			
			index++;
		}
		my_chapters.add(index, moveChapter);
		
		Book.getInstance().save();
	}

	public boolean isLastChapter(Chapter chapter) {
		return my_chapters.indexOf(chapter) == (my_chapters.size()-1);
	}

	public Chapter getChapter(ObjectID parentChapterID) {
		if(my_chapters == null) {return null;}
		for(Chapter chapter : my_chapters) {
			if(parentChapterID.equals(chapter.getID())) {
				return chapter;
			}
		}
		return null;
	}
	
}
