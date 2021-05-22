package GUI.sectionPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TextPreview;
import GUI_components.TutorialCard;
import book.Chapter;
import book.Section;
import global.UserSettings;

public class SectionPage extends Page {
	private static final long serialVersionUID = 1L;

	public SectionPage(Section section, Chapter chapter) {
		super("Edit Section: " + section.getName());

		if(UserSettings.getInstance().getTutorial().createFirstSection && !UserSettings.getInstance().getTutorial().setDevelopmentStatus) {		
			if(section != null) {
				addCard(new TutorialCard(6, false));
			}
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(7, false));
		}
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			if(section != null) {
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
		//****************************************************************************************
		if(section != null) {
			Section preSection = section.getPreSection();
			if(preSection != null) {				
				String preText = preSection.getText();
				if(!"".equals(preText)) {				
					addCard(new StructureCard("Review Content", new TextPreview(preText, true, true)));
				}
			}
		}
		
		if(section != null) {
			addCard(new StructureCard("Section Content", new EditSectionTextCard(section)));
		}
		
		if(section != null) {
			Section postSection = section.getPostSection();
			if(postSection != null) {				
				String preText = postSection.getText();
				if(!"".equals(preText)) {				
					addCard(new StructureCard("Preview Content", new TextPreview(preText, false, true)));
				}
			}
		}
	
		//****************************************************************************************
		//****************************************************************************************
		setMenu(new SectionInformationMenu(section, chapter));
	}
	
}
