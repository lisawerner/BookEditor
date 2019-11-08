package GUI.sortPage;

import javax.swing.BoxLayout;

import GUI_components.TransparentPanel;
import book.Book;
import book.Section;

public class SortChapterCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public SortChapterCard() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Section section : Book.getInstance().getSectionList().getSections()) {			
			add(new SortChapterElement(section, this));
		}
	}

	public void reload() {
		removeAll();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		for(Section section : Book.getInstance().getSectionList().getSections()) {			
			add(new SortChapterElement(section, this));
		}
	}

}
