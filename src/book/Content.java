package book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import global.ObjectID;
import person.Person;
import person.Relationship;
import time.Timestamp;

public class Content {
	
	private ArrayList<Chapter> my_chapters;
		
	public Content() {
		my_chapters = new ArrayList<Chapter>();
	}
					
	public int getCountChapters() {
		return my_chapters.size();
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
		Book.getInstance().save();
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

	public ArrayList<Section> getAllSectionsWhichUsesThatSectionAsTimeRelation(ObjectID maybeRelatedSection) {
		ArrayList<Section> listOfUsers = new ArrayList<Section>();
		for(Chapter chapter : my_chapters){
			for(Section section : chapter.getSections()){
				if(section.hasTimestamp()){
					Timestamp timestamp = Book.getInstance().getTimeline().getTimestamp(section.getTimestampID());
					if(!timestamp.isSpecificDate()){						
						Section relSection = timestamp.getUnspecificDate().getRelationSection();
						if(relSection != null){						
							if(relSection.getID().equals(maybeRelatedSection)){
								listOfUsers.add(section);
							}
						}
					}
				}
			}
		}
		return listOfUsers;
	}
	
}
