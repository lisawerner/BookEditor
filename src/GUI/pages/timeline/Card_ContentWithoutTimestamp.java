package GUI.pages.timeline;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Chapter;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;
import GUI.pages.content.viewChapter.Page_viewChapter;

public class Card_ContentWithoutTimestamp extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ContentWithoutTimestamp() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()){
			TransparentPanel panel_chapter = new TransparentPanel();
			panel_chapter.setLayout(new WrapLayout(FlowLayout.LEADING));
			
			panel_chapter.add(new SimpleLabel("Chapter: "));
			
			LinkButton btn_chapter = new LinkButton(chapter.getTitle());
			panel_chapter.add(btn_chapter);
			btn_chapter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter)));
			
			panel_chapter.add(new SimpleLabel(" has following Sections without Timestamp: "));
			
			boolean hasMissingTimestamps = false;
			for(Section section : chapter.getSections()){
				if(!section.hasTimestamp()){
					LinkButton btn_section = new LinkButton(chapter.getTitle());
					panel_chapter.add(btn_section);
					btn_section.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, chapter)));
					hasMissingTimestamps = true;
				}
			}
			if(hasMissingTimestamps){
				this.add(panel_chapter);
			}
		}

	}

}
