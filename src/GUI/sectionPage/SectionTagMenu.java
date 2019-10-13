package GUI.sectionPage;

import java.util.ArrayList;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI.sectionTags.SectionTagEditor;
import GUI.worldPage.PlaceEditor;
import GUI_components.MenuPage;
import GUI_components.Submenu;
import book.Book;
import book.Section;
import person.Person;
import person.Relationship;
import world.Place;

public class SectionTagMenu extends MenuPage {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	private JButton btnEdit;
	
	public SectionTagMenu(Section section) {
		super("Tags of this Section");
		my_section = section;
		
		Submenu submenu_changeTags = new Submenu("");
		addSubmenu(submenu_changeTags);
		btnEdit = submenu_changeTags.addButton("Change");
		if(my_section == null) {
			btnEdit.setEnabled(false);
			btnEdit.setToolTipText("You have to create a section first.");
		}
		btnEdit.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionTagEditor(my_section)));
		
		Submenu submenu_personTags = new Submenu("Person Tags:");
		addSubmenu(submenu_personTags);
		submenu_personTags.activateList();
		if(my_section != null) {
			ArrayList<Person> personTags = my_section.getPersonByTag();
			for(Person person : personTags) {
				JButton personButton = submenu_personTags.addListButton(person.getName());
				personButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
			}
		}
		
		Submenu submenu_relationshipsTags = new Submenu("Switched Relationships:");
		addSubmenu(submenu_relationshipsTags);
		if(my_section != null) {
			ArrayList<Relationship> relationshipTags = my_section.getRelationships();
			for(Relationship relationship : relationshipTags) {
				submenu_relationshipsTags.addText(relationship.getSwitchToString(Book.getInstance()));
			}
		}

		Submenu submenu_placeTags = new Submenu("Place Tags:");
		addSubmenu(submenu_placeTags);
		submenu_placeTags.activateList();
		if(my_section != null) {
		ArrayList<Place> placeTags = my_section.getPelaceByTag();
			for(Place tag : placeTags) {
				JButton placeButton = submenu_placeTags.addListButton(tag.getName());
				placeButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(tag)));
			}
		}

	}

}
