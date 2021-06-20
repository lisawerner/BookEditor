package book.person;

import book.content.Book;
import global.ObjectID;
import global.SerializedObject;

public class Relationship  extends SerializedObject{
	
	private ObjectID refToPersonA;
	private ObjectID refToPersonB;
	
	private String my_describingRelationshipType;
	
	public Relationship(Person personA, Person personB, String newDescribingRelationshipType) {
		super();

		refToPersonA = personA.getID();
		personA.addRelationship(this.my_uID);
		refToPersonB = personB.getID();
		personB.addRelationship(this.my_uID);
		
		my_describingRelationshipType = newDescribingRelationshipType;
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
			Book.getInstance().getSociety().getPerson(refToPersonA).removeRelationship(this.my_uID);
			personA.addRelationship(getID());
			refToPersonA = personA.getID();
		}
		if(!refToPersonB.getIDtoString().equals(personB.getID().getIDtoString())) {
			Book.getInstance().getSociety().getPerson(refToPersonB).removeRelationship(this.my_uID);
			personB.addRelationship(this.my_uID);
			refToPersonB = personB.getID();
		}
			
		my_describingRelationshipType = newDescribingRelationshipType;
		
		Book.getInstance().save();
	}

	public String getSwitchToString() {
		String result = "";
		result += Book.getInstance().getSociety().getPerson(refToPersonA).getInformation().getNickname() + " + ";
		result += Book.getInstance().getSociety().getPerson(refToPersonB).getInformation().getNickname() + "   &#8594;  ";
		result += my_describingRelationshipType;
		return result;
	}

}
