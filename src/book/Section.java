package book;

import java.util.ArrayList;

import global.ObjectID;
import global.Tag;
import person.Person;
import person.Relationship;
import time.Timestamp;
import world.Place;

public class Section {
	
	private ObjectID my_uID;
	
	private String my_name;
	private String my_text;
	
	private boolean isUnsorted;
	private boolean isChapterStart;
	
	private ArrayList<Tag> my_tags; //TODO: Persönlich/Privat Tags die von den Nutzern spezifisch angelegt wurden
	private ArrayList<Relationship> my_relationshipSwitches;
	private Timestamp my_timestamp;
	private int my_devStatus;
	
	public Section(String newName, String newText) {
		my_uID = new ObjectID(this.getClass().getName());
		
		my_name = newName;
		my_text = newText;
		
		isUnsorted = true;
		isChapterStart = false;
		
		my_tags = new ArrayList<Tag>();
		my_relationshipSwitches = new ArrayList<Relationship>();
		my_timestamp = null;
		my_devStatus = -1;
	}
	
	public ObjectID getID() {
		return my_uID;
	}
	
	public boolean hasTag(ObjectID tagID){
		if(my_tags == null) {return false;}
		for(Tag tag : my_tags) {
			if(tag.getRefID().getIDtoString().equals(tagID.getIDtoString())) {
				return true;
			}
		}
		return false;
	}
	
	public void removeTag(ObjectID tagID) {
		if(my_tags == null) {my_tags = new ArrayList<Tag>();}
		ArrayList<Tag> newList = new ArrayList<Tag>();
		for(Tag tag : my_tags) {
			if(!tag.getRefID().getIDtoString().equals(tagID.getIDtoString())) {
				newList.add(tag);
			}
		}
		my_tags = newList;
	}
	
	public void addTag(Tag tag) {
		if(my_tags == null) {my_tags = new ArrayList<Tag>();}
		my_tags.add(tag);
	}
	
	public ArrayList<String> getTagsToString(Book my_book){
		if(my_tags == null) {return new ArrayList<String>();}
		ArrayList<String> stringList = new ArrayList<String>();
		for(Tag tag : my_tags) {
			if(tag.getType().equals("person.Person")) {
				stringList.add(my_book.getPerson(tag.getRefID()).getName());
			}
			if(tag.getType().equals("world.Place")) {
				stringList.add(my_book.getPlace(tag.getRefID()).getName());
			}
		}
		return stringList;
	}
	
	private ArrayList<Tag> getTags(String tagType){
		if(my_tags == null) {return new ArrayList<Tag>();}
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		for(Tag tag : my_tags) {
			if(tag.getType().equals(tagType)) {
				if(tag.getType().equals("person.Person")) {
					tagList.add(tag);
				}
				if(tag.getType().equals("world.Place")) {
					tagList.add(tag);
				}
			}
		}
		return tagList;
	}
	
	public ArrayList<Person> getPersonByTag(){
		ArrayList<Tag> tagList = getTags("person.Person");
		if(tagList == null) {return new ArrayList<Person>();}
		ArrayList<Person> personList = new ArrayList<Person>();
		for(Tag tag : tagList) {
			personList.add(Book.getInstance().getPerson(tag.getRefID()));
		}
		return personList;
	}
	
	public ArrayList<Place> getPelaceByTag(){
		ArrayList<Tag> tagList = getTags("world.Place");
		if(tagList == null) {return new ArrayList<Place>();}
		ArrayList<Place> placeList = new ArrayList<Place>();
		for(Tag tag : tagList) {
			placeList.add(Book.getInstance().getPlace(tag.getRefID()));
		}
		return placeList;
	}
	
	public String getName() {
		return my_name;
	}

	public String getText() {
		return my_text;
	}
	
	public void edit(String newName, String newText) {
		my_name = newName;
		my_text = newText;
	}
	
	public String getShortTextPreview() {
		if(my_text.length() > 200) {			
			return my_text.substring(0, 200) + "...";
		}
		return my_text;
	}
	
	public boolean isUnsorted() {
		return isUnsorted;
	}
	
	public void makeToChapter() {
		isChapterStart = true;
		if(isUnsorted) {
			isUnsorted = false;
			Book.getInstance().getSectionList().resortSections();
		}
	}
	
	public void removeChapterStatus() {
		isChapterStart = false;
		if(isUnsorted) {
			isUnsorted = false;
			Book.getInstance().getSectionList().resortSections();
		}
	}
	
	public boolean isChapter() {
		return isChapterStart;
	}
	
	public void sortUp() {
		if(isUnsorted) {
			isUnsorted = false;
			Book.getInstance().getSectionList().resortSections();
		} else {
			Book.getInstance().getSectionList().sortSectionUp(my_uID);
		}
	}
	
	public void sortDown() {
		if(isUnsorted) {
			isUnsorted = false;
			Book.getInstance().getSectionList().resortSections();
		} else {
			Book.getInstance().getSectionList().sortSectionDown(my_uID);
		}
	}

	public void setTimestamp(Timestamp newTimestamp) {
		my_timestamp = newTimestamp;
	}
	
	public Timestamp getTimestamp() {
		return my_timestamp;
	}

	public boolean hasTimestamp() {
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
		if(my_relationshipSwitches == null) {my_relationshipSwitches = new ArrayList<Relationship>();}
		my_relationshipSwitches.add(newRelationship);
		Book.getInstance().save();
	}

	public ArrayList<Relationship> getRelationships() {
		if(my_relationshipSwitches == null) {return new ArrayList<Relationship>();}
		return my_relationshipSwitches;
	}
	
	public int getCountWords() {
		if(my_text.equals("")) {
			return 0;
		}
		String[] words = my_text.split("\\s+");
	    return words.length;
	}
}
