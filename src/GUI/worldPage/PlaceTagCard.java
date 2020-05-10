package GUI.worldPage;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Chapter;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import world.Place;

public class PlaceTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;

	public PlaceTagCard(Place place) {
		my_place = place;
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section section : chapter.getSections()) {
				if(section.hasTag(my_place.getID())) {
					LinkButton btnSectionTag = new LinkButton(section.getName());
					btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID()))));
					add(btnSectionTag);
					add(new SimpleLabel(";  "));
				}
			}
		}

	}

}
