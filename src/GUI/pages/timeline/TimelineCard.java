package GUI.pages.timeline;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import time.TimelineController;
import time.Timestamp;
import GUI.components.TimelineItem;
import GUI.components.TransparentPanel;

public class TimelineCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public TimelineCard(Timestamp startDate) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		TransparentPanel panel_leftTimeline = new TransparentPanel();
		add(panel_leftTimeline);
		panel_leftTimeline.setLayout(new BoxLayout(panel_leftTimeline, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_rightTimeline = new TransparentPanel();
		add(panel_rightTimeline);
		panel_rightTimeline.setLayout(new BoxLayout(panel_rightTimeline, BoxLayout.PAGE_AXIS));
		
		
		boolean leftPosition = true;
		ArrayList<Timestamp> sortedTimestamps_NOTfilteredYET = TimelineController.getPartOfTimelineWithSplit(startDate); //TODO: sort timestampResult!!!
		if((sortedTimestamps_NOTfilteredYET.size()%2)==0) {
			panel_rightTimeline.add(new TimelineItem(false));
		} else {
			panel_rightTimeline.add(new TimelineItem(false));
		}
		
		for(Timestamp timestamp : sortedTimestamps_NOTfilteredYET) {
			TimelineElement currentElement = null;
			if(leftPosition) {
				currentElement = new TimelineElement(timestamp, leftPosition);
				panel_leftTimeline.add(currentElement);
			} else {
				currentElement = new TimelineElement(timestamp, leftPosition);
				panel_rightTimeline.add(currentElement);
			}
			int currentHeight = currentElement.getPreferredSize().height;
			if(currentHeight > 121) {
				int diffrence = currentHeight - 121;
				if(leftPosition) {
					panel_rightTimeline.add(new TimelineItem(false, diffrence));
				} else {
					panel_leftTimeline.add(new TimelineItem(true, diffrence));
				}
			}
			leftPosition = !leftPosition;
		}
		
		if((sortedTimestamps_NOTfilteredYET.size()%2)==0) {
			panel_leftTimeline.add(new TimelineItem(true));
		} else {
			panel_rightTimeline.add(new TimelineItem(false));
		}

	}

}
