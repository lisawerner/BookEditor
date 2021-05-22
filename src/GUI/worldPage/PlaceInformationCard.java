package GUI.worldPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.*;
import book.Book;
import global.UserSettings;
import world.Place;

import javax.swing.*;
import java.awt.*;

public class PlaceInformationCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	private final SimpleTextField txt_placeName;
	private final SimpleLabel lblPlaceName;
	private final SimpleTextField txtPlaceType;
	private final SimpleTextarea txtNotes;

	public PlaceInformationCard(Place place) {
		my_place = place;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel panel_title = new TransparentPanel();
		panel_title.setLayout(new BorderLayout(5, 5));
		add(panel_title);
		
		lblPlaceName = new SimpleLabel("Place-Name:");
		panel_title.add(lblPlaceName, BorderLayout.WEST);
		
		txt_placeName = new SimpleTextField();
		if(my_place != null) {			
			txt_placeName.setText(my_place.getName());
		}
		panel_title.add(txt_placeName, BorderLayout.CENTER);

		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel panel_placeType = new TransparentPanel();
		add(panel_placeType);
		panel_placeType.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblPlaceType = new SimpleLabel("Place-Type:");
		panel_placeType.add(lblPlaceType, BorderLayout.NORTH);
		
		InfoButton btnPlaceTypeInfo = new InfoButton("<html>Enter something like city, state, village, parc, house, disco, ...<br/>"
				+ "Can changed every time.</html>");
		panel_placeType.add(btnPlaceTypeInfo, BorderLayout.EAST);
		
		txtPlaceType = new SimpleTextField();
		panel_placeType.add(txtPlaceType, BorderLayout.CENTER);
		if(my_place != null) {			
			txtPlaceType.setText(my_place.getType());
		}
		
		TransparentPanel panel_notes = new TransparentPanel();
		add(panel_notes);
		panel_notes.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblNotesResearch = new SimpleLabel("Notes, Research, ...:");
		panel_notes.add(lblNotesResearch, BorderLayout.NORTH);
		
		txtNotes = new SimpleTextarea();
		panel_notes.add(txtNotes, BorderLayout.CENTER);
		if(my_place != null) {txtNotes.setText(my_place.getNotes());}
		
		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel footer = new TransparentPanel();
		add(footer);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblWarning = new SimpleLabel(" ");
		lblWarning.setWarning(true);
		footer.add(lblWarning, BorderLayout.SOUTH);		

		JButton btnSave = new JButton("Save Place");
		btnSave.addActionListener(e -> {
			lblWarning.setText(" ");
			boolean canSave = true;

			setWarningEnterName(false);
			String name = txt_placeName.getText();
			if(name.equals("")) {
				lblWarning.setText("WARNING: Can not save place, because no name was entered!");
				setWarningEnterName(true);
				canSave = false;
			}

			if(canSave) {
				if(my_place == null) {
					my_place = new Place(name, txtPlaceType.getText(), txtNotes.getText());
					Book.getInstance().getWorld().addPlace(my_place);
					if(!UserSettings.getInstance().getTutorial().createFirstPlace) {
						UserSettings.getInstance().getTutorial().createFirstPlace = true;
						UserSettings.getInstance().save();
					}
					BookEditorFrame.getInstance().switchBody(new PlaceEditor(my_place, false));
				} else {
					my_place.editPlace(name, txtPlaceType.getText(), txtNotes.getText());
					BookEditorFrame.getInstance().switchBody(new PlaceEditor(my_place, false));
				}
			}
		});
		footer.add(btnSave, BorderLayout.SOUTH);
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_placeName.setWarning(true);
			lblPlaceName.setWarning(true);
		} else {
			txt_placeName.setWarning(false);
			lblPlaceName.setWarning(false);
		}
	}

}
