package GUI.sectionChangePage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI_components.SimpleCheckbox;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import global.Tag;
import person.Person;

public class SectionPersontagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	public SectionPersontagCard(Section section) {
		my_section = section;
		
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			SimpleCheckbox chckbxPerson = new SimpleCheckbox(person.getInformation().getName());
			add(chckbxPerson);			
			chckbxPerson.setSelected(my_section.hasTag(person.getID()));
			chckbxPerson.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxPerson.isSelected()) {			
						//System.out.println("Click: Is Selected now");
						my_section.addTag(new Tag(person.getID(), person.getClass().getName()));
					} else {
						//System.out.println("Click: Is NOOOTTT Selected anymore");
						my_section.removeTag(person.getID());
					}
				}
			});
		}

	}

}
