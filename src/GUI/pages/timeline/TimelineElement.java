package GUI.pages.timeline;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TimelineItem;
import GUI.components.TransparentPanel;
import person.Person;
import person.Relationship;
import time.Timestamp;
import world.Place;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;

public class TimelineElement extends TimelineItem {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	public TimelineElement(Timestamp timestamp, boolean leftPosition) {
		super(leftPosition, timestamp);
		my_section = Book.getInstance().getTableOfContent().getSection(timestamp.getSection());

		//TODO: Filterfunktionen!!!!

		//*****************************************************************************************************
		TransparentPanel panel_sectionName = new TransparentPanel();
		panel_sectionName.setLayout(new BoxLayout(panel_sectionName, BoxLayout.LINE_AXIS));
		SimpleLabel lblSection = new SimpleLabel("Section:  ");
		panel_sectionName.add(lblSection);
		LinkButton btnOpenSection = new LinkButton(my_section.getName());
		btnOpenSection.addActionListener(e -> BookEditorFrame.getInstance().openSectionPage(my_section, Book.getInstance().getTableOfContent().getChapter(my_section.getParentChapterID())));
		panel_sectionName.add(btnOpenSection);
		this.setSection(panel_sectionName);
	
		//*****************************************************************************************************
		TransparentPanel panel_personTags = new TransparentPanel();
		this.addToBody(panel_personTags);
		panel_personTags.setLayout(new BoxLayout(panel_personTags, BoxLayout.LINE_AXIS));
		SimpleLabel lblPersons = new SimpleLabel("Persons:  ");
		panel_personTags.add(lblPersons);
		List<Person> personTags = my_section.getPersonByTag();
		for(Person tag : personTags) {
			LinkButton tagName = new LinkButton(tag.getInformation().getNickname());
			tagName.addActionListener(e -> BookEditorFrame.getInstance().openPersonPage(tag, false));
			panel_personTags.add(tagName);
			panel_personTags.add(new SimpleLabel(";  "));
		}
		
		//*****************************************************************************************************	
		TransparentPanel panel_placeTags = new TransparentPanel();
		this.addToBody(panel_placeTags);
		panel_placeTags.setLayout(new BoxLayout(panel_placeTags, BoxLayout.LINE_AXIS));
		SimpleLabel lblPlaces = new SimpleLabel("Places:  ");
		panel_placeTags.add(lblPlaces);
		List<Place> placeTags = my_section.getPelaceByTag();
		for(Place tag : placeTags) {
			LinkButton tagName = new LinkButton(tag.getName());
			tagName.addActionListener(e -> BookEditorFrame.getInstance().openPlacePage(tag, false));
			panel_placeTags.add(tagName);
			panel_placeTags.add(new SimpleLabel(";  "));
		}
		
		//*****************************************************************************************************	
		TransparentPanel panel_relationshipSwitchs = new TransparentPanel();
		this.addToBody(panel_relationshipSwitchs);
		panel_relationshipSwitchs.setLayout(new BoxLayout(panel_relationshipSwitchs, BoxLayout.LINE_AXIS));
		SimpleLabel lblRelationshipSwitchs = new SimpleLabel("Switched Relationships:  ");
		panel_relationshipSwitchs.add(lblRelationshipSwitchs);
		ArrayList<Relationship> relSwitches = my_section.getRelationships();
		for(Relationship rel : relSwitches) {
			SimpleLabel lblRel = new SimpleLabel("<html>&emsp; &emsp;" + rel.getSwitchToString() + "; </html>");
			this.addToBody(lblRel);
		}

	}

}
