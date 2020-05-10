package GUI.worldPage;

import global.UserSettings;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;

public class ViewWorldmapPage extends Page {
	private static final long serialVersionUID = 1L;
	
	public ViewWorldmapPage() {
		super("World, States, Regions, Citys, Places, ...");

		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, true));
		}
		
		//**************************************************************************************************************************
		//**************************************************************************************************************************
		this.addCard(new StructureCard("World-Hierachy", new WorldMapCard()));
		
	}


}
