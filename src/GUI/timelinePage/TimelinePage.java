package GUI.timelinePage;

import GUI_components.Page;
import GUI_components.PageBody;
import GUI_components.StructureCard;
import GUI_components.TimelineItem;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import book.Section;
import global.UserSettings;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;

public class TimelinePage extends Page {
	private static final long serialVersionUID = 1L;
	
	public TimelinePage() {
		super("Timeline");
		
		PageBody my_body = new PageBody();
		this.changeBody(my_body);
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			my_body.addStructureCard(new TutorialCard(9, true));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			my_body.addStructureCard(new TutorialCard(15, true));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			my_body.addStructureCard(new TutorialCard(19, true));
		}
		
		StructureCard card_viewTimeline = new StructureCard("View Timeline");
		my_body.addStructureCard(card_viewTimeline);
		
		TransparentPanel panel_timeline = new TransparentPanel();
		card_viewTimeline.setBody(panel_timeline);
		panel_timeline.setLayout(new GridLayout(1, 0, 0, 0));
		
		TransparentPanel panel_leftTimeline = new TransparentPanel();
		panel_timeline.add(panel_leftTimeline);
		panel_leftTimeline.setLayout(new BoxLayout(panel_leftTimeline, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_rightTimeline = new TransparentPanel();
		panel_timeline.add(panel_rightTimeline);
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
