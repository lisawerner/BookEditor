package GUI.bookeditorFrame;

import javax.swing.JButton;

import GUI.chapter.Page_viewChapter;
import GUI.content.Page_sortContent;
import GUI.filterPage.FilterChaptersPage;
import GUI.miscPage.NotesPage;
import GUI.personPage.ViewSocietyPage;
import GUI.printPage.PrintPage;
import GUI.settingsPage.BookSettingsPage;
import GUI.timelinePages.Page_ViewTimeline;
import GUI.worldPage.ViewWorldmapPage;
import GUI_components.MenuBook;
import book.Book;
import book.Chapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorMenu extends MenuBook {
	private static final long serialVersionUID = 1L;
	
	public EditorMenu() {
		
		JButton btnOpenHome = createMainButton("Home");
		btnOpenHome.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new HomePage()));
		
		JButton btnOpenSettings = createMainButton("    Book Settings    ");
		btnOpenSettings.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new BookSettingsPage()));
		
		JButton btnOpenPersons = createMainButton("Society");
		btnOpenPersons.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new ViewSocietyPage()));
		
		JButton btnOpenPlaces = createMainButton("World");
		btnOpenPlaces.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new ViewWorldmapPage()));
		
		JButton btnOpenTimeline = createMainButton("Timeline");
		btnOpenTimeline.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_ViewTimeline()));
		
		JButton btnOpenNoteStuff = createMainButton("Notes");
		btnOpenNoteStuff.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new NotesPage()));
		
		JButton btnOpenPrint = createMainButton("Print & Export");
		btnOpenPrint.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PrintPage()));

		JButton btnSort = createContentButton("Change Content");
		btnSort.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_sortContent()));
		
		JButton btnFilter = createContentButton("Filter Content");
		btnFilter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new FilterChaptersPage()));

		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {
			String name = chapter.getTitle();
			if(name.length() > 17) {
				name = name.substring(0,15) + "...";
				//TODO: W needs more space, then i, so name has cut by 17 instead of 25 letters! Maybe can get string-spacelength instead of letter-count
			}
			JButton btnOpenChapter = createSectionItem(name);
			btnOpenChapter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter));
					//TODO: Set this Border to a line Border
					//setBorder(BorderFactory.createLineBorder(Color.black));
					//AND set ALL other borders of MenuSectionButton-List to EmptyBorder!!!!
				}
			});
			
			//TODO: If BookSetting is selected for huge-frame, then also show all Sections at chapterlist
//		for(Section section : Book.getInstance().getSectionList().getSections()) {
//			String name = section.getName();
//			if(name.length() > 17) {
//				name = name.substring(0,15) + "...";
//				//TODO: W needs more space, then i, so name has cut by 17 instead of 25 letters! Maybe can get string-spacelength instead of letter-count
//			}
//			String my_text = "";
//			if(section.isUnsorted()) {			
//				my_text = "  ??? ";
//			} else {
//				if(section.isChapter()) {
//					my_text = "  ";
//				} else {
//					my_text = "        >> ";
//				}
//			}
//			my_text += name;
//			JButton btnOpenSection = createSectionItem(my_text);
//			btnOpenSection.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getSectionList().getChapter(section.getParentChapterID())));
//					//TODO: Set this Border to a line Border
//					//setBorder(BorderFactory.createLineBorder(Color.black));
//					//AND set ALL other borders of MenuSectionButton-List to EmptyBorder!!!!
//				}
//			});
//		}
		}
		

	}

}
