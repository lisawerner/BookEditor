package book;

import global.ObjectID;
import person.Person;
import person.Relationship;
import world.Place;

import java.util.ArrayList;
import java.util.Collections;

import GUI_components.Theme;
import global.FileManager;

public class Book {
	
	private static Book my_instance;
	private ObjectID my_uID;
	private String my_filename;
	private Theme my_theme;
	
	private String my_title;
	private SectionList my_sectionlist;

	private ArrayList<Person> my_persons;
	private ArrayList<Place> my_world;
	private boolean useGregorianCalendar;

	// About Print-Settings:
	private boolean isWorktitle;
	private boolean printChapterName;
		
	
	private Book() {
		my_title = "";
		isWorktitle = false;
		
		my_uID = new ObjectID(this.getClass().getName());
		my_filename = "empty.json";
		
		my_sectionlist = new SectionList();
		
		my_persons = new ArrayList<Person>();
		
		my_world = new ArrayList<Place>();
		
		my_theme = null;
	}
	
	public void createNewBook(String newTitle, boolean isNewTitleWorktitle) {
		my_title = newTitle;
		isWorktitle = isNewTitleWorktitle;
		my_filename = newTitle + "_" + my_uID.getIDtoString().substring(0, 13) + ".json";
		save();
	}
	
	public void loadFromFile(String selectedBook) {
		my_instance = (Book) FileManager.loadJSONFile(selectedBook, Book.class);
	}
	
	public static Book getInstance() {
		if(my_instance == null) {
			my_instance = new Book();
		}
		return my_instance;
	}
	
	public void save() {
		if(!"".equals(my_title)) {			
			FileManager.saveJSONFile(my_filename, this);
		}
	}
	
	public String getTitle() {
		return my_title;
	}
	
	public boolean isWorkTitle() {
		return isWorktitle;
	}
	
	public void changeBookSettings(String newTitle, boolean isNewTitleWorktitle, boolean newGregorainCalendarSettings, boolean chapterPrintName, Theme newTheme) {
		my_title = newTitle;
		isWorktitle = isNewTitleWorktitle;
		useGregorianCalendar = newGregorainCalendarSettings;
		printChapterName = chapterPrintName;
		my_theme = newTheme;
		save();
	}
	
	public void addPerson(Person newPerson) {
		if(my_persons == null) { my_persons = new ArrayList<Person>();}
		my_persons.add(newPerson);
		sortPersons();
		save();
	}
	
	private void sortPersons() {
		//TODO: Or sort: most set tags; -> Or both and let user decide ^^
		ArrayList<String> listOfSuperMainCharNames = new ArrayList<String>();
		ArrayList<String> listOfMainCharNames = new ArrayList<String>();
		ArrayList<String> listOfSomebodyNames = new ArrayList<String>();
		for(Person person : my_persons) {
			if(person.isSuperMainChar()) {				
				listOfSuperMainCharNames.add(person.getName());
			} else if(person.isFrequentlyChar()) {				
				listOfMainCharNames.add(person.getName());
			} else {				
				listOfSomebodyNames.add(person.getName());
			}
		}

		Collections.sort(listOfSuperMainCharNames);
		ArrayList<Person> sortedPersonList = new ArrayList<Person>();
		for(String personName : listOfSuperMainCharNames) {
			for(Person person : my_persons) {
				if(personName.equals(person.getName())) {
					sortedPersonList.add(person);
				}
			}
		}
		Collections.sort(listOfMainCharNames);
		for(String personName : listOfMainCharNames) {
			for(Person person : my_persons) {
				if(personName.equals(person.getName())) {
					sortedPersonList.add(person);
				}
			}
		}
		Collections.sort(listOfSomebodyNames);
		for(String personName : listOfSomebodyNames) {
			for(Person person : my_persons) {
				if(personName.equals(person.getName())) {
					sortedPersonList.add(person);
				}
			}
		}
		my_persons = sortedPersonList;
	}
	
	public ArrayList<Person> getPersonList(){
		if(my_persons == null) {return new ArrayList<Person>();}
		return my_persons;
	}
	
	public void changePerson(ObjectID personID, Person newPerson) {
		ArrayList<Person> newList = new ArrayList<Person>();
		
		for(Person person : my_persons) {
			if(person.equals(personID)) {
				newList.add(newPerson);
			} else {
				newList.add(person);
			}
		}
		
		my_persons = newList;
		sortPersons();
		save();
	}
	
	public Person getPerson(ObjectID personID) {
		for(Person person : my_persons) {
			if(person.equals(personID)) {
				return person;
			}
		}
		return null;
	}
	
	public void addPlace(Place newPlace) {
		if(my_world == null) {my_world = new ArrayList<Place>();}
		my_world.add(newPlace);
		sortPlace();
		save();
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
		save();
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
		ArrayList<Place> parentPlaceList = new ArrayList<Place>();
		for(Place place : my_world) {
			if(place.getParentRef() == null) {
				parentPlaceList.add(place);
			}
		}	
		ArrayList<String> listOfNames = new ArrayList<String>();
		for(Place place : parentPlaceList) {
			listOfNames.add(place.getName());
		}
		Collections.sort(listOfNames);
		ArrayList<Place> sortedParentList = new ArrayList<Place>();
		for(String placeName : listOfNames) {
			for(Place place : parentPlaceList) {
				if(placeName.equals(place.getName())) {
					sortedParentList.add(place);
				}
			}
			
		}
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
	
	public boolean useGregorianCalendar() {
		return useGregorianCalendar;
	}

	public Theme getTheme() {
		return my_theme;
	}

	public boolean printChapterName() {
		return printChapterName;
	}

	public boolean exportToTXT() {
		String book_text = my_title + "\n" + "\n";
		int countChapters = 1;
		for(Section section : my_sectionlist.getSections()) {
			if(section.isChapter()) {
				book_text += "\n" + "\n" + "*******************************************************************************" + "\n" ;
				if(printChapterName) {
					book_text += "###" + section.getName() + "###" + "\n" + "\n";
				} else {
					book_text += "Chapter " + countChapters + "\n" + "\n";
					countChapters++;
				}
			}
			book_text += section.getText() + "\n" + "\n";
		}
		return FileManager.exportTXTfile(my_title + ".txt", book_text);
		
	}

	public ArrayList<Person> getPersonListOfSuperMainCharacters() {
		if(my_persons == null) {return new ArrayList<Person>();}
		ArrayList<Person> superMains = new ArrayList<Person>();
		for(Person person : my_persons) {
			if(person.isSuperMainChar()) {
				superMains.add(person);
			}
		}
		return superMains;
	}

	public ArrayList<Person> getPersonListImportantCharacters() {
		if(my_persons == null) {return new ArrayList<Person>();}
		ArrayList<Person> mainChars = new ArrayList<Person>();
		for(Person person : my_persons) {
			if(person.isFrequentlyChar()) {
				mainChars.add(person);
			}
		}
		return mainChars;
	}

	public ArrayList<Person> getPersonListTheRest() {
		if(my_persons == null) {return new ArrayList<Person>();}
		ArrayList<Person> theRest = new ArrayList<Person>();
		for(Person person : my_persons) {
			if(person.isSomebody()) {
				theRest.add(person);
			}
		}
		return theRest;
	}
	
	public Relationship getRelationship(ObjectID relID) {
		for(Section section : my_sectionlist.getSections()) {
			for(Relationship relationship : section.getRelationships()) {
				if(relID.getIDtoString().equals(relationship.getID().getIDtoString())) {
					return relationship;
				}
			}
		}
		return null;
	}

	public SectionList getSectionList() {
		return my_sectionlist;
	}
	
}
