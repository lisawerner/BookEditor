package GUI.pages.content;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;

import book.Book;
import book.Chapter;
import GUI.components.TransparentPanel;

public class Card_SortChapter extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_SortChapter() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		showAllChapters();
	}

	private void showAllChapters() {
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {
			add(new Listelement_Chapter(chapter, this));
			add(Box.createRigidArea(new Dimension(20, 20)));
		}
		
	}

	public void reload() {
		removeAll();
		showAllChapters();
	}

}
