package GUI.pages.society.raceView;

import GUI.components.*;
import GUI.components.form.FormButton;
import GUI.components.form.FormTextField;
import GUI.components.form.FormTextarea;
import GUI.components.form.SimpleForm;
import book.person.Race;

import javax.swing.*;

public class Card_raceDefaultInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Race my_race;
	
	private final FormTextField txt_raceName;
	private final FormTextarea txt_raceNotes;

	public Card_raceDefaultInformation(Race givenRace) {
		my_race = givenRace;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		FormButton btnSave = new FormButton("Save Race", e -> save());
		SimpleForm saveForm = new SimpleForm(this::save, btnSave);
		
		txt_raceName = new FormTextField("Race Name:", my_race.getName(), true);
		add(txt_raceName);
		saveForm.addInput(txt_raceName);
		
		txt_raceNotes = new FormTextarea("Notes about the Race (Skills, look, ...):", my_race.getNotes());
		add(txt_raceNotes);
		saveForm.addInput(txt_raceNotes);

		add(btnSave);
	}

	private void save() {
		if(!txt_raceName.getText().isEmpty()) {
			my_race.edit(txt_raceName.getText(), txt_raceNotes.getText());
		}
	}

}
