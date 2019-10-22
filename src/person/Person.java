package person;

import java.util.ArrayList;

import global.ObjectID;

public class Person {
	
	private ObjectID my_uID;
	
	private PersonInformation my_information;
	
	private ArrayList<ObjectID> my_relationships;
	
	public Person(String newName, String newAge, boolean ageBookStart, boolean ageFirstAppearance, 
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes) {
		my_uID = new ObjectID(this.getClass().getName());
		
		my_information = new PersonInformation(newName, newAge, ageBookStart, ageFirstAppearance, newIsSuperMainCharapter, newIsMainCharapter, newNotes);
		
		my_relationships = new ArrayList<ObjectID>();
	}
	
	public ObjectID getID() {
		return my_uID;
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

}
