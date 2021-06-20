package GUI.pages.society.personEditorPage;

import book.content.Book;
import global.ObjectID;
import GUI.components.ComboItem;
import GUI.components.InfoButton;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import book.person.Person;
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

	private final Person my_person;
	
	private final SimpleLabel lblSaveHint;
	
	private final JComboBox<ComboItem> comboBox_selectPerson;
	
	private final SimpleRadiobutton rdbtnIsChild;
	private final SimpleRadiobutton rdbtnIsParent;
	private final SimpleRadiobutton rdbtnIsSpouse;
	private final SimpleRadiobutton rdbtnIsDescendant;
	private final SimpleRadiobutton rdbtnIsAncestor;
	
	private final TransparentPanel panel_showExistingFamiliarRelationships;
	
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
		
		TransparentPanel panel_selectRelationType = new TransparentPanel();
		panel_selectionOptions.add(panel_selectRelationType);
		panel_selectRelationType.setLayout(new GridLayout(0, 3, 0, 0));
		
		rdbtnIsChild = new SimpleRadiobutton("is Child of");
		btn_type.add(rdbtnIsChild);
		panel_selectRelationType.add(rdbtnIsChild);
		
		rdbtnIsParent = new SimpleRadiobutton("is Parent of");
		btn_type.add(rdbtnIsParent);
		panel_selectRelationType.add(rdbtnIsParent);
		
		rdbtnIsSpouse = new SimpleRadiobutton("is Spouse of");
		btn_type.add(rdbtnIsSpouse);
		panel_selectRelationType.add(rdbtnIsSpouse);
		
		rdbtnIsDescendant = new SimpleRadiobutton("is distant descendant of");
		btn_type.add(rdbtnIsDescendant);
		panel_selectRelationType.add(rdbtnIsDescendant);
		
		rdbtnIsAncestor = new SimpleRadiobutton("is distant ancestor");
		btn_type.add(rdbtnIsAncestor);
		panel_selectRelationType.add(rdbtnIsAncestor);
		
		comboBox_selectPerson = new JComboBox<>();
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
	
	private void fill(ArrayList<ObjectID> personIdList, String relationDescription, int type) {
		for(ObjectID otherPersonID : personIdList) {
			addFamiliar(relationDescription, otherPersonID, type);
		}
	}
	
	private void addFamiliar(String relationDescription, ObjectID otherPersonID, int type) {
		TransparentPanel panel_singleRelation = new TransparentPanel();
		panel_singleRelation.setLayout(new BoxLayout(panel_singleRelation, BoxLayout.LINE_AXIS));
		panel_showExistingFamiliarRelationships.add(panel_singleRelation);
		
		panel_singleRelation.add(new SimpleLabel(relationDescription));
		Person parent = Book.getInstance().getSociety().getPerson(otherPersonID);
		LinkButton btnPersonLinkButton = new LinkButton(parent.getInformation().getName(),
				e -> BookEditorFrame.getInstance().openPersonPage(parent, false));
		panel_singleRelation.add(btnPersonLinkButton);
		
		panel_singleRelation.add(new SimpleLabel("<html>; &emsp;</html>"));	
		
		if(type != -1) {
			JButton btnDelete = new JButton("Remove");
			panel_singleRelation.add(btnDelete);
			btnDelete.addActionListener(e -> deleteFamiliarRelationship(otherPersonID, type, btnPersonLinkButton, btnDelete));
		}
		
		panel_showExistingFamiliarRelationships.revalidate();
		panel_showExistingFamiliarRelationships.repaint();
	}

	private void deleteFamiliarRelationship(ObjectID otherPerson, int type, LinkButton disableButton, JButton disableAlsoThisButton) {
		if(type == 1) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipParent(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipChild(my_person.getID());
		} else if(type == 2) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipChild(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipParent(my_person.getID());
		} else if(type == 3) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipSpouse(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipSpouse(my_person.getID());
		} else if(type == 4) {
			my_person.getFamiliarRelation().removeFamiliarRelationshipDistantDescendant(otherPerson);
			Book.getInstance().getSociety().getPerson(otherPerson).getFamiliarRelation().removeFamiliarRelationshipDistantAncestor(my_person.getID());
		} else if(type == 5) {
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
			lblSaveHint.setText("Can not save! Select a book.person for adding a familiar relationship!");
		}
		
		ObjectID selectedPersonID = ((ComboItem) comboBox_selectPerson.getSelectedItem()).getValue();
		if(selectedPersonID == null) {
			canSave = false;
			lblSaveHint.setText("Can not save! Select a book.person for adding a familiar relationship!");
		}
		
		if(!rdbtnIsChild.isSelected() && !rdbtnIsParent.isSelected() && !rdbtnIsSpouse.isSelected() && !rdbtnIsDescendant.isSelected() && !rdbtnIsAncestor.isSelected()) {
			canSave = false;
			lblSaveHint.setText("Can not save! Select a familiar relation type!");
		}
		
		if(canSave) {
			//Add own familiar relationship to other book.person
			my_person.getFamiliarRelation().addRelationship(rdbtnIsChild.isSelected(), rdbtnIsParent.isSelected(),
					rdbtnIsSpouse.isSelected(),
					rdbtnIsDescendant.isSelected(), rdbtnIsAncestor.isSelected(),
					selectedPersonID);
			//Add reverse familiar relationship for other book.person
			Book.getInstance().getSociety().getPerson(selectedPersonID).getFamiliarRelation().addRelationship(rdbtnIsParent.isSelected(), rdbtnIsChild.isSelected(),
						rdbtnIsSpouse.isSelected(),
						rdbtnIsAncestor.isSelected(), rdbtnIsDescendant.isSelected(),
						my_person.getID());
			Book.getInstance().save();
			lblSaveHint.setText("Successfully saved!");
			if(rdbtnIsChild.isSelected()) {
				addFamiliar("Is child of ", selectedPersonID, 1);
			} else if(rdbtnIsParent.isSelected()) {
				addFamiliar("Is parent of ", selectedPersonID, 2);
			} else if(rdbtnIsSpouse.isSelected()) {
				addFamiliar("Is spouse with ", selectedPersonID, 3);
			} else if(rdbtnIsDescendant.isSelected()) {
				addFamiliar("Is distant descendant with ", selectedPersonID, 4);
			} else if(rdbtnIsAncestor.isSelected()) {
				addFamiliar("Is distant ancestor with ", selectedPersonID, 5);
			}
		}
	}

}
