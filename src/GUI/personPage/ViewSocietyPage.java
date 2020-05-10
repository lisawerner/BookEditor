package GUI.personPage;

import GUI.race.Card_RaceSociety;
import book.Book;
import GUI.components.Page;
import GUI.components.StructureCard;

public class ViewSocietyPage extends Page {
	private static final long serialVersionUID = 1L;

	public ViewSocietyPage() {
		super("Society: Persons, Relationships, ...");
	
		this.addCard(new StructureCard("Familiar Society", new SocietyFamiliarCard()));
		
		if(Book.getInstance().getSociety().isRaceSystemActivated()) {
			this.addCard(new StructureCard("Race Society", new Card_RaceSociety()));
		}
	}

}
