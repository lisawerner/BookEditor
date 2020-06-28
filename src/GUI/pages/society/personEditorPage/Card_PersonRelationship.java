package GUI.pages.society.personEditorPage;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Section;
import global.ObjectID;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import person.Person;
import person.Relationship;

public class Card_PersonRelationship extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person;
	
	public Card_PersonRelationship(Person person) {
		my_person = person;
		
		setLayout(new GridLayout(0, 1, 5, 5));
		
		if(my_person != null) {			
			for(ObjectID relID : my_person.getRelationships()) {	
				Relationship relship = Book.getInstance().getRelationship(relID);
				TransparentPanel relationPanel = new TransparentPanel();
				add(relationPanel);
				relationPanel.setLayout(new BoxLayout(relationPanel, BoxLayout.LINE_AXIS));
				relationPanel.add(new SimpleLabel("Has Relationship '" + relship.getDescribingRelationshipType() + "' with Person "));
				Person relPerson = Book.getInstance().getSociety().getPerson(relship.getOtherPerson(my_person.getID()));
				relationPanel.add(new LinkButton(relPerson.getInformation().getNickname(),
						e -> BookEditorFrame.getInstance().openPersonPage(relPerson, false)));
				relationPanel.add(new SimpleLabel(" [Switched in Section: "));
				Section switchSection = Book.getInstance().getTableOfContent().getSectionByRelationship(relID);
				relationPanel.add(new LinkButton(switchSection.getName(),
						e -> BookEditorFrame.getInstance().switchBody(new SectionPage(switchSection, Book.getInstance().getTableOfContent().getChapter(switchSection.getParentChapterID())))));
				relationPanel.add(new SimpleLabel("]"));
			}
		}
	}

}
