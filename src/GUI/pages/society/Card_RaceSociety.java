package GUI.pages.society;

import book.Book;
import GUI.components.TransparentPanel;
import person.Race;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;

public class Card_RaceSociety extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_RaceSociety() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				
		for(Race race : Book.getInstance().getSociety().getRaces()) {
			if(race.getParentRace() == null){
				add(new Element_RaceSociety(race));
				add(new JSeparator());				
			}
		}

	}

}
