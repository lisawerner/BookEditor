package GUI.settingsPage;

import java.awt.BorderLayout;

import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;

public class SocietySettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final SimpleCheckbox checkbox_fantasyRaces;

	public SocietySettingsCard() {
		
		this.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lbl_fantasyRacesHint = new SimpleLabel("<html>If you want to write a fantasy-story you can activate race-system here.<br/>"
				+ "With the race-system you can declare races and tag them to persons.<br/>"
				+ "Without race-system the book-editor think every person is a human.</html>");
		add(lbl_fantasyRacesHint, BorderLayout.NORTH);
		
		checkbox_fantasyRaces = new SimpleCheckbox("Activate Race-System");
		checkbox_fantasyRaces.setSelected(Book.getInstance().getSociety().isRaceSystemActivated());
		add(checkbox_fantasyRaces, BorderLayout.CENTER);
		checkbox_fantasyRaces.addActionListener(e -> changeRaceSystemSettings());
	}

	private void changeRaceSystemSettings() {
		Book.getInstance().getSociety().activateRaceSettings(checkbox_fantasyRaces.isSelected());
	}

}
