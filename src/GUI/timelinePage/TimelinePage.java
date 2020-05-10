package GUI.timelinePage;

import global.UserSettings;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;

public class TimelinePage extends Page {
	private static final long serialVersionUID = 1L;
	
	public TimelinePage() {
		super("Timeline");
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			addCard(new TutorialCard(9, true));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			addCard(new TutorialCard(15, true));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			addCard(new TutorialCard(19, true));
		}

		//*******************************************************************************************************************
		//*******************************************************************************************************************
		addCard(new StructureCard("Filter Timeline", new TimelineFilterCard()));
		//*******************************************************************************************************************
		addCard(new StructureCard("View Timeline", new TimelineCard()));
	}

}
