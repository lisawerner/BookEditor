package GUI.personPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import book.Book;

public class ViewSocietyPage extends Page {
	private static final long serialVersionUID = 1L;

	public ViewSocietyPage() {
		super("Society: Persons, Relationships, ...");
	
		this.addCard(new StructureCard("Familiar Society", new SocietyFamiliarCard()));
		
		if(Book.getInstance().getSociety().isRaceSystemActivated()) {
			this.addCard(new StructureCard("Race Society", new SocietyRaceCard()));
		}
		
		setMenu(new PersonMenu());
	}

}
