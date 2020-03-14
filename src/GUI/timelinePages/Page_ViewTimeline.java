package GUI.timelinePages;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;
public class Page_ViewTimeline extends Page {
	private static final long serialVersionUID = 1L;
	
	public Page_ViewTimeline() {
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
		addCard(new StructureCard("View Timeline", new TimelineCard()));
		
		//*******************************************************************************************************************
		//*******************************************************************************************************************
		setMenu(new TimelineMenu());
	}

}
