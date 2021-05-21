package GUI.race;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.personPage.PersonMenu;
import GUI_components.Page;
import GUI_components.StructureCard;
import person.Race;

public class Page_EditRace extends Page {
	private static final long serialVersionUID = 1L;

	public Page_EditRace(Race givenRace) {
		super("Society: Persons, Relationships, Races...");

		//****************************************************************************************
		this.addCard(new StructureCard("Edit Race", new Card_CreateRace(givenRace)));
		
		//****************************************************************************************
		if(givenRace != null) {
			this.addCard(new StructureCard("Race Representatives", new Card_ViewRaceRepresentatives(givenRace)));
			this.addCard(new StructureCard("Edit Race Development", new Card_RaceEditDevelopment(givenRace)));
		}
		
		//****************************************************************************************
		//****************************************************************************************
		//TODO: Bestätungs Panel
		JButton btnDelete = new JButton("Delete Race");
		btnDelete.addActionListener(e -> {
			//TODO:
		});
		btnDelete.setEnabled(false); //TODO!!
		setFooter(btnDelete);
		
		//*********************************************************************************
		//*********************************************************************************
		setMenu(new PersonMenu());
	}

}
