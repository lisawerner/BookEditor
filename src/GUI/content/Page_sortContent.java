package GUI.content;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;

public class Page_sortContent extends Page {
	private static final long serialVersionUID = 1L;
	
	public Page_sortContent() {
		super("Sort your chapters and sections");
		
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(8, true));
		}
		
		//***************************************************************************************************************************
		//***************************************************************************************************************************
		addCard(new StructureCard("Don't know a title at the moment TODO", new Card_ContentInformation()));
		addCard(new StructureCard("Sort your Story", new Card_SortChapter()));
	
	}

}
