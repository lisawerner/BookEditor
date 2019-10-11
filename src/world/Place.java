package world;

import java.util.ArrayList;
import java.util.Collections;

import book.Book;
import global.ObjectID;

public class Place {
	
	private ObjectID my_uID;
	
	private String my_name;
	private String my_type;
	
	private ObjectID my_parentPlace;
	private ArrayList<ObjectID> my_childrenPlaces;
	
	
	public Place(String newName, String newType, ObjectID newParent) {
		my_uID = new ObjectID(this.getClass().getName());
		
		my_name = newName;
		my_type = newType;
		
		my_parentPlace = newParent;
		if(newParent != null) {			
			Book.getInstance().getPlace(newParent).addChild(my_uID);
		}
		my_childrenPlaces = new ArrayList<ObjectID>();
	}
	
	public ObjectID getID() {
		return my_uID;
	}

	public String getName() {
		return my_name;
	}

	public String getType() {
		return my_type;
	}

	public void editPlace(String newName, String newType, ObjectID newParent, Book my_book) {
		my_name = newName;
		my_type = newType;
		if(my_parentPlace != null) {
			if(newParent != null) {				
				if(!my_parentPlace.getIDtoString().equals(newParent.getIDtoString())) {
					my_book.getPlace(my_parentPlace).removeChild(my_uID);
				}			
			} else {
				my_book.getPlace(my_parentPlace).removeChild(my_uID);
			}
		}
		my_parentPlace = newParent;
		if(newParent != null) {	
			my_book.getPlace(newParent).addChild(my_uID);
		}
	}

	private void removeChild(ObjectID childID) {
		if(my_childrenPlaces == null) {my_childrenPlaces = new ArrayList<ObjectID>();}
		my_childrenPlaces.remove(childID);
	}

	public String getParentInfo(Book my_book) {
		if(my_parentPlace == null) {return null;}
		Place parent = my_book.getPlace(my_parentPlace);
		return parent.getName() + "[Type: " + parent.getType() + "]";
	}
	
	public ObjectID getParentRef() {
		return my_parentPlace;
	}
	
	public ArrayList<Place> getChildrenObject() {
		if(my_childrenPlaces == null) {return new ArrayList<Place>();}
		if(my_childrenPlaces.size() == 0) {return new ArrayList<Place>();}
		ArrayList<Place> result = new ArrayList<Place>();
		for(ObjectID childID : my_childrenPlaces) {
			result.add(Book.getInstance().getPlace(childID));
		}
		return result;
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
		System.out.println(my_name);
		if(my_childrenPlaces != null) {			
			ArrayList<String> listOfNames = new ArrayList<String>();
			for(ObjectID placeID : my_childrenPlaces) {
				System.out.println(placeID.getIDtoString());
				listOfNames.add(Book.getInstance().getPlace(placeID).getName());
			}
			Collections.sort(listOfNames);
			ArrayList<ObjectID> sortedChildrenList = new ArrayList<ObjectID>();
			for(String placeName : listOfNames) {
				for(ObjectID placeID : my_childrenPlaces) {
					Place child = Book.getInstance().getPlace(placeID);
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

}
