package GUI.sectionChangePage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TutorialCard;
import book.Book;
import book.Chapter;
import book.Section;
import global.UserSettings;
import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SectionEditorPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;

	public SectionEditorPage( Section section, Chapter chapter) {
		super("Edit Section: " + section.getName());
		my_section = section;
		
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
		addCard(new StructureCard("Section Title", new SectionTitleCard(my_section, null)));

		//********************************************************************************
		addCard(new StructureCard("Development Status", new SectionDevstatusCard(my_section, chapter)));
		
		//********************************************************************************
		addCard(new StructureCard("Notes, Research and more", new SectionNoteCard(my_section)));

		//********************************************************************************
		addCard(new StructureCard("Change Timestamp", new SectionTimestampCard(my_section, chapter)));		
		
		//********************************************************************************
		addCard(new StructureCard("Change Person-Tags", new SectionPersontagCard(my_section)));

		//********************************************************************************
		addCard(new StructureCard("Change Relationships", new SectionRelationshipCard(my_section)));

		//********************************************************************************
		addCard(new StructureCard("Change Place-Tags", new SectionPlacetagCard(my_section)));
		
		//********************************************************************************
		addCard(new StructureCard("Change your Personal-Tags", new SectionPersonaltagCard(my_section)));
		
		//********************************************************************************
		addCard(new StructureCard("Delete Section", new DeleteSectionCard(my_section, chapter)));
	
		//********************************************************************************
		//********************************************************************************
		JButton btnBackToSection = new JButton("Back to Section");
		btnBackToSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book.getInstance().save();
				if(!UserSettings.getInstance().getTutorial().tagPersonToSection) {
					UserSettings.getInstance().getTutorial().tagPersonToSection = true;
					UserSettings.getInstance().save();
				}
				BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, chapter));
			}
		});
		setFooter(btnBackToSection);

	}
}
