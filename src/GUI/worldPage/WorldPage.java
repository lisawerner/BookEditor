package GUI.worldPage;

import GUI_components.MenuPage;
import GUI_components.Page;
import GUI_components.Submenu;
import GUI_components.TransparentPanel;
import book.Book;
import world.Place;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

public class WorldPage extends Page {
	private static final long serialVersionUID = 1L;

	public WorldPage(Place openPlace) {
		super("World, States, Regions, Citys, Places, ...");
	
		TransparentPanel panel_placeholder = new TransparentPanel();
		panel_placeholder.setLayout(new BorderLayout(0, 0));
		add(panel_placeholder, BorderLayout.CENTER);
		JLabel lblOpenAnExisting = new JLabel("<html><div style='text-align: center;'><font size=\"5\">Open an existing place or add a new one.<br/>"
				+ "You can also open whole world &#x27A1;</size></div></html>");
		lblOpenAnExisting.setHorizontalTextPosition(JLabel.RIGHT);
		lblOpenAnExisting.setVerticalTextPosition(JLabel.CENTER);
		panel_placeholder.add(lblOpenAnExisting, BorderLayout.CENTER);
		
		
		//*****************************************************************************
		//*****************************************************************************
		MenuPage panel_placeList = new MenuPage("List of Places:");
		add(panel_placeList, BorderLayout.EAST);
		//*****************************************************************************
		Submenu submenu_viewWholeMap = new Submenu("");
		panel_placeList.addSubmenu(submenu_viewWholeMap);
		
		JButton btnViewMap = submenu_viewWholeMap.addButton("View Map");
		btnViewMap.addActionListener(e -> changeBody(new WorldEditor()));
		
		JButton btnAddPlace = submenu_viewWholeMap.addButton("Add Place");
		btnAddPlace.addActionListener(e -> changeBody(new PlaceEditor(null)));
		//*****************************************************************************
		Submenu submenu_listOfPlaces = new Submenu("");
		panel_placeList.addSubmenu(submenu_listOfPlaces);
		submenu_listOfPlaces.activateList();
		for(Place place : Book.getInstance().getPlaces()) {
			JButton btnPlace = submenu_listOfPlaces.addListButton(place.getName());
			btnPlace.addActionListener(e -> changeBody(new PlaceEditor(place)));
		}
		

		//*****************************************************************************
		//*****************************************************************************
		if(openPlace != null) {
			changeBody(new PlaceEditor(openPlace));
		}
	}

}
