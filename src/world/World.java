package world;

import book.Book;
import global.ObjectID;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class World {
	
	private ArrayList<Place> my_world;
	
	public World() {
		my_world = new ArrayList<>();
	}
	
	public void addPlace(Place newPlace) {
		if(my_world == null) {my_world = new ArrayList<>();}
		my_world.add(newPlace);
		sortPlace();
		Book.getInstance().save();
	}

	public ArrayList<Place> getPlaces() {
		if(my_world == null) {return new ArrayList<>();}
		return my_world;
	}

	public void changePlace() {
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
		ArrayList<Place> completeSortedList = new ArrayList<>();
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

	public ArrayList<Place> getPossibleParentsList(Place parentPlace) {
		if(parentPlace == null) {
			return getPlaces();
		}
		return removeChildren(parentPlace, my_world);
	}
	
	private ArrayList<Place> removeChildren(Place parentPlace, ArrayList<Place> currentList){
		ArrayList<Place> withoutChildren = new ArrayList<>(currentList);
		
		if(parentPlace.getChildrenIDs() == null) {
			return currentList;
		}
		for(Place possibleChild : currentList) {
			for(ObjectID childID : parentPlace.getChildrenIDs()) {				
				if(childID.equals(possibleChild.getID())) {				
					withoutChildren.remove(possibleChild);
					withoutChildren = removeChildren(possibleChild, withoutChildren);
				}
			}
		}
		return withoutChildren;
	}

}
