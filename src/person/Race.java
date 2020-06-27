package person;
import java.util.ArrayList;

import book.Book;
import global.ObjectID;
import global.SerializedObject;

public class Race extends SerializedObject {
	
	private String my_name;
		
	private String my_notes;

	private ArrayList<ObjectID> my_descendants;
	private ObjectID my_firstAscendant;
	private ObjectID my_secondAscendant;
	
	private ObjectID isSubtypeOf;
	private ArrayList<ObjectID> my_subtypes;

	private ArrayList<ObjectID> my_representative;
		
	
	public Race(String newRaceName, String newNotes) {
		super();
		
		my_name = newRaceName;
		my_notes = newNotes;
		
		my_descendants = new ArrayList<ObjectID>();
		my_firstAscendant = null;
		my_secondAscendant = null;
		
		isSubtypeOf = null;
		my_subtypes = new ArrayList<ObjectID>();
		
		my_representative = new ArrayList<ObjectID>();		
	}

	public String getName() {
		return my_name;
	}

	public String getNotes() {
		return my_notes;
	}

	public void addRepresentative(ObjectID personID) {
		my_representative.add(personID);
	}

	public void removeRepresentative(ObjectID personID) {
		my_representative.remove(personID);
	}
	
	public ArrayList<ObjectID> getRepresentative(){
		return my_representative;
	}

	public void edit(String newRaceName, String newNotes) {
		my_name = newRaceName;
		my_notes = newNotes;
		
		Book.getInstance().save();
	}

	public boolean hasAscendants() {
		return my_firstAscendant != null || my_secondAscendant != null;
	}

	public ArrayList<Race> getPossibleAscendants(){
		ArrayList<Race> possibleRaces = new ArrayList<Race>();
		if(my_subtypes == null){my_subtypes = new ArrayList<ObjectID>();}
		if(my_descendants == null){my_descendants = new ArrayList<ObjectID>();}
		ArrayList<ObjectID> allDescendants = getCompleteDescendantList();
		for(Race race : Book.getInstance().getSociety().getRaces()) {
			if(race.isPossible(this, my_subtypes, allDescendants)) {
				possibleRaces.add(race);
			}
		}
		return possibleRaces;
	}
	
	private boolean isPossible(Race possibleEqualsRace, ArrayList<ObjectID> subtypeContainer, ArrayList<ObjectID> descendantList){
		boolean isCurrentRaceSubtypeOfRace = false;
		for(ObjectID subtypes : subtypeContainer){
			if(subtypes.equals(this.getID())){
				isCurrentRaceSubtypeOfRace = true;
			}
		}
		boolean isCurrentRaceDescendnatOfRace = false;
		for(ObjectID child : descendantList){
			if(child.equals(this.getID())){
				isCurrentRaceDescendnatOfRace = true;
			}
		}
		return !this.getID().equals(possibleEqualsRace.getID())
				&& this.isSubtypeOf == null
				&& !isCurrentRaceSubtypeOfRace
				&& !isCurrentRaceDescendnatOfRace;
	}
	
	private ArrayList<ObjectID> getCompleteDescendantList(){
		ArrayList<ObjectID> descendantList = new ArrayList<ObjectID>();
		descendantList.addAll(my_descendants);
		for(ObjectID desc : my_descendants){
			descendantList.addAll(Book.getInstance().getSociety().getRace(desc).getCompleteDescendantList());
		}
		return descendantList;
	}
	
	public void setSingleAscending(ObjectID newAscendant) {
		//Remove old relationships
		if(my_firstAscendant != null) {		
			Book.getInstance().getSociety().getRace(my_firstAscendant).removeDescendant(this.getID());
		}
		if(my_secondAscendant != null) {			
			Book.getInstance().getSociety().getRace(my_secondAscendant).removeDescendant(this.getID());
		}
		
		//Set new relationships
		my_firstAscendant = newAscendant;
		my_secondAscendant = null;
		Book.getInstance().getSociety().getRace(my_firstAscendant).addDescendant(this.getID());
		
		Book.getInstance().save();
	}

	private void removeDescendant(ObjectID removeDescendant) {
		if(my_descendants == null) {my_descendants = new ArrayList<ObjectID>();}
		if(my_descendants != null) {
			my_descendants.remove(removeDescendant);
			Book.getInstance().save();
		}		
	}

	private void addDescendant(ObjectID newDescendant) {
		if(my_descendants == null) {my_descendants = new ArrayList<ObjectID>();}
		if(!my_descendants.contains(newDescendant)) {
			my_descendants.add(newDescendant);
			Book.getInstance().save();
		}		
	}

	public ObjectID getFirstAscendant() {
		return my_firstAscendant;
	}

	public ArrayList<ObjectID> getDescendants() {
		if(my_descendants == null) {return new ArrayList<ObjectID>();}
		return my_descendants;
	}
	
	public ObjectID getParentRace() {
		return isSubtypeOf;
	}

	public ArrayList<Race> getPossibleParents() {
		ArrayList<Race> selectedRaces = new ArrayList<Race>();
		for(Race race : Book.getInstance().getSociety().getRaces()) {
			if(!this.getID().equals(race.getID()) && race.isSubtypeOf == null) {
				selectedRaces.add(race);
			}
		}
		return selectedRaces;
	}

	public void setParentType(ObjectID newParent) {
		//Remove old relationship
		if(isSubtypeOf != null) {
			Book.getInstance().getSociety().getRace(isSubtypeOf).removeSubtype(this.getID());
		}
		
		//Set new relationship
		isSubtypeOf = newParent;
		Book.getInstance().getSociety().getRace(isSubtypeOf).addSubtype(this.getID());
	}

	private void addSubtype(ObjectID addChild) {
		if(my_subtypes == null) {my_subtypes = new ArrayList<ObjectID>();}
		if(!my_subtypes.contains(addChild)) {
			my_subtypes.add(addChild);
			Book.getInstance().save();
		}
	}

	private void removeSubtype(ObjectID removerace) {
		if(my_subtypes != null) {
			ArrayList<ObjectID> newSubtypeList = new ArrayList<ObjectID>();
			for(ObjectID id : my_subtypes){
				if(!id.equals(removerace)){
					newSubtypeList.add(id);
				}
			}
			my_subtypes = newSubtypeList;
			Book.getInstance().save();
		}		
	}

	public ArrayList<ObjectID> getSubtypes() {
		if(my_subtypes == null) {my_subtypes = new ArrayList<ObjectID>();}
		return my_subtypes;
	}
	
}
