package GUI.pages.settings;

import java.awt.BorderLayout;

import book.Book;
import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;

public class SocietySettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private SimpleCheckbox chbox_fantasyRaces;

	public SocietySettingsCard() {
		
		this.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lbl_fantasyRacesHint = new SimpleLabel("<html>If you want to write a fantasy-story you can activate race-sysem here.<br/>"
				+ "With the race-system you can declare races and tag them to persons.<br/>"
				+ "Without race-system the book-editor think every person is a human.</html>");
		add(lbl_fantasyRacesHint, BorderLayout.NORTH);
		
		chbox_fantasyRaces = new SimpleCheckbox("Activate Race-System");
		chbox_fantasyRaces.setSelected(Book.getInstance().getSociety().isRaceSystemActivated());
		add(chbox_fantasyRaces, BorderLayout.CENTER);
		chbox_fantasyRaces.addActionListener(e -> changeRaceSystemSettings());
	}

	private void changeRaceSystemSettings() {
		Book.getInstance().getSociety().activateRaceSettings(chbox_fantasyRaces.isSelected());
	}

}
