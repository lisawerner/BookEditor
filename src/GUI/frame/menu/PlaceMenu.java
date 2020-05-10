package GUI.frame.menu;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.FrameSubmenu;
import GUI.components.MenuButton;
import GUI.components.MenuListButton;
import book.Book;
import world.Place;

public class PlaceMenu extends FrameSubmenu {
	private static final long serialVersionUID = 1L;

	public PlaceMenu() {
		super("World:", "List of Places:");

		addButton(new MenuButton("View Map", e -> BookEditorFrame.getInstance().openWorldPage()));
		addButton(new MenuButton("Add Place", e -> BookEditorFrame.getInstance().openPlacePage(null, false)));
				
		for(Place place : Book.getInstance().getWorld().getPlaces()) {
			addListEntry(new MenuListButton(place.getName(), e -> BookEditorFrame.getInstance().openPlacePage(place, false)));
		}
				
	}
}
