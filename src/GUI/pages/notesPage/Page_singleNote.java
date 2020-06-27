package GUI.pages.notesPage;

import GUI.components.Page;
import GUI.components.StructureCard;
import notes.GeneralNote;

public class Page_singleNote extends Page {

	private static final long serialVersionUID = 1L;

	public Page_singleNote(GeneralNote note) {
		super(note!= null ? "Note: " + note.getName() : "Create Note");
		
		addCard(new StructureCard("View and Edit Note", new NoteCard(note)));

	}

}
