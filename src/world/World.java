package world;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import book.Book;
import global.ObjectID;

public class World {
	
	private ArrayList<Place> my_world;
	
	public World() {
		my_world = new ArrayList<Place>();
	}
	
	public void addPlace(Place newPlace) {
		if(my_world == null) {my_world = new ArrayList<Place>();}
		my_world.add(newPlace);
		sortPlace();
		Book.getInstance().save();
	}

	public ArrayList<Place> getPlaces() {
		if(my_world == null) {return new ArrayList<Place>();}
		return my_world;
	}

	public void changePlace(ObjectID placeID, Place newPlace) {
		ArrayList<Place> newList = new ArrayList<Place>();
		
		for(Place place : my_world) {
			if(place.getID().getIDtoString().equals(placeID.getIDtoString())) {
				newList.add(newPlace);
			} else {
				newList.add(place);
			}
		}
		my_world = newList;
		sortPlace();
		Book.getInstance().save();
	}

	public Place getPlace(ObjectID placeID) {
		for(Place place : my_world) {
			if(place.equals(placeID)) {
				return place;
			}
		}
		return null;
	}
	
	private void sortPlace() {
		//Select Parents First and sort alphabetical
		ArrayList<Place> parentPlaceList = (ArrayList<Place>) my_world.stream().filter(place -> place.getParentRef() == null).collect(Collectors.toList());
		ArrayList<Place> sortedParentList = (ArrayList<Place>) parentPlaceList.stream().sorted(Comparator.comparing(n -> n.getName())).collect(Collectors.toList());				

		//Create Sorted List of Parent-Children hierarchic and every level alphabetical
		ArrayList<Place> completeSortedList = new ArrayList<Place>();
		for(Place parent : sortedParentList) {
			completeSortedList.add(parent);
			for(Place child : parent.getChildrenObject()) {
				//Hint: Children should already sorted alphabetical, because they will be sorted by adding a new child automatically
				completeSortedList.add(child);
				if(child.getChildrenIDs().size() > 0) {
					completeSortedList = addChildren(completeSortedList, child);
				}
			}			
		}
		
		my_world = completeSortedList;
	}
	
	private ArrayList<Place> addChildren(ArrayList<Place> currentList, Place parent){
		for(Place child : parent.getChildrenObject()) {
			//Hint: Children should already sorted alphabetical, because they will be sorted by adding a new child automatically
			currentList.add(child);
			if(child.getChildrenIDs().size() > 0) {
				currentList = addChildren(currentList, child);
			}
		}
		return currentList;
	}

}