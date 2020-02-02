package book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import global.ObjectID;
import person.Person;
import person.Relationship;

public class Content {
	
	private ArrayList<Section> my_sections;
	
	public Content() {
		my_sections = new ArrayList<Section>();
	}

	public void addSection(Section newSection) {
		if(my_sections == null) {my_sections = new ArrayList<Section>();}
		my_sections.add(newSection);
		Book.getInstance().save();
	}
	
	public void deleteSection(Section delSection) {
		if(my_sections == null) {my_sections = new ArrayList<Section>();}
		my_sections.remove(delSection);
		Book.getInstance().save();
	}
		
	public void resortSections() {
		if(my_sections == null) {my_sections = new ArrayList<Section>();}
		ArrayList<Section> new_sortedSectionList = new ArrayList<Section>();
		ArrayList<Section> new_unsortedSectionList = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.isUnsorted()) {
				new_unsortedSectionList.add(section);
			} else {
				new_sortedSectionList.add(section);
			}
		}
		my_sections = new_sortedSectionList;
		my_sections.addAll(new_unsortedSectionList);
		Book.getInstance().save();
	}
	
	public void sortSectionUp(ObjectID sectionID) {
		if(my_sections == null) {my_sections = new ArrayList<Section>();}
		ArrayList<Section> new_sortedSectionList = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.equals(sectionID)) {
				Section lastSection = new_sortedSectionList.get(new_sortedSectionList.size() - 1);
				new_sortedSectionList.remove(new_sortedSectionList.size() - 1);
				new_sortedSectionList.add(section);
				new_sortedSectionList.add(lastSection);
			} else {
				new_sortedSectionList.add(section);
			}
		}
		my_sections = new_sortedSectionList;
		Book.getInstance().save();
	}
	
	public void sortSectionDown(ObjectID sectionID) {
		if(my_sections == null) {my_sections = new ArrayList<Section>();}
		ArrayList<Section> new_oldStartSectionList = new ArrayList<Section>();
		ArrayList<Section> new_movedUpSection = new ArrayList<Section>();
		ArrayList<Section> newSortedSectionTail = new ArrayList<Section>();
		boolean found = false;
		for(Section section : my_sections) {
			if(section.equals(sectionID)) {
				found = true;
				newSortedSectionTail.add(section);
			} else {
				if(found) {
					if(new_movedUpSection.size() >= 1) {
						newSortedSectionTail.add(section);
					} else {
						new_movedUpSection.add(section);
					}
					
				} else {				
					new_oldStartSectionList.add(section);
				}
			}
			
		}
		my_sections = new_oldStartSectionList;
		my_sections.addAll(new_movedUpSection);
		my_sections.addAll(newSortedSectionTail);
		resortSections();
		Book.getInstance().save();
	}
	
	
	
	public int getCountChapters() {
		int count = 0;
		for(Section section : my_sections) {
			if(section.isChapter()) {
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<Section> getSections(){
		if(my_sections == null) {return new ArrayList<Section>();}
		return my_sections;
	}
	
	public ArrayList<Section> getTimeSortedSections(){
		ArrayList<Section> sortedSections = new ArrayList<Section>();
		List<Section> UNsortedCops = my_sections.stream().filter(section -> section.hasTimestamp()).collect(Collectors.toList());

		while(UNsortedCops.size() != 0) {			
			Section smallestDateSection = getSectionWithSmallestTimestamp(UNsortedCops);
			sortedSections.add(smallestDateSection);
			UNsortedCops.remove(smallestDateSection);
		}
		
		return sortedSections;
	}
	
	private Section getSectionWithSmallestTimestamp(List<Section> restList) {
		//Get First -> First could be smallest
//		Section smallestDateSection = restList.get(0);
//		
//		for(Section currentSection : restList) {
//			smallestDateSection = getSmallerSection(smallestDateSection, currentSection);
//		}
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
		return my_sections.stream()
				.filter(section -> section.getDevelopmentStatus() < 4)
				.collect(Collectors.toList());
	}
	
	public List<Section> getEmptySections(){
		return my_sections.stream().filter(section -> "".equals(section.getText())).collect(Collectors.toList());
	}
	
	public List<Section> getUnsortedSections(){
		return my_sections.stream().filter(section -> section.isUnsorted()).collect(Collectors.toList());
	}
	
	public List<Section> getSectionsByDevStatus(int devStatus, boolean andSmaller){
		return my_sections.stream().filter(section -> section.getDevelopmentStatus() == devStatus
					|| (andSmaller && section.getDevelopmentStatus() < devStatus)).collect(Collectors.toList());
	}

		
	public List<Section> getSectionsByPersons(ArrayList<Person> selectedPersons, boolean intersectionSelect) {
		return my_sections.stream()
				.filter(section -> allPersonsTagged(intersectionSelect,selectedPersons,section) || anyPersonTagged(intersectionSelect,selectedPersons,section))
				.collect(Collectors.toList());
	}
	
	private boolean allPersonsTagged(boolean intersectionSelect, List<Person> selectedPersons, Section section) {
		//Select section only if ALL persons are tagged in the section
		return intersectionSelect && selectedPersons.stream().allMatch(person -> section.hasTag(person.getID()));
	}
	
	private boolean anyPersonTagged(boolean intersectionSelect, List<Person> selectedPersons, Section section) {
		//Select section if min one of the persons is tagged in the section
		return selectedPersons.stream().anyMatch(person -> section.hasTag(person.getID()));
	}
	
	
	public Section getPreSection(Section inputSection) {
		Section preSection = null;
		for(Section currentSection : my_sections) {
			if(currentSection.equals(inputSection)) {
				return preSection;
			}
			preSection = currentSection;
		}
		return null;
	}

	public Section getPostSection(Section inputSection) {
		boolean foundInputSection = false;
		for(Section currentSection : my_sections) {
			if(foundInputSection) {
				return currentSection;
			}
			if(currentSection.equals(inputSection)) {
				foundInputSection = true;
			}
		}
		return null;
	}
	
	public Section getSection(ObjectID sectionID) {
		for(Section section : my_sections) {
			if(section.equals(sectionID)) {
				return section;
			}
		}
		return null;
	}

	public ArrayList<Section> filterTimelineSections() {
		ArrayList<Section> sectionsSortedByTimestamp = Book.getInstance().getSectionList().getTimeSortedSections();
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
		for(Section section : my_sections) {
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
		return filteredList;
	}

	public List<Section> getSectionWithoutTimestamp() {
		return my_sections.stream().filter(section -> !section.hasTimestamp()).collect(Collectors.toList());
	}

	public Section getSectionByRelationship(ObjectID relID) {
		for(Section section : my_sections) {
			for(Relationship relship : section.getRelationships()) {
				if(relship.getID().equals(relID)) {
					return section;
				}
			}
		}
		return null;
	}
	
}
