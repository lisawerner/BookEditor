package GUI.pages.world.viewPlace;

import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;
import global.UserSettings;
import book.world.Place;

public class Page_viewPlace extends Page {
	private static final long serialVersionUID = 1L;

	public Page_viewPlace(Place place, boolean isSecondFrame) {
		super("World, States, Regions, Citys, Places, ...");

		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			addCard(new TutorialCard(17, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, false));
		}
		
		//****************************************************************************************
		this.addCard(new StructureCard("Change Place Name", new Card_placeInformation(place)));
		
		//****************************************************************************************
		if(place != null) {
			this.addCard(new StructureCard("Place - World Dependencies", new Card_placeWorldDependencies(place)));
		}
			
		//****************************************************************************************
		if(place != null) {
			this.addCard(new StructureCard("Place was tagged in Section(s)", new Card_placeTags(place)));
		}
	}	
}
