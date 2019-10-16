package GUI.bookeditorFrame;

import java.awt.GridLayout;

import GUI_components.Page;
import GUI_components.SimpleLabel;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import book.Section;
import global.UserSettings;

public class HomePage extends Page {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		super("Home");
				
		if(!UserSettings.getInstance().getTutorial().chooseFirstColorTheme) {			
			addCard(new TutorialCard(1, false));
		}
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			addCard(new TutorialCard(3, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstSection && !UserSettings.getInstance().getTutorial().setDevelopmentStatus) {			
			addCard(new TutorialCard(5, false));
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(7, false));
		}
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			addCard(new TutorialCard(9, false));
		}
		if(UserSettings.getInstance().getTutorial().setTimestamps && !UserSettings.getInstance().getTutorial().createFirstPerson) {			
			addCard(new TutorialCard(10, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPerson && !UserSettings.getInstance().getTutorial().addFurtherPersons) {			
			addCard(new TutorialCard(12, false));
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			addCard(new TutorialCard(13, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			addCard(new TutorialCard(15, false));
		}
		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			addCard(new TutorialCard(16, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, false));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			addCard(new TutorialCard(19, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline && !UserSettings.getInstance().getTutorial().finishTutorial) {			
			addCard(new TutorialCard(20, true));
		}
		
		//*************************************************************************
		StructureCard card_Statistics = new StructureCard("Statistics");
		addCard(card_Statistics);
		
		TransparentPanel panel_statistics = new TransparentPanel();
		panel_statistics.setLayout(new GridLayout(0, 1, 5, 5));
		card_Statistics.setBody(panel_statistics);
		
		SimpleLabel lblSectionStatistics = new SimpleLabel("Chapters: " + Book.getInstance().getSectionList().getCountChapters() + " + with Sections: " + Book.getInstance().getSectionList().getSections().size());
		panel_statistics.add(lblSectionStatistics);
		
		int countWords = 0;
		int countChars = 0;
		for(Section section : Book.getInstance().getSectionList().getSections()) {
			countWords += section.getCountWords();
			countChars += section.getText().length();
		}
		SimpleLabel lblContentStatistics = new SimpleLabel("Words: " + countWords + "; Chars: " + countChars);
		panel_statistics.add(lblContentStatistics);
		
		SimpleLabel lblPersonStatistics = new SimpleLabel("Persons: " + Book.getInstance().getSociety().getPersonList().size());
		panel_statistics.add(lblPersonStatistics);
		
		SimpleLabel lblPlacesStatistics = new SimpleLabel("Places: " + Book.getInstance().getWorld().getPlaces().size());
		panel_statistics.add(lblPlacesStatistics);
	}

}
