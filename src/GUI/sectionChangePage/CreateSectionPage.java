package GUI.sectionChangePage;

import book.Chapter;
import global.UserSettings;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;

public class CreateSectionPage extends Page {
	private static final long serialVersionUID = 1L;

	public CreateSectionPage(Chapter chapter) {
		super("Create Section");
		
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			addCard(new TutorialCard(4, false));
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(7, false));
		}
		
		//****************************************************************************************
		addCard(new StructureCard("Section Title", new SectionTitleCard(null, chapter)));
	}

}
