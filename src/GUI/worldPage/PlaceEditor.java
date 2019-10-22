package GUI.worldPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;
import world.Place;

public class PlaceEditor extends Page {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	public PlaceEditor(Place place) {
		super("World, States, Regions, Citys, Places, ...");
		my_place = place;
		
		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			addCard(new TutorialCard(17, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, false));
		}
		
		//****************************************************************************************
		this.addCard(new StructureCard("Change Place Name", new PlaceInformationCard(my_place)));
		
		//****************************************************************************************
		if(my_place != null) {			
			this.addCard(new StructureCard("Place - World Dependencies", new PlaceWorlddependenciesCard(my_place)));
		}
			
		//****************************************************************************************
		if(my_place != null) {
			this.addCard(new StructureCard("Place was tagged in Section(s)", new PlaceTagCard(my_place)));			
		}		
		
		//****************************************************************************************
		//****************************************************************************************
		setMenu(new PlaceMenu());
	}
}
