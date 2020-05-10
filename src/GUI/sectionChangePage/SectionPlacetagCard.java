package GUI.sectionChangePage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import book.Book;
import book.Section;
import global.Tag;
import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import world.Place;

public class SectionPlacetagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	public SectionPlacetagCard(Section section) {
		my_section = section;
		// &#8627; -> Arrow
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Place place : Book.getInstance().getWorld().getPlaces()) {
			if(place.getParentRef() == null) {
				TransparentPanel parentPlacePanel = new TransparentPanel();
				parentPlacePanel.setLayout(new GridLayout(0, 1, 5, 5));
				add(parentPlacePanel);
				SimpleCheckbox chckbxPlace = new SimpleCheckbox(place.getName());
				parentPlacePanel.add(chckbxPlace);			
				chckbxPlace.setSelected(my_section.hasTag(place.getID()));
				chckbxPlace.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(chckbxPlace.isSelected()) {			
							//System.out.println("Click: Is Selected now");
							my_section.addTag(new Tag(place.getID(), place.getClass().getName()));
						} else {
							//System.out.println("Click: Is NOOOTTT Selected anymore");
							my_section.removeTag(place.getID());
						}
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
			SimpleCheckbox chckbxPlace = new SimpleCheckbox(child.getName());
			distancePanel.add(chckbxPlace, BorderLayout.CENTER);			
			chckbxPlace.setSelected(my_section.hasTag(child.getID()));
			chckbxPlace.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxPlace.isSelected()) {			
						//System.out.println("Click: Is Selected now");
						my_section.addTag(new Tag(child.getID(), child.getClass().getName()));
					} else {
						//System.out.println("Click: Is NOOOTTT Selected anymore");
						my_section.removeTag(child.getID());
					}
				}
			});
			String newDistance = "&emsp; " + distance;
			addChildren(parentPanel, child, newDistance);
		}
	}

}
