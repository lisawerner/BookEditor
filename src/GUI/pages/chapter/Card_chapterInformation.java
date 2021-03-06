package GUI.pages.chapter;

import book.Book;
import book.Chapter;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextfield;
import GUI.components.TransparentPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;

import java.awt.GridLayout;

public class Card_chapterInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Chapter my_chapter;
	
	private SimpleLabel lblWarning;
	private SimpleTextfield txt_chapterTitle;
	
	public Card_chapterInformation(Chapter chapter) {
		my_chapter = chapter;
		setLayout(new GridLayout(0, 1, 10, 10));
		
		//TODO: Es sollte eine Info kommen, wenn der Titel ungespeichert ist! Diese sollte sich irgendwie unterscheiden von der kurzen Info, dass gespeichert ist; Vlt. so machen wie beim Sectiontext
		
		TransparentPanel panel_chapterTitle = new TransparentPanel();
		add(panel_chapterTitle);
		panel_chapterTitle.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblChapterTitle = new SimpleLabel("Chapter Title:");
		panel_chapterTitle.add(lblChapterTitle, BorderLayout.WEST);
		
		txt_chapterTitle = new SimpleTextfield("");
		if(my_chapter != null) {
			txt_chapterTitle.setText(my_chapter.getTitle());
		}
		panel_chapterTitle.add(txt_chapterTitle, BorderLayout.CENTER);
		
		JButton btnChange = new JButton("Save changes");
		btnChange.addActionListener(e -> createChapter());
		if(my_chapter == null) {
			btnChange.setText("Create Chapter");
		}
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
			
			if(my_chapter == null) {				
				my_chapter = new Chapter(txt_chapterTitle.getText());
				Book.getInstance().getTableOfContent().addChapter(my_chapter);
//				BookEditorFrame.getInstance().reloadMenu();
				BookEditorFrame.getInstance().openChapterPage(my_chapter);
			} else {
				my_chapter.editTitle(txt_chapterTitle.getText());
//				BookEditorFrame.getInstance().reloadMenu();
				lblWarning.setText("Title successfully changed!");
			}
		}
	}

}
