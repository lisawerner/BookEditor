package person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import book.Book;
import book.Chapter;
import book.Section;
import global.ObjectID;

public class Society {
	
	private ArrayList<Person> my_persons;
	
	private boolean isAFantasyStory;
	private ArrayList<Race> my_races;
	
	public Society() {
		my_persons = new ArrayList<Person>();
		
		isAFantasyStory = false;
		my_races = new ArrayList<Race>();
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

	public List<Person> getPersonListNeverTagged() {
		List<Person> filteredPersons = new ArrayList<Person>();
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
		List<Person> filteredPersons = new ArrayList<Person>();
		
		//TODO: thats crazy shit o.O -> move Relationship to single class for relationship list and only add relshipID to section -> do not search relationships over looking for each section o.O
		for(Person person : my_persons) {
			boolean hasMainRelationship = false;
			for(ObjectID relPerson : person.getRelationships()) {
				//Relationships are saved in every section -> for each section
				for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {					
					for(Section section : chapter.getSections()) {
						for(Relationship relship : section.getRelationships()) {
							//Check if Relationship in Section is the relationship, of this person
							if(relPerson.equals(relship.getID())) {							
								if(Book.getInstance().getSociety().getPerson(relship.getOtherPerson(person.getID())).getInformation().isSuperMainChar()) {
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
		List<Person> filteredPersons = new ArrayList<Person>();
		
		if(checkingPerson != null) {			
			//TODO: thats crazy shit o.O -> move Relationship to single class for relationship list and only add relshipID to section -> do not search relationships over looking for each section o.O
			for(Person currentPerson : my_persons) {
				if(!currentPerson.equals(checkingPerson)) {					
					boolean hasRelationship = false;
					for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {						
						for(Section section : chapter.getSections()) {
							for(Relationship relship : section.getRelationships()) {
								if(relship.getOtherPerson(checkingPerson.getID()) != null) {								
									if(relship.getOtherPerson(checkingPerson.getID()).equals(currentPerson.getID())) {
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
		ArrayList<Person> newList = new ArrayList<Person>();
		for(Person currentPerson : my_persons) {
			if(!currentPerson.equals(person)) {
				newList.add(currentPerson);
			}
		}
		my_persons = newList;
		Book.getInstance().save();
	}

	public void activateRaceSettings(boolean selected) {
		isAFantasyStory = selected;
		Book.getInstance().save();
	}

	public boolean isRaceSystemActivated() {
		return isAFantasyStory;
	}

	public void addRace(Race race) {
		if(my_races == null) { my_races = new ArrayList<Race>();}

		my_races.add(race);
		Book.getInstance().save();
	}

	public ArrayList<Race> getRaces() {
		return my_races;
	}
	
	public ArrayList<Person> getPersonListByRace(Race filteredRace) {
		ArrayList<Person> filteredList = new ArrayList<Person>();
		
		for(Person person : my_persons) {
			if(filteredRace == null) {
				if(person.getInformation().getRace() == null) {
					filteredList.add(person);
				}
			} else {				
				if(person.getInformation().getRace() != null) {				
					if(person.getInformation().getRace().equals(filteredRace.getID())) {
						filteredList.add(person);
					}
				}
			}
		}
		
		return filteredList;
	}

	public void updateRaceRepresantives(ObjectID raceID, ObjectID personID) {
		if(raceID != null) {			
			getRace(raceID).addRepresentative(personID);
		}
	}

	private Race getRace(ObjectID raceID) {
		for(Race race : my_races) {
			if(race.getID().equals(raceID)) {
				return race;
			}
		}
		return null;
	}

	public void updateRaceRepresantives(ObjectID oldRaceID, ObjectID newRace, ObjectID personID) {
		if(oldRaceID != null) {			
			getRace(oldRaceID).removeRepresentative(personID);
		}
		updateRaceRepresantives(newRace, personID);
	}

	public ArrayList<Person> getPersonListFirstAncestors() {
		ArrayList<Person> ancestorList = new ArrayList<Person>();
		for(Person person : my_persons) {
			if(person.getFamiliarRelation().getParents().isEmpty() && person.getFamiliarRelation().getDistantAncestor().isEmpty()
					&& (!person.getFamiliarRelation().getChildren().isEmpty() || !person.getFamiliarRelation().getDistantDescendant().isEmpty())) {
				ancestorList.add(person);
			}
		}
		return ancestorList;
	}


}
