package GUI.pages.world.createPlace;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.FormButton;
import GUI.components.FormTextfield;
import GUI.components.InfoButton;
import GUI.components.SimpleFormular;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextarea;
import GUI.components.TransparentPanel;
import book.Book;
import global.UserSettings;
import world.Place;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;

public class Card_createPlace extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private FormTextfield txt_placename;
	private FormTextfield txtPlacetype;
	private SimpleTextarea txtNotes;
	
	public Card_createPlace() {
		setLayout(new BorderLayout(5, 5));
		
		FormButton btnSave = new FormButton("Save Place", e -> savePlace());
		add(btnSave, BorderLayout.SOUTH);
		SimpleFormular saveFormular = new SimpleFormular(this::savePlace, btnSave);

		TransparentPanel panel_bodyForFurtherInfomations = new TransparentPanel();
		add(panel_bodyForFurtherInfomations, BorderLayout.CENTER);
		panel_bodyForFurtherInfomations.setLayout(new BoxLayout(panel_bodyForFurtherInfomations, BoxLayout.PAGE_AXIS));
				
		//TODO: If adding text to notes and type saving is disabled, but user does not get any feedback, why it is disabled until he enters something into name text field... TODO: add feedback
		txt_placename = new FormTextfield("Placename:", "", true);
		panel_bodyForFurtherInfomations.add(txt_placename);
		saveFormular.addInput(txt_placename);
		
		txt_placename.add(Box.createRigidArea(new Dimension(11, 11)), BorderLayout.EAST);
		
		panel_bodyForFurtherInfomations.add(Box.createRigidArea(new Dimension(5, 5)));
		
		TransparentPanel panel_placetype = new TransparentPanel();
		panel_bodyForFurtherInfomations.add(panel_placetype);
		panel_placetype.setLayout(new BorderLayout(0, 0));
		
		InfoButton btnPlaceTypeInfo = new InfoButton("<html>Enter something like city, state, village, parc, house, disco, living room, ...<br/>"
				+ "Can changed every time.</html>");
		panel_placetype.add(btnPlaceTypeInfo, BorderLayout.EAST);
		
		txtPlacetype = new FormTextfield("Place-Type:", "", false);
		panel_placetype.add(txtPlacetype, BorderLayout.CENTER);
		saveFormular.addInput(txtPlacetype);
		
		TransparentPanel panel_notes = new TransparentPanel();
		panel_bodyForFurtherInfomations.add(panel_notes);
		panel_notes.setLayout(new BorderLayout(0, 0));
		
		panel_notes.add(new SimpleLabel("Notes, Research, ...:"), BorderLayout.NORTH);
		
		txtNotes = new SimpleTextarea();
		panel_notes.add(txtNotes, BorderLayout.CENTER);
	}
	
	private void savePlace(){
		String name = txt_placename.getText();				

		if(!name.isEmpty()) {
			Place newPlace = new Place(name, txtPlacetype.getText(), txtNotes.getText());
			Book.getInstance().getWorld().addPlace(newPlace);
			if(!UserSettings.getInstance().getTutorial().createFirstPlace) {
				UserSettings.getInstance().getTutorial().createFirstPlace = true;
				UserSettings.getInstance().save();
			}
			BookEditorFrame.getInstance().openPlacePage(newPlace, false);
		}
	}	
}

