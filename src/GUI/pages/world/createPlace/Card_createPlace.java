package GUI.pages.world.createPlace;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import GUI.components.form.FormButton;
import GUI.components.form.FormTextField;
import GUI.components.form.SimpleForm;
import book.content.Book;
import global.UserSettings;
import book.world.Place;

import javax.swing.*;
import java.awt.*;

public class Card_createPlace extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final FormTextField txt_placeName;
	private final FormTextField txtPlaceType;
	private final SimpleTextarea txtNotes;
	
	public Card_createPlace() {
		setLayout(new BorderLayout(5, 5));
		
		FormButton btnSave = new FormButton("Save Place", e -> savePlace());
		add(btnSave, BorderLayout.SOUTH);
		SimpleForm saveForm = new SimpleForm(this::savePlace, btnSave);

		TransparentPanel panel_bodyForFurtherInformation = new TransparentPanel();
		add(panel_bodyForFurtherInformation, BorderLayout.CENTER);
		panel_bodyForFurtherInformation.setLayout(new BoxLayout(panel_bodyForFurtherInformation, BoxLayout.PAGE_AXIS));
				
		//TODO: If adding text to notes and type saving is disabled, but user does not get any feedback, why it is disabled until he enters something into name text field... TODO: add feedback
		txt_placeName = new FormTextField("Place name:", "", true);
		panel_bodyForFurtherInformation.add(txt_placeName);
		saveForm.addInput(txt_placeName);
		
		txt_placeName.add(Box.createRigidArea(new Dimension(11, 11)), BorderLayout.EAST);
		
		panel_bodyForFurtherInformation.add(Box.createRigidArea(new Dimension(5, 5)));
		
		TransparentPanel panel_placeType = new TransparentPanel();
		panel_bodyForFurtherInformation.add(panel_placeType);
		panel_placeType.setLayout(new BorderLayout(0, 0));
		
		InfoButton btnPlaceTypeInfo = new InfoButton("<html>Enter something like city, state, village, parc, house, disco, living room, ...<br/>"
				+ "Can changed every book.time.</html>");
		panel_placeType.add(btnPlaceTypeInfo, BorderLayout.EAST);
		
		txtPlaceType = new FormTextField("Place-Type:", "", false);
		panel_placeType.add(txtPlaceType, BorderLayout.CENTER);
		saveForm.addInput(txtPlaceType);
		
		TransparentPanel panel_notes = new TransparentPanel();
		panel_bodyForFurtherInformation.add(panel_notes);
		panel_notes.setLayout(new BorderLayout(0, 0));
		
		panel_notes.add(new SimpleLabel("Notes, Research, ...:"), BorderLayout.NORTH);
		
		txtNotes = new SimpleTextarea();
		panel_notes.add(txtNotes, BorderLayout.CENTER);
	}
	
	private void savePlace(){
		String name = txt_placeName.getText();

		if(!name.isEmpty()) {
			Place newPlace = new Place(name, txtPlaceType.getText(), txtNotes.getText());
			Book.getInstance().getWorld().addPlace(newPlace);
			if(!UserSettings.getInstance().getTutorial().createFirstPlace) {
				UserSettings.getInstance().getTutorial().createFirstPlace = true;
				UserSettings.getInstance().save();
			}
			BookEditorFrame.getInstance().openPlacePage(newPlace, false);
		}
	}	
}

