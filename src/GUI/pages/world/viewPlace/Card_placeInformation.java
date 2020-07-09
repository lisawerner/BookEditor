package GUI.pages.world.viewPlace;

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
import world.Place;
import javax.swing.Box;
import java.awt.Dimension;

public class Card_placeInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	private FormTextfield txt_placename;
	private FormTextfield txtPlacetype;
	private SimpleTextarea txtNotes;

	public Card_placeInformation(Place place) {
		my_place = place;
		setLayout(new BorderLayout(5, 5));
		
		FormButton btnSave = new FormButton("Save Place", e -> savePlace());
		add(btnSave, BorderLayout.SOUTH);
		
		SimpleFormular saveFormular = new SimpleFormular(this::savePlace, btnSave);

		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel panel_bodyForFurtherInfomations = new TransparentPanel();
		add(panel_bodyForFurtherInfomations, BorderLayout.CENTER);
		panel_bodyForFurtherInfomations.setLayout(new BoxLayout(panel_bodyForFurtherInfomations, BoxLayout.PAGE_AXIS));
				
		txt_placename = new FormTextfield("Placename:", my_place.getName(), true);
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
		
		txtPlacetype = new FormTextfield("Place-Type:", my_place.getType(), false);
		panel_placetype.add(txtPlacetype, BorderLayout.CENTER);
		saveFormular.addInput(txtPlacetype);
		
		TransparentPanel panel_notes = new TransparentPanel();
		panel_bodyForFurtherInfomations.add(panel_notes);
		panel_notes.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblNotesResearch = new SimpleLabel("Notes, Research, ...:");
		panel_notes.add(lblNotesResearch, BorderLayout.NORTH);
		
		txtNotes = new SimpleTextarea(my_place.getNotes());
		panel_notes.add(txtNotes, BorderLayout.CENTER);
	}
	
	private void savePlace(){
		String name = txt_placename.getText();
		if(!name.isEmpty()) {
			my_place.editPlace(name, txtPlacetype.getText(), txtNotes.getText());
			BookEditorFrame.getInstance().openPlacePage(my_place, false);
		}
	}
}
