package GUI.worldPage;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.MenuPage;
import GUI_components.Submenu;
import book.Book;
import world.Place;

public class PlaceMenu extends MenuPage {
	private static final long serialVersionUID = 1L;

	public PlaceMenu() {
		super("List of Places:");
		
		//*****************************************************************************
		Submenu submenu_viewWholeMap = new Submenu("");
		addSubmenu(submenu_viewWholeMap);
		
		JButton btnViewMap = submenu_viewWholeMap.addButton("View Map");
		btnViewMap.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new WorldEditor()));
		
		JButton btnAddPlace = submenu_viewWholeMap.addButton("Add Place");
		btnAddPlace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(null)));
		//*****************************************************************************
		Submenu submenu_listOfPlaces = new Submenu("");
		addSubmenu(submenu_listOfPlaces);
		submenu_listOfPlaces.activateList();
		for(Place place : Book.getInstance().getPlaces()) {
			JButton btnPlace = submenu_listOfPlaces.addListButton(place.getName());
			btnPlace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(place)));
		}

	}

}
