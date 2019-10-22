package GUI.sectionPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import book.Section;
import global.UserSettings;

public class SectionPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	public SectionPage(Section section) {
		super("Edit Section: " + section.getName());
		my_section = section;
				
		if(UserSettings.getInstance().getTutorial().createFirstSection && !UserSettings.getInstance().getTutorial().setDevelopmentStatus) {		
			if(my_section != null) {				
				addCard(new TutorialCard(6, false));
			}
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(7, false));
		}
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			if(my_section != null) {
				addCard(new TutorialCard(9, false));
			}
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			addCard(new TutorialCard(13, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			addCard(new TutorialCard(15, true));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			addCard(new TutorialCard(19, false));
		}
		
		//****************************************************************************************
		if(my_section != null) {			
			addCard(new StructureCard("Section Information", new SectionInformationCard(my_section)));
		}
		//****************************************************************************************
		if(my_section != null) {			
			addCard(new StructureCard("Section Content", new EditSectiontextCard(my_section)));
		}
	
		//****************************************************************************************
		//****************************************************************************************
		setMenu(new SectionInformationMenu(my_section));		
	}
	
}
