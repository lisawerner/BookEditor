package GUI.personPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ButtonGroup;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import GUI.components.ComboItem;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import person.Person;
import javax.swing.JComboBox;

public class FilterRelationshipsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private SimpleLabel lblHint;
	
	private boolean relationType;
	private SimpleRadiobutton rdbtnHasRelationship;
	private SimpleRadiobutton rdbtnMissingRelationship;
	
	private JComboBox<ComboItem> comboBox;

	private TransparentPanel panel_filterResult;
	
	public FilterRelationshipsCard() {
		setLayout(new BorderLayout(10, 10));
		
		TransparentPanel panel_filterSettings = new TransparentPanel();
		panel_filterSettings.setLayout(new GridLayout(0, 1, 5, 5));
		add(panel_filterSettings, BorderLayout.NORTH);
		
		ButtonGroup btnGroup = new ButtonGroup();
		TransparentPanel panel_typeSettings = new TransparentPanel();
		panel_typeSettings.setLayout(new GridLayout(0, 2, 5, 5));
		panel_filterSettings.add(panel_typeSettings);
		
		rdbtnHasRelationship = new SimpleRadiobutton("Never tagged Person");
		rdbtnHasRelationship.setText("Has Relationship to following Person");
		panel_typeSettings.add(rdbtnHasRelationship);
		btnGroup.add(rdbtnHasRelationship);
		rdbtnHasRelationship.addActionListener(e -> switchRelationType());
		
		rdbtnMissingRelationship = new SimpleRadiobutton("Persons without Relationship to any Maincharacter");
		rdbtnMissingRelationship.setText("Has NO Relationship to following Person");
		panel_typeSettings.add(rdbtnMissingRelationship);
		btnGroup.add(rdbtnMissingRelationship);
		rdbtnMissingRelationship.addActionListener(e -> switchRelationType());
		
		comboBox = new JComboBox<ComboItem>();
		panel_filterSettings.add(comboBox);
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			comboBox.addItem(new ComboItem(person.getInformation().getName(), person.getID()));
		}
		comboBox.addActionListener(e -> selectPerson());
		
		lblHint = new SimpleLabel(" ");
		lblHint.setWarning(true);
		panel_filterSettings.add(lblHint);
		
		
		panel_filterResult = new TransparentPanel();
		add(panel_filterResult, BorderLayout.CENTER);
	}
	
	private void selectPerson() {
		setHints();
		updateFilter();
	}

	private void updateFilter() {
		Person person = Book.getInstance().getSociety().getPerson(((ComboItem) comboBox.getSelectedItem()).getValue());
		if(person != null) {
			clear();
			fill(Book.getInstance().getSociety().getPersonWithRelationshipToPerson(relationType, person));
		}
	}

	private void switchRelationType() {
		relationType = rdbtnHasRelationship.isSelected() && !rdbtnMissingRelationship.isSelected();
		setHints();
		updateFilter();
	}
	
	private void setHints() {
		if(!rdbtnHasRelationship.isSelected() && !rdbtnMissingRelationship.isSelected()) {
			lblHint.setText("You have to select one of the checkboxes");
			rdbtnHasRelationship.setWarning(true);
			rdbtnMissingRelationship.setWarning(true);
		} else {
			lblHint.setText(" ");
			rdbtnHasRelationship.setWarning(false);
			rdbtnMissingRelationship.setWarning(false);
		}
		if(comboBox.getSelectedIndex() == -1) {
			lblHint.setText("You have to select one person of the list");
			//TODO: comboBox.setWarning(true);
		} else {
			lblHint.setText(" ");
			//TODO: comboBox.setWarning(false);
		}
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
		
}
