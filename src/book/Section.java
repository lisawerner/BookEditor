package book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import global.ObjectID;
import global.SerializedObject;
import global.Tag;
import person.Person;
import person.Relationship;
import world.Place;

public class Section extends SerializedObject {
	
	private final ObjectID my_parentChapter;
	
	//Text and Structure
	private String my_name;
	private String my_text;
	
	//Tagged Information
	private ArrayList<Tag> my_tags; //TODO: User can create specific personal/Privat Tags
	private ArrayList<Relationship> my_relationshipSwitches;
	private ObjectID my_timestamp;
	private int my_devStatus;
	private String my_notes;
	
	public Section(Chapter parentChapter, String newName) {
		super();
		my_parentChapter = parentChapter.getID();
	
		my_name = newName;
		my_text = "";
		
		my_tags = new ArrayList<>();
		my_relationshipSwitches = new ArrayList<>();
		my_timestamp = null;
		my_devStatus = -1;
		my_notes = "";
	}
		
	public boolean hasTag(ObjectID tagID){
		if(my_tags == null) {return false;}
		return my_tags.stream().anyMatch(tag -> tag.getRefID().getIDtoString().equals(tagID.getIDtoString()));
	}
	
	public void removeTag(ObjectID tagID) {
		if(my_tags == null) {my_tags = new ArrayList<>();}
		ArrayList<Tag> newList = new ArrayList<>();
		for(Tag tag : my_tags) {
			if(!tag.getRefID().getIDtoString().equals(tagID.getIDtoString())) {
				newList.add(tag);
			}
		}
		my_tags = newList;
	}
	
	public void addTag(Tag tag) {
		if(my_tags == null) {my_tags = new ArrayList<>();}
		my_tags.add(tag);
	}
	
	public ArrayList<String> getTagsToString(){
		if(my_tags == null) {return new ArrayList<>();}
		ArrayList<String> stringList = new ArrayList<>();
		for(Tag tag : my_tags) {
			if(tag.getType().equals("person.Person")) {
				stringList.add(Book.getInstance().getSociety().getPerson(tag.getRefID()).getInformation().getNickname());
			}
			if(tag.getType().equals("world.Place")) {
				stringList.add(Book.getInstance().getWorld().getPlace(tag.getRefID()).getName());
			}
		}
		return stringList;
	}
	
	private List<Tag> getTagsByTagType(String tagType){
		if(my_tags == null) {return new ArrayList<>();}
		return my_tags.stream().filter(tag -> tag.getType().equals(tagType)).collect(Collectors.toList());
	}
	
	public List<Person> getPersonByTag(){
		return getTagsByTagType("person.Person").stream().map(tag -> Book.getInstance().getSociety().getPerson(tag.getRefID())).collect(Collectors.toList());
	}
	
	public List<Place> getPlaceByTag(){
		return getTagsByTagType("world.Place").stream().map(tag -> Book.getInstance().getWorld().getPlace(tag.getRefID())).collect(Collectors.toList());
	}
	
	public String getName() {
		return my_name;
	}

	public String getText() {
		return my_text;
	}
	
	public void setTitle(String newName) {
		my_name = newName;
		Book.getInstance().save();
	}
	
	public void setText(String newText) {
		my_text = newText;
		Book.getInstance().save();
	}
	
	public String getShortTextPreview() {
		if(my_text.length() > 200) {			
			return my_text.substring(0, 200) + "...";
		}
		return my_text;
	}

	public void setTimestampID(ObjectID newTimestampID) {
		my_timestamp = newTimestampID;
	}

	public ObjectID getTimestampID() {
		return my_timestamp;
	}

	public boolean hasTimestamp(){
		return my_timestamp != null;
	}

	public int getDevelopmentStatus() {
		return my_devStatus;
	}
	
	public String getDevelopmentStatusToString() {
		return DevelopmentStatus.getDevStatus(my_devStatus);
	}

	public String getDevelopmentStatusDescription() {
		return DevelopmentStatus.getDevStatDescription(my_devStatus);
	}

	public void changeDevStatus(int newDevStatus) {
		my_devStatus = newDevStatus;
		Book.getInstance().save();
	}

	public void addRelationship(Relationship newRelationship) {
		if(my_relationshipSwitches == null) {my_relationshipSwitches = new ArrayList<>();}
		my_relationshipSwitches.add(newRelationship);

		Book.getInstance().save();
	}

	public ArrayList<Relationship> getRelationships() {
		if(my_relationshipSwitches == null) {return new ArrayList<>();}
		return my_relationshipSwitches;
	}
	
	public int getCountWords() {
		if(my_text.equals("")) {
			return 0;
		}
		String[] words = my_text.split("\\s+");
	    return words.length;
	}
	
	public boolean equals(Section otherSection) {
		return this.my_uID.getIDtoString().equals(otherSection.getID().getIDtoString());
	}
	
	public boolean equals(ObjectID otherSection) {
		return this.my_uID.getIDtoString().equals(otherSection.getIDtoString());
	}

	public Section getPreSection() {
		Section preSection = null;
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section currentSection : chapter.getSections()) {
				if(currentSection.getID().equals(this.getID())) {
					return preSection;
				}
				preSection = currentSection;
			}
		}
		return null;
	}
	
	public Section getPostSection() {
		boolean foundInputSection = false;
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section currentSection : chapter.getSections()) {
				if(foundInputSection) {
					return currentSection;
				}
				if(currentSection.getID().equals(this.getID())) {
					foundInputSection = true;
				}
			}
		}
		return null;
	}

	public void removeRelationship(Relationship relationship) {
		my_relationshipSwitches.remove(relationship);
		Book.getInstance().getSociety().getPerson(relationship.getPersonA()).removeRelationship(relationship.getID());
		Book.getInstance().getSociety().getPerson(relationship.getPersonB()).removeRelationship(relationship.getID());
		Book.getInstance().save();
	}
	
	public void removeRelationship(Person person) {
		ArrayList<Relationship> removeThisRelationships = new ArrayList<>();
		for(Relationship currentRelationship : my_relationshipSwitches) {
			if(currentRelationship.getPersonA().equals(person.getID()) || currentRelationship.getPersonB().equals(person.getID())) {
				removeThisRelationships.add(currentRelationship);
			}
		}
		for(Relationship deleteThis : removeThisRelationships) {
			removeRelationship(deleteThis);
		}
	}

	public String getNotes() {
		if(my_notes == null) {return "";}
		return my_notes;
	}

	public void setNotes(String newNotes) {
		my_notes = newNotes;
	}

	public boolean isFirstSection() {
		return Book.getInstance().getTableOfContent().getChapters().get(0).getSections().get(0).getID().equals(this.getID());
	}

	public boolean isLastSection() {
		ArrayList<Chapter> chapters = Book.getInstance().getTableOfContent().getChapters();
		ArrayList<Section> sections = chapters.get(chapters.size()-1).getSections();
		return sections.get(sections.size()-1).getID().equals(this.getID());
	}

	public boolean containsPerson(Person searchedPerson) {
		for(Person taggedPerson : getPersonByTag()) {
			if(taggedPerson.equals(searchedPerson)) {
				return true;
			}
		}
		return false;
	}

	public ObjectID getParentChapterID() {
		return my_parentChapter;
	}

}
