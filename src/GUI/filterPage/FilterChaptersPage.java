package GUI.filterPage;

import GUI_components.Page;
import GUI_components.PageBody;
import GUI_components.StructureCard;

public class FilterChaptersPage extends Page {
	private static final long serialVersionUID = 1L;

	public FilterChaptersPage() {
		super("Filter and Search for Sections");
			
		PageBody my_body = new PageBody();
		this.changeBody(my_body);
		
		//**************************************************************************************************
		StructureCard card_filterByDevStatus = new StructureCard("Filter by Development Status");
		my_body.addStructureCard(card_filterByDevStatus);
		card_filterByDevStatus.setBody(new FilterDevStatusCard());

		//**************************************************************************************************
		StructureCard card_filterOthers = new StructureCard("Filter for unfinished Sections");
		my_body.addStructureCard(card_filterOthers);
		card_filterOthers.setBody(new FilterMiscCard());
		
		//**************************************************************************************************
		StructureCard card_Persons = new StructureCard("Filter for Persons");
		my_body.addStructureCard(card_Persons);
		card_Persons.setBody(new FilterPersonCard());

	}

}
