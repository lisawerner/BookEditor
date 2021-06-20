package GUI.pages.society.raceCreate;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import GUI.components.form.FormButton;
import GUI.components.form.FormTextField;
import GUI.components.form.FormTextarea;
import GUI.components.form.SimpleForm;
import book.content.Book;
import book.person.Race;

import javax.swing.*;
import java.awt.*;

public class Card_createRace extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final FormTextField txt_raceName;
	private final FormTextarea txt_raceNotes;
	
	public Card_createRace() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		FormButton btnSave = new FormButton("Save Race", e -> save());
		SimpleForm saveForm = new SimpleForm(this::save, btnSave);
		
		txt_raceName = new FormTextField("Race Name:", "", true);
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
			Book.getInstance().getSociety().getRaceSystem().addRace(newRace);
			BookEditorFrame.getInstance().openRacePage(newRace);
		}
	}

}
