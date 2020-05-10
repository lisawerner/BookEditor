package GUI.sectionPage;

import book.Chapter;
import book.Section;
import global.UserSettings;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TextPreview;
import GUI.components.TutorialCard;

public class SectionPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	public SectionPage(Section section, Chapter chapter) {
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
		//****************************************************************************************
		if(my_section != null) {
			Section preSection = my_section.getPreSection();
			if(preSection != null) {				
				String preText = preSection.getText();
				if(!"".equals(preText)) {				
					addCard(new StructureCard("Review Content", new TextPreview(preText, true, true)));
				}
			}
		}
		
		if(my_section != null) {			
			addCard(new StructureCard("Section Content", new EditSectiontextCard(my_section)));
		}
		
		if(my_section != null) {
			Section postSection = my_section.getPostSection();
			if(postSection != null) {				
				String preText = postSection.getText();
				if(!"".equals(preText)) {				
					addCard(new StructureCard("Preview Content", new TextPreview(preText, false, true)));
				}
			}
		}
	
		//****************************************************************************************
		//****************************************************************************************
		setMenu(new SectionInformationMenu(my_section, chapter));
	}
	
}
