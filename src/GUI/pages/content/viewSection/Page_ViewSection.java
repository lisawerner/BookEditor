package GUI.pages.content.viewSection;

import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TextPreview;
import GUI.components.TutorialCard;
import book.Chapter;
import book.Section;
import global.UserSettings;

public class Page_ViewSection extends Page {
	private static final long serialVersionUID = 1L;

	public Page_ViewSection(Section section, Chapter chapter) {
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
			this.addPreview(preSection, TextPreview.TYPE_PREVIEW_BEFORE);

			addCard(new StructureCard("Section Content", new Card_EditSectionText(section)));

			Section postSection = section.getPostSection();
			this.addPreview(postSection, TextPreview.TYPE_PREVIEW_AFTER);
		}
	
		//****************************************************************************************
		//****************************************************************************************
		setMenu(new Menu_SectionInformation(section, chapter));
	}

	private void addPreview(Section section, String previewType) {
		if(section != null) {
			String preText = section.getText();
			if(!"".equals(preText)) {
				addCard(new StructureCard("Preview of previous/following content", new Card_PreviewNearContent(section, previewType)));
			}
		}
	}
	
}
