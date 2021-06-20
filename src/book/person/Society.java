package book.person;

import book.content.Book;
import book.content.Chapter;
import book.content.Section;
import global.ObjectID;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Society {
	
	private ArrayList<Person> my_persons;

	private RaceSystem my_raceSystem;
	
	public List<Person> getPersonListOfSuperMainCharacters() {
		if(my_persons == null) {return new ArrayList<>();}
		return my_persons.stream().filter(person -> person.getInformation().isSuperMainChar()).collect(Collectors.toList());
	}

	public List<Person> getPersonListImportantCharacters() {
		if(my_persons == null) {return new ArrayList<>();}
		return my_persons.stream().filter(person -> person.getInformation().isFrequentlyChar()).collect(Collectors.toList());
	}

	public List<Person> getPersonListTheRest() {
		if(my_persons == null) {return new ArrayList<>();}
		return my_persons.stream().filter(person -> person.getInformation().isSomebody()).collect(Collectors.toList());
	}
	
	public void addPerson(Person newPerson) {
		if(my_persons == null) { my_persons = new ArrayList<>();}
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
		
		my_persons = new ArrayList<>();
		my_persons.addAll(result);
		my_persons.addAll(result2);
		my_persons.addAll(result3);
	}
	
	public ArrayList<Person> getPersonList(){
		if(my_persons == null) {return new ArrayList<>();}
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

	public List<Person> getPersonListNeverTagged() {
		List<Person> filteredPersons = new ArrayList<>();
		for(Person person : my_persons) {	
			boolean neverTagged = true;
			for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {				
				for(Section section : chapter.getSections()) {
					if(section.containsPerson(person)) {
						neverTagged = false;
						break;
					}
					
				}
			}
			if(neverTagged) {				
				filteredPersons.add(person);
			}
		}
		return filteredPersons;
	}

	public List<Person> getPersonListWithMissingRelationship() {
		List<Person> filteredPersons = new ArrayList<>();
		
		//TODO: that's crazy shit o.O
		// -> move Relationship to single class for relationship list and only add relationshipID to section
		// -> do not search relationships over looking for each section o.O
		for(Person person : my_persons) {
			boolean hasMainRelationship = false;
			for(ObjectID relPerson : person.getRelationships()) {
				//Relationships are saved in every section -> for each section
				for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {					
					for(Section section : chapter.getSections()) {
						for(Relationship relationship : section.getRelationships()) {
							//Check if Relationship in Section is the relationship, of this book.person
							if(relPerson.equals(relationship.getID())) {
								if(Book.getInstance().getSociety().getPerson(relationship.getOtherPerson(person.getID())).getInformation().isSuperMainChar()) {
									hasMainRelationship = true;
									break;
								}
							}
						}
						
					}
				}
			}
			if(!hasMainRelationship) {
				filteredPersons.add(person);
			}
		}
		return filteredPersons;
	}

	public List<Person> getPersonWithRelationshipToPerson(boolean relationType, Person checkingPerson) {
		List<Person> filteredPersons = new ArrayList<>();
		
		if(checkingPerson != null) {			
			//TODO: that's crazy shit o.O
			// -> move Relationship to single class for relationship list and only add relationshipID to section
			// -> do not search relationships over looking for each section o.O
			for(Person currentPerson : my_persons) {
				if(!currentPerson.equals(checkingPerson)) {					
					boolean hasRelationship = false;
					for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {						
						for(Section section : chapter.getSections()) {
							for(Relationship relationship : section.getRelationships()) {
								if(relationship.getOtherPerson(checkingPerson.getID()) != null) {
									if(relationship.getOtherPerson(checkingPerson.getID()).equals(currentPerson.getID())) {
										hasRelationship = true;
										break;
									}
								}
							}
						}
					}
					
					if((relationType && hasRelationship) || (!relationType && !hasRelationship)) {
						filteredPersons.add(currentPerson);
					}
				}
			}
		}

		return filteredPersons;
	}

	public void deletePerson(Person person) {
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section section : chapter.getSections()) {
				//Delete Tags
				section.removeTag(person.getID());
				//Delete Relationships
				section.removeRelationship(person);
			}
		}
		//Delete Person
		ArrayList<Person> newList = new ArrayList<>();
		for(Person currentPerson : my_persons) {
			if(!currentPerson.equals(person)) {
				newList.add(currentPerson);
			}
		}
		my_persons = newList;
		Book.getInstance().save();
	}
	
	public ArrayList<Person> getPersonListByRace(Race filteredRace) {
		if(filteredRace == null) {
			return this.getPersonListWithEmptyRace();
		}

		ArrayList<Person> filteredList = new ArrayList<>();
		
		for(Person person : my_persons) {
			if(person.getInformation().getRace() != null) {
				if(person.getInformation().getRace().equals(filteredRace.getID())) {
					filteredList.add(person);
				}
			}
		}
		
		return filteredList;
	}

	public ArrayList<Person> getPersonListFirstAncestors() {
		ArrayList<Person> ancestorList = new ArrayList<>();
		for(Person person : my_persons) {
			if(person.getFamiliarRelation().getParents().isEmpty() && person.getFamiliarRelation().getDistantAncestor().isEmpty()
					&& (!person.getFamiliarRelation().getChildren().isEmpty() || !person.getFamiliarRelation().getDistantDescendant().isEmpty())) {
				ancestorList.add(person);
			}
		}
		return ancestorList;
	}

	public RaceSystem getRaceSystem() {
		if(my_raceSystem == null) {
			my_raceSystem = new RaceSystem();
		}
		return my_raceSystem;
	}
	private ArrayList<Person> getPersonListWithEmptyRace() {
		ArrayList<Person> filteredList = new ArrayList<>();

		for(Person person : my_persons) {
			if(person.getInformation().getRace() == null) {
				filteredList.add(person);
			}
		}

		return filteredList;
	}

}
