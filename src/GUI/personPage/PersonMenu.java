package GUI.personPage;

import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.PageMenu;
import book.Book;
import person.Person;

public class PersonMenu extends PageMenu {
	private static final long serialVersionUID = 1L;

	public PersonMenu() {
		super("List of Persons:");
		
		JButton btnAddNew = this.addButtonToTopMenu("Add new");
		btnAddNew.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(null)));
		
		this.addBetweenTitle("Super Main Characters");
		for(Person person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()) {
			JButton btnOpenPerson = this.addLinkedListButton(person.getInformation().getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
		}
		
		this.addBetweenTitle("'Important' Characters");
		for(Person person : Book.getInstance().getSociety().getPersonListImportantCharacters()) {
			JButton btnOpenPerson = this.addLinkedListButton(person.getInformation().getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
		}
		
		this.addBetweenTitle("Somebody");
		for(Person person : Book.getInstance().getSociety().getPersonListTheRest()) {
			JButton btnOpenPerson = this.addLinkedListButton(person.getInformation().getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
		}
	}
}
