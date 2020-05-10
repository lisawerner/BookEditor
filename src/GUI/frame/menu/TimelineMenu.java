package GUI.frame.menu;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.timelinePages.Page_RelativeDateListView;
import GUI.timelinePages.Page_TimelineFilter;
import GUI.timelinePages.Page_TimelineSettings;
import GUI.timelinePages.Page_ViewTimeline;
import GUI.components.FrameSubmenu;
import GUI.components.MenuButton;
public class TimelineMenu extends FrameSubmenu {
	private static final long serialVersionUID = 1L;

	public TimelineMenu() {
		super("Timeline:");
			
		addButton(new MenuButton("View Timeline", e -> BookEditorFrame.getInstance().switchBody(new Page_ViewTimeline())));
		addButton(new MenuButton("Timeline Settings", e -> BookEditorFrame.getInstance().switchBody(new Page_TimelineSettings())));
		addButton(new MenuButton("Content Filter", e -> BookEditorFrame.getInstance().switchBody(new Page_TimelineFilter())));
		addButton(new MenuButton("View Relative Dates", e -> BookEditorFrame.getInstance().switchBody(new Page_RelativeDateListView())));		
	}

}
