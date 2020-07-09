package GUI.pages.society.raceView;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
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
			
			personalPanel.add(new LinkButton(person.getInformation().getName(),
					e -> BookEditorFrame.getInstance().openPersonPage(person, false)));
			
			personalPanel.add(new SimpleLabel("; "));
		}

	}

}
