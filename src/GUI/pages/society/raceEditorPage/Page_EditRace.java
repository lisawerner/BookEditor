package GUI.pages.society.raceEditorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.components.Page;
import GUI.components.StructureCard;
import person.Race;

public class Page_EditRace extends Page {
	private static final long serialVersionUID = 1L;
	
	private Race my_race;

	public Page_EditRace(Race givenRace) {
		super("Society: Persons, Relationships, Races...");
		
		my_race = givenRace;
		
		//****************************************************************************************
		this.addCard(new StructureCard("Edit Race", new Card_CreateRace(my_race)));
		
		//****************************************************************************************
		if(my_race != null) {
			this.addCard(new StructureCard("Race Representatives", new Card_ViewRaceRepresentatives(my_race)));
			this.addCard(new StructureCard("Edit Race Development", new Card_RaceEditDevelopment(my_race)));
		}
		
		//****************************************************************************************
		//****************************************************************************************
		//TODO: Bestätungs Panel
		JButton btnDelete = new JButton("Delete Race");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO:
			}
		});
		btnDelete.setEnabled(false); //TODO!!
		setFooter(btnDelete);

	}

}
