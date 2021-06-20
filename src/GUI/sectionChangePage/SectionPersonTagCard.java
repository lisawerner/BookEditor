package GUI.sectionChangePage;

import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;
import book.content.Book;
import book.content.Section;
import global.Tag;
import book.person.Person;

import javax.swing.*;
import java.awt.*;

public class SectionPersonTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final Section my_section;
	
	private final TransparentPanel panel_appearedTagUnimportantList;
	private final JToggleButton btn_showHide;
	
	public SectionPersonTagCard(Section section) {
		my_section = section;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_appearedPersons = new TransparentPanel();
		panel_appearedPersons.setLayout(new BorderLayout(5, 5));
		this.add(panel_appearedPersons);
		
		SimpleLabel lbl_appearedPersons = new SimpleLabel("Tag Person, who are appeared at that section");
		panel_appearedPersons.add(lbl_appearedPersons, BorderLayout.NORTH);
		
		TransparentPanel panel_appearedTagList = new TransparentPanel();
		panel_appearedPersons.add(panel_appearedTagList, BorderLayout.CENTER);
		panel_appearedTagList.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		for(Person person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()) {
			SimpleCheckbox checkboxPerson = new SimpleCheckbox(person.getInformation().getNickname());
			panel_appearedTagList.add(checkboxPerson);
			checkboxPerson.setSelected(my_section.hasTag(person.getID()));
			checkboxPerson.addActionListener(e -> save(checkboxPerson, person));
		}
		for(Person person : Book.getInstance().getSociety().getPersonListImportantCharacters()) {
			SimpleCheckbox checkboxPerson = new SimpleCheckbox(person.getInformation().getNickname());
			panel_appearedTagList.add(checkboxPerson);
			checkboxPerson.setSelected(my_section.hasTag(person.getID()));
			checkboxPerson.addActionListener(e -> save(checkboxPerson, person));
		}
		
		TransparentPanel panel_showHide = new TransparentPanel();
		panel_showHide.setLayout(new BorderLayout(5, 5));
		panel_appearedPersons.add(panel_showHide, BorderLayout.SOUTH);
		
		panel_appearedTagUnimportantList = new TransparentPanel();

		
		btn_showHide = new JToggleButton("Show/Hide full book.person list");
		panel_showHide.add(btn_showHide, BorderLayout.NORTH);
		btn_showHide.addActionListener(e -> showFullPersonList());
		
		panel_showHide.add(panel_appearedTagUnimportantList, BorderLayout.CENTER);
		panel_appearedTagUnimportantList.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		
		for(Person person : Book.getInstance().getSociety().getPersonListTheRest()) {
			SimpleCheckbox checkboxPerson = new SimpleCheckbox(person.getInformation().getNickname());
			panel_appearedTagUnimportantList.add(checkboxPerson);
			checkboxPerson.setSelected(my_section.hasTag(person.getID()));
			if(checkboxPerson.isSelected()){
				btn_showHide.setSelected(checkboxPerson.isSelected());
				showFullPersonList();
			}
			checkboxPerson.addActionListener(e -> save(checkboxPerson, person));
		}
		
		this.add(new JSeparator());
		
		TransparentPanel panel_mentionedPersons = new TransparentPanel();
		panel_mentionedPersons.setLayout(new BorderLayout(5, 5));
		this.add(panel_mentionedPersons);
		
		SimpleLabel lbl_mentionedPersons = new SimpleLabel("<html>Tag Person, who are only mentioned at that section<br/>(coming soon)</html>");
		panel_mentionedPersons.add(lbl_mentionedPersons, BorderLayout.NORTH);

	}
	
	private void showFullPersonList() {
		panel_appearedTagUnimportantList.setVisible(btn_showHide.isSelected());
	}

	private void save(SimpleCheckbox checkboxPerson, Person person) {
		if(checkboxPerson.isSelected()) {
			//System.out.println("Click: Is Selected now");
			my_section.addTag(new Tag(person.getID(), person.getClass().getName()));
		} else {
			//System.out.println("Click: Is NOT Selected anymore");
			my_section.removeTag(person.getID());
		}
	}

}
