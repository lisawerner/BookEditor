package GUI.pages.society.raceView;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.ComboItem;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.Book;
import global.ObjectID;
import person.Race;

import javax.swing.*;
import java.awt.*;

public class Card_RaceEditDevelopment extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final Race my_race;
	
	private final JComboBox<ComboItem> cmbox_listOfAscendants;
	private final TransparentPanel panel_showAscendantInformation;
	
	private final JComboBox<ComboItem> cmbox_possibleParentRaces;
	private final TransparentPanel panel_allInfo;
	
	public Card_RaceEditDevelopment(Race givenRace) {
		my_race = givenRace;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_ownAscendants = new TransparentPanel();
		add(panel_ownAscendants);
		panel_ownAscendants.setLayout(new BoxLayout(panel_ownAscendants, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_showAscendant = new TransparentPanel();
		panel_ownAscendants.add(panel_showAscendant);
		panel_showAscendant.setLayout(new GridLayout(0, 1, 0, 0));
		
		SimpleLabel lblAscendantInformation = new SimpleLabel("Ascendant-Information: ");
		panel_showAscendant.add(lblAscendantInformation);
		
		panel_showAscendantInformation = new TransparentPanel();
		panel_showAscendant.add(panel_showAscendantInformation);
		
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
		
		cmbox_listOfAscendants = new JComboBox<>();
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
		
		SimpleLabel lblDescendantInformation = new SimpleLabel("List of Descendant:");
		panel_ownDescendants.add(lblDescendantInformation);
		
		TransparentPanel panel_DescendantList = new TransparentPanel();
		panel_ownDescendants.add(panel_DescendantList);
		panel_DescendantList.setLayout(new GridLayout(1, 0, 0, 0));
		
		TransparentPanel panel_crazyDescendantStructurePanel = new TransparentPanel();
		panel_DescendantList.add(panel_crazyDescendantStructurePanel);
		panel_crazyDescendantStructurePanel.setLayout(new BoxLayout(panel_crazyDescendantStructurePanel, BoxLayout.X_AXIS));
		
		for(ObjectID descendantID : my_race.getDescendants()) {
			Race descendant = Book.getInstance().getSociety().getRace(descendantID);
			
			panel_crazyDescendantStructurePanel.add(new LinkButton(descendant.getName(),
					e -> BookEditorFrame.getInstance().openRacePage(descendant)));
			panel_crazyDescendantStructurePanel.add(new SimpleLabel("; "));
		}
		
		if(my_race.getDescendants().isEmpty()) {
			SimpleLabel label = new SimpleLabel("     '" + my_race.getName() + "' has no descendants!");
			panel_crazyDescendantStructurePanel.add(label);
		}
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1);

		JSeparator separator_1 = new JSeparator();
		add(separator_1);
		
		TransparentPanel panel_subCategories = new TransparentPanel();
		add(panel_subCategories);
		panel_subCategories.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblSubCategoryInformation = new SimpleLabel("Subtype Information:");
		panel_subCategories.add(lblSubCategoryInformation);
		
		panel_allInfo = new TransparentPanel();
		panel_subCategories.add(panel_allInfo);
		

		
		TransparentPanel panel_setParentType = new TransparentPanel();
		panel_subCategories.add(panel_setParentType);
		panel_setParentType.setLayout(new BorderLayout(5, 5));
		
		
		SimpleLabel lblSetARace = new SimpleLabel("Set a race as category parent for this race: ");
		panel_setParentType.add(lblSetARace, BorderLayout.NORTH);
		lblSetARace.setEnabled(my_race.getSubtypes().isEmpty());
		
		JButton btnSetParentRace = new JButton("Set");
		panel_setParentType.add(btnSetParentRace, BorderLayout.EAST);
		btnSetParentRace.addActionListener(e -> setParentType());
		btnSetParentRace.setEnabled(my_race.getSubtypes().isEmpty());
		
		cmbox_possibleParentRaces = new JComboBox<>();
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
			
			panel_showAscendantInformation.add(new LinkButton(Book.getInstance().getSociety().getRace(my_race.getFirstAscendant()).getName(),
					e -> BookEditorFrame.getInstance().openRacePage(Book.getInstance().getSociety().getRace(my_race.getFirstAscendant()))));
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
		panel_allInfo.removeAll();
		
		if(!my_race.getSubtypes().isEmpty()) {
			panel_allInfo.add(new SimpleLabel("Has Subtypes: "));
			for(ObjectID childID : my_race.getSubtypes()) {			
				TransparentPanel panel_children = new TransparentPanel();
				panel_allInfo.add(panel_children);
				panel_children.setLayout(new BoxLayout(panel_children, BoxLayout.X_AXIS));
				
				Race child = Book.getInstance().getSociety().getRace(childID);
				panel_children.add(new LinkButton(child.getName(),
						e -> BookEditorFrame.getInstance().openRacePage(child)));
				panel_children.add(new SimpleLabel(";"));
			}
		}
		
		if(my_race.getParentRace() != null) {			
			TransparentPanel panel_parentType = new TransparentPanel();
			panel_allInfo.add(panel_parentType);
			panel_parentType.setLayout(new BoxLayout(panel_parentType, BoxLayout.X_AXIS));
			
			SimpleLabel lblIsSubtypeOf = new SimpleLabel("Is Subtype of: ");
			panel_parentType.add(lblIsSubtypeOf);
			
			Race parent = Book.getInstance().getSociety().getRace(my_race.getParentRace());
			panel_parentType.add(new LinkButton(parent.getName(),
					e -> BookEditorFrame.getInstance().openRacePage(parent)));
		}
		
		panel_allInfo.revalidate();
		panel_allInfo.repaint();
	}

	private void setAscendant() {
		my_race.setSingleAscending(((ComboItem) cmbox_listOfAscendants.getSelectedItem()).getValue());
		
		showAscendantInformation();
	}



}
