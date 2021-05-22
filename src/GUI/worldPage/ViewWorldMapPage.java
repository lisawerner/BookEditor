package GUI.worldPage;

import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;
import global.UserSettings;

public class ViewWorldMapPage extends Page {
	private static final long serialVersionUID = 1L;
	
	public ViewWorldMapPage() {
		super("World, States, Regions, Citys, Places, ...");

		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, true));
		}
		
		//**************************************************************************************************************************
		//**************************************************************************************************************************
		this.addCard(new StructureCard("World-Hierarchy", new WorldMapCard()));
		
	}


}
