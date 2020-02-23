package GUI.race;

import GUI_components.ComboItem;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import global.ObjectID;
import person.Race;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;

import GUI.bookeditorFrame.BookEditorFrame;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class Card_RaceDevelopment extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Race my_race;
	
	private JComboBox<ComboItem> cmbox_listOfAscendants;
	private TransparentPanel panel_showAscendantInformation;
	
	private JComboBox<ComboItem> cmbox_possibleParentRaces;
	private TransparentPanel panel_allInfos;
	
	public Card_RaceDevelopment(Race givenRace) {
		my_race = givenRace;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_ownAscendants = new TransparentPanel();
		add(panel_ownAscendants);
		panel_ownAscendants.setLayout(new BoxLayout(panel_ownAscendants, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_showAcsendant = new TransparentPanel();
		panel_ownAscendants.add(panel_showAcsendant);
		panel_showAcsendant.setLayout(new GridLayout(0, 1, 0, 0));
		
		SimpleLabel lblAscendantinformation = new SimpleLabel("Ascendant-Information: ");
		panel_showAcsendant.add(lblAscendantinformation);
		
		panel_showAscendantInformation = new TransparentPanel();
		panel_showAcsendant.add(panel_showAscendantInformation);
		
		TransparentPanel panel_addAscendants = new TransparentPanel();
		panel_ownAscendants.add(panel_addAscendants);
		panel_addAscendants.setLayout(new GridLayout(1, 0, 0, 0));
		
		TransparentPanel panel_addSingleAscendant = new TransparentPanel();
		panel_addAscendants.add(panel_addSingleAscendant);
		panel_addSingleAscendant.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblAddAscendant = new SimpleLabel("Add/Change ascendant-race to:");
		panel_addSingleAscendant.add(lblAddAscendant);
		
		TransparentPanel panel_selectNewAscendant = new TransparentPanel();
		panel_addSingleAscendant.add(panel_selectNewAscendant);
		panel_selectNewAscendant.setLayout(new BorderLayout(5, 5));
		
		cmbox_listOfAscendants = new JComboBox<ComboItem>();
		cmbox_listOfAscendants.setEnabled(my_race.getParentRace() == null);
		panel_selectNewAscendant.add(cmbox_listOfAscendants, BorderLayout.CENTER);
		for(Race race : my_race.getPossibleAscendants()) {
			cmbox_listOfAscendants.addItem(new ComboItem(race.getName(), race.getID()));
		}
		
		JButton btnAddSingleAscendant = new JButton("Set");
		btnAddSingleAscendant.addActionListener(e -> setAscendant());
		btnAddSingleAscendant.setEnabled(my_race.getParentRace() == null);
		panel_selectNewAscendant.add(btnAddSingleAscendant, BorderLayout.EAST);
		
		TransparentPanel panel_addHybridParents = new TransparentPanel();
		panel_addAscendants.add(panel_addHybridParents);
		
		SimpleLabel lblAddTwoDifferent = new SimpleLabel("Add two different Ascendants (for hybrid race): ");
		panel_addHybridParents.add(lblAddTwoDifferent);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea);
		
		JSeparator separator = new JSeparator();
		add(separator);
		
		TransparentPanel panel_ownDescendants = new TransparentPanel();
		add(panel_ownDescendants);
		panel_ownDescendants.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblDescendantinformation = new SimpleLabel("List of Descendant:");
		panel_ownDescendants.add(lblDescendantinformation);
		
		TransparentPanel panel_DescendantList = new TransparentPanel();
		panel_ownDescendants.add(panel_DescendantList);
		panel_DescendantList.setLayout(new GridLayout(1, 0, 0, 0));
		
		TransparentPanel panel_crazyDescendantStrucutrePanel = new TransparentPanel();
		panel_DescendantList.add(panel_crazyDescendantStrucutrePanel);
		panel_crazyDescendantStrucutrePanel.setLayout(new BoxLayout(panel_crazyDescendantStrucutrePanel, BoxLayout.X_AXIS));
		
		for(ObjectID descendantID : my_race.getDescendants()) {
			Race descendant = Book.getInstance().getSociety().getRace(descendantID);
			
			LinkButton btnLinkbutton = new LinkButton(descendant.getName());
			panel_crazyDescendantStrucutrePanel.add(btnLinkbutton);
			btnLinkbutton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(descendant)));
			
			SimpleLabel label = new SimpleLabel("; ");
			panel_crazyDescendantStrucutrePanel.add(label);
		}
		
		if(my_race.getDescendants().isEmpty()) {
			SimpleLabel label = new SimpleLabel("     '" + my_race.getName() + "' has no descendants!");
			panel_crazyDescendantStrucutrePanel.add(label);
		}
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1);

		JSeparator separator_1 = new JSeparator();
		add(separator_1);
		
		TransparentPanel panel_subkategories = new TransparentPanel();
		add(panel_subkategories);
		panel_subkategories.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblSubkategoryinformation = new SimpleLabel("Subtype Information:");
		panel_subkategories.add(lblSubkategoryinformation);
		
		panel_allInfos = new TransparentPanel();
		panel_subkategories.add(panel_allInfos);
		

		
		TransparentPanel panel_setParentType = new TransparentPanel();
		panel_subkategories.add(panel_setParentType);
		panel_setParentType.setLayout(new BorderLayout(5, 5));
		
		
		SimpleLabel lblSetARace = new SimpleLabel("Set a race as kategory parent for this race: ");
		panel_setParentType.add(lblSetARace, BorderLayout.NORTH);
		lblSetARace.setEnabled(my_race.getSubtypes().isEmpty());
		
		JButton btnSetParentRace = new JButton("Set");
		panel_setParentType.add(btnSetParentRace, BorderLayout.EAST);
		btnSetParentRace.addActionListener(e -> setParentType());
		btnSetParentRace.setEnabled(my_race.getSubtypes().isEmpty());
		
		cmbox_possibleParentRaces = new JComboBox<ComboItem>();
		panel_setParentType.add(cmbox_possibleParentRaces, BorderLayout.CENTER);
		for(Race currentRace : my_race.getPossibleParents()) {
			cmbox_possibleParentRaces.addItem(new ComboItem(currentRace.getName(), currentRace.getID()));
		}
		cmbox_possibleParentRaces.setEnabled(my_race.getSubtypes().isEmpty());
		
		showAscendantInformation();
		showParentType();
	}

	private void setParentType() {
		my_race.setParentType(((ComboItem) cmbox_possibleParentRaces.getSelectedItem()).getValue());
		
		showAscendantInformation();
		showParentType();
	}

	private void showAscendantInformation() {
		panel_showAscendantInformation.removeAll();
		
		panel_showAscendantInformation.setLayout(new BoxLayout(panel_showAscendantInformation, BoxLayout.LINE_AXIS));
		
		SimpleLabel lbl_noAscendants = new SimpleLabel(" ");
		panel_showAscendantInformation.add(lbl_noAscendants);
		if(my_race.hasAscendants()) {
			lbl_noAscendants.setText("     '" + my_race.getName() + "' is descendant of: ");
			
			LinkButton btn_ascendant = new LinkButton(Book.getInstance().getSociety().getRace(my_race.getFirstAscendant()).getName());
			panel_showAscendantInformation.add(btn_ascendant);
			btn_ascendant.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(Book.getInstance().getSociety().getRace(my_race.getFirstAscendant()))));
		} else {
			lbl_noAscendants = new SimpleLabel("     '" + my_race.getName() + "' is first of its kind!");
		}
		if(my_race.getParentRace() != null){
			lbl_noAscendants.setWarning(true);
			lbl_noAscendants.setText("Race can not have an ascendant, if it is a subtype of another race");
		}
		
		panel_showAscendantInformation.revalidate();
		panel_showAscendantInformation.repaint();
	}
	
	private void showParentType(){
		panel_allInfos.removeAll();
		
		if(!my_race.getSubtypes().isEmpty()) {
			panel_allInfos.add(new SimpleLabel("Has Subtypes: "));
			for(ObjectID childID : my_race.getSubtypes()) {			
				TransparentPanel panel_children = new TransparentPanel();
				panel_allInfos.add(panel_children);
				panel_children.setLayout(new BoxLayout(panel_children, BoxLayout.X_AXIS));
				
				Race child = Book.getInstance().getSociety().getRace(childID);
				LinkButton btnChild = new LinkButton(child.getName());
				panel_children.add(btnChild);
				btnChild.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(child)));
				
				SimpleLabel lblNewLabel = new SimpleLabel(";");
				panel_children.add(lblNewLabel);
			}
		}
		
		if(my_race.getParentRace() != null) {			
			TransparentPanel panel_parentType = new TransparentPanel();
			panel_allInfos.add(panel_parentType);
			panel_parentType.setLayout(new BoxLayout(panel_parentType, BoxLayout.X_AXIS));
			
			SimpleLabel lblIsSubtypeOf = new SimpleLabel("Is Subtype of: ");
			panel_parentType.add(lblIsSubtypeOf);
			
			Race parent = Book.getInstance().getSociety().getRace(my_race.getParentRace());
			LinkButton btnParent = new LinkButton(parent.getName());
			panel_parentType.add(btnParent);
			btnParent.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(parent)));
		}
		
		panel_allInfos.revalidate();
		panel_allInfos.repaint();
	}

	private void setAscendant() {
		my_race.setSingleAscending(((ComboItem) cmbox_listOfAscendants.getSelectedItem()).getValue());
		
		showAscendantInformation();
	}



}
