package GUI.pages.timeline;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TimelineItem;
import GUI.components.TransparentPanel;
import book.Book;
import book.Section;
import person.Person;
import person.Relationship;
import time.Timestamp;
import world.Place;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TimelineElement extends TimelineItem {
	private static final long serialVersionUID = 1L;

	private final Section my_section;
	
	public TimelineElement(Timestamp timestamp, boolean leftPosition) {
		super(leftPosition, timestamp);
		my_section = Book.getInstance().getTableOfContent().getSection(timestamp.getSection());

		//TODO: FilterFunctions!!!!

		//*****************************************************************************************************
		TransparentPanel panel_sectionName = new TransparentPanel();
		panel_sectionName.setLayout(new BoxLayout(panel_sectionName, BoxLayout.LINE_AXIS));
		SimpleLabel lblSection = new SimpleLabel("Section:  ");
		panel_sectionName.add(lblSection);
		panel_sectionName.add(new LinkButton(my_section.getName(),
				e -> BookEditorFrame.getInstance().openSectionPage(my_section, Book.getInstance().getTableOfContent().getChapter(my_section.getParentChapterID()))));
		this.setSection(panel_sectionName);
	
		//*****************************************************************************************************
		TransparentPanel panel_personTags = new TransparentPanel();
		this.addToBody(panel_personTags);
		panel_personTags.setLayout(new BoxLayout(panel_personTags, BoxLayout.LINE_AXIS));
		SimpleLabel lblPersons = new SimpleLabel("Persons:  ");
		panel_personTags.add(lblPersons);
		List<Person> personTags = my_section.getPersonByTag();
		for(Person tag : personTags) {
			panel_personTags.add(new LinkButton(tag.getInformation().getNickname(),
					e -> BookEditorFrame.getInstance().openPersonPage(tag, false)));
			panel_personTags.add(new SimpleLabel(";  "));
		}
		
		//*****************************************************************************************************	
		TransparentPanel panel_placeTags = new TransparentPanel();
		this.addToBody(panel_placeTags);
		panel_placeTags.setLayout(new BoxLayout(panel_placeTags, BoxLayout.LINE_AXIS));
		SimpleLabel lblPlaces = new SimpleLabel("Places:  ");
		panel_placeTags.add(lblPlaces);
		List<Place> placeTags = my_section.getPlaceByTag();
		for(Place tag : placeTags) {
			panel_placeTags.add(new LinkButton(tag.getName(),
					e -> BookEditorFrame.getInstance().openPlacePage(tag, false)));
			panel_placeTags.add(new SimpleLabel(";  "));
		}
		
		//*****************************************************************************************************	
		TransparentPanel panel_relationshipSwitches = new TransparentPanel();
		this.addToBody(panel_relationshipSwitches);
		panel_relationshipSwitches.setLayout(new BoxLayout(panel_relationshipSwitches, BoxLayout.LINE_AXIS));
		SimpleLabel lblRelationshipSwitches = new SimpleLabel("Switched Relationships:  ");
		panel_relationshipSwitches.add(lblRelationshipSwitches);
		ArrayList<Relationship> relSwitches = my_section.getRelationships();
		for(Relationship rel : relSwitches) {
			SimpleLabel lblRel = new SimpleLabel("<html>&emsp; &emsp;" + rel.getSwitchToString() + "; </html>");
			this.addToBody(lblRel);
		}

	}

}
