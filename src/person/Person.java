package person;

import java.util.ArrayList;

import book.Book;
import global.ObjectID;

public class Person {
	
	private ObjectID my_uID;
	
	private String my_name;
	
	private String my_age;
	private boolean age_startAtBook;
	private boolean age_startAtFirstAppearance;
	
	private boolean isSuperMainCharapter;
	private boolean isMainCharapter;
	
	private String my_notes;
	
	private ArrayList<ObjectID> my_relationships;
	
	public Person(String newName, String newAge, boolean ageBookStart, boolean ageFirstAppearance, 
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes) {
		my_uID = new ObjectID(this.getClass().getName());
		
		my_name = newName;
		
		my_age = newAge;
		age_startAtBook = ageBookStart;
		age_startAtFirstAppearance = ageFirstAppearance;
		
		isSuperMainCharapter = newIsSuperMainCharapter;
		isMainCharapter = newIsMainCharapter;
		
		my_notes = newNotes;
		
		my_relationships = new ArrayList<ObjectID>();
	}
	
	public ObjectID getID() {
		return my_uID;
	}
	
	public String getName() {
		return my_name;
	}
	
	public String getAge() {
		return my_age;
	}
	
	public boolean getAgeBookStart() {
		return age_startAtBook;
	}
	
	public boolean getAgeFirstAppear() {
		return age_startAtFirstAppearance;
	}
	
	public String getNotes() {
		return my_notes;
	}
	
	public void editPerson(String newName, String newAge, boolean ageBookStart, boolean ageFirstAppearance, 
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes) {
		my_name = newName;
		my_age = newAge;
		
		age_startAtBook = ageBookStart;
		age_startAtFirstAppearance = ageFirstAppearance;
		
		isSuperMainCharapter = newIsSuperMainCharapter;
		isMainCharapter = newIsMainCharapter;
		
		my_notes = newNotes;
		Book.getInstance().save();
	}

	public void addRelationship(ObjectID relationship) {
		if(my_relationships == null) {my_relationships = new ArrayList<ObjectID>();}
		my_relationships.add(relationship);
		//TODO: Eigentlich müssten die chronologisch sortiert werden!!???!?!?! Was wenn Section keinen Timestamp hat? Dann in Reihenfolge der Sections?
	}

	public void removeRelationship(ObjectID relationshipID) {
		if(my_relationships == null) {my_relationships = new ArrayList<ObjectID>();}
		for(ObjectID relshipID : my_relationships) {
			if(relshipID.getIDtoString().equals(relationshipID.getIDtoString())) {
				my_relationships.remove(relshipID);
				break;
			}
		}
	}
	
	public ArrayList<ObjectID> getRelationships(){
		return my_relationships;
	}

	public boolean isSuperMainChar() {
		return isSuperMainCharapter;
	}

	public boolean isFrequentlyChar() {
		return !isSuperMainCharapter && isMainCharapter;
	}

	public boolean isSomebody() {
		return !isSuperMainCharapter && !isMainCharapter;
	}
	
	public boolean equals(Person otherPerson) {
		return this.my_uID.getIDtoString().equals(otherPerson.getID().getIDtoString());
	}
	
	public boolean equals(ObjectID otherPerson) {
		return this.my_uID.getIDtoString().equals(otherPerson.getIDtoString());
	}

}
