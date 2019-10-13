package GUI.filterPage;

import GUI_components.Page;
import GUI_components.StructureCard;

public class FilterChaptersPage extends Page {
	private static final long serialVersionUID = 1L;

	public FilterChaptersPage() {
		super("Filter and Search for Sections");
					
		//**************************************************************************************************
		StructureCard card_filterByDevStatus = new StructureCard("Filter by Development Status");
		addCard(card_filterByDevStatus);
		card_filterByDevStatus.setBody(new FilterDevStatusCard());

		//**************************************************************************************************
		StructureCard card_filterOthers = new StructureCard("Filter for unfinished Sections");
		addCard(card_filterOthers);
		card_filterOthers.setBody(new FilterMiscCard());
		
		//**************************************************************************************************
		StructureCard card_Persons = new StructureCard("Filter for Persons");
		addCard(card_Persons);
		card_Persons.setBody(new FilterPersonCard());

	}
}
