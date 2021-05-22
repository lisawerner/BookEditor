package GUI.personPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import book.Book;
import global.UserSettings;
import person.Person;

import javax.swing.*;

public class PersonEditorPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private final Person my_person;

	public PersonEditorPage(Person person, boolean isSecondFrame) {
		super("Society: Persons, Relationships, ...");
		
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
		
		if(my_person != null) {this.addCard(new StructureCard("Person has following familiar Relationships", new PersonFamiliarRelationshipCard(my_person)));}
			
		//*********************************************************************************

		if(my_person != null) {this.addCard(new StructureCard("Person has following Relationships", new PersonRelationshipCard(my_person)));}
		
		//*********************************************************************************

		//TODO: panel to submit the deletion
		JButton btnDelete = new JButton("Delete Person");
		btnDelete.addActionListener(e -> {
			Book.getInstance().getSociety().deletePerson(my_person);
			BookEditorFrame.getInstance().switchBody(new ViewSocietyPage());
		});
		setFooter(btnDelete);
		
		//*********************************************************************************
		//*********************************************************************************
		if(!isSecondFrame) {setMenu(new PersonMenu());}
	}

}
