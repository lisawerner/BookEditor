package GUI.personPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import global.ObjectID;
import person.Person;
import person.Relationship;

import javax.swing.*;
import java.awt.*;

public class PersonRelationshipCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public PersonRelationshipCard(Person person) {

		setLayout(new GridLayout(0, 1, 5, 5));
		
		if(person != null) {
			for(ObjectID relID : person.getRelationships()) {
				Relationship relationship = Book.getInstance().getRelationship(relID);
				TransparentPanel relationPanel = new TransparentPanel();
				add(relationPanel);
				relationPanel.setLayout(new BoxLayout(relationPanel, BoxLayout.LINE_AXIS));
				relationPanel.add(new SimpleLabel("Has Relationship '" + relationship.getDescribingRelationshipType() + "' with Person "));
				Person relPerson = Book.getInstance().getSociety().getPerson(relationship.getOtherPerson(person.getID()));
				LinkButton linkButtonRelPerson = new LinkButton(relPerson.getInformation().getNickname());
				relationPanel.add(linkButtonRelPerson);
				linkButtonRelPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(relPerson, false)));
				relationPanel.add(new SimpleLabel(" [Switched in Section: "));
				Section switchSection = Book.getInstance().getTableOfContent().getSectionByRelationship(relID);
				LinkButton linkButtonSection = new LinkButton(switchSection.getName());
				relationPanel.add(linkButtonSection);
				linkButtonSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(switchSection, Book.getInstance().getTableOfContent().getChapter(switchSection.getParentChapterID()))));
				relationPanel.add(new SimpleLabel("]"));
			}
		}
	}

}
