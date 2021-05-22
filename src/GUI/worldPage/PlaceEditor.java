package GUI.worldPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;
import world.Place;

public class PlaceEditor extends Page {
	private static final long serialVersionUID = 1L;

	public PlaceEditor(Place place, boolean isSecondFrame) {
		super("World, States, Regions, City's, Places, ...");

		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			addCard(new TutorialCard(17, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, false));
		}
		
		//****************************************************************************************
		this.addCard(new StructureCard("Change Place Name", new PlaceInformationCard(place)));
		
		//****************************************************************************************
		if(place != null) {
			this.addCard(new StructureCard("Place - World Dependencies", new PlaceWorldDependenciesCard(place)));
		}
			
		//****************************************************************************************
		if(place != null) {
			this.addCard(new StructureCard("Place was tagged in Section(s)", new PlaceTagCard(place)));
		}		
		
		//****************************************************************************************
		//****************************************************************************************
		if(!isSecondFrame) {setMenu(new PlaceMenu());}
	}	
}
