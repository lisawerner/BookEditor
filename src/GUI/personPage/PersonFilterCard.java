package GUI.personPage;

import book.Book;
import GUI.components.LinkButton;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import person.Person;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.ButtonGroup;

import GUI.bookeditorFrame.BookEditorFrame;

public class PersonFilterCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private TransparentPanel panel_filterResult;

	public PersonFilterCard() {
		setLayout(new BorderLayout(10, 10));
		
		TransparentPanel panel_filterSettings = new TransparentPanel();
		add(panel_filterSettings, BorderLayout.NORTH);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		SimpleRadiobutton rdbtnNeverTaggedPerson = new SimpleRadiobutton("Never tagged Person");
		panel_filterSettings.add(rdbtnNeverTaggedPerson);
		btnGroup.add(rdbtnNeverTaggedPerson);
		rdbtnNeverTaggedPerson.addActionListener(e -> filterNeverTaggesPerson());
		
		SimpleRadiobutton rdbtnMissingMainRelationship = new SimpleRadiobutton("Persons without Relationship to any Maincharacter");
		panel_filterSettings.add(rdbtnMissingMainRelationship);
		btnGroup.add(rdbtnMissingMainRelationship);
		rdbtnMissingMainRelationship.addActionListener(e -> filterMissingMainRelationship());
		
		
		panel_filterResult = new TransparentPanel();
		add(panel_filterResult, BorderLayout.CENTER);

	}
	
	private void clear() {
		panel_filterResult.removeAll();
		panel_filterResult.setLayout(new GridLayout(0, 5, 5, 5));
	}
	
	private void fill(List<Person> personList) {
		for(Person person : personList) {
			LinkButton sectionBTN = new LinkButton(person.getInformation().getName());
			sectionBTN.addActionListener(e -> BookEditorFrame.getInstance().openPersonPage(person, false));
			panel_filterResult.add(sectionBTN);
		}
		panel_filterResult.revalidate();
		panel_filterResult.repaint();
	}
	
	private void filterNeverTaggesPerson(){
		clear();
		fill(Book.getInstance().getSociety().getPersonListNeverTagged());
	}
	
	private void filterMissingMainRelationship(){
		clear();
		fill(Book.getInstance().getSociety().getPersonListWithMissingRelationship());
	}

}
