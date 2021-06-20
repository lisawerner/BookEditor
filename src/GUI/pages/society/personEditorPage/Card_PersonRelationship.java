package GUI.pages.society.personEditorPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.pages.content.changeSection.Page_ChangeSection;
import book.content.Book;
import book.content.Section;
import global.ObjectID;
import book.person.Person;
import book.person.Relationship;

import javax.swing.*;
import java.awt.*;

public class Card_PersonRelationship extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_PersonRelationship(Person person) {

		setLayout(new GridLayout(0, 1, 5, 5));
		
		if(person != null) {
			for(ObjectID relID : person.getRelationships()) {
				Relationship relationship = Book.getInstance().getRelationship(relID);
				TransparentPanel relationPanel = new TransparentPanel();
				add(relationPanel);
				relationPanel.setLayout(new BoxLayout(relationPanel, BoxLayout.LINE_AXIS));
				relationPanel.add(new SimpleLabel("Has Relationship '" + relationship.getDescribingRelationshipType() + "' with Person "));
				Person relPerson = Book.getInstance().getSociety().getPerson(relationship.getOtherPerson(person.getID()));
				relationPanel.add(new LinkButton(relPerson.getInformation().getNickname(),
						e -> BookEditorFrame.getInstance().openPersonPage(relPerson, false)));
				relationPanel.add(new SimpleLabel(" [Switched in Section: "));
				Section switchSection = Book.getInstance().getTableOfContent().getSectionByRelationship(relID);
				relationPanel.add(new LinkButton(switchSection.getName(),
						e -> BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(switchSection, Book.getInstance().getTableOfContent().getChapter(switchSection.getParentChapterID())))));
				relationPanel.add(new SimpleLabel("]"));
			}
		}
	}

}
