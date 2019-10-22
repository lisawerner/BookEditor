package GUI.filterPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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
	
	private TransparentPanel panel_unfinishedFilteredSections;

	public FilterMiscCard() {
		setLayout(new BorderLayout(5, 5));
		TransparentPanel unfinishedFilter = new TransparentPanel();
		add(unfinishedFilter, BorderLayout.NORTH);
		unfinishedFilter.setLayout(new BoxLayout(unfinishedFilter, BoxLayout.LINE_AXIS));
		ButtonGroup btngroup_unfinished = new ButtonGroup();
		SimpleRadiobutton rdbtn_EmptyText = new SimpleRadiobutton("Sections with Empty Text");
		unfinishedFilter.add(rdbtn_EmptyText);
		btngroup_unfinished.add(rdbtn_EmptyText);
		rdbtn_EmptyText.addActionListener(e -> filterUnfinished(true, false, false));
		SimpleRadiobutton rdbtn_UnsortedText = new SimpleRadiobutton("Unsorted Sections");
		unfinishedFilter.add(rdbtn_UnsortedText);
		btngroup_unfinished.add(rdbtn_UnsortedText);
		rdbtn_UnsortedText.addActionListener(e -> filterUnfinished(false, true, false));
		SimpleRadiobutton rdbtn_Unfinished = new SimpleRadiobutton("Unfinished Sections");
		unfinishedFilter.add(rdbtn_Unfinished);
		btngroup_unfinished.add(rdbtn_Unfinished);
		rdbtn_Unfinished.addActionListener(e -> filterUnfinished(false, false, true));
		panel_unfinishedFilteredSections = new TransparentPanel();
		add(panel_unfinishedFilteredSections, BorderLayout.CENTER);
	}
	
	private void filterUnfinished(boolean empty, boolean unsorted, boolean unfinished) {
		panel_unfinishedFilteredSections.removeAll();
		panel_unfinishedFilteredSections.setLayout(new GridLayout(0, 5, 5, 5));
		List<Section> sectionList = new ArrayList<>();
		if(empty) {
			sectionList = Book.getInstance().getSectionList().getEmptySections();
		} else if(unfinished) {
			sectionList= Book.getInstance().getSectionList().getUnfinishedSections();
		} else if(unsorted) {
			sectionList = Book.getInstance().getSectionList().getUnsortedSections();
		}
		for(Section section : sectionList) {
			LinkButton sectionBTN = new LinkButton(section.getName());
			sectionBTN.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section)));
			panel_unfinishedFilteredSections.add(sectionBTN);
		}
		panel_unfinishedFilteredSections.revalidate();
		panel_unfinishedFilteredSections.repaint();
	}

}
