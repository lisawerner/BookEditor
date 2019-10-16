package person;

import java.util.ArrayList;
import java.util.Collections;

import book.Book;
import global.ObjectID;

public class Society {
	
	private ArrayList<Person> my_persons;
	
	public Society() {
		my_persons = new ArrayList<Person>();
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
	
	public void addPerson(Person newPerson) {
		if(my_persons == null) { my_persons = new ArrayList<Person>();}
		my_persons.add(newPerson);
		sortPersons();
		Book.getInstance().save();
	}
	
	void sortPersons() {
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
	
	public Person getPerson(ObjectID personID) {
		for(Person person : my_persons) {
			if(person.equals(personID)) {
				return person;
			}
		}
		return null;
	}

}
