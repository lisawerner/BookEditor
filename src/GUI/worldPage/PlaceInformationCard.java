package GUI.worldPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import global.UserSettings;
import GUI.components.InfoButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextarea;
import GUI.components.SimpleTextfield;
import GUI.components.TransparentPanel;
import world.Place;

public class PlaceInformationCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	private SimpleTextfield txt_placename;
	private SimpleLabel lblPlacename;
	private SimpleTextfield txtPlacetype;
	private SimpleTextarea txtNotes;

	public PlaceInformationCard(Place place) {
		my_place = place;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel panel_title = new TransparentPanel();
		panel_title.setLayout(new BorderLayout(5, 5));
		add(panel_title);
		
		lblPlacename = new SimpleLabel("Placename:");
		panel_title.add(lblPlacename, BorderLayout.WEST);
		
		txt_placename = new SimpleTextfield();
		if(my_place != null) {			
			txt_placename.setText(my_place.getName());
		}
		panel_title.add(txt_placename, BorderLayout.CENTER);

		//*****************************************************************************************
		//*****************************************************************************************
		TransparentPanel panel_placetype = new TransparentPanel();
		add(panel_placetype);
		panel_placetype.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblPlacetype = new SimpleLabel("Place-Type:");
		panel_placetype.add(lblPlacetype, BorderLayout.NORTH);
		
		InfoButton btnPlaceTypeInfo = new InfoButton("<html>Enter something like city, state, village, parc, house, disco, living room, ...<br/>"
				+ "Can changed every time.</html>");
		panel_placetype.add(btnPlaceTypeInfo, BorderLayout.EAST);
		
		txtPlacetype = new SimpleTextfield();
		panel_placetype.add(txtPlacetype, BorderLayout.CENTER);
		if(my_place != null) {			
			txtPlacetype.setText(my_place.getType());
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
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblWarning.setText(" ");
				boolean canSave = true;
				
				setWarningEnterName(false);
				String name = txt_placename.getText();				
				if(name.equals("")) {
					lblWarning.setText("WARNING: Can not save place, because no name was entered!");
					setWarningEnterName(true);
					canSave = false;
				}
							
				if(canSave) {
					if(my_place == null) {
						my_place = new Place(name, txtPlacetype.getText(), txtNotes.getText());
						Book.getInstance().getWorld().addPlace(my_place);
						if(!UserSettings.getInstance().getTutorial().createFirstPlace) {
							UserSettings.getInstance().getTutorial().createFirstPlace = true;
							UserSettings.getInstance().save();
						}
					} else {
						my_place.editPlace(name, txtPlacetype.getText(), txtNotes.getText());
					}
					BookEditorFrame.getInstance().openPlacePage(my_place, false);
				}
			}
		});
		footer.add(btnSave, BorderLayout.SOUTH);
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_placename.setWarning(true);
			lblPlacename.setWarning(true);
		} else {
			txt_placename.setWarning(false);
			lblPlacename.setWarning(false);
		}
	}

}
