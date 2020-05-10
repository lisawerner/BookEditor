package GUI.miscPage;

import book.Book;
import GUI.components.Page;
import GUI.components.StructureCard;
import notes.GeneralNote;

public class NotesPage extends Page {
	private static final long serialVersionUID = 1L;

	public NotesPage() {
		super("Additional Stuff as notes, research, descriptions, ...");
		
		//******************************************************************************************
		addCard(new StructureCard("Create new Note", new NoteCard(null)));
		
		//******************************************************************************************
		for(GeneralNote note : Book.getInstance().getNotes()) {
			addCard(new StructureCard("View and Edit Note", new NoteCard(note)));
		}

	}

}
