package GUI.race;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import person.Person;
import person.Race;

public class Card_ViewRaceRepresentatives extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Race my_race;

	public Card_ViewRaceRepresentatives(Race givenRace) {
		my_race = givenRace;
		
		setLayout(new GridLayout(0, 1, 5, 5));
		
		for(Person person : Book.getInstance().getSociety().getPersonListByRace(my_race)) {
			TransparentPanel personalPanel = new TransparentPanel();
			personalPanel.setLayout(new BoxLayout(personalPanel, BoxLayout.LINE_AXIS));
			this.add(personalPanel);
			
			LinkButton lnbtn_person = new LinkButton(person.getInformation().getName());
			lnbtn_person.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person, false)));
			personalPanel.add(lnbtn_person);
			
			personalPanel.add(new SimpleLabel("; "));
		}

	}

}
