package GUI.worldPage;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.PageMenu;
import book.Book;
import world.Place;

public class PlaceMenu extends PageMenu {
	private static final long serialVersionUID = 1L;

	public PlaceMenu() {
		super("World Menu");
		
		
		JButton btnViewMap = this.addButtonToTopMenu("View Map");
		btnViewMap.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new ViewWorldmapPage()));

		JButton btnAddPlace = this.addButtonToTopMenu("Add Place");
		btnAddPlace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(null)));
		
		
		//*****************************************************************************
		
		this.addBetweenTitle("List of Places");
		for(Place place : Book.getInstance().getWorld().getPlaces()) {
			JButton btnPlace = this.addLinkedListButton(place.getName());
			btnPlace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(place)));
		}
	}
}
