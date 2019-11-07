package GUI.personPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI_components.Page;
import GUI_components.StructureCard;
import person.Race;

public class RaceEditorPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Race my_race;

	public RaceEditorPage(Race givenRace) {
		super("Society: Persons, Relationships, Races...");
		
		my_race = givenRace;
		
		//****************************************************************************************
		this.addCard(new StructureCard("Edit Race", new RaceCreateCard(my_race)));
		
		//****************************************************************************************
		if(my_race != null) {this.addCard(new StructureCard("Race Representatives", new RaceRepresentativesCard(my_race)));}
		
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
		
		//*********************************************************************************
		//*********************************************************************************
		setMenu(new PersonMenu());
	}

}
