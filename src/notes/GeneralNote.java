package notes;

import book.Book;
import global.SerializedObject;

public class GeneralNote extends SerializedObject {
	
	private String my_title;
	private String my_contetn;
	
	public GeneralNote(String newTitle, String newNoteContent) {
		super();
		
		my_title = newTitle;
		my_contetn = newNoteContent;
	}

	public String getName() {
		return my_title;
	}

	public String getContent() {
		return my_contetn;
	}

	public void edit(String newTitle, String newNoteContent) {
		my_title = newTitle;
		my_contetn = newNoteContent;
		Book.getInstance().save();
	}

}
