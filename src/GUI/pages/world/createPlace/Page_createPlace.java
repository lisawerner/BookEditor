package GUI.pages.world.createPlace;

import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;
import global.UserSettings;

public class Page_createPlace extends Page {
	private static final long serialVersionUID = 1L;
	
	public Page_createPlace() {
		super("Create a Place");
		
		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			addCard(new TutorialCard(17, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, false));
		}
		
		//****************************************************************************************
		this.addCard(new StructureCard("Enter Place Name", new Card_createPlace()));
	}
}

