package book;

import java.util.ArrayList;

import global.ObjectID;
import person.Person;

public class SectionList {
	
	private ArrayList<Section> my_sections;
	
	public SectionList() {
		my_sections = new ArrayList<Section>();
	}

	public void addSection(Section newSection) {
		if(my_sections == null) {my_sections = new ArrayList<Section>();}
		my_sections.add(newSection);
		Book.getInstance().save();
	}
	
	public void editSection(Section newSection) {
		if(my_sections == null) {my_sections = new ArrayList<Section>();}
		ArrayList<Section> new_sectionList = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.getID().getIDtoString().equals(newSection.getID().getIDtoString())) {
				new_sectionList.add(newSection);
			} else {
				new_sectionList.add(section);
			}
		}
		my_sections = new_sectionList;
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
			if(section.getID().getIDtoString().equals(sectionID.getIDtoString())) {
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
			if(section.getID().getIDtoString().equals(sectionID.getIDtoString())) {
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
		ArrayList<Section> UNsortedCops = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.hasTimestamp()) {
				UNsortedCops.add(section);
			}
		}
		while(UNsortedCops.size() != 0) {			
			Section smallestDateSection = getSectionWithSmallestTimestamp(UNsortedCops);
			sortedSections.add(smallestDateSection);
			UNsortedCops.remove(smallestDateSection);
		}
		
		return sortedSections;
	}
	
	private Section getSectionWithSmallestTimestamp(ArrayList<Section> restList) {
		//Get First -> First could be smallest
		Section smallestDateSection = restList.get(0);
		for(Section currentSection : restList) {
			if(smallestDateSection.getTimestamp().greaterThen(currentSection.getTimestamp())) {
				smallestDateSection = currentSection;
			}
		}
		return smallestDateSection;
	}
	
	public ArrayList<Section> getUnfinishedSections(){
		ArrayList<Section> unfinishedSections = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.getDevelopmentStatus() < 4) {
				unfinishedSections.add(section);
			}
		}
		return unfinishedSections;
	}
	
	public ArrayList<Section> getEmptySections(){
		ArrayList<Section> unfinishedSections = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.getText().equals("")) {
				unfinishedSections.add(section);
			}
		}
		return unfinishedSections;
	}
	
	public ArrayList<Section> getUnsortedSections(){
		ArrayList<Section> unfinishedSections = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.isUnsorted()) {
				unfinishedSections.add(section);
			}
		}
		return unfinishedSections;
	}
	
	public ArrayList<Section> getSectionsByDevStatus(int devStatus, boolean andSmaller){
		ArrayList<Section> unfinishedSections = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(section.getDevelopmentStatus() == devStatus) {
				unfinishedSections.add(section);
			}
			if(andSmaller) {
				if(section.getDevelopmentStatus() < devStatus) {
					unfinishedSections.add(section);
				}
			}
		}
		return unfinishedSections;
	}

	public ArrayList<Section> getSectionsByPersons(ArrayList<Person> selectedPersons, boolean ANDselect) {
		ArrayList<Section> selectedSections = new ArrayList<Section>();
		for(Section section : my_sections) {
			if(ANDselect) {
				//Select section only if ALL persons are tagged in the section
				boolean allPersonsTagged = true;
				for(Person person : selectedPersons) {
					if(!section.hasTag(person.getID())) {
						allPersonsTagged = false;
					}
				}
				if(allPersonsTagged) {
					selectedSections.add(section);
				}
			} else {				
				//Select section if min one of the persons is tagged in the section
				for(Person person : selectedPersons) {
					if(section.hasTag(person.getID())) {
						selectedSections.add(section);
						break;
					}
				}
			}
		}
		return selectedSections;
	}
	
	
	public Section getPreSection(Section inputSection) {
		Section preSection = null;
		for(Section currentSection : my_sections) {
			if(currentSection.getID().getIDtoString().equals(inputSection.getID().getIDtoString())) {
				return preSection;
			}
			preSection = currentSection;
		}
		return null;
	}
	
	
}
