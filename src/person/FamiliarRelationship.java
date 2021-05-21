package person;

import java.util.ArrayList;

import book.Book;
import global.ObjectID;

public class FamiliarRelationship {

	private final ArrayList<ObjectID> my_parents;
	private final ArrayList<ObjectID> my_children;
	private final ArrayList<ObjectID> my_spouse; //Ehepartner
	private final ArrayList<ObjectID> my_distantDescendant; //Entfernter Nachkomme
	private final ArrayList<ObjectID> my_distantAncestor;
	
	public FamiliarRelationship() {
		my_parents = new ArrayList<>();
		my_children = new ArrayList<>();
		my_spouse = new ArrayList<>();
		my_distantDescendant = new ArrayList<>();
		my_distantAncestor = new ArrayList<>();
	}

	public void addRelationship(
			boolean childRelation, boolean parentRelation, boolean spouseRelation, boolean distantDescendant,
			boolean distantAncestor, ObjectID otherPersonID
	) {
		if(childRelation) {
			my_parents.add(otherPersonID);
		} else if(parentRelation) {
			my_children.add(otherPersonID);
		} else if(spouseRelation) {
			my_spouse.add(otherPersonID);
		} else if(distantDescendant) {
			my_distantAncestor.add(otherPersonID);
		} else if(distantAncestor) {
			my_distantDescendant.add(otherPersonID);
		}
		Book.getInstance().save();
	}
	
	public ArrayList<ObjectID> getParents(){
		return my_parents;
	}

	public ArrayList<ObjectID> getChildren() {
		return my_children;
	}

	public ArrayList<ObjectID> getSpouse() {
		return my_spouse;
	}

	public ArrayList<ObjectID> getDistantDescendant(){
		return my_distantDescendant;
	}
	
	public ArrayList<ObjectID> getDistantAncestor(){
		return my_distantAncestor;
	}
	
	public ArrayList<ObjectID> getSiblings(ObjectID myID){
		ArrayList<ObjectID> siblings = new ArrayList<>();
		for(ObjectID parentID : my_parents) {
			for(ObjectID sibling : Book.getInstance().getSociety().getPerson(parentID).getFamiliarRelation().getChildren()) {
				if(!sibling.equals(myID)) {
					siblings.add(sibling);
				}
			}
		}
		return siblings;
	}
	
	public void removeFamiliarRelationshipChild(ObjectID otherPerson) {
		my_children.remove(otherPerson);
	}

	public void removeFamiliarRelationshipParent(ObjectID otherPerson) {
		my_parents.remove(otherPerson);
	}

	public void removeFamiliarRelationshipSpouse(ObjectID otherPerson) {
		my_spouse.remove(otherPerson);
	}
	
	public void removeFamiliarRelationshipDistantDescendant(ObjectID otherPerson) {
		my_distantDescendant.remove(otherPerson);
	}

	public void removeFamiliarRelationshipDistantAncestor(ObjectID otherPerson) {
		my_distantAncestor.remove(otherPerson);
	}
}
