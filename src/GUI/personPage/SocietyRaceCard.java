package GUI.personPage;

import GUI_components.TransparentPanel;
import book.Book;
import person.Race;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.race.Page_EditRace;


public class SocietyRaceCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public SocietyRaceCard() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JButton btnAddRace = new JButton("Add new Race");
		this.add(btnAddRace);
		btnAddRace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(null)));
		
		for(Race race : Book.getInstance().getSociety().getRaces()) {			
			add(new SociatyRaceElement(race));
			add(new JSeparator());
		}

	}

}
