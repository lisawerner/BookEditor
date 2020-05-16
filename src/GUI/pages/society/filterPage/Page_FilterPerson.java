package GUI.pages.society.filterPage;

import book.Book;
import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_FilterPerson extends Page {
	private static final long serialVersionUID = 1L;

	public Page_FilterPerson() {
		super("Society: Persons, Relationships, ...");
		
		//*********************************************************************************
		//*********************************************************************************
		this.addCard(new StructureCard("Filter Persons:", new Card_PersonFilter()));
		this.addCard(new StructureCard("Filter Persons by Relationship:", new Card_FilterRelationships()));
		if(Book.getInstance().getSociety().isRaceSystemActivated()) {this.addCard(new StructureCard("Filter Persons by Race:", new Card_FilterRace()));}

	}

}
