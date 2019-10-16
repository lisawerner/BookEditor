package GUI.timelinePage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI.sectionPage.SectionPage;
import GUI.worldPage.PlaceEditor;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TimelineItem;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import person.Person;
import person.Relationship;
import world.Place;
import java.util.ArrayList;
import javax.swing.BoxLayout;

public class TimelineElement extends TimelineItem {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	public TimelineElement(Section section, boolean leftPosition) {
		super(leftPosition, section.getTimestamp().toString(), section.getTimestamp().isSpecificDate(), !section.getTimestamp().getSpecificDate().isAnnoDomini());
		my_section = section;

		//TODO: Filterfunktionen!!!!

		//*****************************************************************************************************
		TransparentPanel panel_sectionName = new TransparentPanel();
		panel_sectionName.setLayout(new BoxLayout(panel_sectionName, BoxLayout.LINE_AXIS));
		SimpleLabel lblSection = new SimpleLabel("Section:  ");
		panel_sectionName.add(lblSection);
		LinkButton btnOpenSection = new LinkButton(my_section.getName());
		btnOpenSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(my_section)));
		panel_sectionName.add(btnOpenSection);
		this.setSection(panel_sectionName);
	
		//*****************************************************************************************************
		TransparentPanel panel_personTags = new TransparentPanel();
		this.addToBody(panel_personTags);
		panel_personTags.setLayout(new BoxLayout(panel_personTags, BoxLayout.LINE_AXIS));
		SimpleLabel lblPersons = new SimpleLabel("Persons:  ");
		panel_personTags.add(lblPersons);
		ArrayList<Person> personTags = my_section.getPersonByTag();
		for(Person tag : personTags) {
			LinkButton tagName = new LinkButton(tag.getName());
			tagName.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(tag)));
			panel_personTags.add(tagName);
			panel_personTags.add(new SimpleLabel(";  "));
		}
		
		//*****************************************************************************************************	
		TransparentPanel panel_placeTags = new TransparentPanel();
		this.addToBody(panel_placeTags);
		panel_placeTags.setLayout(new BoxLayout(panel_placeTags, BoxLayout.LINE_AXIS));
		SimpleLabel lblPlaces = new SimpleLabel("Places:  ");
		panel_placeTags.add(lblPlaces);
		ArrayList<Place> placeTags = my_section.getPelaceByTag();
		for(Place tag : placeTags) {
			LinkButton tagName = new LinkButton(tag.getName());
			tagName.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(tag)));
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
			SimpleLabel lblRel = new SimpleLabel("<html>" + rel.getSwitchToString(Book.getInstance()) + "; </html>");
			panel_relationshipSwitchs.add(lblRel);
		}

	}

}
