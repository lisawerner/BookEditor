package person;

import book.Book;
import global.ObjectID;

public class Relationship {
	
	private ObjectID my_uID;
	
	private ObjectID refToPersonA;
	private ObjectID refToPersonB;
	
	private String my_describingRelationshipType;
	
	public Relationship(Person personA, Person personB, String newDescribingRelationshipType) {
		my_uID = new ObjectID(this.getClass().getName());
		
		refToPersonA = personA.getID();
		personA.addRelationship(my_uID);
		refToPersonB = personB.getID();
		personB.addRelationship(my_uID);
		
		my_describingRelationshipType = newDescribingRelationshipType;
	}
	
	public ObjectID getID() {
		return my_uID;
	}

	public ObjectID getPersonA() {
		return refToPersonA;
	}
	
	public ObjectID getPersonB() {
		return refToPersonB;
	}
	
	public ObjectID getOtherPerson(ObjectID ownID) {
		if(refToPersonA.getIDtoString().equals(ownID.getIDtoString())) {
			return refToPersonB;
		} else if(refToPersonB.getIDtoString().equals(ownID.getIDtoString())) {
			return refToPersonA;
		}
		return null;
	}
	
	public String getDescribingRelationshipType() {
		return my_describingRelationshipType;
	}

	public void change(Person personA, Person personB, String newDescribingRelationshipType) {
		if(!refToPersonA.getIDtoString().equals(personA.getID().getIDtoString())) {
			Book.getInstance().getSociety().getPerson(refToPersonA).removeRelationship(my_uID);
			personA.addRelationship(my_uID);
			refToPersonA = personA.getID();
		}
		if(!refToPersonB.getIDtoString().equals(personB.getID().getIDtoString())) {
			Book.getInstance().getSociety().getPerson(refToPersonB).removeRelationship(my_uID);
			personB.addRelationship(my_uID);
			refToPersonB = personB.getID();
		}
			
		my_describingRelationshipType = newDescribingRelationshipType;
		
		Book.getInstance().save();
	}

	public String getSwitchToString() {
		String result = "";
		result += Book.getInstance().getSociety().getPerson(refToPersonA).getInformation().getName() + " + ";
		result += Book.getInstance().getSociety().getPerson(refToPersonB).getInformation().getName() + "   &#8594;  ";
		result += my_describingRelationshipType;
		return result;
	}

}
