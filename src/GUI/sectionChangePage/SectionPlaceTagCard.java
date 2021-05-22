package GUI.sectionChangePage;

import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import global.Tag;
import world.Place;

import javax.swing.*;
import java.awt.*;

public class SectionPlaceTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final Section my_section;
	
	public SectionPlaceTagCard(Section section) {
		my_section = section;
		// &#8627; -> Arrow
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Place place : Book.getInstance().getWorld().getPlaces()) {
			if(place.getParentRef() == null) {
				TransparentPanel parentPlacePanel = new TransparentPanel();
				parentPlacePanel.setLayout(new GridLayout(0, 1, 5, 5));
				add(parentPlacePanel);
				SimpleCheckbox checkboxPlace = new SimpleCheckbox(place.getName());
				parentPlacePanel.add(checkboxPlace);
				checkboxPlace.setSelected(my_section.hasTag(place.getID()));
				checkboxPlace.addActionListener(e -> {
					if(checkboxPlace.isSelected()) {
						//System.out.println("Click: Is Selected now");
						my_section.addTag(new Tag(place.getID(), place.getClass().getName()));
					} else {
						//System.out.println("Click: Is NOT Selected anymore");
						my_section.removeTag(place.getID());
					}
				});
				addChildren(parentPlacePanel, place, "&emsp; &#8627;");

			}
		}	
	}
	
	private void addChildren(TransparentPanel parentPanel, Place parent, String distance) {
		for(Place child : parent.getChildrenObject()) {
			TransparentPanel distancePanel = new TransparentPanel();
			distancePanel.setLayout(new BorderLayout(5, 5));
			parentPanel.add(distancePanel);
			SimpleLabel lblDistance = new SimpleLabel("<html>" + distance + "</html>");
			distancePanel.add(lblDistance, BorderLayout.WEST);
			SimpleCheckbox checkboxPlace = new SimpleCheckbox(child.getName());
			distancePanel.add(checkboxPlace, BorderLayout.CENTER);
			checkboxPlace.setSelected(my_section.hasTag(child.getID()));
			checkboxPlace.addActionListener(e -> {
				if(checkboxPlace.isSelected()) {
					//System.out.println("Click: Is Selected now");
					my_section.addTag(new Tag(child.getID(), child.getClass().getName()));
				} else {
					//System.out.println("Click: Is NOT Selected anymore");
					my_section.removeTag(child.getID());
				}
			});
			String newDistance = "&emsp; " + distance;
			addChildren(parentPanel, child, newDistance);
		}
	}

}
