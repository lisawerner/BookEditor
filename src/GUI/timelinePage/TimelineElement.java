package GUI.timelinePage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI.sectionPage.SectionPage;
import GUI.worldPage.WorldPage;
import GUI_components.LinkButton;
import GUI_components.TimelineItem;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import person.Person;
import person.Relationship;
import world.Place;

import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.BoxLayout;

public class TimelineElement extends TimelineItem {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	public TimelineElement(Section section, boolean leftPosition) {
		super(leftPosition, section.getTimestamp().toString(), section.getTimestamp().isSpecificDate());
		my_section = section;

		//TODO: Filterfunktionen!!!!

		//*****************************************************************************************************
		TransparentPanel panel_sectionName = new TransparentPanel();
		panel_sectionName.setLayout(new BoxLayout(panel_sectionName, BoxLayout.LINE_AXIS));
		JLabel lblSection = new JLabel("Section:  ");
		panel_sectionName.add(lblSection);
		LinkButton btnOpenSection = new LinkButton(my_section.getName());
		btnOpenSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(my_section)));
		panel_sectionName.add(btnOpenSection);
		this.setSection(panel_sectionName);
	
		//*****************************************************************************************************
		TransparentPanel panel_personTags = new TransparentPanel();
		this.addToBody(panel_personTags);
		panel_personTags.setLayout(new BoxLayout(panel_personTags, BoxLayout.LINE_AXIS));
		JLabel lblPersons = new JLabel("Persons:  ");
		panel_personTags.add(lblPersons);
		ArrayList<Person> personTags = my_section.getPersonByTag();
		for(Person tag : personTags) {
			LinkButton tagName = new LinkButton(tag.getName());
			tagName.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(tag)));
			panel_personTags.add(tagName);
			panel_personTags.add(new JLabel(";  "));
		}
		
		//*****************************************************************************************************	
		TransparentPanel panel_placeTags = new TransparentPanel();
		this.addToBody(panel_placeTags);
		panel_placeTags.setLayout(new BoxLayout(panel_placeTags, BoxLayout.LINE_AXIS));
		JLabel lblPlaces = new JLabel("Places:  ");
		panel_placeTags.add(lblPlaces);
		ArrayList<Place> placeTags = my_section.getPelaceByTag();
		for(Place tag : placeTags) {
			LinkButton tagName = new LinkButton(tag.getName());
			tagName.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new WorldPage(tag)));
			panel_placeTags.add(tagName);
			panel_placeTags.add(new JLabel(";  "));
		}
		
		//*****************************************************************************************************	
		TransparentPanel panel_relationshipSwitchs = new TransparentPanel();
		this.addToBody(panel_relationshipSwitchs);
		panel_relationshipSwitchs.setLayout(new BoxLayout(panel_relationshipSwitchs, BoxLayout.LINE_AXIS));
		JLabel lblRelationshipSwitchs = new JLabel("Switched Relationships:  ");
		panel_relationshipSwitchs.add(lblRelationshipSwitchs);
		ArrayList<Relationship> relSwitches = my_section.getRelationships();
		for(Relationship rel : relSwitches) {
			JLabel lblRel = new JLabel(rel.getSwitchToString(Book.getInstance()) + "; ");
			panel_relationshipSwitchs.add(lblRel);
		}

	}

}
