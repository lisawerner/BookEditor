package GUI.pages.filterPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Section;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilterMiscCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final TransparentPanel panel_unfinishedFilteredSections;

	public FilterMiscCard() {
		setLayout(new BorderLayout(5, 5));
		TransparentPanel unfinishedFilter = new TransparentPanel();
		add(unfinishedFilter, BorderLayout.NORTH);
		unfinishedFilter.setLayout(new BoxLayout(unfinishedFilter, BoxLayout.LINE_AXIS));
		ButtonGroup btnGroup_unfinished = new ButtonGroup();
		SimpleRadiobutton rdbtn_EmptyText = new SimpleRadiobutton("Sections with Empty Text");
		unfinishedFilter.add(rdbtn_EmptyText);
		btnGroup_unfinished.add(rdbtn_EmptyText);
		rdbtn_EmptyText.addActionListener(e -> filterEmpty());
		SimpleRadiobutton rdbtn_Unfinished = new SimpleRadiobutton("Unfinished Sections");
		unfinishedFilter.add(rdbtn_Unfinished);
		btnGroup_unfinished.add(rdbtn_Unfinished);
		rdbtn_Unfinished.addActionListener(e -> filterUnfinished());
		SimpleRadiobutton rdbtn_missingMainCharacter = new SimpleRadiobutton("Section without tagged main characters");
		unfinishedFilter.add(rdbtn_missingMainCharacter);
		btnGroup_unfinished.add(rdbtn_missingMainCharacter);
		rdbtn_missingMainCharacter.addActionListener(e -> filterNoMainCharacter());
		SimpleRadiobutton rdbtn_missingTimestamp = new SimpleRadiobutton("Section without timestamp");
		unfinishedFilter.add(rdbtn_missingTimestamp);
		btnGroup_unfinished.add(rdbtn_missingTimestamp);
		rdbtn_missingTimestamp.addActionListener(e -> filterNoTimestamp());
		panel_unfinishedFilteredSections = new TransparentPanel();
		add(panel_unfinishedFilteredSections, BorderLayout.CENTER);
	}
	
	private void clear() {
		panel_unfinishedFilteredSections.removeAll();
		panel_unfinishedFilteredSections.setLayout(new GridLayout(0, 5, 5, 5));
	}
	
	private void fill(List<Section> sectionList) {
		for(Section section : sectionList) {
			panel_unfinishedFilteredSections.add(new LinkButton(section.getName(),
					e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID())))));
		}
		panel_unfinishedFilteredSections.revalidate();
		panel_unfinishedFilteredSections.repaint();
	}
	
	private void filterEmpty(){
		clear();
		fill(Book.getInstance().getTableOfContent().getEmptySections());
	}
	
	private void filterUnfinished() {
		clear();
		fill(Book.getInstance().getTableOfContent().getUnfinishedSections());
	}
	
	private void filterNoMainCharacter() {
		clear();
		fill(Book.getInstance().getTableOfContent().getSectionWithoutTaggedMainCharacters());
	}
	
	private void filterNoTimestamp() {
		clear();
		fill(Book.getInstance().getTableOfContent().getSectionWithoutTimestamp());
	}

}
