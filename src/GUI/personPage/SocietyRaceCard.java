package GUI.personPage;

import GUI_components.TransparentPanel;
import book.Book;
import person.Race;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;


public class SocietyRaceCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public SocietyRaceCard() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Race race : Book.getInstance().getSociety().getRaces()) {			
			add(new SociatyRaceElement(race));
			add(new JSeparator());
		}

	}

}
