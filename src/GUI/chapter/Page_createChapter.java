package GUI.chapter;

import GUI_components.Page;
import GUI_components.StructureCard;

public class Page_createChapter extends Page {
	private static final long serialVersionUID = 1L;

	public Page_createChapter() {
		super("Create a new Chapter");
		
		addCard(new StructureCard("Chapter Information", new Card_chapterInformation(null)));

	}

}
