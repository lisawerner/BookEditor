package GUI.race;

import GUI.personPage.PersonMenu;
import GUI.personPage.SocietyRaceCard;
import GUI_components.Page;
import GUI_components.StructureCard;

public class Page_RaceSystem extends Page {
	private static final long serialVersionUID = 1L;

	public Page_RaceSystem() {
		super("Society: Persons, Relationships, Races...");
		
		this.addCard(new StructureCard("Race System", new Card_RaceOverview()));
		
		this.addCard(new StructureCard("Race Society", new SocietyRaceCard()));
		
		setMenu(new PersonMenu());
	}

}
