package GUI.personPage;

import GUI_components.Page;
import GUI_components.StructureCard;

public class ViewSocietyPage extends Page {
	private static final long serialVersionUID = 1L;

	public ViewSocietyPage() {
		super("Society: Persons, Relationships, ...");
	
		this.addCard(new StructureCard("Familiar Society", new SocietyFamiliarCard()));
		
		setMenu(new PersonMenu());
	}

}
