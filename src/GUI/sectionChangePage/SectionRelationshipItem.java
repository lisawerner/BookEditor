package GUI.sectionChangePage;

import GUI.components.ComboItem;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextField;
import GUI.components.TransparentPanel;
import book.Book;
import book.Section;
import person.Person;
import person.Relationship;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SectionRelationshipItem extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	private final SimpleTextField txt_relationshipType;
	
	private final Section my_section;
	private final SectionRelationshipCard my_body;
	private Relationship my_relationship;
	
	private final SimpleLabel lblWARNING;
	
	private final JComboBox<ComboItem> cmboxPersonA;
	private Person personA;
	private final JComboBox<ComboItem> cmboxPersonB;
	private Person personB;

	public SectionRelationshipItem(SectionRelationshipCard body, Section section, Relationship relationship) {
		my_section = section;
		my_body = body;
		my_relationship = relationship;
		setLayout(new BorderLayout(5, 5));
	
		TransparentPanel panel_buttons = new TransparentPanel();
		panel_buttons.setLayout(new GridLayout(0, 1, 2, 2));
		add(panel_buttons, BorderLayout.EAST);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> save());
		panel_buttons.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(e -> delete());

		if(my_relationship != null) {
			btnAdd.setText("Change");
			panel_buttons.add(btnDelete);
		}
		
		TransparentPanel panel_type = new TransparentPanel();
		add(panel_type, BorderLayout.WEST);
		panel_type.setLayout(new GridLayout(0, 1, 0, 0));
		SimpleLabel lblType = new SimpleLabel("Relationship-Type: ");
		panel_type.add(lblType);
		txt_relationshipType = new SimpleTextField();
		if(my_relationship != null) {txt_relationshipType.setText(my_relationship.getDescribingRelationshipType());}
		panel_type.add(txt_relationshipType);
		txt_relationshipType.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void removeUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void insertUpdate(DocumentEvent e) {
				updateSaveHint();
			}
		});

		lblWARNING = new SimpleLabel(" ");
		add(lblWARNING, BorderLayout.SOUTH);
		lblWARNING.setWarning(true);
		
		TransparentPanel panel_Persons = new TransparentPanel();
		add(panel_Persons, BorderLayout.CENTER);
		panel_Persons.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel panel_personA = new TransparentPanel();
		panel_personA.setLayout(new BorderLayout(5, 5));
		panel_Persons.add(panel_personA);
		SimpleLabel lblPersonA = new SimpleLabel("Choose Person A: ");
		panel_personA.add(lblPersonA, BorderLayout.WEST);
		cmboxPersonA = new JComboBox<>();
		panel_personA.add(cmboxPersonA, BorderLayout.CENTER);
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			ComboItem item = new ComboItem(person.getInformation().getNickname(), person.getID());
			cmboxPersonA.addItem(item);

			if(my_relationship != null) {
				if(my_relationship.getPersonA().getIDtoString().equals(person.getID().getIDtoString())) {
					cmboxPersonA.setSelectedItem(item);
					personA = person;
				}
			}
		}
		cmboxPersonA.addActionListener(e -> setPersonA());
		
		
		TransparentPanel panel_personB = new TransparentPanel();
		panel_Persons.add(panel_personB);
		panel_personB.setLayout(new BorderLayout(5, 5));
		SimpleLabel lblPersonB = new SimpleLabel("Choose Person B: ");
		panel_personB.add(lblPersonB, BorderLayout.WEST);
		cmboxPersonB = new JComboBox<>();
		panel_personB.add(cmboxPersonB, BorderLayout.CENTER);
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			ComboItem item = new ComboItem(person.getInformation().getNickname(), person.getID());
			cmboxPersonB.addItem(item);

			if(my_relationship != null) {
				if(my_relationship.getPersonB().getIDtoString().equals(person.getID().getIDtoString())) {
					cmboxPersonB.setSelectedItem(item);
					personB = person;
				}
			}
		}
		cmboxPersonB.addActionListener(e -> setPersonB());
	}
	
	private void setPersonA() {
		ComboItem item = (ComboItem) cmboxPersonA.getSelectedItem();
		personA = Book.getInstance().getSociety().getPerson(item.getValue());
		updateSaveHint();
	}
	private void setPersonB() {
		ComboItem item = (ComboItem) cmboxPersonB.getSelectedItem();
		personB = Book.getInstance().getSociety().getPerson(item.getValue());
		updateSaveHint();
	}
	
	private void save() {
		lblWARNING.setText(" ");
		boolean canSave = true;
		
		if(personA == null || personB == null) {
			lblWARNING.setText("You have to choose two persons for setting a relationship.");
			canSave = false;
		} else {
			if(personA.equals(personB)) {
				lblWARNING.setText("You have to choose two different persons for setting a relationship.");
				canSave = false;
			}
		}
		
		if(txt_relationshipType.getText().equals("")) {
			lblWARNING.setText("You have to enter a describing relation type (for example: Father, Friends, Colleagues.");
			canSave = false;
		}
		
		if(canSave) {
			if(my_relationship == null) {
				Relationship newRelationship = new Relationship(personA, personB, txt_relationshipType.getText());
				my_body.addRelationship(newRelationship);
				my_section.addRelationship(newRelationship);
				txt_relationshipType.setText("");
				my_relationship = null;
				lblWARNING.setText("Successfully saved!");
			} else {
				my_relationship.change(personA, personB, txt_relationshipType.getText());
			}
		}
	}
	
	private void delete() {
		my_section.removeRelationship(my_relationship);
		my_body.reloadRelationships(my_section);
	}
	
	private void updateSaveHint() {
		if(my_relationship != null) {
			if(!personA.equals(my_relationship.getPersonA())
					|| !personB.equals(my_relationship.getPersonB())
					|| !txt_relationshipType.getText().equals(my_relationship.getDescribingRelationshipType())) {				
				lblWARNING.setText("Attention: Unsaved information!");
			}
		}
	}

}
