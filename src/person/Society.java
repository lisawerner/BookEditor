package person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import book.Book;
import global.ObjectID;

public class Society {
	
	private ArrayList<Person> my_persons;
	
	public Society() {
		my_persons = new ArrayList<Person>();
	}
	
	public List<Person> getPersonListOfSuperMainCharacters() {
		if(my_persons == null) {return new ArrayList<Person>();}
		return my_persons.stream().filter(person -> person.getInformation().isSuperMainChar()).collect(Collectors.toList());
	}

	public List<Person> getPersonListImportantCharacters() {
		if(my_persons == null) {return new ArrayList<Person>();}
		return my_persons.stream().filter(person -> person.getInformation().isFrequentlyChar()).collect(Collectors.toList());
	}

	public List<Person> getPersonListTheRest() {
		if(my_persons == null) {return new ArrayList<Person>();}
		return my_persons.stream().filter(person -> person.getInformation().isSomebody()).collect(Collectors.toList());
	}
	
	public void addPerson(Person newPerson) {
		if(my_persons == null) { my_persons = new ArrayList<Person>();}
		my_persons.add(newPerson);
		sortPersons();
		Book.getInstance().save();
	}
	
	void sortPersons() {
		//TODO: Or sort: most set tags; -> Or both and let user decide ^^
		ArrayList<Person> listOfSuperMainCharNames = (ArrayList<Person>) my_persons.stream().filter(person -> person.getInformation().isSuperMainChar()).collect(Collectors.toList());
		ArrayList<Person> listOfMainCharNames = (ArrayList<Person>) my_persons.stream().filter(person -> person.getInformation().isFrequentlyChar()).collect(Collectors.toList());
		ArrayList<Person> listOfSomebodyNames = (ArrayList<Person>) my_persons.stream().filter(person -> person.getInformation().isSomebody()).collect(Collectors.toList());
		
		List<Person> result = listOfSuperMainCharNames.stream().sorted(Comparator.comparing(n -> n.getInformation().getName())).collect(Collectors.toList());
		List<Person> result2 = listOfMainCharNames.stream().sorted(Comparator.comparing(n -> n.getInformation().getName())).collect(Collectors.toList());
		List<Person> result3 = listOfSomebodyNames.stream().sorted(Comparator.comparing(n -> n.getInformation().getName())).collect(Collectors.toList());
		
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
