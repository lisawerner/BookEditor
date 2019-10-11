package GUI.bookeditorFrame;

import java.awt.GridLayout;

import javax.swing.JLabel;

import GUI_components.Page;
import GUI_components.PageBody;
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
		
		PageBody my_body = new PageBody();
		this.changeBody(my_body);
		
		if(!UserSettings.getInstance().getTutorial().chooseFirstColorTheme) {			
			my_body.addStructureCard(new TutorialCard(1, false));
		}
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			my_body.addStructureCard(new TutorialCard(3, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstSection && !UserSettings.getInstance().getTutorial().setDevelopmentStatus) {			
			my_body.addStructureCard(new TutorialCard(5, false));
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			my_body.addStructureCard(new TutorialCard(7, false));
		}
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			my_body.addStructureCard(new TutorialCard(9, false));
		}
		if(UserSettings.getInstance().getTutorial().setTimestamps && !UserSettings.getInstance().getTutorial().createFirstPerson) {			
			my_body.addStructureCard(new TutorialCard(10, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPerson && !UserSettings.getInstance().getTutorial().addFurtherPersons) {			
			my_body.addStructureCard(new TutorialCard(12, false));
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			my_body.addStructureCard(new TutorialCard(13, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			my_body.addStructureCard(new TutorialCard(15, false));
		}
		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			my_body.addStructureCard(new TutorialCard(16, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			my_body.addStructureCard(new TutorialCard(18, false));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			my_body.addStructureCard(new TutorialCard(19, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline && !UserSettings.getInstance().getTutorial().finishTutorial) {			
			my_body.addStructureCard(new TutorialCard(20, true));
		}
		
		//*************************************************************************
		StructureCard card_Statistics = new StructureCard("Statistics");
		my_body.addStructureCard(card_Statistics);
		
		TransparentPanel panel_statistics = new TransparentPanel();
		panel_statistics.setLayout(new GridLayout(0, 1, 5, 5));
		card_Statistics.setBody(panel_statistics);
		
		JLabel lblSectionStatistics = new JLabel("Chapters: " + Book.getInstance().getSectionList().getCountChapters() + " + with Sections: " + Book.getInstance().getSectionList().getSections().size());
		panel_statistics.add(lblSectionStatistics);
		
		int countWords = 0;
		int countChars = 0;
		for(Section section : Book.getInstance().getSectionList().getSections()) {
			countWords += section.getCountWords();
			countChars += section.getText().length();
		}
		JLabel lblContentStatistics = new JLabel("Words: " + countWords + "; Chars: " + countChars);
		panel_statistics.add(lblContentStatistics);
		
		JLabel lblPersonStatistics = new JLabel("Persons: " + Book.getInstance().getPersonList().size());
		panel_statistics.add(lblPersonStatistics);
		
		JLabel lblPlacesStatistics = new JLabel("Places: " + Book.getInstance().getPlaces().size());
		panel_statistics.add(lblPlacesStatistics);
	}

}
