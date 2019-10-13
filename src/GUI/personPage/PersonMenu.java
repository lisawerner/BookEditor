package GUI.personPage;

import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.MenuPage;
import GUI_components.Submenu;
import book.Book;
import person.Person;

public class PersonMenu extends MenuPage {
	private static final long serialVersionUID = 1L;

	public PersonMenu() {
		super("List of Persons:");
		
		Submenu submenu_addPerson = new Submenu("");
		addSubmenu(submenu_addPerson);
		JButton btnAddNew = submenu_addPerson.addButton("Add new");
		btnAddNew.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(null)));
		Submenu submenu_personListSuperMain = new Submenu("Super Main Characters");
		addSubmenu(submenu_personListSuperMain);
		submenu_personListSuperMain.activateList();
		for(Person person : Book.getInstance().getPersonListOfSuperMainCharacters()) {
			JButton btnOpenPerson = submenu_personListSuperMain.addListButton(person.getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
		}
		Submenu submenu_personListMainChars = new Submenu("'Important' Characters");
		addSubmenu(submenu_personListMainChars);
		submenu_personListMainChars.activateList();
		for(Person person : Book.getInstance().getPersonListImportantCharacters()) {
			JButton btnOpenPerson = submenu_personListMainChars.addListButton(person.getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
		}
		Submenu submenu_personList = new Submenu("Somebody");
		addSubmenu(submenu_personList);
		submenu_personList.activateList();
		for(Person person : Book.getInstance().getPersonListTheRest()) {
			JButton btnOpenPerson = submenu_personList.addListButton(person.getName());
			btnOpenPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
		}

	}

}
