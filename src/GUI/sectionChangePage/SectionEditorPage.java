package GUI.sectionChangePage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.Page;
import GUI.components.StructureCard;
import GUI.components.TutorialCard;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Chapter;
import book.Section;
import global.UserSettings;

import javax.swing.*;

public class SectionEditorPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private final Section my_section;
	private final Chapter my_chapter;

	public SectionEditorPage( Section section, Chapter chapter) {
		super("Edit Section: " + section.getName());
		my_section = section;
		my_chapter = chapter;
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			addCard(new TutorialCard(9, false));
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			addCard(new TutorialCard(14, false));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			addCard(new TutorialCard(19, false));
		}
		
		//********************************************************************************
		//********************************************************************************
		JButton btnTopBackToSection = new JButton("Back to Section");
		btnTopBackToSection.addActionListener(e -> actionBackToSection());
		setHeader(btnTopBackToSection);
		//********************************************************************************
		//********************************************************************************
		addCard(new StructureCard("Section Title", new SectionTitleCard(my_section)));

		//********************************************************************************
		addCard(new StructureCard("Development Status", new SectionDevStatusCard(my_section, chapter)));
		
		//********************************************************************************
		addCard(new StructureCard("Notes, Research and more", new SectionNoteCard(my_section)));

		//********************************************************************************
		addCard(new StructureCard("Change Timestamp", new SectionTimestampCard(my_section, chapter)));		
		
		//********************************************************************************
		addCard(new StructureCard("Change Person-Tags", new SectionPersonTagCard(my_section)));

		//********************************************************************************
		addCard(new StructureCard("Change Relationships", new SectionRelationshipCard(my_section)));

		//********************************************************************************
		addCard(new StructureCard("Change Place-Tags", new SectionPlaceTagCard(my_section)));
		
		//********************************************************************************
		addCard(new StructureCard("Change your Personal-Tags", new SectionPersonalTagCard(my_section)));
		
		//********************************************************************************
		addCard(new StructureCard("Delete Section", new DeleteSectionCard(my_section, chapter)));
	
		//********************************************************************************
		//********************************************************************************
		JButton btnBackToSection = new JButton("Back to Section");
		btnBackToSection.addActionListener(e -> actionBackToSection());
		setFooter(btnBackToSection);

	}
	
	private void actionBackToSection(){
		Book.getInstance().save();
		if(!UserSettings.getInstance().getTutorial().tagPersonToSection) {
			UserSettings.getInstance().getTutorial().tagPersonToSection = true;
			UserSettings.getInstance().save();
		}
		BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_chapter));
	}
}
