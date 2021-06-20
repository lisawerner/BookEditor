package GUI.pages.world.viewPlace;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.pages.content.changeSection.Page_ChangeSection;
import book.Book;
import book.Chapter;
import book.Section;
import world.Place;

import javax.swing.*;

public class Card_placeTags extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_placeTags(Place place) {

		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section section : chapter.getSections()) {
				if(section.hasTag(place.getID())) {
					add(new LinkButton(section.getName(),
							e -> BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID())))));
					add(new SimpleLabel(";  "));
				}
			}
		}

	}

}
