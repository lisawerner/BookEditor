package GUI.pages.content.viewChapter;

import GUI.components.ComplexTextFieldSavable;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.Chapter;

import javax.swing.*;
import java.awt.*;

public class Card_viewChapterInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final Chapter my_chapter;

	private final ComplexTextFieldSavable txt_chapterTitle;
	
	public Card_viewChapterInformation(Chapter chapter) {
		my_chapter = chapter;
		setLayout(new GridLayout(0, 1, 10, 10));

		txt_chapterTitle = new ComplexTextFieldSavable("Chapter Title:", my_chapter.getTitle(), this::createChapter);
		add(txt_chapterTitle);		
		
		TransparentPanel panel_furtherInformation = new TransparentPanel();
		add(panel_furtherInformation);
		panel_furtherInformation.setLayout(new BoxLayout(panel_furtherInformation, BoxLayout.PAGE_AXIS));
		
		panel_furtherInformation.add(new SimpleLabel("Information about the Chapter:"));
		panel_furtherInformation.add(new SimpleLabel(" - Summary of Words: " + my_chapter.getCountOfWords()));
		//TODO: Show timeline for the chapter?
		//TODO: Show places of the chapter?
	
	}

	private void createChapter() {
		if(!txt_chapterTitle.getText().isEmpty()) {			
			my_chapter.editTitle(txt_chapterTitle.getText());
//			TODO: BookEditorFrame.getInstance().reloadMenu();
		}
	}

}
