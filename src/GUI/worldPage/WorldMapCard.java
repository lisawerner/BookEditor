package GUI.worldPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.InfoButton;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.content.Book;
import global.ObjectID;
import book.world.Place;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WorldMapCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final TransparentPanel panel_sortBody;

	public WorldMapCard() {
		setLayout(new BorderLayout(5 , 5));
		
		//**************************************************************************************************************************
		TransparentPanel panel_hint = new TransparentPanel();
		panel_hint.setLayout(new BorderLayout(5 , 5));
		add(panel_hint, BorderLayout.NORTH);
		
		InfoButton hintButton = new InfoButton("You can change hierarchy by changing every single element.");
		panel_hint.add(hintButton, BorderLayout.WEST);
		
		SimpleLabel lblChangingHint = new SimpleLabel("You can change hierarchy by changing every single element.");
		lblChangingHint.setWarning(true);
		panel_hint.add(lblChangingHint, BorderLayout.CENTER);
		

		//*************************************************************
		panel_sortBody = new TransparentPanel();
		add(panel_sortBody, BorderLayout.CENTER);
		panel_sortBody.setLayout(new GridLayout(0, 1, 20, 20));
		
		JSeparator firstSeparator = new JSeparator();
		panel_sortBody.add(firstSeparator);
		
		for(Place place : Book.getInstance().getWorld().getPlaces()) {
			if(place.getParentRef() == null) {
				TransparentPanel panel_worldEntry = new TransparentPanel();
				panel_worldEntry.setLayout(new BoxLayout(panel_worldEntry, BoxLayout.LINE_AXIS));
				panel_sortBody.add(panel_worldEntry);
				
				panel_worldEntry.add(new LinkButton(place.getName(),
						e -> BookEditorFrame.getInstance().openPlacePage(place, false)));
				if(!place.getType().isEmpty()) {						
					panel_worldEntry.add(new SimpleLabel(" [Type: " + place.getType() + "]"));
				}
				if(place.getChildrenIDs() != null) {					
					String childDepth = "      >>  ";
					addAllChildren(place.getChildrenIDs(), childDepth);
					JSeparator separator = new JSeparator();
					panel_sortBody.add(separator);
				}
			}
		}
	}
	
	private void addAllChildren(ArrayList<ObjectID> childrenPlaceRefs, String hierarchyDepth) {
		if(childrenPlaceRefs != null) {
			for(ObjectID childID : childrenPlaceRefs) {
				TransparentPanel panel_worldEntry = new TransparentPanel();
				panel_worldEntry.setLayout(new BoxLayout(panel_worldEntry, BoxLayout.LINE_AXIS));
				panel_sortBody.add(panel_worldEntry);
				panel_worldEntry.add(new SimpleLabel(hierarchyDepth));
				Place currentPlace = Book.getInstance().getWorld().getPlace(childID);
				panel_worldEntry.add(new LinkButton(currentPlace.getName(),
						e -> BookEditorFrame.getInstance().openPlacePage(currentPlace, false)));
				if(!currentPlace.getType().isEmpty()) {					
					panel_worldEntry.add(new SimpleLabel(" [Type: " + currentPlace.getType() + "]"));
				}
				
				if(currentPlace.getChildrenIDs() != null) {					
					String childDepth = hierarchyDepth + "      >>  ";
					addAllChildren(currentPlace.getChildrenIDs(), childDepth);
				}
			}
		}
	}

}
