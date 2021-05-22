package GUI.pages.notesPage;

import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.pages.notes.createNote.Card_createNote;
import GUI.pages.notes.viewNote.Card_editNote;
import book.Book;
import notes.GeneralNote;

import java.util.ArrayList;

public class Page_viewNotes extends Page {
	private static final long serialVersionUID = 1L;

	public Page_viewNotes() {
		super("Additional Stuff as notes, research, descriptions, ...");
		
		//******************************************************************************************
		ArrayList<GeneralNote> notes = Book.getInstance().getNotes();
		for(GeneralNote note : notes) {
			addCard(new StructureCard("View and Edit Note", new Card_editNote(note)));
		}

		//******************************************************************************************
		if(notes.isEmpty()){
			addCard(new StructureCard("Create new Note", new Card_createNote()));	
		}
		
	}

}
