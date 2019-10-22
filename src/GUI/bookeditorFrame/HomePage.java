package GUI.bookeditorFrame;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
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
		addCard(new StructureCard("Statistics", new HomeStatisticsCard()));
	}

}
