package GUI.miscPage;

import GUI_components.SimpleLabel;
import GUI_components.SimpleTextarea;
import GUI_components.SimpleTextfield;
import GUI_components.TransparentPanel;
import book.Book;
import notes.GeneralNote;

import java.awt.BorderLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;

import java.awt.GridLayout;

public class NoteCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private GeneralNote my_note;
	
	private SimpleLabel lblSaveHint;
	private SimpleTextfield txt_name;
	private SimpleTextarea txt_noteContent;

	public NoteCard(GeneralNote givenNote) {
		my_note = givenNote;
		setLayout(new BorderLayout(10, 10));
		
		TransparentPanel panel_noteName = new TransparentPanel();
		add(panel_noteName, BorderLayout.NORTH);
		panel_noteName.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblName = new SimpleLabel("Note name:");
		panel_noteName.add(lblName, BorderLayout.WEST);
		
		txt_name = new SimpleTextfield();
		panel_noteName.add(txt_name, BorderLayout.CENTER);
		if(my_note != null) {txt_name.setText(my_note.getName());}
		
		txt_noteContent = new SimpleTextarea();
		add(txt_noteContent, BorderLayout.CENTER);
		if(my_note != null) {txt_noteContent.setText(my_note.getContent());}
		
		TransparentPanel panel_saveFooter = new TransparentPanel();
		add(panel_saveFooter, BorderLayout.SOUTH);
		panel_saveFooter.setLayout(new GridLayout(0, 1, 2, 2));
		
		lblSaveHint = new SimpleLabel(" ");
		lblSaveHint.setWarning(true);
		panel_saveFooter.add(lblSaveHint);
		
		JButton btnSaveNote = new JButton("Save Note");
		panel_saveFooter.add(btnSaveNote);
		btnSaveNote.addActionListener(e -> save());
		
	}

	private void save() {
		boolean canSave = true;
		lblSaveHint.setText(" ");
		
		if("".equals(txt_name.getText())) {
			canSave = false;
			lblSaveHint.setText("Can not save Note without a name!");
		}
		
		if(canSave) {			
			if(my_note == null) {
				Book.getInstance().addNote(new GeneralNote(txt_name.getText(), txt_noteContent.getText()));
				BookEditorFrame.getInstance().switchBody(new NotesPage());
				//TODO: Reload bug: Ändert man ein existierendes Note ohne es zu speichern, geht diese Änderung verloren, wenn ein neues Note erzeugt wird!
			} else {
				my_note.edit(txt_name.getText(), txt_noteContent.getText());
				lblSaveHint.setText("Successfully saved!");
			}
		}
	}

}
