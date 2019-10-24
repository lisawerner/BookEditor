package GUI.printPage;

import java.awt.Color;
import java.awt.GridLayout;

import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;

public class PrintHintCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public PrintHintCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblChapterSettings = new SimpleLabel("You can change all Settings in 'Book-Settings' in the left navigation menu");
		add(lblChapterSettings);
		
		//TODO: LinkButton hier positionieren (wozu? zum Book-Settigns??? Weis nicht mehr, was der Kommentar sollte ^^""") 
		SimpleLabel lblBookTitle = new SimpleLabel("Printed Title of the Book: " + Book.getInstance().getTitle());
		add(lblBookTitle);
		
		SimpleLabel lblTitleSettings = new SimpleLabel("Warning: Title is not set yet! It is only a work title!");
		if(Book.getInstance().isWorkTitle()) {add(lblTitleSettings);}
		lblTitleSettings.setForeground(Color.RED);
		
		SimpleLabel lblChapter = new SimpleLabel("Chapters will printed with numbers as chapter-title");
		if(Book.getInstance().printChapterName()) {lblChapter.setText("Chapters will printed with their names you add for Table of Content");}
		add(lblChapter);

		SimpleLabel lblSectionless = new SimpleLabel("Warning: You have no Sections and no Text!");
		lblSectionless.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getSections().size() == 0) {add(lblSectionless);}
		
		SimpleLabel lblSectionUnfinished = new SimpleLabel("Warning: You have Sections, which are not finished! Filter for Sections with DevelopmentStatus != Finish");
		lblSectionUnfinished.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getUnfinishedSections().size() > 0) {add(lblSectionUnfinished);}
		
		SimpleLabel lblSectionsEmpty = new SimpleLabel("Warning: You have Sections, which have no text! Filter for Sections without text");
		lblSectionsEmpty.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getEmptySections().size() > 0) {add(lblSectionsEmpty);}
		
		SimpleLabel lblSectionsUnsorted = new SimpleLabel("Warning: You have Sections, which are not sorted inside table of content! Filter for these unsorted Sections");
		lblSectionsUnsorted.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getUnsortedSections().size() > 0) {add(lblSectionsUnsorted);}
	}

}