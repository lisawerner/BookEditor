package person;

import java.util.ArrayList;
import java.util.List;

import book.Book;
import global.ObjectID;
import global.SerializedObject;

public class Person extends SerializedObject {
	
	private PersonInformation my_information;
	
	private FamiliarRelationship my_familiarRelationships;
	
	private ArrayList<ObjectID> my_relationships;
	
	public Person(String newName, String newNickname, 
			String newAge, boolean ageBookStart, boolean ageFirstAppearance, boolean isDeathBeforeBookStart, String newDeathTime,
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes, ObjectID newRace) {
		super();
		
		my_information = new PersonInformation(newName, newNickname, 
				newAge, ageBookStart, ageFirstAppearance, isDeathBeforeBookStart, newDeathTime,
				newIsSuperMainCharapter, newIsMainCharapter, newNotes, newRace);
		Book.getInstance().getSociety().updateRaceRepresantives(newRace, this.getID());
		my_relationships = new ArrayList<ObjectID>();
		my_familiarRelationships = new FamiliarRelationship();
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
	
	public PersonInformation getInformation() {
		return my_information;
	}
	
	public boolean equals(Person otherPerson) {
		return this.my_uID.getIDtoString().equals(otherPerson.getID().getIDtoString());
	}
	
	public boolean equals(ObjectID otherPerson) {
		return this.my_uID.getIDtoString().equals(otherPerson.getIDtoString());
	}

	protected void setRelationships(List<ObjectID> filteredRelationships) {
		my_relationships = (ArrayList<ObjectID>) filteredRelationships;	
	}
	
	public FamiliarRelationship getFamiliarRelation() {
		if(my_familiarRelationships == null) {my_familiarRelationships = new FamiliarRelationship();}
		return my_familiarRelationships;
	}

}
