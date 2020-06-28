package GUI.pages.content.viewChapter;

import book.Chapter;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextfield;
import GUI.components.TransparentPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;

import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class Card_viewChapterInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Chapter my_chapter;
	
	private SimpleLabel lblWarning;
	private SimpleTextfield txt_chapterTitle;
	
	public Card_viewChapterInformation(Chapter chapter) {
		my_chapter = chapter;
		setLayout(new GridLayout(0, 1, 10, 10));
		
		//TODO: Es sollte eine Info kommen, wenn der Titel ungespeichert ist! Diese sollte sich irgendwie unterscheiden von der kurzen Info, dass gespeichert ist; Vlt. so machen wie beim Sectiontext
		
		TransparentPanel panel_chapterTitle = new TransparentPanel();
		add(panel_chapterTitle);
		panel_chapterTitle.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblChapterTitle = new SimpleLabel("Chapter Title:");
		panel_chapterTitle.add(lblChapterTitle, BorderLayout.WEST);
		
		txt_chapterTitle = new SimpleTextfield(my_chapter.getTitle());
		panel_chapterTitle.add(txt_chapterTitle, BorderLayout.CENTER);
		
		JButton btnChange = new JButton("Save changes");
		btnChange.addActionListener(e -> createChapter());
		panel_chapterTitle.add(btnChange, BorderLayout.EAST);
		
		lblWarning = new SimpleLabel(" ");
		lblWarning.setWarning(true);
		panel_chapterTitle.add(lblWarning, BorderLayout.SOUTH);
		
		TransparentPanel panel_furtherInformation = new TransparentPanel();
		add(panel_furtherInformation);
		panel_furtherInformation.setLayout(new BoxLayout(panel_furtherInformation, BoxLayout.PAGE_AXIS));
		
		panel_furtherInformation.add(new SimpleLabel("Information about the Chapter:"));
		panel_furtherInformation.add(new SimpleLabel(" - Summary of Words: " + my_chapter.getCountOfWords()));
		//TODO: Show timeline for the chapter?
		//TODO: Show places of the chapter?
	
	}

	private void createChapter() {
		if("".equals(txt_chapterTitle.getText())) {
			lblWarning.setText("You can not save, without giving a name to the chapter");
		} else {
			lblWarning.setText(" ");
			
			my_chapter.editTitle(txt_chapterTitle.getText());
//			TODO: BookEditorFrame.getInstance().reloadMenu();
			lblWarning.setText("Title successfully changed!");
		}
	}

}
