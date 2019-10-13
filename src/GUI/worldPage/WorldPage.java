package GUI.worldPage;

import GUI_components.Page;
import GUI_components.TransparentPanel;
import world.Place;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import GUI.bookeditorFrame.BookEditorFrame;

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
		setMenu(new PlaceMenu());
		

		//*****************************************************************************
		//*****************************************************************************
		if(openPlace != null) {
			BookEditorFrame.getInstance().switchBody(new PlaceEditor(openPlace));
		}
	}

}
