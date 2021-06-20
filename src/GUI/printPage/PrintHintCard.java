package GUI.printPage;

import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.content.Book;

import java.awt.*;

public class PrintHintCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public PrintHintCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblChapterSettings = new SimpleLabel("You can change all Settings in 'Book-Settings' in the left navigation menu");
		add(lblChapterSettings);
		 
		SimpleLabel lblBookTitle = new SimpleLabel("Printed Title of the Book: " + Book.getInstance().getTitle());
		add(lblBookTitle);
		
		SimpleLabel lblTitleSettings = new SimpleLabel("Warning: Title is not set yet! It is only a work title!");
		if(Book.getInstance().isWorkTitle()) {add(lblTitleSettings);}
		lblTitleSettings.setWarning(true);
		
		SimpleLabel lblChapter = new SimpleLabel("Chapters will printed with numbers as chapter-title");
		if(Book.getInstance().printChapterName()) {lblChapter.setText("Chapters will printed with their names you add for Table of Content");}
		add(lblChapter);

		SimpleLabel lblNoChapter = new SimpleLabel("Warning: You have no Chapters and no Text!");
		lblNoChapter.setWarning(true);
		if(Book.getInstance().getTableOfContent().getChapters().size() == 0) {add(lblNoChapter);}
		
		SimpleLabel lblChapterWithoutSection = new SimpleLabel("Warning: Some chapters does not have a section");
		lblChapterWithoutSection.setWarning(true);
		//TODO: Warning for missing Sections in Chapters!
		
		SimpleLabel lblSectionUnfinished = new SimpleLabel("Warning: You have Sections, which are not finished! Filter for Sections with DevelopmentStatus != Finish");
		lblSectionUnfinished.setWarning(true);
		if(Book.getInstance().getTableOfContent().getUnfinishedSections().size() > 0) {add(lblSectionUnfinished);}
		
		SimpleLabel lblSectionsEmpty = new SimpleLabel("Warning: You have Sections, which have no text! Filter for Sections without text");
		lblSectionsEmpty.setWarning(true);
		if(Book.getInstance().getTableOfContent().getEmptySections().size() > 0) {add(lblSectionsEmpty);}
	}

}
