package GUI.pages.content.createChapter;

import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_createChapter extends Page {
	private static final long serialVersionUID = 1L;

	public Page_createChapter() {
		super("Create a new Chapter");
		
		addCard(new StructureCard("Chapter Information", new Card_createChapter()));

	}

}
