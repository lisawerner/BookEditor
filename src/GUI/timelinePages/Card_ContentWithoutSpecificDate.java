package GUI.timelinePages;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.pages.chapter.Page_viewChapter;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Chapter;
import book.Section;
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
		
		panel_hint.add(new SimpleLabel("<html>If a Section A has set a relative date to another section B:<br/>"
				+ "But the other section B does not have specific date:<br/>"
				+ "Then section A can not calculate a specific date too. </html>"));
		
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()){
			TransparentPanel panel_chapter = new TransparentPanel();
			panel_chapter.setLayout(new BoxLayout(panel_chapter, BoxLayout.PAGE_AXIS));
			
			TransparentPanel panel_chapterInfo = new TransparentPanel();
			panel_chapterInfo.setLayout(new WrapLayout(FlowLayout.LEADING));
			panel_chapter.add(panel_chapterInfo);
			
			panel_chapterInfo.add(new SimpleLabel("Chapter: "));
			
			LinkButton btn_chapter = new LinkButton(chapter.getTitle());
			panel_chapterInfo.add(btn_chapter);
			btn_chapter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter)));
			
			panel_chapterInfo.add(new SimpleLabel(" has following Sections without specific Timestamp: "));
			
			boolean hasMissingTimestamps = false;
			for(Section section : chapter.getSections()){
				if(section.hasTimestamp()){
					if(section.getTimestamp().getSpecificDate() == null){
						TransparentPanel panel_unspecificSection = new TransparentPanel();
						panel_unspecificSection.setLayout(new WrapLayout(FlowLayout.LEADING));
						panel_chapter.add(panel_unspecificSection);
						
						panel_unspecificSection.add(new SimpleLabel("  >> "));
						
						LinkButton btn_section = new LinkButton(chapter.getTitle());
						panel_unspecificSection.add(btn_section);
						btn_section.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, chapter)));
						
						Section relatedSection = section.getTimestamp().getRelationSection();
						if(relatedSection != null){							
							panel_unspecificSection.add(new SimpleLabel(" because of a relation to: "));

							LinkButton btn_relatesToSection = new LinkButton(relatedSection.getName());
							panel_unspecificSection.add(btn_relatesToSection);
							btn_relatesToSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(relatedSection, Book.getInstance().getTableOfContent().getChapter(relatedSection.getParentChapterID()))));
						} else {
							panel_unspecificSection.add(new SimpleLabel(" of unknown reasons..."));
						}
						
						hasMissingTimestamps = true;
					}
				}
			}
			if(hasMissingTimestamps){
				this.add(panel_chapter);
			}
		}
	}

}
