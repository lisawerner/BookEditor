package GUI.personPage;

import GUI_components.Page;
import GUI_components.SimpleLabel;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;

import javax.swing.JLabel;

public class PersonsEmptyPage extends Page {
	private static final long serialVersionUID = 1L;

	public PersonsEmptyPage() {
		super("Persons, Relationships, ...");
		
		TransparentPanel panel_placeholder = new TransparentPanel();
		SimpleLabel lblOpenAnExisting = new SimpleLabel("<html><div style='text-align: center;'><font size=\"5\">Open an existing person<br/>"
				+ "or add a new one &#x27A1;</size></div></html>");
		lblOpenAnExisting.setHorizontalTextPosition(JLabel.RIGHT);
		lblOpenAnExisting.setVerticalTextPosition(JLabel.CENTER);
		panel_placeholder.add(lblOpenAnExisting);
		this.addCard(new StructureCard(" ", panel_placeholder));
		
		setMenu(new PersonMenu());	
	}
	
}
