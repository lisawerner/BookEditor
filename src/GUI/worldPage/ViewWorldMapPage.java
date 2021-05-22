package GUI.worldPage;

import global.UserSettings;
import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;

public class ViewWorldMapPage extends Page {
	private static final long serialVersionUID = 1L;
	
	public ViewWorldMapPage() {
		super("World, States, Regions, City's, Places, ...");

		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, true));
		}
		
		//**************************************************************************************************************************
		//**************************************************************************************************************************
		this.addCard(new StructureCard("World-Hierarchy", new WorldMapCard()));
		
		//**************************************************************************************************************************
		//**************************************************************************************************************************
		setMenu(new PlaceMenu());
	}


}
