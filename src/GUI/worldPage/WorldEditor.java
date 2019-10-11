package GUI.worldPage;

import global.ObjectID;
import global.UserSettings;
import world.Place;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JSeparator;

import GUI_components.InfoButton;
import GUI_components.PageBody;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;

public class WorldEditor extends PageBody {
	private static final long serialVersionUID = 1L;
	
	private TransparentPanel panel_sortBody;
	
	public WorldEditor() {

		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addStructureCard(new TutorialCard(18, true));
		}
		
		StructureCard card_world = new StructureCard("World-Hierachy");
		this.addStructureCard(card_world);
		
		TransparentPanel card_body = new TransparentPanel();
		card_body.setLayout(new BorderLayout(5 , 5));
		card_world.setBody(card_body);
		
		//*************************************************************
		TransparentPanel panel_hint = new TransparentPanel();
		panel_hint.setLayout(new BorderLayout(5 , 5));
		card_body.add(panel_hint, BorderLayout.NORTH);
		
		InfoButton hintButton = new InfoButton("You can change hirachy by changing every single element.");
		panel_hint.add(hintButton, BorderLayout.WEST);
		
		JLabel lblChangingHint = new JLabel("You can change hirachy by changing every single element.");
		lblChangingHint.setForeground(Color.RED);
		panel_hint.add(lblChangingHint, BorderLayout.CENTER);
		

		//*************************************************************
		panel_sortBody = new TransparentPanel();
		card_body.add(panel_sortBody, BorderLayout.CENTER);
		panel_sortBody.setLayout(new GridLayout(0, 1, 20, 20));
		
		JSeparator firstSeparator = new JSeparator();
		panel_sortBody.add(firstSeparator);
		
		for(Place place : Book.getInstance().getPlaces()) {
			if(place.getParentRef() == null) {				
				JLabel lblPlaceInfo = new JLabel(place.getName() + " [Type: " + place.getType() + "]");
				panel_sortBody.add(lblPlaceInfo);
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
				Place currentPlace = Book.getInstance().getPlace(childID);
				JLabel lblPlaceInfo = new JLabel(hierarchyDepth + currentPlace.getName() + " [Type: " + currentPlace.getType() + "]");
				panel_sortBody.add(lblPlaceInfo);
				if(currentPlace.getChildrenIDs() != null) {					
					String childDepth = hierarchyDepth + "      >>  ";
					addAllChildren(currentPlace.getChildrenIDs(), childDepth);
				}
			}
		}
	}

}
