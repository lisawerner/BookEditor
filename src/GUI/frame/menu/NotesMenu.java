package GUI.frame.menu;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.FrameSubmenu;
import GUI.components.MenuButton;
import GUI.components.MenuListButton;
import book.Book;
import notes.GeneralNote;

public class NotesMenu extends FrameSubmenu {
	private static final long serialVersionUID = 1L;

	public NotesMenu() {
		super("Notes", "List of Notes:");
		
		addButton(new MenuButton("View Notes", e -> BookEditorFrame.getInstance().openNotesListPage()));
		addButton(new MenuButton("Add Note", e -> BookEditorFrame.getInstance().openNotePage(null)));
		
		for(GeneralNote note : Book.getInstance().getNotes()){
			addListEntry(new MenuListButton(note.getName(), e -> BookEditorFrame.getInstance().openNotePage(note)));
		}

	}

}
