package GUI.personPage;

import book.Book;
import GUI.components.Page;
import GUI.components.StructureCard;

public class FilterPersonPage extends Page {
	private static final long serialVersionUID = 1L;

	public FilterPersonPage() {
		super("Society: Persons, Relationships, ...");
		
		//*********************************************************************************
		//*********************************************************************************
		this.addCard(new StructureCard("Filter Persons:", new PersonFilterCard()));
		this.addCard(new StructureCard("Filter Persons by Relationship:", new FilterRelationshipsCard()));
		if(Book.getInstance().getSociety().isRaceSystemActivated()) {this.addCard(new StructureCard("Filter Persons by Race:", new FilterRaceCard()));}

	}

}
