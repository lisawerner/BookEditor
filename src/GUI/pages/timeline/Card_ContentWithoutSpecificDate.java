package GUI.pages.timeline;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.pages.chapter.Page_viewChapter;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Chapter;
import book.Section;
import time.Timestamp;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;

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
		
		//TODO: Gut wäre es hier den Anfang der Kette nur anzuzeigen und nicht die ganze Kette...
		for(Timestamp timestamp : Book.getInstance().getTimeline().getAllTimestamps()){
			if(timestamp.getSpecificDate() == null){
				Section section = Book.getInstance().getTableOfContent().getSection(timestamp.getSection());
				Chapter chapter = Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID());
				Section relatedSection = timestamp.getRelationSection();
				Chapter chapterOfRelatedSection = Book.getInstance().getTableOfContent().getChapter(relatedSection.getParentChapterID());
				
				TransparentPanel panel_unspecificSection = new TransparentPanel();
				panel_unspecificSection.setLayout(new WrapLayout(FlowLayout.LEADING));
				this.add(panel_unspecificSection);
				
				panel_unspecificSection.add(new SimpleLabel("The timestamp of section: "));
				panel_unspecificSection.add(new LinkButton(section.getName(), e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, chapter))));
				panel_unspecificSection.add(new SimpleLabel(" [Chapter: "));
				panel_unspecificSection.add(new LinkButton(chapter.getTitle(), e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter))));
				if(relatedSection != null){					
					panel_unspecificSection.add(new SimpleLabel("] because of relation to following section with unspecific timestamp: "));
					panel_unspecificSection.add(new LinkButton(section.getName(), e -> BookEditorFrame.getInstance().switchBody(new SectionPage(relatedSection, chapterOfRelatedSection))));
				}
				//TODO: Die IDE sagt mir, dass das deadcode ist. Warum? Ich bin mir sicher das timestamp.getRelationSection(); null zurückgeben kann...
//				} else {
//					panel_unspecificSection.add(new SimpleLabel("] because of unknown reason"));					
//				}
			}
		}
	}

}
