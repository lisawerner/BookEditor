package GUI.pages.notes.viewNote;

import GUI.components.*;
import notes.GeneralNote;

import java.awt.*;

public class Card_editNote extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final GeneralNote my_note;
	
	private final FormTextField txt_name;
	private final FormTextarea txt_noteContent;

	public Card_editNote(GeneralNote givenNote) {
		my_note = givenNote;
		setLayout(new BorderLayout(10, 10));
		
		FormButton btnSaveNote = new FormButton("Save Note", e -> save());
		add(btnSaveNote, BorderLayout.SOUTH);
		SimpleForm saveForm = new SimpleForm(this::save, btnSaveNote);
		
		TransparentPanel panel_noteName = new TransparentPanel();
		add(panel_noteName, BorderLayout.NORTH);
		panel_noteName.setLayout(new BorderLayout(5, 5));
		
		txt_name = new FormTextField("Note name:", my_note.getName(), true);
		panel_noteName.add(txt_name, BorderLayout.CENTER);
		saveForm.addInput(txt_name);
		
		txt_noteContent = new FormTextarea("", my_note.getContent());
		add(txt_noteContent, BorderLayout.CENTER);
		saveForm.addInput(txt_noteContent);
	}

	private void save() {
		if(!txt_name.getText().isEmpty()) {			
			my_note.edit(txt_name.getText(), txt_noteContent.getText());
		}
	}

}
