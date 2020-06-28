package GUI.pages.content.createSection;

import book.Chapter;
import global.UserSettings;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;

public class Page_createSection extends Page {
	private static final long serialVersionUID = 1L;

	public Page_createSection(Chapter chapter) {
		super("Create Section");
		
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			addCard(new TutorialCard(4, false));
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(7, false));
		}
		
		//****************************************************************************************
		addCard(new StructureCard("Section Title", new Card_createSectionByTitle(chapter)));
	}

}
