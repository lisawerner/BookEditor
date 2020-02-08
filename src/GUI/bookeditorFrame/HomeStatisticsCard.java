package GUI.bookeditorFrame;

import java.awt.GridLayout;

import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Chapter;
import book.Section;

public class HomeStatisticsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public HomeStatisticsCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		int countSections = 0;
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {
			countSections += chapter.getSections().size();
		}
		
		SimpleLabel lblSectionStatistics = new SimpleLabel("Chapters: " + Book.getInstance().getTableOfContent().getCountChapters() + " + with Sections: " + countSections);
		add(lblSectionStatistics);
		
		int countWords = 0;
		int countChars = 0;
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section section : chapter.getSections()) {
				countWords += section.getCountWords();
				countChars += section.getText().length();
			}
		}
		SimpleLabel lblContentStatistics = new SimpleLabel("Words: " + countWords + "; Chars: " + countChars);
		add(lblContentStatistics);
		
		SimpleLabel lblPersonStatistics = new SimpleLabel("Persons: " + Book.getInstance().getSociety().getPersonList().size());
		add(lblPersonStatistics);
		
		SimpleLabel lblPlacesStatistics = new SimpleLabel("Places: " + Book.getInstance().getWorld().getPlaces().size());
		add(lblPlacesStatistics);
	}

}
