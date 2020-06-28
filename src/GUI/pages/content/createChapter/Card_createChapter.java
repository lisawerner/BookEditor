package GUI.pages.content.createChapter;

import book.Book;
import book.Chapter;
import GUI.components.ComplexeTextfieldSaveable;
import GUI.components.TransparentPanel;
import java.awt.BorderLayout;

import GUI.bookeditorFrame.BookEditorFrame;

public class Card_createChapter extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private ComplexeTextfieldSaveable txt_chapterTitle;
	
	public Card_createChapter() {
		setLayout(new BorderLayout(5, 5));
		
		txt_chapterTitle = new ComplexeTextfieldSaveable("Chapter Title:", "", this::createChapter, true, true);
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
