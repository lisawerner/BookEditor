package GUI.personPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;
import person.Person;

public class PersonEditorPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Person my_person = null;

	public PersonEditorPage(Person person) {
		super("Persons, Relationships, ...");
		
		my_person = person;
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().createFirstPerson) {			
			addCard(new TutorialCard(11, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPerson && !UserSettings.getInstance().getTutorial().addFurtherPersons) {			
			addCard(new TutorialCard(12, true));
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			addCard(new TutorialCard(13, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			addCard(new TutorialCard(15, true));
		}
		
		//*********************************************************************************
		//*********************************************************************************
		
		this.addCard(new StructureCard("General Person Information", new PersonInformationCard(my_person)));
		
		//*********************************************************************************

		if(my_person != null) {this.addCard(new StructureCard("Person was tagged in following sections", new PersonTagCard(my_person)));}
			
		//*********************************************************************************

		if(my_person != null) {this.addCard(new StructureCard("Person has following Relationships", new PersonRelationshipCard(my_person)));}
		
		//*********************************************************************************
		//*********************************************************************************
		setMenu(new PersonMenu());
	}

}
