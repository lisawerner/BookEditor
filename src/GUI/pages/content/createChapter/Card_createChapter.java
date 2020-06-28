package GUI.pages.content.createChapter;

import book.Book;
import book.Chapter;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextfield;
import GUI.components.TransparentPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;

import java.awt.GridLayout;

public class Card_createChapter extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private SimpleLabel lblWarning;
	private SimpleTextfield txt_chapterTitle;
	
	public Card_createChapter() {
		setLayout(new GridLayout(0, 1, 10, 10));
		
		TransparentPanel panel_chapterTitle = new TransparentPanel();
		add(panel_chapterTitle);
		panel_chapterTitle.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblChapterTitle = new SimpleLabel("Chapter Title:");
		panel_chapterTitle.add(lblChapterTitle, BorderLayout.WEST);
		
		txt_chapterTitle = new SimpleTextfield("");
		panel_chapterTitle.add(txt_chapterTitle, BorderLayout.CENTER);
		
		JButton btnChange = new JButton("Create changes");
		btnChange.addActionListener(e -> createChapter());
		panel_chapterTitle.add(btnChange, BorderLayout.EAST);
		
		lblWarning = new SimpleLabel(" ");
		lblWarning.setWarning(true);
		panel_chapterTitle.add(lblWarning, BorderLayout.SOUTH);
	
	}

	private void createChapter() {
		if("".equals(txt_chapterTitle.getText())) {
			lblWarning.setText("You can not save, without giving a name to the chapter");
		} else {
			lblWarning.setText(" ");
			
			Chapter newChapter = new Chapter(txt_chapterTitle.getText());
			Book.getInstance().getTableOfContent().addChapter(newChapter);
			BookEditorFrame.getInstance().openChapterPage(newChapter);
		}
	}

}
