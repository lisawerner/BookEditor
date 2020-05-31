package GUI.frame.menu;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.FrameSubmenu;
import GUI.components.MenuButton;
import GUI.components.MenuListButton;
import GUI.pages.timeline.Page_RelativeDateListView;
import GUI.pages.timeline.Page_TimelineFilter;
import GUI.pages.timeline.settingsPage.Page_TimelineSettings;
import book.Book;
import time.SpecificDate;
import time.TimelineController;
import time.Timestamp;

public class TimelineMenu extends FrameSubmenu {
	private static final long serialVersionUID = 1L;

	public TimelineMenu() {
		super("Timeline:", "Timeline points:");
			
		addButton(new MenuButton("View Timeline", e -> BookEditorFrame.getInstance().openTimelinePage(null)));
		addButton(new MenuButton("Timeline Settings", e -> BookEditorFrame.getInstance().switchBody(new Page_TimelineSettings())));
		addButton(new MenuButton("Content Filter", e -> BookEditorFrame.getInstance().switchBody(new Page_TimelineFilter())));
		addButton(new MenuButton("View Relative Dates", e -> BookEditorFrame.getInstance().switchBody(new Page_RelativeDateListView())));
		
		for(Timestamp date : TimelineController.getTimelineWithSplit()){
			SpecificDate specificDate = date.getSpecificDate();
			if(specificDate != null){				
				addListEntry(new MenuListButton(specificDate.toCompleteString(), e -> BookEditorFrame.getInstance().openTimelinePage(date)));
			} else {
				addListEntry(new MenuListButton(Book.getInstance().getTableOfContent().getSection(date.getSection()).getName(), e -> BookEditorFrame.getInstance().openTimelinePage(date)));
			}
		}
	}

}
