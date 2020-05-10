package GUI.timelinePages;

import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_TimelineSettings extends Page {
	private static final long serialVersionUID = 1L;

	public Page_TimelineSettings() {
		super("Timeline Filter-Settings");
		
		addCard(new StructureCard("Set Filter to the Timeline", new FilterCard()));
	}

}
