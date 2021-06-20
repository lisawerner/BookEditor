package GUI.pages.society.raceSystemPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.content.Book;
import global.ObjectID;
import book.person.Race;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Card_RaceDevelopmentTree extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_RaceDevelopmentTree() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JButton btnAddRace = new JButton("Add new Race");
		this.add(btnAddRace);
		btnAddRace.addActionListener(e -> BookEditorFrame.getInstance().openCreateRacePage());
		
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
		ArrayList<Race> rootRaces = new ArrayList<>();
		for(Race race : Book.getInstance().getSociety().getRaceSystem().getRaces()){
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
		
		panel_RaceWithSubtypes.add(new LinkButton(currentRace.getName(),
				e -> BookEditorFrame.getInstance().openRacePage(currentRace)));
		
		if(!currentRace.getSubtypes().isEmpty()){			
			SimpleLabel lbl_leftBrace = new SimpleLabel("   [ ");
			panel_RaceWithSubtypes.add(lbl_leftBrace);
			
			for(ObjectID subtypeID : currentRace.getSubtypes()){
				Race subtype = Book.getInstance().getSociety().getRaceSystem().getRace(subtypeID);
				panel_RaceWithSubtypes.add(new LinkButton(subtype.getName(),
						e -> BookEditorFrame.getInstance().openRacePage(subtype)));
				panel_RaceWithSubtypes.add(new SimpleLabel("; "));
			}
			
			SimpleLabel lbl_rightBrace = new SimpleLabel("]");
			panel_RaceWithSubtypes.add(lbl_rightBrace);
		}
		
		for(ObjectID childID : currentRace.getDescendants()){
			Race child = Book.getInstance().getSociety().getRaceSystem().getRace(childID);
			currentSpace += "      ";
			addRace(panel_raceTree, child, currentSpace);
		}
	}
}
