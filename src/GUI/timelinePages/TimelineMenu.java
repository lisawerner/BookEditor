package GUI.timelinePages;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.PageMenu;

public class TimelineMenu extends PageMenu {
	private static final long serialVersionUID = 1L;

	public TimelineMenu() {
		super("Timeline-Settings:");
		
		JButton btnViewTimeline = this.addButtonToTopMenu("View Timeline");
		btnViewTimeline.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_ViewTimeline()));
		
		JButton btnChangeTimelineViewSettings = this.addButtonToTopMenu("Timeline Settings");
		btnChangeTimelineViewSettings.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_TimelineSettings()));
		
		JButton btnContentFilter = this.addButtonToTopMenu("Content Filter");
		btnContentFilter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_TimelineFilter()));
		
		JButton btnRelativeList = this.addButtonToTopMenu("View Relative Dates");
		btnRelativeList.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_RelativeDateListView()));
	}

}
