package GUI.personPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.LinkButton;
import GUI_components.SimpleRadiobutton;
import GUI_components.TransparentPanel;
import book.Book;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PersonFilterCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final TransparentPanel panel_filterResult;

	public PersonFilterCard() {
		setLayout(new BorderLayout(10, 10));
		
		TransparentPanel panel_filterSettings = new TransparentPanel();
		add(panel_filterSettings, BorderLayout.NORTH);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		SimpleRadiobutton rdbtnNeverTaggedPerson = new SimpleRadiobutton("Never tagged Person");
		panel_filterSettings.add(rdbtnNeverTaggedPerson);
		btnGroup.add(rdbtnNeverTaggedPerson);
		rdbtnNeverTaggedPerson.addActionListener(e -> filterNeverTaggedPerson());
		
		SimpleRadiobutton rdbtnMissingMainRelationship = new SimpleRadiobutton("Persons without Relationship to any main character");
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
			sectionBTN.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person, false)));
			panel_filterResult.add(sectionBTN);
		}
		panel_filterResult.revalidate();
		panel_filterResult.repaint();
	}
	
	private void filterNeverTaggedPerson(){
		clear();
		fill(Book.getInstance().getSociety().getPersonListNeverTagged());
	}
	
	private void filterMissingMainRelationship(){
		clear();
		fill(Book.getInstance().getSociety().getPersonListWithMissingRelationship());
	}

}
