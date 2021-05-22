package GUI.worldPage;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Chapter;
import book.Section;
import world.Place;

public class PlaceTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public PlaceTagCard(Place place) {

		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section section : chapter.getSections()) {
				if(section.hasTag(place.getID())) {
					LinkButton btnSectionTag = new LinkButton(section.getName());
					btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID()))));
					add(btnSectionTag);
					add(new SimpleLabel(";  "));
				}
			}
		}

	}

}
