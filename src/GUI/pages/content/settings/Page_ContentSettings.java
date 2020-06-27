package GUI.pages.content.settings;

import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_ContentSettings extends Page {
	private static final long serialVersionUID = 1L;

	public Page_ContentSettings() {
		super("Change Settings for Table of Content");

		addCard(new StructureCard("List Settings", new Card_ListSettings()));
	}

}
