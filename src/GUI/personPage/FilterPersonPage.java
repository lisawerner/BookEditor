package GUI.personPage;

import GUI_components.Page;
import GUI_components.StructureCard;

public class FilterPersonPage extends Page {
	private static final long serialVersionUID = 1L;

	public FilterPersonPage() {
		super("Persons, Relationships, ...");
		
		//*********************************************************************************
		//*********************************************************************************
		this.addCard(new StructureCard("Filter Persons:", new PersonFilterCard()));
		this.addCard(new StructureCard("Filter Persons by Relationship:", new FilterRelationshipsCard()));
		//*********************************************************************************
		//*********************************************************************************
		setMenu(new PersonMenu());
	}

}
