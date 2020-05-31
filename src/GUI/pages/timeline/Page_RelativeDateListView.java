package GUI.pages.timeline;

import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_RelativeDateListView extends Page {
	private static final long serialVersionUID = 1L;

	public Page_RelativeDateListView() {
		super("Tree of Relative Dates");
		
		addCard(new StructureCard("List all Sections with Relative Dates in a tree:", new Card_RelativeList()));
	}

}
