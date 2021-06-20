package GUI.pages.timeline;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;
import GUI.pages.content.viewChapter.Page_viewChapter;
import GUI.pages.content.changeSection.Page_ChangeSection;
import book.Book;
import book.Chapter;
import book.Section;

import javax.swing.*;
import java.awt.*;

public class Card_ContentWithoutTimestamp extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ContentWithoutTimestamp() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()){
			TransparentPanel panel_chapter = new TransparentPanel();
			panel_chapter.setLayout(new WrapLayout(FlowLayout.LEADING));
			
			panel_chapter.add(new SimpleLabel("Chapter: "));
			
			panel_chapter.add(new LinkButton(chapter.getTitle(),
					e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter))));
			
			panel_chapter.add(new SimpleLabel(" has following Sections without Timestamp: "));
			
			boolean hasMissingTimestamps = false;
			for(Section section : chapter.getSections()){
				if(!section.hasTimestamp()){
					panel_chapter.add(new LinkButton(chapter.getTitle(),
							e -> BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(section, chapter))));
					hasMissingTimestamps = true;
				}
			}
			if(hasMissingTimestamps){
				this.add(panel_chapter);
			}
		}

	}

}
