package GUI.personPage;

import GUI_components.Page;
import GUI_components.StructureCard;

public class ViewSociety extends Page {
	private static final long serialVersionUID = 1L;

	public ViewSociety() {
		super("Persons, Relationships, ...");
		
		this.addCard(new StructureCard("General Person Information", new SocietyFilterCard()));

		setMenu(new PersonMenu());
	}

}
