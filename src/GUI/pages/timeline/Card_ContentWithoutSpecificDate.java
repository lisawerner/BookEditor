package GUI.pages.timeline;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;
import GUI.pages.content.viewChapter.Page_viewChapter;
import GUI.pages.content.viewSection.Page_ViewSection;
import book.Book;
import book.Chapter;
import book.Section;
import time.Timestamp;

import javax.swing.*;
import java.awt.*;

public class Card_ContentWithoutSpecificDate extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ContentWithoutSpecificDate() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		TransparentPanel panel_hint = new TransparentPanel();
		panel_hint.setLayout(new WrapLayout(FlowLayout.LEADING));
		this.add(panel_hint);
		
		panel_hint.add(new SimpleLabel("<html>If a timestamp A has set a relative date to another timestamp B:<br/>"
				+ "But the other timestamp B does not have specific date:<br/>"
				+ "Then timestamp A can not calculate a specific date too. </html>"));
		
		//TODO: It would be better to show only the start of the list and not the complete list...
		for(Timestamp timestamp : Book.getInstance().getTimeline().getAllTimestamps()){
			if(timestamp.getDate() == null){
				Section section = Book.getInstance().getTableOfContent().getSection(timestamp.getSection());
				Chapter chapter = Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID());
				Section relatedSection = timestamp.getUnspecificDate().getRelationSection();
				if(relatedSection == null){
					continue;
				}
				Chapter chapterOfRelatedSection = Book.getInstance().getTableOfContent().getChapter(relatedSection.getParentChapterID());
				
				TransparentPanel panel_unspecificSection = new TransparentPanel();
				panel_unspecificSection.setLayout(new WrapLayout(FlowLayout.LEADING));
				this.add(panel_unspecificSection);
				
				panel_unspecificSection.add(new SimpleLabel("The timestamp of section: "));
				panel_unspecificSection.add(new LinkButton(section.getName(), e -> BookEditorFrame.getInstance().switchBody(new Page_ViewSection(section, chapter))));
				panel_unspecificSection.add(new SimpleLabel(" [Chapter: "));
				panel_unspecificSection.add(new LinkButton(chapter.getTitle(), e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter))));
				if(relatedSection != null){					
					panel_unspecificSection.add(new SimpleLabel("] because of relation to following section with unspecific timestamp: "));
					panel_unspecificSection.add(new LinkButton(section.getName(), e -> BookEditorFrame.getInstance().switchBody(new Page_ViewSection(relatedSection, chapterOfRelatedSection))));
				}
//				} else {
//					panel_unspecificSection.add(new SimpleLabel("] because of unknown reason"));					
//				}
			}
		}
	}

}
