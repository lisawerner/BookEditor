package GUI.pages.content;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.pages.content.viewChapter.Page_viewChapter;
import book.content.Book;
import book.content.Chapter;
import book.content.Section;

import javax.swing.*;
import java.awt.*;

public class ListElement_Chapter extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Chapter my_chapter;
	
	private final TransparentPanel panel_listOfSections;
	
	public ListElement_Chapter(Chapter chapter, Card_SortChapter parentBody) {
		my_chapter = chapter;
		setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_chapterInfo = new TransparentPanel();
		add(panel_chapterInfo, BorderLayout.CENTER);
		panel_chapterInfo.setLayout(new BorderLayout(0, 0));
		
		
		TransparentPanel panel_chapterTitle = new TransparentPanel();
		panel_chapterInfo.add(panel_chapterTitle, BorderLayout.NORTH);
		panel_chapterTitle.setLayout(new FlowLayout(FlowLayout.LEADING));
		SimpleLabel lblChapterSpace = new SimpleLabel(">>   ");
		panel_chapterTitle.add(lblChapterSpace);
		LinkButton lblChapterTitle = new LinkButton(my_chapter.getTitle(),
				e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(my_chapter)));
		panel_chapterTitle.add(lblChapterTitle);
		if(Book.getInstance().getTableOfContent().showWordSumInChapterLists()){			
			panel_chapterTitle.add(new SimpleLabel("    (Words: " + chapter.getCountOfWords() + ")"));
		}
		
		panel_listOfSections = new TransparentPanel();
		panel_chapterInfo.add(panel_listOfSections, BorderLayout.CENTER);
		panel_listOfSections.setLayout(new BoxLayout(panel_listOfSections, BoxLayout.PAGE_AXIS));
		
		showAllSections();
		
		TransparentPanel panel_move = new TransparentPanel();
		add(panel_move, BorderLayout.WEST);
		panel_move.setLayout(new GridLayout(0, 1, 5, 5));
		
		JButton btn_moveChapterUp = new JButton("^");
		panel_move.add(btn_moveChapterUp);
		btn_moveChapterUp.addActionListener(e -> moveChapter(parentBody, true));
		btn_moveChapterUp.setEnabled(!Book.getInstance().getTableOfContent().isFirstChapter(my_chapter));
		
		JButton btn_moveChapterDown = new JButton("v");
		panel_move.add(btn_moveChapterDown);
		btn_moveChapterDown.addActionListener(e -> moveChapter(parentBody, false));
		btn_moveChapterDown.setEnabled(!Book.getInstance().getTableOfContent().isLastChapter(my_chapter));
	}
	
	private void moveChapter(Card_SortChapter parentBody, boolean up) {
		Book.getInstance().getTableOfContent().sortChapter(my_chapter, up);
		parentBody.reload();
//		BookEditorFrame.getInstance().reloadMenu();
	}
	
	protected void reload() {
		panel_listOfSections.removeAll();
		showAllSections();
	}
	
	private void showAllSections() {
		for(Section section : my_chapter.getSections()) {
			ListElement_Section lblSectionTitle = new ListElement_Section(section, my_chapter, this);
			panel_listOfSections.add(lblSectionTitle);
			panel_listOfSections.add(Box.createRigidArea(new Dimension(5, 5)));
		}
	}
	
}
