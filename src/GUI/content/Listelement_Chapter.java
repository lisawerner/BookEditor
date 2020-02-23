package GUI.content;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.chapter.Page_viewChapter;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Chapter;
import book.Section;
import java.awt.GridLayout;

public class Listelement_Chapter extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Chapter my_chapter;
	
	private TransparentPanel panel_listOfSections;
	
	public Listelement_Chapter(Chapter chapter, Card_SortChapter parentBody) {
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
		LinkButton lblChapterTitle = new LinkButton(my_chapter.getTitle());
		lblChapterTitle.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(my_chapter)));
		panel_chapterTitle.add(lblChapterTitle);
		
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
		BookEditorFrame.getInstance().reloadMenu();
	}
	
	protected void reload() {
		panel_listOfSections.removeAll();
		showAllSections();
	}
	
	private void showAllSections() {
		for(Section section : my_chapter.getSections()) {
			Listelement_Section lblSectionTitle = new Listelement_Section(section, my_chapter, this);
			panel_listOfSections.add(lblSectionTitle);
			panel_listOfSections.add(Box.createRigidArea(new Dimension(5, 5)));
		}
	}
	
}
