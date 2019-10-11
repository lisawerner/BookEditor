package GUI.personPage;

import GUI_components.MenuPage;
import GUI_components.Page;
import GUI_components.Submenu;
import GUI_components.TransparentPanel;
import book.Book;
import person.Person;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PersonsPage extends Page {
	private static final long serialVersionUID = 1L;

	private Person my_person;
	public PersonsPage(Person openPerson) {
		super("Persons, Relationships, ...");
		my_person = openPerson;
		
		TransparentPanel panel_placeholder = new TransparentPanel();
		panel_placeholder.setLayout(new BorderLayout(0, 0));
		add(panel_placeholder, BorderLayout.CENTER);
		JLabel lblOpenAnExisting = new JLabel("<html><div style='text-align: center;'><font size=\"5\">Open an existing person<br/>"
				+ "or add a new one &#x27A1;</size></div></html>");
		lblOpenAnExisting.setHorizontalTextPosition(JLabel.RIGHT);
		lblOpenAnExisting.setVerticalTextPosition(JLabel.CENTER);
		panel_placeholder.add(lblOpenAnExisting, BorderLayout.CENTER);
		
		MenuPage panel_personslist = new MenuPage("List of Persons:");
		add(panel_personslist, BorderLayout.EAST);
		
		Submenu submenu_addPerson = new Submenu("");
		panel_personslist.addSubmenu(submenu_addPerson);
		JButton btnAddNew = submenu_addPerson.addButton("Add new");
		btnAddNew.addActionListener(e -> changeBody(new PersonEditor(null)));
		Submenu submenu_personListSuperMain = new Submenu("Super Main Characters");
		panel_personslist.addSubmenu(submenu_personListSuperMain);
		submenu_personListSuperMain.activateList();
		for(Person person : Book.getInstance().getPersonListOfSuperMainCharacters()) {
			JButton btnOpenPerson = submenu_personListSuperMain.addListButton(person.getName());
			btnOpenPerson.addActionListener(e -> changeBody(new PersonEditor(person)));
		}
		Submenu submenu_personListMainChars = new Submenu("'Important' Characters");
		panel_personslist.addSubmenu(submenu_personListMainChars);
		submenu_personListMainChars.activateList();
		for(Person person : Book.getInstance().getPersonListImportantCharacters()) {
			JButton btnOpenPerson = submenu_personListMainChars.addListButton(person.getName());
			btnOpenPerson.addActionListener(e -> changeBody(new PersonEditor(person)));
		}
		Submenu submenu_personList = new Submenu("Somebody");
		panel_personslist.addSubmenu(submenu_personList);
		submenu_personList.activateList();
		for(Person person : Book.getInstance().getPersonListTheRest()) {
			JButton btnOpenPerson = submenu_personList.addListButton(person.getName());
			btnOpenPerson.addActionListener(e -> changeBody(new PersonEditor(person)));
		}
				
		
		if(my_person != null) {
			changeBody(new PersonEditor(my_person));
		}
			
	}
	
}
