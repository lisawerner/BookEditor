package GUI.pages.content.createChapter;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.ComplexTextFieldSavable;
import GUI.components.TransparentPanel;
import book.Book;
import book.Chapter;

import java.awt.*;

public class Card_createChapter extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final ComplexTextFieldSavable txt_chapterTitle;
	
	public Card_createChapter() {
		setLayout(new BorderLayout(5, 5));
		
		txt_chapterTitle = new ComplexTextFieldSavable("Chapter Title:", "", this::createChapter);
		add(txt_chapterTitle, BorderLayout.CENTER);
	}

	private void createChapter() {
		if(!txt_chapterTitle.getText().isEmpty()) {
			Chapter newChapter = new Chapter(txt_chapterTitle.getText());
			Book.getInstance().getTableOfContent().addChapter(newChapter);
			BookEditorFrame.getInstance().openChapterPage(newChapter);
		}
	}

}
