package GUI.race;

import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_RaceSystem extends Page {
	private static final long serialVersionUID = 1L;

	public Page_RaceSystem() {
		super("Society: Persons, Relationships, Races...");
		
		this.addCard(new StructureCard("Race development tree", new Card_RaceDevelopmentTree()));
		
		this.addCard(new StructureCard("Race representantives", new Card_RaceSociety()));
	}

}
