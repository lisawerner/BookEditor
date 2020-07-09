package GUI.pages.society.raceView;

import GUI.components.FormButton;
import GUI.components.FormTextarea;
import GUI.components.FormTextfield;
import GUI.components.SimpleFormular;
import GUI.components.TransparentPanel;
import person.Race;
import javax.swing.BoxLayout;

public class Card_raceDefaultInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Race my_race;
	
	private FormTextfield txt_raceName;
	private FormTextarea txt_raceNotes;

	public Card_raceDefaultInformation(Race givenRace) {
		my_race = givenRace;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		FormButton btnSave = new FormButton("Save Race", e -> save());
		SimpleFormular saveForm = new SimpleFormular(this::save, btnSave);
		
		txt_raceName = new FormTextfield("Race Name:", my_race.getName(), true);
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
