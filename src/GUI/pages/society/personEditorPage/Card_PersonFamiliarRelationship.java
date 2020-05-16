package GUI.pages.society.personEditorPage;

import book.Book;
import global.ObjectID;
import GUI.components.ComboItem;
import GUI.components.InfoButton;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import person.Person;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.util.ArrayList;
import javax.swing.JComboBox;
import GUI.bookeditorFrame.BookEditorFrame;

public class Card_PersonFamiliarRelationship extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person;
	
	private SimpleLabel lblSaveHint;
	
	private JComboBox<ComboItem> comboBox_selectPerson;
	
	private SimpleRadiobutton rdbtnIschild;
	private SimpleRadiobutton rdbtnIsparent;
	private SimpleRadiobutton rdbtnIsspouse;
	private SimpleRadiobutton rdbtnIsdescendant;
	private SimpleRadiobutton rdbtnIsancestor;
	
	private TransparentPanel panel_showExistingFamiliarRelationships;
	
	public Card_PersonFamiliarRelationship(Person givenPerson) {
		my_person = givenPerson;
		setLayout(new BorderLayout(10, 10));
		
		ButtonGroup btn_type = new ButtonGroup();
		
		TransparentPanel panel_addFamiliarRelationship = new TransparentPanel();
		add(panel_addFamiliarRelationship, BorderLayout.NORTH);
		panel_addFamiliarRelationship.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblAddFamiliarRelationship = new SimpleLabel("Add familiar Relationship:");
		panel_addFamiliarRelationship.add(lblAddFamiliarRelationship, BorderLayout.NORTH);
		
		TransparentPanel panel_selectForAdding = new TransparentPanel();
		panel_addFamiliarRelationship.add(panel_selectForAdding, BorderLayout.CENTER);
		panel_selectForAdding.setLayout(new BorderLayout(0, 0));
		
		TransparentPanel panel_selectionOptions = new TransparentPanel();
		panel_selectForAdding.add(panel_selectionOptions, BorderLayout.CENTER);
		panel_selectionOptions.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel panel_selectRelationtype = new TransparentPanel();
		panel_selectionOptions.add(panel_selectRelationtype);
		panel_selectRelationtype.setLayout(new GridLayout(0, 3, 0, 0));
		
		rdbtnIschild = new SimpleRadiobutton("is Child of");
		btn_type.add(rdbtnIschild);
		panel_selectRelationtype.add(rdbtnIschild);
		
		rdbtnIsparent = new SimpleRadiobutton("is Parent of");
		btn_type.add(rdbtnIsparent);
		panel_selectRelationtype.add(rdbtnIsparent);
		
		rdbtnIsspouse = new SimpleRadiobutton("is Spouse of");
		btn_type.add(rdbtnIsspouse);
		panel_selectRelationtype.add(rdbtnIsspouse);
		
		rdbtnIsdescendant = new SimpleRadiobutton("is distant descendant of");
		btn_type.add(rdbtnIsdescendant);
		panel_selectRelationtype.add(rdbtnIsdescendant);
		
		rdbtnIsancestor = new SimpleRadiobutton("is distant ancestor");
		btn_type.add(rdbtnIsancestor);
		panel_selectRelationtype.add(rdbtnIsancestor);
		
		comboBox_selectPerson = new JComboBox<ComboItem>();
		panel_selectionOptions.add(comboBox_selectPerson);
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			if(!person.equals(my_person)) {				
				comboBox_selectPerson.addItem(new ComboItem(person.getInformation().getName(), person.getID()));
			}
		}
		
		TransparentPanel panel_selectionHints = new TransparentPanel();
		panel_selectForAdding.add(panel_selectionHints, BorderLayout.WEST);
		panel_selectionHints.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblChooseFamiliarRelation = new SimpleLabel("Choose familiar Relation");
		panel_selectionHints.add(lblChooseFamiliarRelation);
		
		SimpleLabel lblChoosePerson = new SimpleLabel("Choose Person");
		panel_selectionHints.add(lblChoosePerson);
		
		JButton btnAdd = new JButton("Add");
		panel_selectForAdding.add(btnAdd, BorderLayout.EAST);
		btnAdd.addActionListener(e -> save());
		
		lblSaveHint = new SimpleLabel(" ");
		lblSaveHint.setWarning(true);
		panel_selectForAdding.add(lblSaveHint, BorderLayout.SOUTH);
		
		InfoButton btnI = new InfoButton("Familiar Relationships can not change during story, because a brother is always a brother");
		panel_addFamiliarRelationship.add(btnI, BorderLayout.EAST);
		
		panel_showExistingFamiliarRelationships = new TransparentPanel();
		add(panel_showExistingFamiliarRelationships, BorderLayout.CENTER);
		panel_showExistingFamiliarRelationships.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblPersonHasFollowing = new SimpleLabel("Person has following familiar Relationships:");
		panel_showExistingFamiliarRelationships.add(lblPersonHasFollowing);
		
		fill(my_person.getFamiliarRelation().getParents(), "Is child of ", 1);
		fill(my_person.getFamiliarRelation().getChildren(), "Is parent of ", 2);
		fill(my_person.getFamiliarRelation().getSpouse(), "Is spouse with ", 3);
		fill(my_person.getFamiliarRelation().getDistantDescendant(), "Is distant descendant of ", 4);
		fill(my_person.getFamiliarRelation().getDistantAncestor(), "Is distant ancestor of ", 5);
		fill(my_person.getFamiliarRelation().getSiblings(my_person.getID()), "Is sibling with ", -1);
	}
	
	private void fill(ArrayList<ObjectID> personIDlist, String relationDescription, int typeBlub) {
		for(ObjectID otherPersonID : personIDlist) {			
			addFamiliar(relationDescription, otherPersonID, typeBlub);
		}
	}
	
	private void addFamiliar(String relationDescription, ObjectID otherPersonID, int typeBlub) {
		TransparentPanel panel_singleRelation = new TransparentPanel();
		panel_singleRelation.setLayout(new BoxLayout(panel_singleRelation, BoxLayout.LINE_AXIS));
		panel_showExistingFamiliarRelationships.add(panel_singleRelation);
		
		panel_singleRelation.add(new SimpleLabel(relationDescription));
		Person parent = Book.getInstance().getSociety().getPerson(otherPersonID);
		LinkButton btnPersonlinkbutton = new LinkButton(parent.getInformation().getName());
		btnPersonlinkbutton.addActionListener(e -> BookEditorFrame.getInstance().openPersonPage(parent, false));
		panel_singleRelation.add(btnPersonlinkbutton);
		
		panel_singleRelation.add(new SimpleLabel("<html>; &emsp;</html>"));	
		
		if(typeBlub != -1) {			
			JButton btnDelete = new JButton("Remove");
			panel_singleRelation.add(btnDelete);
			btnDelete.addActionListener(e -> deleteFamiliarRelationship(otherPersonID, typeBlub, btnPersonlinkbutton, btnDelete));
		}
		
		panel_showExistingFamiliarRelationships.revalidate();
		panel_showExistingFamiliarRelationships.repaint();
	}

	private void deleteFamiliarRelationship(ObjectID otherPerson, int typeBlub, LinkButton disableButton, JButton disableAlsoThisButton) {
		if(typeBlub == 1) {			
			my_person.getFamiliarRelation().removeFamiliarRelationshipParent(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipChild(my_person.getID());
		} else if(typeBlub == 2) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipChild(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipParent(my_person.getID());
		} else if(typeBlub == 3) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipSpouse(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipSpouse(my_person.getID());
		} else if(typeBlub == 4) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipDistantDescendant(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipDistantAncestor(my_person.getID());
		} else if(typeBlub == 5) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipDistantAncestor(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipDistantDescendant(my_person.getID());
		}
		disableButton.setEnabled(false);
		disableAlsoThisButton.setEnabled(false);
		Book.getInstance().save();
	}

	private void save() {
		boolean canSave = true;
		lblSaveHint.setText(" ");
		
		if(comboBox_selectPerson.getSelectedIndex() == -1) {
			canSave = false;
			lblSaveHint.setText("Can not save! Select a person for adding a familiar relationship!");
		}
		
		ObjectID selectedPersonID = ((ComboItem) comboBox_selectPerson.getSelectedItem()).getValue();
		if(selectedPersonID == null) {
			canSave = false;
			lblSaveHint.setText("Can not save! Select a person for adding a familiar relationship!");
		}
		
		if(!rdbtnIschild.isSelected() && !rdbtnIsparent.isSelected() && !rdbtnIsspouse.isSelected() && !rdbtnIsdescendant.isSelected() && !rdbtnIsancestor.isSelected()) {
			canSave = false;
			lblSaveHint.setText("Can not save! Select a familiar relation type!");
		}
		
		if(canSave) {
			//Add own familiar relationship to other person
			my_person.getFamiliarRelation().addRelationship(rdbtnIschild.isSelected(), rdbtnIsparent.isSelected(), 
					rdbtnIsspouse.isSelected(), 
					rdbtnIsdescendant.isSelected(), rdbtnIsancestor.isSelected(), 
					selectedPersonID);
			//Add reverse familiar relationship for other person
			Book.getInstance().getSociety().getPerson(selectedPersonID).getFamiliarRelation().addRelationship(rdbtnIsparent.isSelected(), rdbtnIschild.isSelected(), 
						rdbtnIsspouse.isSelected(), 
						rdbtnIsancestor.isSelected(), rdbtnIsdescendant.isSelected(), 
						my_person.getID());
			Book.getInstance().save();
			lblSaveHint.setText("Successfully saved!");
			if(rdbtnIschild.isSelected()) {
				addFamiliar("Is child of ", selectedPersonID, 1);
			} else if(rdbtnIsparent.isSelected()) {
				addFamiliar("Is parent of ", selectedPersonID, 2);
			} else if(rdbtnIsspouse.isSelected()) {
				addFamiliar("Is spouse with ", selectedPersonID, 3);
			} else if(rdbtnIsdescendant.isSelected()) {
				addFamiliar("Is distant descendant with ", selectedPersonID, 4);
			} else if(rdbtnIsancestor.isSelected()) {
				addFamiliar("Is distant ancestor with ", selectedPersonID, 5);
			}
		}
	}

}
