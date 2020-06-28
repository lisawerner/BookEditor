package GUI.pages.world.viewPlace;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.ComplexeTextfieldSaveable;
import GUI.components.InfoButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextarea;
import GUI.components.TransparentPanel;
import world.Place;

public class Card_placeInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	private ComplexeTextfieldSaveable txt_placename;
	private ComplexeTextfieldSaveable txtPlacetype;
	private SimpleTextarea txtNotes;

	public Card_placeInformation(Place place) {
		my_place = place;
		setLayout(new BorderLayout(5, 5));

		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel panel_bodyForFurtherInfomations = new TransparentPanel();
		add(panel_bodyForFurtherInfomations, BorderLayout.CENTER);
		panel_bodyForFurtherInfomations.setLayout(new BoxLayout(panel_bodyForFurtherInfomations, BoxLayout.PAGE_AXIS));
				
		txt_placename = new ComplexeTextfieldSaveable("Placename:", my_place.getName(), this::savePlace, false, true);
		panel_bodyForFurtherInfomations.add(txt_placename);
		
		TransparentPanel panel_placetype = new TransparentPanel();
		panel_bodyForFurtherInfomations.add(panel_placetype);
		panel_placetype.setLayout(new BorderLayout(0, 0));
		
		InfoButton btnPlaceTypeInfo = new InfoButton("<html>Enter something like city, state, village, parc, house, disco, living room, ...<br/>"
				+ "Can changed every time.</html>");
		panel_placetype.add(btnPlaceTypeInfo, BorderLayout.EAST);
		
		txtPlacetype = new ComplexeTextfieldSaveable("Place-Type:", my_place.getType(), this::savePlace, false, false);
		panel_placetype.add(txtPlacetype, BorderLayout.CENTER);
		
		TransparentPanel panel_notes = new TransparentPanel();
		panel_bodyForFurtherInfomations.add(panel_notes);
		panel_notes.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblNotesResearch = new SimpleLabel("Notes, Research, ...:");
		panel_notes.add(lblNotesResearch, BorderLayout.NORTH);
		
		txtNotes = new SimpleTextarea(my_place.getNotes());
		panel_notes.add(txtNotes, BorderLayout.CENTER);
		
		//*****************************************************************************************
		//*****************************************************************************************
		JButton btnSave = new JButton("Save Place");
		btnSave.addActionListener(e -> savePlace());
		add(btnSave, BorderLayout.SOUTH);
	}
	
	private void savePlace(){
		String name = txt_placename.getText();
		if(!name.isEmpty()) {
			my_place.editPlace(name, txtPlacetype.getText(), txtNotes.getText());
			BookEditorFrame.getInstance().openPlacePage(my_place, false);
		}
	}
}
