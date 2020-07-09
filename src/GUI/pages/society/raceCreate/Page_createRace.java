package GUI.pages.society.raceCreate;

import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_createRace extends Page {
	private static final long serialVersionUID = 1L;
	
	public Page_createRace() {
		super("Society: Persons, Relationships, Races...");
		
		this.addCard(new StructureCard("Create Race", new Card_createRace()));
	}

}
