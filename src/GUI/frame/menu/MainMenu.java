package GUI.frame.menu;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.MenuButton;
import GUI.components.TransparentPanel;

import java.awt.GridLayout;

public class MainMenu extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public MainMenu() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		add(new MenuButton("Home", e -> BookEditorFrame.getInstance().openStartPage()));
		
		add(new MenuButton("Table of Content", e -> BookEditorFrame.getInstance().openTableOfContentPage()));
		
		add(new MenuButton("Society", e -> BookEditorFrame.getInstance().openSocietyPage()));
		
		add(new MenuButton("World", e -> BookEditorFrame.getInstance().openWorldPage()));
		
		add(new MenuButton("Timeline", e -> BookEditorFrame.getInstance().openTimelinePage()));
		
		add(new MenuButton("Notes", e -> BookEditorFrame.getInstance().openNotesListPage()));
		
		add(new MenuButton("Book Settings", e -> BookEditorFrame.getInstance().openSettingsPage()));
		
		add(new MenuButton("Print & Export", e -> BookEditorFrame.getInstance().openPrintPage()));
		
	}

}
