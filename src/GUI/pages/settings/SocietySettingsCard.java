package GUI.pages.settings;

import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.content.Book;

import javax.swing.*;
import java.awt.*;

public class SocietySettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private SimpleCheckbox checkbox_fantasyRaces;

	public SocietySettingsCard() {
		
		this.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lbl_fantasyRacesHint = new SimpleLabel("<html>If you want to write a fantasy-story you can activate race-sysem here.<br/>"
				+ "With the race-system you can declare races and tag them to persons.<br/>"
				+ "Without race-system the book-editor think every book.person is a human.</html>");
		add(lbl_fantasyRacesHint, BorderLayout.NORTH);

		this.buildRaceSystemStuff();
	}

	private void buildRaceSystemStuff() {
		TransparentPanel panel_raceSystem = new TransparentPanel();
		panel_raceSystem.setLayout(new BoxLayout(panel_raceSystem, BoxLayout.LINE_AXIS));
		add(panel_raceSystem, BorderLayout.CENTER);

		checkbox_fantasyRaces = new SimpleCheckbox("Activate Race-System");
		checkbox_fantasyRaces.setSelected(Book.getInstance().getSociety().getRaceSystem().isRaceSystemActivated());
		panel_raceSystem.add(checkbox_fantasyRaces);
		if(!Book.getInstance().getSociety().getRaceSystem().canChangeActivationOfSystem()
		) {
			checkbox_fantasyRaces.setEnabled(false);
			SimpleLabel lbl_deleteWarning = new SimpleLabel("Can not deactivate race system when still races or representatives exist.");
			lbl_deleteWarning.setWarning(true);
			panel_raceSystem.add(lbl_deleteWarning);

		}
		checkbox_fantasyRaces.addActionListener(e -> changeRaceSystemSettings());
	}

	private void changeRaceSystemSettings() {
		Book.getInstance().getSociety().getRaceSystem().activateRaceSettings(checkbox_fantasyRaces.isSelected());
	}

}
