package GUI.personPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Chapter;
import book.Section;
import person.Person;

import javax.swing.*;
import java.awt.*;

public class PersonTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public PersonTagCard(Person person) {
		//		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setLayout(new GridLayout(0, 5, 5, 5));
		
		if(person != null) {
			for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {				
				for(Section section : chapter.getSections()) {
					if(section.hasTag(person.getID())) {
						TransparentPanel panel = new TransparentPanel();
						panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
						add(panel);
						LinkButton btnSectionTag = new LinkButton(section.getName());
						btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID()))));
						panel.add(btnSectionTag);
						panel.add(new SimpleLabel(";  "));
					}
				}
			}
		}

	}

}
