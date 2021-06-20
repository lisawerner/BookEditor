package GUI.bookeditorFrame;

import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.content.Book;
import book.content.Chapter;
import book.content.Section;

import java.awt.*;

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

		// A norm page has 1800 characters
		// A page can contain ~ 7000 "i" characters
		// A page can contain ~ 2800 "w" characters
		int countPagesMax = countChars / 3000;
		int countPagesMin = countChars / 7000;
		SimpleLabel lblPagesStatistics = new SimpleLabel("~ Pages: " + countPagesMin + " to " + countPagesMax);
		add(lblPagesStatistics);


		
		SimpleLabel lblPersonStatistics = new SimpleLabel("Persons: " + Book.getInstance().getSociety().getPersonList().size());
		add(lblPersonStatistics);
		
		SimpleLabel lblPlacesStatistics = new SimpleLabel("Places: " + Book.getInstance().getWorld().getPlaces().size());
		add(lblPlacesStatistics);
	}

}
