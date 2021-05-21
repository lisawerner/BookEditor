package GUI.race;

import GUI_components.SimpleLabel;
import GUI_components.SimpleTextarea;
import GUI_components.SimpleTextfield;
import GUI_components.TransparentPanel;
import book.Book;
import person.Race;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;

import java.awt.GridLayout;

public class Card_CreateRace extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Race my_race;
	
	private final SimpleTextfield txt_raceName;
	private final SimpleTextarea txt_raceNotes;
	
	private final SimpleLabel lblSaveHint;

	public Card_CreateRace(Race givenRace) {
		my_race = givenRace;
		
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_name = new TransparentPanel();
		add(panel_name);
		panel_name.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblRaceName = new SimpleLabel("Race Name:");
		panel_name.add(lblRaceName, BorderLayout.WEST);
		
		txt_raceName = new SimpleTextfield();
		panel_name.add(txt_raceName, BorderLayout.CENTER);
		if(my_race != null) {txt_raceName.setText(my_race.getName());}
		txt_raceName.setColumns(10);
		
		TransparentPanel panel_raceNotes = new TransparentPanel();
		add(panel_raceNotes);
		panel_raceNotes.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblRaceNotes = new SimpleLabel("Notes about the Race (Skills, look, ...):");
		panel_raceNotes.add(lblRaceNotes, BorderLayout.NORTH);
		
		txt_raceNotes = new SimpleTextarea();
		if(my_race != null) {txt_raceNotes.setText(my_race.getNotes());}
		panel_raceNotes.add(txt_raceNotes, BorderLayout.CENTER);
		
		TransparentPanel panel_SAVEfooter = new TransparentPanel();
		add(panel_SAVEfooter);
		panel_SAVEfooter.setLayout(new GridLayout(0, 1, 5, 5));
		
		lblSaveHint = new SimpleLabel(" ");
		lblSaveHint.setWarning(true);
		panel_SAVEfooter.add(lblSaveHint);
		
		JButton btnSave = new JButton("Save Race");
		panel_SAVEfooter.add(btnSave);
		btnSave.addActionListener(e -> save());
	}

	private void save() {
		boolean canSave = true;
		lblSaveHint.setText(" ");
		
		if("".endsWith(txt_raceName.getText())) {
			canSave = false;
			lblSaveHint.setText("You have to enter a name for saving the race");
		}
		
		if(canSave) {
			if(my_race == null) {
				my_race = new Race(txt_raceName.getText(), txt_raceNotes.getText());
				Book.getInstance().getSociety().addRace(my_race);
				BookEditorFrame.getInstance().switchBody(new Page_EditRace(my_race));
			} else {
				my_race.edit(txt_raceName.getText(), txt_raceNotes.getText());
			}
		}
	}

}
