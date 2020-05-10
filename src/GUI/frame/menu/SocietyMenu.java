package GUI.frame.menu;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.FrameSubmenu;
import GUI.components.MenuButton;
import GUI.components.MenuListButton;
import GUI.personPage.FilterPersonPage;
import GUI.personPage.ViewSocietyPage;
import GUI.race.Page_RaceSystem;
import book.Book;

import person.Person;

public class SocietyMenu extends FrameSubmenu {
	private static final long serialVersionUID = 1L;

	public SocietyMenu() {
		super("Society:", "List of Persons:");
		
		addButton(new MenuButton("View Society", e -> BookEditorFrame.getInstance().switchBody(new ViewSocietyPage())));
		if(Book.getInstance().getSociety().isRaceSystemActivated()){			
			addButton(new MenuButton("View Race-System", e -> BookEditorFrame.getInstance().switchBody(new Page_RaceSystem())));
		}
		addButton(new MenuButton("Filter Persons", e -> BookEditorFrame.getInstance().switchBody(new FilterPersonPage())));
		addButton(new MenuButton("Add new Person", e -> BookEditorFrame.getInstance().openPersonPage(null, false)));
		
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			addListEntry(new MenuListButton(person.getInformation().getNickname(), e -> BookEditorFrame.getInstance().openPersonPage(person, false)));
			
		}
	}
}
