package GUI.filterPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleRadiobutton;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;

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
		rdbtn_missingMainCharacter.addActionListener(e -> filterNoMainCharachter());
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
			LinkButton sectionBTN = new LinkButton(section.getName());
			sectionBTN.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID()))));
			panel_unfinishedFilteredSections.add(sectionBTN);
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
	
	private void filterNoMainCharachter() {
		clear();
		fill(Book.getInstance().getTableOfContent().getSectionWithoutTaggedMainCharacters());
	}
	
	private void filterNoTimestamp() {
		clear();
		fill(Book.getInstance().getTableOfContent().getSectionWithoutTimestamp());
	}

}
