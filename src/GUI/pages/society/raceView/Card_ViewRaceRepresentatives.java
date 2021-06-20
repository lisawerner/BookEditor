package GUI.pages.society.raceView;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.content.Book;
import book.person.Person;
import book.person.Race;

import javax.swing.*;
import java.awt.*;

public class Card_ViewRaceRepresentatives extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ViewRaceRepresentatives(Race givenRace) {

		setLayout(new GridLayout(0, 1, 5, 5));
		
		for(Person person : Book.getInstance().getSociety().getPersonListByRace(givenRace)) {
			TransparentPanel personalPanel = new TransparentPanel();
			personalPanel.setLayout(new BoxLayout(personalPanel, BoxLayout.LINE_AXIS));
			this.add(personalPanel);
			
			personalPanel.add(new LinkButton(person.getInformation().getName(),
					e -> BookEditorFrame.getInstance().openPersonPage(person, false)));
			
			personalPanel.add(new SimpleLabel("; "));
		}

	}

}
