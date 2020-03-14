package GUI.timelinePages;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.chapter.Page_viewChapter;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Chapter;
import book.Section;
import global.ObjectID;
import javax.swing.Box;
import java.awt.Dimension;

public class Card_RelativeList extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_RelativeList() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {	
			for(Section section : chapter.getSections()){
				if(section.hasTimestamp()){
					if(section.getTimestamp().isSpecificDate()){
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
						
						panel_child.add(new SimpleLabel("] has specific Date: " + section.getTimestamp().getSpecificDate().toString() + " and is related by: "));
						
						addRelatedTimestamp(panel_TimeRelationTree, section.getID(), "  >>");
						
						add(Box.createRigidArea(new Dimension(20, 20)));
						JSeparator firstSeparator = new JSeparator();
						add(firstSeparator);
					}
				}
			}
			
		}		
	}
	
	private void addRelatedTimestamp(TransparentPanel parentPanel, ObjectID sectionID, String space){
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()){
			for(Section section : chapter.getSections()){
				if(section.hasTimestamp()){
					Section relatedTo = section.getTimestamp().getRelationSection();
					if(relatedTo != null){
						if(sectionID.equals(relatedTo.getID())){
							// Found a child Timestamp! Uff o.O
							
							TransparentPanel panel_child = new TransparentPanel();
							panel_child.setLayout(new BoxLayout(panel_child, BoxLayout.LINE_AXIS));
							parentPanel.add(panel_child);
							
							panel_child.add(new SimpleLabel(space + " "));
							
							LinkButton btnSpecificDateSection = new LinkButton(section.getName());
							panel_child.add(btnSpecificDateSection);
							btnSpecificDateSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, chapter)));
							
							panel_child.add(new SimpleLabel("  [Chapter: "));
							
							LinkButton btnChapter = new LinkButton(chapter.getTitle());
							panel_child.add(btnChapter);
							btnChapter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(chapter)));
							
							panel_child.add(new SimpleLabel("] and is also related by: "));
							
							addRelatedTimestamp(parentPanel, section.getID(), "  >> " + space);
						}
					}
				}
			}
		}
	}
	
}
