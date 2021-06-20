package GUI.pages.society.personEditorPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.pages.content.changeSection.Page_ChangeSection;
import book.content.Book;
import book.content.Chapter;
import book.content.Section;
import book.person.Person;

import javax.swing.*;
import java.awt.*;

public class Card_PersonTag extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_PersonTag(Person person) {
		//		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setLayout(new GridLayout(0, 5, 5, 5));
		
		if(person != null) {
			for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {				
				for(Section section : chapter.getSections()) {
					if(section.hasTag(person.getID())) {
						TransparentPanel panel = new TransparentPanel();
						panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
						add(panel);
						panel.add(new LinkButton(section.getName(),
								e -> BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID())))));
						panel.add(new SimpleLabel(";  "));
					}
				}
			}
		}

	}

}
