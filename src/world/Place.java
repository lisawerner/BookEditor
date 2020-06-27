package world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import book.Book;
import global.ObjectID;
import global.SerializedObject;

public class Place  extends SerializedObject{
	
	private String my_name;
	private String my_type;
	private String my_notes;
	
	private ObjectID my_parentPlace;
	private ArrayList<ObjectID> my_childrenPlaces;
	
	
	public Place(String newName, String newType, String newNotes) {
		super();
		
		my_name = newName;
		my_type = newType;
		my_notes = newNotes;
		
		my_parentPlace = null;
		my_childrenPlaces = new ArrayList<ObjectID>();
	}

	public String getName() {
		return my_name;
	}

	public String getType() {
		return my_type;
	}

	public void editPlace(String newName, String newType, String newNotes) {
		my_name = newName;
		my_type = newType;
		my_notes = newNotes;
		
		Book.getInstance().getWorld().changePlace();
	}

	private void removeChild(ObjectID childID) {
		if(my_childrenPlaces == null) {my_childrenPlaces = new ArrayList<ObjectID>();}
		my_childrenPlaces.remove(childID);
	}

	public String getParentInfo() {
		if(my_parentPlace == null) {return null;}
		Place parent = Book.getInstance().getWorld().getPlace(my_parentPlace);
		return parent.getName() + "[Type: " + parent.getType() + "]";
	}
	
	public ObjectID getParentRef() {
		return my_parentPlace;
	}
	
	public List<Place> getChildrenObject() {
		if(my_childrenPlaces == null) {return new ArrayList<Place>();}
		if(my_childrenPlaces.size() == 0) {return new ArrayList<Place>();}
		return my_childrenPlaces.stream().map(childID -> Book.getInstance().getWorld().getPlace(childID)).collect(Collectors.toList());
	}
	
	public ArrayList<ObjectID> getChildrenIDs(){
		return my_childrenPlaces;
	}

	public void addChild(ObjectID childID) {
		if(my_childrenPlaces == null) {my_childrenPlaces = new ArrayList<ObjectID>();}
		my_childrenPlaces.add(childID);
		removeDoubleChildren();
		sortChildrenAlphabetic();
	}
	
	private void removeDoubleChildren() {
		ArrayList<ObjectID> newList = new ArrayList<ObjectID>();
		for(ObjectID oldID : my_childrenPlaces) {
			boolean found = false;
			for(ObjectID newID : newList) {
				if(newID.getIDtoString().equals(oldID.getIDtoString())) {
					found = true;
					break;
				}
			}
			if(!found) {
				newList.add(oldID);
			}
		}
		my_childrenPlaces = newList;
	}
	
	private void sortChildrenAlphabetic() {
		if(my_childrenPlaces != null) {			
			ArrayList<String> listOfNames = new ArrayList<String>();
			for(ObjectID placeID : my_childrenPlaces) {
				listOfNames.add(Book.getInstance().getWorld().getPlace(placeID).getName());
			}
			Collections.sort(listOfNames);
			ArrayList<ObjectID> sortedChildrenList = new ArrayList<ObjectID>();
			for(String placeName : listOfNames) {
				for(ObjectID placeID : my_childrenPlaces) {
					Place child = Book.getInstance().getWorld().getPlace(placeID);
					if(placeName.equals(child.getName())) {
						sortedChildrenList.add(child.getID());
					}
				}
				
			}
			my_childrenPlaces = sortedChildrenList;
		}
	}
	
	public boolean equals(Place otherPlace) {
		return this.my_uID.getIDtoString().equals(otherPlace.getID().getIDtoString());
	}
	
	public boolean equals(ObjectID otherPlace) {
		return this.my_uID.getIDtoString().equals(otherPlace.getIDtoString());
	}

	public void setParent(ObjectID newParent) {
		if(my_parentPlace != null) {
			if(newParent != null) {				
				if(!my_parentPlace.getIDtoString().equals(newParent.getIDtoString())) {
					Book.getInstance().getWorld().getPlace(my_parentPlace).removeChild(my_uID);
				}			
			} else {
				Book.getInstance().getWorld().getPlace(my_parentPlace).removeChild(my_uID);
			}
		}
		my_parentPlace = newParent;
		if(newParent != null) {	
			Book.getInstance().getWorld().getPlace(newParent).addChild(my_uID);
		}
		
		Book.getInstance().getWorld().changePlace();
	}

	public String getNotes() {
		return my_notes;
	}

}
