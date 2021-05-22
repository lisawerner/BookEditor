package GUI.filterPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleCheckbox;
import GUI_components.SimpleRadiobutton;
import GUI_components.TransparentPanel;
import book.Book;
import book.DevelopmentStatus;
import book.Section;

import javax.swing.*;
import java.awt.*;

public class FilterDevStatusCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private int currentDevStatus;
	private boolean devStatusDown;
	
	private final TransparentPanel panel_devFilteredSections;

	public FilterDevStatusCard() {
		setLayout(new BorderLayout(5, 5));
		
		currentDevStatus = -1;
		devStatusDown = false;
		
		TransparentPanel devFilter = new TransparentPanel();
		add(devFilter, BorderLayout.NORTH);
		devFilter.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel panel_generalSettings = new TransparentPanel();
		devFilter.add(panel_generalSettings);
		panel_generalSettings.setLayout(new GridLayout(1, 0, 0, 0));
		SimpleCheckbox boxBtn_single = new SimpleCheckbox("Select also all Sections with lower Development Status");
		panel_generalSettings.add(boxBtn_single);
		
		TransparentPanel panel_devFilter = new TransparentPanel();
		devFilter.add(panel_devFilter);
		panel_devFilter.setLayout(new GridLayout(0, 6, 5, 5));
		boxBtn_single.addActionListener(e -> {
			devStatusDown = boxBtn_single.isSelected();
			filterSectionsByDevStatus();
		});
		
		ButtonGroup btnGroup_devStatus = new ButtonGroup();
		for(int i = -1; i < 5; i++) {
			SimpleRadiobutton rdbtn_devStatus = new SimpleRadiobutton(DevelopmentStatus.getDevStatus(i));
			panel_devFilter.add(rdbtn_devStatus);
			btnGroup_devStatus.add(rdbtn_devStatus);
			int dev = i;
			rdbtn_devStatus.addActionListener(e -> {
				currentDevStatus = dev;
				filterSectionsByDevStatus();
			});
		}
		
		panel_devFilteredSections = new TransparentPanel();
		add(panel_devFilteredSections, BorderLayout.CENTER);

	}
	
	private void filterSectionsByDevStatus() {
		panel_devFilteredSections.removeAll();
		panel_devFilteredSections.setLayout(new GridLayout(0, 5, 5, 5));
		for(Section section :  Book.getInstance().getTableOfContent().getSectionsByDevStatus(currentDevStatus, devStatusDown)) {
			LinkButton sectionBTN = new LinkButton(section.getName());
			sectionBTN.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID()))));
			panel_devFilteredSections.add(sectionBTN);
		}
		panel_devFilteredSections.revalidate();
		panel_devFilteredSections.repaint();
	}

}
