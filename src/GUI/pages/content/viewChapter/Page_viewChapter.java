package GUI.pages.content.viewChapter;

import book.Chapter;
import GUI.components.Page;
import GUI.components.StructureCard;

public class Page_viewChapter extends Page {
	private static final long serialVersionUID = 1L;

	public Page_viewChapter(Chapter chapter) {
		super("View complete Chapter");
		
		addCard(new StructureCard("Chapter Information", new Card_viewChapterInformation(chapter)));
		addCard(new StructureCard("List of Sections", new Card_SectionList(chapter)));
		
	}

}
