package GUI.worldPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.InfoButton;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import global.ObjectID;
import world.Place;

public class WorldMapCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private TransparentPanel panel_sortBody;

	public WorldMapCard() {
		setLayout(new BorderLayout(5 , 5));
		
		//**************************************************************************************************************************
		TransparentPanel panel_hint = new TransparentPanel();
		panel_hint.setLayout(new BorderLayout(5 , 5));
		add(panel_hint, BorderLayout.NORTH);
		
		InfoButton hintButton = new InfoButton("You can change hirachy by changing every single element.");
		panel_hint.add(hintButton, BorderLayout.WEST);
		
		SimpleLabel lblChangingHint = new SimpleLabel("You can change hirachy by changing every single element.");
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
				
				LinkButton lblPlaceInfo = new LinkButton(place.getName());
				lblPlaceInfo.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(place, false)));
				panel_worldEntry.add(lblPlaceInfo);
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
				LinkButton lblPlaceInfo = new LinkButton(currentPlace.getName());
				lblPlaceInfo.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(currentPlace, false)));
				panel_worldEntry.add(lblPlaceInfo);
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
