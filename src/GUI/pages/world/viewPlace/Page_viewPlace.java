package GUI.pages.world.viewPlace;

import global.UserSettings;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;
import world.Place;

public class Page_viewPlace extends Page {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	public Page_viewPlace(Place place, boolean isSecondFrame) {
		super("World, States, Regions, Citys, Places, ...");
		my_place = place;
		
		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			addCard(new TutorialCard(17, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, false));
		}
		
		//****************************************************************************************
		this.addCard(new StructureCard("Change Place Name", new Card_placeInformation(my_place)));
		
		//****************************************************************************************
		if(my_place != null) {			
			this.addCard(new StructureCard("Place - World Dependencies", new Card_placeWorldDependencies(my_place)));
		}
			
		//****************************************************************************************
		if(my_place != null) {
			this.addCard(new StructureCard("Place was tagged in Section(s)", new Card_placeTags(my_place)));			
		}
	}	
}
