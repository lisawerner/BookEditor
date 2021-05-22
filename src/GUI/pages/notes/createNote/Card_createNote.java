package GUI.pages.notes.createNote;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import GUI.pages.notesPage.Page_viewNotes;
import book.Book;
import notes.GeneralNote;

import java.awt.*;

public class Card_createNote extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final FormTextField txt_name;
	private final FormTextarea txt_noteContent;

	public Card_createNote() {
		setLayout(new BorderLayout(10, 10));
		
		FormButton btnSaveNote = new FormButton("Save Note", e -> save());
		add(btnSaveNote, BorderLayout.SOUTH);
		SimpleForm saveForm = new SimpleForm(this::save, btnSaveNote);
		
		TransparentPanel panel_noteName = new TransparentPanel();
		add(panel_noteName, BorderLayout.NORTH);
		panel_noteName.setLayout(new BorderLayout(5, 5));
		
		txt_name = new FormTextField("Note name:", "", true);
		panel_noteName.add(txt_name, BorderLayout.CENTER);
		saveForm.addInput(txt_name);
		
		txt_noteContent = new FormTextarea("", "");
		add(txt_noteContent, BorderLayout.CENTER);
		saveForm.addInput(txt_noteContent);
	}

	private void save() {
		if(!txt_name.getText().isEmpty()) {			
			Book.getInstance().addNote(new GeneralNote(txt_name.getText(), txt_noteContent.getText()));
			BookEditorFrame.getInstance().switchBody(new Page_viewNotes());
		}
	}

}
