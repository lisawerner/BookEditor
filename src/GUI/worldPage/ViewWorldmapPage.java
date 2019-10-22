package GUI.worldPage;

import global.UserSettings;
import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;

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
		
		//**************************************************************************************************************************
		//**************************************************************************************************************************
		setMenu(new PlaceMenu());
	}


}
