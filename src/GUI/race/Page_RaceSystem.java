package GUI.race;

import GUI.personPage.PersonMenu;
import GUI_components.Page;
import GUI_components.StructureCard;

public class Page_RaceSystem extends Page {
	private static final long serialVersionUID = 1L;

	public Page_RaceSystem() {
		super("Society: Persons, Relationships, Races...");
		
		this.addCard(new StructureCard("Race development tree", new Card_RaceDevelopmentTree()));
		
		this.addCard(new StructureCard("Race representatives", new Card_RaceSociety()));
		
		setMenu(new PersonMenu());
	}

}
