package GUI.personPage;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import person.Person;

public class PersonTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person;
	
	public PersonTagCard(Person person) {
		my_person = person;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		if(my_person != null) {			
			for(Section section : Book.getInstance().getSectionList().getSections()) {
				if(section.hasTag(my_person.getID())) {					
					LinkButton btnSectionTag = new LinkButton(section.getName());
					btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section)));
					add(btnSectionTag);
					add(new SimpleLabel(";  "));
				}
			}
		}

	}

}
