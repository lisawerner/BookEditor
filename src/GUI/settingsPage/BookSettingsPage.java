package GUI.settingsPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import global.UserSettings;

public class BookSettingsPage extends Page {
	private static final long serialVersionUID = 1L;

	public BookSettingsPage() {
		super("Book Settings");
				
		//******************************************************************************************************************************************
		
		if(!UserSettings.getInstance().getTutorial().chooseFirstColorTheme) {			
			addCard(new TutorialCard(2, false));
		}
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			addCard(new TutorialCard(3, false));
		}
		
		//******************************************************************************************************************************************
		//********************************************************************
		//CARD change Book Title
		addCard(new StructureCard("Change Book Title", new BooktitleSettingsCard()));
		
		//********************************************************************
		//CARD change Book Society
		addCard(new StructureCard("Change Society-Settings", new SocietySettingsCard()));
		
		//********************************************************************
		//CARD change Timeline Settings
		addCard(new StructureCard("Change Timeline-Settings", new CalendarSettingsCard()));
		
		//********************************************************************
		//CARD change Design settings like: Color-Theme and screenSize
		addCard(new StructureCard("Change Color Theme of Editor:", new DesignSettingsCard()));

		//********************************************************************
		//CARD change Print Settings
		addCard(new StructureCard("Change Print Settings:", new PrintSettingsCard()));
	}

}
