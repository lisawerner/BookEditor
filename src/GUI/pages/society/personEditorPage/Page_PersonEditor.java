package GUI.pages.society.personEditorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import global.UserSettings;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;
import GUI.pages.society.Page_ViewSociety;
import person.Person;

public class Page_PersonEditor extends Page {
	private static final long serialVersionUID = 1L;
	
	private Person my_person = null;

	public Page_PersonEditor(Person person, boolean isSecondFrame) {
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
		
		this.addCard(new StructureCard("General Person Information", new Card_PersonInformation(my_person)));
		
		//*********************************************************************************

		if(my_person != null) {this.addCard(new StructureCard("Person was tagged in following sections", new Card_PersonTag(my_person)));}
		
		//*********************************************************************************
		
		if(my_person != null) {this.addCard(new StructureCard("Person has following familiar Relationships", new Card_PersonFamiliarRelationship(my_person)));}
			
		//*********************************************************************************

		if(my_person != null) {this.addCard(new StructureCard("Person has following Relationships", new Card_PersonRelationship(my_person)));}
		
		//*********************************************************************************

		//TODO: Bestätungs Panel
		JButton btnDelete = new JButton("Delete Person");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book.getInstance().getSociety().deletePerson(my_person);
				BookEditorFrame.getInstance().switchBody(new Page_ViewSociety());
			}
		});
		setFooter(btnDelete);
	}

}
