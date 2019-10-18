package GUI.sectionChangePage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;

public class CreateSectionPage extends Page {
	private static final long serialVersionUID = 1L;

	public CreateSectionPage() {
		super("Create Section");
		
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			addCard(new TutorialCard(4, false));
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(7, false));
		}
		
		//****************************************************************************************
		StructureCard card_sectionTitle = new StructureCard("Section Title");
		addCard(card_sectionTitle);
		card_sectionTitle.setBody(new SectionTitleCard(null));

	}

}