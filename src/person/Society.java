package person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

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
		ArrayList<Person> listOfSuperMainCharNames = (ArrayList<Person>) my_persons.stream().filter(person -> person.isSuperMainChar()).collect(Collectors.toList());
		ArrayList<Person> listOfMainCharNames = (ArrayList<Person>) my_persons.stream().filter(person -> person.isFrequentlyChar()).collect(Collectors.toList());
		ArrayList<Person> listOfSomebodyNames = (ArrayList<Person>) my_persons.stream().filter(person -> person.isSomebody()).collect(Collectors.toList());
		
		ArrayList<Person> result = (ArrayList<Person>) listOfSuperMainCharNames.stream().sorted(Comparator.comparing(n -> n.getName())).collect(Collectors.toList());
		ArrayList<Person> result2 = (ArrayList<Person>) listOfMainCharNames.stream().sorted(Comparator.comparing(n -> n.getName())).collect(Collectors.toList());
		ArrayList<Person> result3 = (ArrayList<Person>) listOfSomebodyNames.stream().sorted(Comparator.comparing(n -> n.getName())).collect(Collectors.toList());
		
		my_persons = new ArrayList<Person>();
		my_persons.addAll(result);
		my_persons.addAll(result2);
		my_persons.addAll(result3);
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