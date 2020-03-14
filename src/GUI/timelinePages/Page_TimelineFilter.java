package GUI.timelinePages;

import GUI_components.Page;
import GUI_components.StructureCard;

public class Page_TimelineFilter extends Page {
	private static final long serialVersionUID = 1L;

	public Page_TimelineFilter() {
		super("Timeline versus Content");
		
		addCard(new StructureCard("Sections without a Timestamp:", new Card_ContentWithoutTimestamp()));
		addCard(new StructureCard("Sections without a specific Timestamp:", new Card_ContentWithoutSpecificDate()));
		
		setMenu(new TimelineMenu());
	}

}
