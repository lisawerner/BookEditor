package GUI.worldPage;

import GUI_components.Page;
import GUI_components.SimpleLabel;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;

public class WorldPage extends Page {
	private static final long serialVersionUID = 1L;

	public WorldPage() {
		super("World, States, Regions, Citys, Places, ...");
	
		TransparentPanel panel_placeholder = new TransparentPanel();
		SimpleLabel lblOpenAnExisting = new SimpleLabel("<html><div style='text-align: center;'><font size=\"5\">Open an existing place or add a new one.<br/>"
				+ "You can also open whole world &#x27A1;</size></div></html>");
		lblOpenAnExisting.setHorizontalTextPosition(SimpleLabel.RIGHT);
		lblOpenAnExisting.setVerticalTextPosition(SimpleLabel.CENTER);
		panel_placeholder.add(lblOpenAnExisting);
		this.addCard(new StructureCard("", panel_placeholder));
		
		setMenu(new PlaceMenu());
	}

}
