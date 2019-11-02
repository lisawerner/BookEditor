package GUI.personPage;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import global.ObjectID;
import person.Person;
import person.Relationship;

public class PersonRelationshipCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person;
	
	public PersonRelationshipCard(Person person) {
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
				LinkButton lbtnRelPerson = new LinkButton(relPerson.getInformation().getNickname());
				relationPanel.add(lbtnRelPerson);
				lbtnRelPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(relPerson)));
			}
		}
	}

}
