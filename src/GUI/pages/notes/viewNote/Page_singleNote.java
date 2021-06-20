package GUI.pages.notes.viewNote;

import GUI.components.Page;
import GUI.components.StructureCard;
import book.notes.GeneralNote;

public class Page_singleNote extends Page {

	private static final long serialVersionUID = 1L;

	public Page_singleNote(GeneralNote note) {
		super("Note: ");
		
		addCard(new StructureCard("View and Edit Note", new Card_editNote(note)));

	}

}
