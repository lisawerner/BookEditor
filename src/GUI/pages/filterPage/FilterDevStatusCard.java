package GUI.pages.filterPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.DevelopmentStatus;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.SimpleCheckbox;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;

public class FilterDevStatusCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private int currentDevStatus;
	private boolean devStatusDown;
	
	private TransparentPanel panel_devFilteredSections;

	public FilterDevStatusCard() {
		setLayout(new BorderLayout(5, 5));
		
		currentDevStatus = -1;
		devStatusDown = false;
		
		TransparentPanel devFilter = new TransparentPanel();
		add(devFilter, BorderLayout.NORTH);
		devFilter.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel panel_generellSettings = new TransparentPanel();
		devFilter.add(panel_generellSettings);
		panel_generellSettings.setLayout(new GridLayout(1, 0, 0, 0));
		SimpleCheckbox boxbtn_single = new SimpleCheckbox("Select also all Sections with lower Development Status");
		panel_generellSettings.add(boxbtn_single);
		
		TransparentPanel panel_devFilter = new TransparentPanel();
		devFilter.add(panel_devFilter);
		panel_devFilter.setLayout(new GridLayout(0, 6, 5, 5));
		boxbtn_single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				devStatusDown = boxbtn_single.isSelected();
				filterSectionsByDevStatus();
			}
		});
		
		ButtonGroup btngroup_devStatus = new ButtonGroup();
		for(int i = -1; i < 5; i++) {
			SimpleRadiobutton rdbtn_devStatus = new SimpleRadiobutton(DevelopmentStatus.getDevStatus(i));
			panel_devFilter.add(rdbtn_devStatus);
			btngroup_devStatus.add(rdbtn_devStatus);
			int dev = i;
			rdbtn_devStatus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					currentDevStatus = dev;
					filterSectionsByDevStatus();
				}
			});
		}
		
		panel_devFilteredSections = new TransparentPanel();
		add(panel_devFilteredSections, BorderLayout.CENTER);

	}
	
	private void filterSectionsByDevStatus() {
		panel_devFilteredSections.removeAll();
		panel_devFilteredSections.setLayout(new GridLayout(0, 5, 5, 5));
		for(Section section :  Book.getInstance().getTableOfContent().getSectionsByDevStatus(currentDevStatus, devStatusDown)) {
			LinkButton sectionBTN = new LinkButton(section.getName(),
					e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID()))));
			panel_devFilteredSections.add(sectionBTN);
		}
		panel_devFilteredSections.revalidate();
		panel_devFilteredSections.repaint();
	}

}
