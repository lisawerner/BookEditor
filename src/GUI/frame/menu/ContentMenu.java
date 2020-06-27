package GUI.frame.menu;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.pages.chapter.Page_viewChapter;
import GUI.pages.content.Page_sortContent;
import GUI.pages.content.settings.Page_ContentSettings;
import GUI.pages.filterPage.FilterChaptersPage;
import book.Book;
import book.Chapter;
import GUI.components.FrameSubmenu;
import GUI.components.MenuButton;
import GUI.components.MenuListButton;

public class ContentMenu extends FrameSubmenu {
	private static final long serialVersionUID = 1L;

	public ContentMenu() {
		super("Table of Content:", "List of Chapters:");
				
		addButton(new MenuButton("Change Content", e -> BookEditorFrame.getInstance().switchBody(new Page_sortContent())));
		addButton(new MenuButton("Filter Content", e -> BookEditorFrame.getInstance().switchBody(new FilterChaptersPage())));
		addButton(new MenuButton("Content Settings", e -> BookEditorFrame.getInstance().switchBody(new Page_ContentSettings())));

		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {
			addListEntry(new MenuListButton(chapter.getTitle(), e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter))));
			
//			TODO: If BookSetting is selected for huge-frame, then also show all Sections at chapterlist
//			for(Section section : Book.getInstance().getSectionList().getSections()) {
//				String name = section.getName();
//				if(name.length() > 17) {
//					name = name.substring(0,15) + "...";
//					//TODO: W needs more space, then i, so name has cut by 17 instead of 25 letters! Maybe can get string-spacelength instead of letter-count
//				}
//				String my_text = "";
//				if(section.isUnsorted()) {			
//					my_text = "  ??? ";
//				} else {
//					if(section.isChapter()) {
//						my_text = "  ";
//					} else {
//						my_text = "        >> ";
//					}
//				}
//				my_text += name;
//				JButton btnOpenSection = createSectionItem(my_text);
//				btnOpenSection.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getSectionList().getChapter(section.getParentChapterID())));
//						//TODO: Set this Border to a line Border
//						//setBorder(BorderFactory.createLineBorder(Color.black));
//						//AND set ALL other borders of MenuSectionButton-List to EmptyBorder!!!!
//					}
//				});
//			}
		}		

	}
	
}
