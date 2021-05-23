package GUI.pages.world.viewPlace;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import GUI.components.form.FormButton;
import GUI.components.form.FormTextField;
import GUI.components.form.SimpleForm;
import world.Place;

import javax.swing.*;
import java.awt.*;

public class Card_placeInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Place my_place;
	
	private final FormTextField txt_placeName;
	private final FormTextField txtPlaceType;
	private final SimpleTextarea txtNotes;

	public Card_placeInformation(Place place) {
		my_place = place;
		setLayout(new BorderLayout(5, 5));
		
		FormButton btnSave = new FormButton("Save Place", e -> savePlace());
		add(btnSave, BorderLayout.SOUTH);
		
		SimpleForm saveForm = new SimpleForm(this::savePlace, btnSave);

		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel panel_bodyForFurtherInformation = new TransparentPanel();
		add(panel_bodyForFurtherInformation, BorderLayout.CENTER);
		panel_bodyForFurtherInformation.setLayout(new BoxLayout(panel_bodyForFurtherInformation, BoxLayout.PAGE_AXIS));
				
		txt_placeName = new FormTextField("Place name:", my_place.getName(), true);
		panel_bodyForFurtherInformation.add(txt_placeName);
		saveForm.addInput(txt_placeName);
		txt_placeName.add(Box.createRigidArea(new Dimension(11, 11)), BorderLayout.EAST);
		
		panel_bodyForFurtherInformation.add(Box.createRigidArea(new Dimension(5, 5)));
		
		TransparentPanel panel_placeType = new TransparentPanel();
		panel_bodyForFurtherInformation.add(panel_placeType);
		panel_placeType.setLayout(new BorderLayout(0, 0));
		
		InfoButton btnPlaceTypeInfo = new InfoButton("<html>Enter something like city, state, village, parc, house, disco, living room, ...<br/>"
				+ "Can changed every time.</html>");
		panel_placeType.add(btnPlaceTypeInfo, BorderLayout.EAST);
		
		txtPlaceType = new FormTextField("Place-Type:", my_place.getType(), false);
		panel_placeType.add(txtPlaceType, BorderLayout.CENTER);
		saveForm.addInput(txtPlaceType);
		
		TransparentPanel panel_notes = new TransparentPanel();
		panel_bodyForFurtherInformation.add(panel_notes);
		panel_notes.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblNotesResearch = new SimpleLabel("Notes, Research, ...:");
		panel_notes.add(lblNotesResearch, BorderLayout.NORTH);
		
		txtNotes = new SimpleTextarea(my_place.getNotes());
		panel_notes.add(txtNotes, BorderLayout.CENTER);
	}
	
	private void savePlace(){
		String name = txt_placeName.getText();
		if(!name.isEmpty()) {
			my_place.editPlace(name, txtPlaceType.getText(), txtNotes.getText());
			BookEditorFrame.getInstance().openPlacePage(my_place, false);
		}
	}
}
