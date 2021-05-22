package GUI.pages.society.raceView;

import GUI.components.Page;
import GUI.components.StructureCard;
import person.Race;

import javax.swing.*;

public class Page_EditRace extends Page {
	private static final long serialVersionUID = 1L;

	public Page_EditRace(Race givenRace) {
		super("Society: Persons, Relationships, Races...");

		//****************************************************************************************
		this.addCard(new StructureCard("Edit Race", new Card_raceDefaultInformation(givenRace)));
		
		//****************************************************************************************
		this.addCard(new StructureCard("Race Representatives", new Card_ViewRaceRepresentatives(givenRace)));
		this.addCard(new StructureCard("Edit Race Development", new Card_RaceEditDevelopment(givenRace)));

		//****************************************************************************************
		//****************************************************************************************
		//TODO: Submit Panel
		JButton btnDelete = new JButton("Delete Race");
		btnDelete.addActionListener(e -> {
			//TODO:
		});
		btnDelete.setEnabled(false); //TODO!!
		setFooter(btnDelete);

	}

}
