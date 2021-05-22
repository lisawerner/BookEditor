package GUI.pages.society;

import GUI.components.Page;
import GUI.components.StructureCard;
import book.Book;

public class Page_ViewSociety extends Page {
	private static final long serialVersionUID = 1L;

	public Page_ViewSociety() {
		super("Society: Persons, Relationships, ...");
	
		this.addCard(new StructureCard("Familiar Society", new Card_SocietyFamiliar()));
		
		this.addCard(new StructureCard("Persons Importance", new Card_PersonImportance()));
		
		if(Book.getInstance().getSociety().isRaceSystemActivated()) {
			this.addCard(new StructureCard("Race Society", new Card_RaceSociety()));
		}
	}
	
}
