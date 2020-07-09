package GUI.pages.society.raceCreate;

import book.Book;
import GUI.components.FormButton;
import GUI.components.FormTextarea;
import GUI.components.FormTextfield;
import GUI.components.SimpleFormular;
import GUI.components.TransparentPanel;
import person.Race;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import GUI.bookeditorFrame.BookEditorFrame;

public class Card_createRace extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private FormTextfield txt_raceName;
	private FormTextarea txt_raceNotes;
	
	public Card_createRace() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		FormButton btnSave = new FormButton("Save Race", e -> save());
		SimpleFormular saveForm = new SimpleFormular(this::save, btnSave);
		
		txt_raceName = new FormTextfield("Race Name:", "", true);
		add(txt_raceName);
		saveForm.addInput(txt_raceName);

		txt_raceNotes = new FormTextarea("Notes about the Race (Skills, look, ...):", "");
		add(txt_raceNotes, BorderLayout.CENTER);
		saveForm.addInput(txt_raceNotes);

		add(btnSave); 
	}

	private void save() {
		if(!txt_raceName.getText().isEmpty()) {
			Race newRace = new Race(txt_raceName.getText(), txt_raceNotes.getText());
			Book.getInstance().getSociety().addRace(newRace);
			BookEditorFrame.getInstance().openRacePage(newRace);
		}
	}

}
