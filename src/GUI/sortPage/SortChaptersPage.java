package GUI.sortPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;

public class SortChaptersPage extends Page {
	private static final long serialVersionUID = 1L;
	
	public SortChaptersPage() {
		super("Sort your chapters and sections");
		
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(8, true));
		}
		
		//***************************************************************************************************************************
		//***************************************************************************************************************************
		addCard(new StructureCard("Hint", new SortHintCard()));
		addCard(new StructureCard("Sort your Story", new SortChapterCard()));
	
	}

}
