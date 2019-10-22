package GUI.filterPage;

import GUI_components.Page;
import GUI_components.StructureCard;

public class FilterChaptersPage extends Page {
	private static final long serialVersionUID = 1L;

	public FilterChaptersPage() {
		super("Filter and Search for Sections");
					
		//**************************************************************************************************
		addCard(new StructureCard("Filter by Development Status", new FilterDevStatusCard()));

		//**************************************************************************************************
		addCard(new StructureCard("Filter for unfinished Sections", new FilterMiscCard()));
		
		//**************************************************************************************************
		addCard(new StructureCard("Filter for Persons", new FilterPersonCard()));
	}
}
