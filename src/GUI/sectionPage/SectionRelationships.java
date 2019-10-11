package GUI.sectionPage;

import GUI_components.SimpleRadiobutton;
import GUI_components.SimpleTextfield;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import person.Person;
import person.Relationship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class SectionRelationships extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	private SimpleTextfield txt_relationshipType;
	
	private Section my_section;
	private SectionTagEditor my_body;
	private Relationship my_relationship;
	
	private JLabel lblWARNING;
	
	private Person personA;
	private Person personB;

	public SectionRelationships(SectionTagEditor body, Section section, Relationship relship) {
		my_section = section;
		my_body = body;
		my_relationship = relship;
		setLayout(new BorderLayout(5, 5));
	
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblWARNING.setText(" ");
				boolean canSave = true;
				
				System.out.println("A: " + personA.getName() + " - B: " + personB.getName());
				
				if(personA == null || personB == null) {
					lblWARNING.setText("You have to choose two persons for setting a relationship.");
					canSave = false;
				} else {
					if(personA.getID().getIDtoString().equals(personB.getID().getIDtoString())) {
						lblWARNING.setText("You have to choose two different persons for setting a relationship.");
						canSave = false;
					}
				}
				
				if(txt_relationshipType.getText().equals("")) {
					lblWARNING.setText("You have to enter a describing relationtype (for example: Father, Friends, Collegues.");
					canSave = false;
				}
				
				if(canSave) {
					if(my_relationship == null) {
						Relationship newRelationship = new Relationship(personA, personB, txt_relationshipType.getText());
						my_body.addRelationship(newRelationship);
						my_section.addRelationship(newRelationship);
						//TODO: Reload this completele!!!!
					} else {
						my_relationship.change(Book.getInstance(), personA, personB, txt_relationshipType.getText());
					}
				}
			}
		});
		if(my_relationship != null) {
			btnAdd.setText("Change");
			//TODO: DeleteButtoN!!!
		}
		add(btnAdd, BorderLayout.EAST);
		
		TransparentPanel panel_type = new TransparentPanel();
		add(panel_type, BorderLayout.WEST);
		panel_type.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblType = new JLabel("Relationship-Type: ");
		panel_type.add(lblType);
		txt_relationshipType = new SimpleTextfield();
		if(my_relationship != null) {txt_relationshipType.setText(my_relationship.getDescribingRelationshipType());}
		panel_type.add(txt_relationshipType);

		lblWARNING = new JLabel(" ");
		add(lblWARNING, BorderLayout.SOUTH);
		lblWARNING.setForeground(Color.RED);
		
		TransparentPanel panel_Persons = new TransparentPanel();
		add(panel_Persons, BorderLayout.CENTER);
		panel_Persons.setLayout(new BoxLayout(panel_Persons, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_personA = new TransparentPanel();
		panel_Persons.add(panel_personA);
		JLabel lblPersonA = new JLabel("Choose Person A: ");
		panel_personA.add(lblPersonA);
		ButtonGroup group_personA = new ButtonGroup();
		for(Person person : Book.getInstance().getPersonList()) {
			SimpleRadiobutton chckbxPersonA = new SimpleRadiobutton(person.getName());
			panel_personA.add(chckbxPersonA);		
			group_personA.add(chckbxPersonA);
			if(my_relationship != null) {
				if(my_relationship.getPersonA().getIDtoString().equals(person.getID().getIDtoString())) {					
					chckbxPersonA.setSelected(true);
					personA = person;
				}
			}
			chckbxPersonA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxPersonA.isSelected()) {			
						//System.out.println("Click: Is Selected now");
						personA = person;
					} else {
						//System.out.println("Click: Is NOOOTTT Selected anymore");
					}
				}
			});
		}
		
		
		TransparentPanel panel_personB = new TransparentPanel();
		panel_Persons.add(panel_personB);
		JLabel lblPersonB = new JLabel("Choose Person B: ");
		panel_personB.add(lblPersonB);
		ButtonGroup group_personB = new ButtonGroup();
		for(Person person : Book.getInstance().getPersonList()) {
			SimpleRadiobutton chckbxPersonB = new SimpleRadiobutton(person.getName());
			panel_personB.add(chckbxPersonB);
			group_personB.add(chckbxPersonB);
			if(my_relationship != null) {
				if(my_relationship.getPersonB().getIDtoString().equals(person.getID().getIDtoString())) {					
					chckbxPersonB.setSelected(true);
					personB = person;
				}
			}
			chckbxPersonB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxPersonB.isSelected()) {			
						//System.out.println("Click: Is Selected now");
						personB = person;
					} else {
						//System.out.println("Click: Is NOOOTTT Selected anymore");
					}
				}
			});
		}
		
	}

}
