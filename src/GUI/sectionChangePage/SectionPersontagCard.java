package GUI.sectionChangePage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import GUI_components.WrapLayout;
import book.Book;
import book.Section;
import global.Tag;
import person.Person;

public class SectionPersontagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	private TransparentPanel panel_appereadTagUnimportendList;
	private JToggleButton btn_showHide;
	
	public SectionPersontagCard(Section section) {
		my_section = section;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_appearedPersons = new TransparentPanel();
		panel_appearedPersons.setLayout(new BorderLayout(5, 5));
		this.add(panel_appearedPersons);
		
		SimpleLabel lbl_appearedPersons = new SimpleLabel("Tag Person, who are appeared at that section");
		panel_appearedPersons.add(lbl_appearedPersons, BorderLayout.NORTH);
		
		TransparentPanel panel_appereadTagList = new TransparentPanel();
		panel_appearedPersons.add(panel_appereadTagList, BorderLayout.CENTER);
		panel_appereadTagList.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		for(Person person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()) {
			SimpleCheckbox chckbxPerson = new SimpleCheckbox(person.getInformation().getNickname());
			panel_appereadTagList.add(chckbxPerson);			
			chckbxPerson.setSelected(my_section.hasTag(person.getID()));
			chckbxPerson.addActionListener(e -> save(chckbxPerson, person));
		}
		for(Person person : Book.getInstance().getSociety().getPersonListImportantCharacters()) {
			SimpleCheckbox chckbxPerson = new SimpleCheckbox(person.getInformation().getNickname());
			panel_appereadTagList.add(chckbxPerson);			
			chckbxPerson.setSelected(my_section.hasTag(person.getID()));
			chckbxPerson.addActionListener(e -> save(chckbxPerson, person));
		}
		
		TransparentPanel panel_showHide = new TransparentPanel();
		panel_showHide.setLayout(new BorderLayout(5, 5));
		panel_appearedPersons.add(panel_showHide, BorderLayout.SOUTH);
		
		panel_appereadTagUnimportendList = new TransparentPanel();

		
		btn_showHide = new JToggleButton("Show/Hide full person list");
		panel_showHide.add(btn_showHide, BorderLayout.NORTH);
		btn_showHide.addActionListener(e -> showFullPersonList());
		
		panel_showHide.add(panel_appereadTagUnimportendList, BorderLayout.CENTER);
		panel_appereadTagUnimportendList.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		
		for(Person person : Book.getInstance().getSociety().getPersonListTheRest()) {
			SimpleCheckbox chckbxPerson = new SimpleCheckbox(person.getInformation().getNickname());
			panel_appereadTagUnimportendList.add(chckbxPerson);			
			chckbxPerson.setSelected(my_section.hasTag(person.getID()));
			if(chckbxPerson.isSelected()){				
				btn_showHide.setSelected(chckbxPerson.isSelected());
				showFullPersonList();
			}
			chckbxPerson.addActionListener(e -> save(chckbxPerson, person));
		}
		
		this.add(new JSeparator());
		
		TransparentPanel panel_mentionedPersons = new TransparentPanel();
		panel_mentionedPersons.setLayout(new BorderLayout(5, 5));
		this.add(panel_mentionedPersons);
		
		SimpleLabel lbl_mentionedPersons = new SimpleLabel("<html>Tag Person, who are only mentioned at that section<br/>(comming soon)</html>");
		panel_mentionedPersons.add(lbl_mentionedPersons, BorderLayout.NORTH);

	}
	
	private void showFullPersonList() {
		panel_appereadTagUnimportendList.setVisible(btn_showHide.isSelected());
	}

	private void save(SimpleCheckbox chckbxPerson, Person person) {
		if(chckbxPerson.isSelected()) {			
			//System.out.println("Click: Is Selected now");
			my_section.addTag(new Tag(person.getID(), person.getClass().getName()));
		} else {
			//System.out.println("Click: Is NOOOTTT Selected anymore");
			my_section.removeTag(person.getID());
		}
	}

}
