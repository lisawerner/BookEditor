package GUI.pages.content;

import GUI.components.TransparentPanel;
import book.content.Book;
import book.content.Chapter;

import javax.swing.*;
import java.awt.*;

public class Card_SortChapter extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_SortChapter() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		showAllChapters();
	}

	private void showAllChapters() {
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {
			add(new ListElement_Chapter(chapter, this));
			add(Box.createRigidArea(new Dimension(20, 20)));
		}
		
	}

	public void reload() {
		removeAll();
		showAllChapters();
	}

}
