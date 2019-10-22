package book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public Section(String newName) {
		my_uID = new ObjectID(this.getClass().getName());
		
		my_name = newName;
		my_text = "";
		
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
		return my_tags.stream().anyMatch(tag -> tag.getRefID().getIDtoString().equals(tagID.getIDtoString()));
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
	
	public ArrayList<String> getTagsToString(){
		if(my_tags == null) {return new ArrayList<String>();}
		ArrayList<String> stringList = new ArrayList<String>();
		for(Tag tag : my_tags) {
			if(tag.getType().equals("person.Person")) {
				stringList.add(Book.getInstance().getSociety().getPerson(tag.getRefID()).getInformation().getName());
			}
			if(tag.getType().equals("world.Place")) {
				stringList.add(Book.getInstance().getWorld().getPlace(tag.getRefID()).getName());
			}
		}
		return stringList;
	}
	
	private List<Tag> getTagsByTagtype(String tagType){
		if(my_tags == null) {return new ArrayList<Tag>();}
		return my_tags.stream().filter(tag -> tag.getType().equals(tagType)).collect(Collectors.toList());
	}
	
	public List<Person> getPersonByTag(){
		return getTagsByTagtype("person.Person").stream().map(tag -> Book.getInstance().getSociety().getPerson(tag.getRefID())).collect(Collectors.toList());
	}
	
	public List<Place> getPelaceByTag(){
		return getTagsByTagtype("world.Place").stream().map(tag -> Book.getInstance().getWorld().getPlace(tag.getRefID())).collect(Collectors.toList());
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
	
	public boolean equals(Section otherSection) {
		return this.my_uID.getIDtoString().equals(otherSection.getID().getIDtoString());
	}
	
	public boolean equals(ObjectID otherSection) {
		return this.my_uID.getIDtoString().equals(otherSection.getIDtoString());
	}
}
