package GUI.timelinePage;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import GUI_components.TimelineItem;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;

public class TimelineCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public TimelineCard() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		TransparentPanel panel_leftTimeline = new TransparentPanel();
		add(panel_leftTimeline);
		panel_leftTimeline.setLayout(new BoxLayout(panel_leftTimeline, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_rightTimeline = new TransparentPanel();
		add(panel_rightTimeline);
		panel_rightTimeline.setLayout(new BoxLayout(panel_rightTimeline, BoxLayout.PAGE_AXIS));
		
		
		boolean leftPosition = true;
		ArrayList<Section> sectionsSortedByTimestamp = Book.getInstance().getSectionList().getTimeSortedSections();
		if((sectionsSortedByTimestamp.size()%2)==0) {
			panel_rightTimeline.add(new TimelineItem(false));
		} else {
			panel_rightTimeline.add(new TimelineItem(false));
		}
		
		for(Section section : Book.getInstance().getSectionList().getTimeSortedSections()) {
			if(section.hasTimestamp()) {
				if(leftPosition) {					
					panel_leftTimeline.add(new TimelineElement(section, leftPosition));
				} else {
					panel_rightTimeline.add(new TimelineElement(section, leftPosition));
				}
				leftPosition = !leftPosition;
			}

		}
		
		if((sectionsSortedByTimestamp.size()%2)==0) {
			panel_leftTimeline.add(new TimelineItem(true));
		} else {
			panel_rightTimeline.add(new TimelineItem(false));
		}

	}

}
