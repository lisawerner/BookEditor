package GUI.personPage;

import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.race.Page_RaceSystem;
import GUI_components.PageMenu;
import book.Book;
import person.Person;

public class PersonMenu extends PageMenu {
	private static final long serialVersionUID = 1L;

	public PersonMenu() {
		super("List of Persons:");
		
		JButton btnViewRelationships = this.addButtonToTopMenu("View Society");
		btnViewRelationships.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new ViewSocietyPage()));
		
		if(Book.getInstance().getSociety().isRaceSystemActivated()){			
			JButton btnViewRaceSystem = this.addButtonToTopMenu("View Race-System");
			btnViewRaceSystem.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_RaceSystem()));
		}
				
		JButton btnPersonFilter = this.addButtonToTopMenu("Filter Persons");
		btnPersonFilter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new FilterPersonPage()));
		
		JButton btnAddNew = this.addButtonToTopMenu("Add new Person");
		btnAddNew.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(null, false)));
		
		this.addBetweenTitle("Super Main Characters");
		for(Person person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()) {
			JButton btnOpenPerson = this.addLinkedListButton(person.getInformation().getNickname());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person, false)));
		}
		
		this.addBetweenTitle("'Important' Characters");
		for(Person person : Book.getInstance().getSociety().getPersonListImportantCharacters()) {
			JButton btnOpenPerson = this.addLinkedListButton(person.getInformation().getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person, false)));
		}
		
		this.addBetweenTitle("Somebody");
		for(Person person : Book.getInstance().getSociety().getPersonListTheRest()) {
			JButton btnOpenPerson = this.addLinkedListButton(person.getInformation().getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person, false)));
		}
	}
}
