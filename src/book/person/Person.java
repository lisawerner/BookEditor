package book.person;

import global.ObjectID;
import global.SerializedObject;

import java.util.ArrayList;

public class Person extends SerializedObject {
	
	private final PersonInformation my_information;
	
	private FamiliarRelationship my_familiarRelationships;
	
	private ArrayList<ObjectID> my_relationships;
	
	public Person(String newName, String newNickname, 
			String newAge, boolean ageBookStart, boolean ageFirstAppearance, boolean isDeathBeforeBookStart, String newDeathTime,
			boolean newIsSuperMainCharacter, boolean newIsMainCharacter,
			String newNotes, ObjectID newRace) {
		super();
		
		my_information = new PersonInformation(newName, newNickname, 
				newAge, ageBookStart, ageFirstAppearance, isDeathBeforeBookStart, newDeathTime,
				newIsSuperMainCharacter, newIsMainCharacter, newNotes, newRace);
		my_relationships = new ArrayList<>();
		my_familiarRelationships = new FamiliarRelationship();
	}
	
	
	public void addRelationship(ObjectID relationship) {
		if(my_relationships == null) {my_relationships = new ArrayList<>();}
		my_relationships.add(relationship);
		//TODO: They should be sorted chronological!???!?!?!
		// But what happens if a section has no timestamp? -> Then sort by order of sections?
	}

	public void removeRelationship(ObjectID relationshipID) {
		if(my_relationships == null) {my_relationships = new ArrayList<>();}
		for(ObjectID currentRelationshipID : my_relationships) {
			if(currentRelationshipID.getIDtoString().equals(relationshipID.getIDtoString())) {
				my_relationships.remove(currentRelationshipID);
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
	
	public FamiliarRelationship getFamiliarRelation() {
		if(my_familiarRelationships == null) {my_familiarRelationships = new FamiliarRelationship();}
		return my_familiarRelationships;
	}

}
