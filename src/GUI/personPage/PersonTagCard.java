package GUI.personPage;

import java.awt.GridLayout;
import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Chapter;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import person.Person;

public class PersonTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person;
	
	public PersonTagCard(Person person) {
		my_person = person;
//		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setLayout(new GridLayout(0, 5, 5, 5));
		
		if(my_person != null) {
			for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {				
				for(Section section : chapter.getSections()) {
					if(section.hasTag(my_person.getID())) {		
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
