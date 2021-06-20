package book.notes;

import book.content.Book;
import global.SerializedObject;

public class GeneralNote extends SerializedObject {
	
	private String my_title;
	private String my_content;
	
	public GeneralNote(String newTitle, String newNoteContent) {
		super();
		
		my_title = newTitle;
		my_content = newNoteContent;
	}

	public String getName() {
		return my_title;
	}

	public String getContent() {
		return my_content;
	}

	public void edit(String newTitle, String newNoteContent) {
		my_title = newTitle;
		my_content = newNoteContent;
		Book.getInstance().save();
	}

}
