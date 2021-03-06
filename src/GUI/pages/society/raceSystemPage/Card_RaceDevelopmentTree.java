package GUI.pages.society.raceSystemPage;

import java.util.ArrayList;

import book.Book;
import global.ObjectID;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.pages.society.raceEditorPage.Page_EditRace;
import person.Race;

import GUI.bookeditorFrame.BookEditorFrame;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.GridLayout;
import javax.swing.JSeparator;

public class Card_RaceDevelopmentTree extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_RaceDevelopmentTree() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JButton btnAddRace = new JButton("Add new Race");
		this.add(btnAddRace);
		btnAddRace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(null)));
		
		for(Race rootRace : getRootRaces()){
			TransparentPanel panel_raceTree = new TransparentPanel();
			add(panel_raceTree);
			panel_raceTree.setLayout(new GridLayout(0, 1, 0, 0));
			
			addRace(panel_raceTree, rootRace, "");
						
			JSeparator separator = new JSeparator();
			add(separator);
		}	
		
	}

	
	private ArrayList<Race> getRootRaces(){
		ArrayList<Race> rootRaces = new ArrayList<Race>();
		for(Race race : Book.getInstance().getSociety().getRaces()){
			if(race.getParentRace() == null && race.getFirstAscendant() == null){
				rootRaces.add(race);
			}
		}
		return rootRaces;
	}
	
	private void addRace(TransparentPanel panel_raceTree, Race currentRace, String currentSpace){
		TransparentPanel panel_RaceWithSubtypes = new TransparentPanel();
		panel_raceTree.add(panel_RaceWithSubtypes);
		panel_RaceWithSubtypes.setLayout(new BoxLayout(panel_RaceWithSubtypes, BoxLayout.X_AXIS));
		
		SimpleLabel lbl_space = new SimpleLabel(currentSpace);
		if(currentRace.getFirstAscendant() != null){
			lbl_space.setText(currentSpace + ">>  ");
		}
		panel_RaceWithSubtypes.add(lbl_space);
		
		LinkButton lblRacename = new LinkButton(currentRace.getName());
		panel_RaceWithSubtypes.add(lblRacename);
		lblRacename.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(currentRace)));
		
		if(!currentRace.getSubtypes().isEmpty()){			
			SimpleLabel lbl_leftBrace = new SimpleLabel("   [ ");
			panel_RaceWithSubtypes.add(lbl_leftBrace);
			
			for(ObjectID subtypeID : currentRace.getSubtypes()){
				Race subtype = Book.getInstance().getSociety().getRace(subtypeID);
				LinkButton btnSubtype = new LinkButton(subtype.getName());
				btnSubtype.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(subtype)));
				panel_RaceWithSubtypes.add(btnSubtype);
				panel_RaceWithSubtypes.add(new SimpleLabel("; "));
			}
			
			SimpleLabel lbl_rightBrace = new SimpleLabel("]");
			panel_RaceWithSubtypes.add(lbl_rightBrace);
		}
		
		for(ObjectID childID : currentRace.getDescendants()){
			Race child = Book.getInstance().getSociety().getRace(childID);
			currentSpace += "      ";
			addRace(panel_raceTree, child, currentSpace);
		}
	}
}
