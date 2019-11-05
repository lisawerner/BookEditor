package GUI.personPage;

import java.awt.GridLayout;
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
//		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setLayout(new GridLayout(0, 5, 5, 5));
		
		if(my_person != null) {			
			for(Section section : Book.getInstance().getSectionList().getSections()) {
				if(section.hasTag(my_person.getID())) {		
					TransparentPanel panel = new TransparentPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
					add(panel);
					LinkButton btnSectionTag = new LinkButton(section.getName());
					btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section)));
					panel.add(btnSectionTag);
					panel.add(new SimpleLabel(";  "));
				}
			}
		}

	}

}
