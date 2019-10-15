package GUI.sectionPage;

import java.util.ArrayList;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI.sectionTags.SectionTagEditor;
import GUI.worldPage.PlaceEditor;
import GUI_components.PageMenu;
import book.Book;
import book.Section;
import person.Person;
import person.Relationship;
import world.Place;

public class SectionTagMenu extends PageMenu {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	private JButton btnEdit;
	
	public SectionTagMenu(Section section) {
		super("Tags of this Section");
		my_section = section;
		
		btnEdit = this.addButtonToTopMenu("Add new");
		if(my_section == null) {
			btnEdit.setEnabled(false);
			btnEdit.setToolTipText("You have to create a section first.");
		}
		btnEdit.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionTagEditor(my_section)));
		
		this.addBetweenTitle("Person Tags:");
		if(my_section != null) {
			ArrayList<Person> personTags = my_section.getPersonByTag();
			for(Person person : personTags) {
				JButton personButton = this.addLinkedListButton(person.getName());
				personButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
			}
		}
		
		this.addBetweenTitle("Switched Relationships:");
		if(my_section != null) {
			ArrayList<Relationship> relationshipTags = my_section.getRelationships();
			for(Relationship relationship : relationshipTags) {
				this.addText(relationship.getSwitchToString(Book.getInstance()));
			}
		}
		
		this.addBetweenTitle("Place Tags:");
		if(my_section != null) {
		ArrayList<Place> placeTags = my_section.getPelaceByTag();
			for(Place tag : placeTags) {
				JButton placeButton = this.addLinkedListButton(tag.getName());
				placeButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(tag)));
			}
		}

	}

}
