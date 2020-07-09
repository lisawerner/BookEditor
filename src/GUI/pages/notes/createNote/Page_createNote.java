package GUI.pages.notes.createNote;

import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_createNote extends Page {
	private static final long serialVersionUID = 1L;

	public Page_createNote() {
		super("Create Note");
		
		addCard(new StructureCard("Enter name and notes", new Card_createNote()));
	}

}
