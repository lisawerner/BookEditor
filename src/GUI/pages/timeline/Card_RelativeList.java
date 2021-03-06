package GUI.pages.timeline;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;

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

import javax.swing.Box;
import java.awt.Dimension;

public class Card_RelativeList extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_RelativeList() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Timestamp timestamp : Book.getInstance().getTimeline().getAllTimestamps()){
			if(timestamp.isSpecificDate()){
				Section section = Book.getInstance().getTableOfContent().getSection(timestamp.getSection());
				Chapter chapter = Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID());
				
				TransparentPanel panel_TimeRelationTree = new TransparentPanel();
				add(panel_TimeRelationTree);
				panel_TimeRelationTree.setLayout(new GridLayout(0, 1, 0, 0));
				
				TransparentPanel panel_child = new TransparentPanel();
				panel_child.setLayout(new BoxLayout(panel_child, BoxLayout.LINE_AXIS));
				panel_TimeRelationTree.add(panel_child);
				
				LinkButton btnSpecificDateSection = new LinkButton(section.getName());
				panel_child.add(btnSpecificDateSection);
				btnSpecificDateSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, chapter)));
				
				panel_child.add(new SimpleLabel("  [Chapter: "));
				
				LinkButton btnChapter = new LinkButton(chapter.getTitle());
				panel_child.add(btnChapter);
				btnChapter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter)));
				
				panel_child.add(new SimpleLabel("] has specific Date: " + timestamp.toString() + " and is related by: "));
				
				addRelatedTimestamp(panel_TimeRelationTree, timestamp, "  >>");
				
				add(Box.createRigidArea(new Dimension(20, 20)));
				JSeparator firstSeparator = new JSeparator();
				add(firstSeparator);
			}
		}	
	}
	
	private void addRelatedTimestamp(TransparentPanel parentPanel, Timestamp parentTimestamp, String space){
		if(parentTimestamp == null){
			return;
		}
		if(parentTimestamp.getSection() == null){
			return;
		}
		for(Timestamp timestamp : Book.getInstance().getTimeline().getAllTimestamps()){
			if(!timestamp.isSpecificDate()){
				if(timestamp.getUnspecificDate().getRelationSection() == null){
					continue;
				}
				if(timestamp.getUnspecificDate().getRelationSection().getID().equals(parentTimestamp.getSection())){
					
					Section ownSection = Book.getInstance().getTableOfContent().getSection(timestamp.getSection());
					Chapter chapter = Book.getInstance().getTableOfContent().getChapter(ownSection.getParentChapterID());
					

					TransparentPanel panel_child = new TransparentPanel();
					panel_child.setLayout(new BoxLayout(panel_child, BoxLayout.LINE_AXIS));
					parentPanel.add(panel_child);
					
					panel_child.add(new SimpleLabel(space + " "));
					
					LinkButton btnSpecificDateSection = new LinkButton(ownSection.getName());
					panel_child.add(btnSpecificDateSection);
					btnSpecificDateSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(ownSection, chapter)));
					
					panel_child.add(new SimpleLabel("  [Chapter: "));
					
					LinkButton btnChapter = new LinkButton(chapter.getTitle());
					panel_child.add(btnChapter);
					btnChapter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter)));
					
					panel_child.add(new SimpleLabel("] and is also related by: "));
					
					addRelatedTimestamp(parentPanel, timestamp, "  >> " + space);
				}
			}
		}
	}
	
}
