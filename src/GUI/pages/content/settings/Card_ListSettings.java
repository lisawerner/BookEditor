package GUI.pages.content.settings;

import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.Book;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;

public class Card_ListSettings extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ListSettings() {
		setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblShowAdditionalInformation = new SimpleLabel("Show additional information for sections in list views:");
		add(lblShowAdditionalInformation, BorderLayout.NORTH);
		
		TransparentPanel panel_possibleSettings = new TransparentPanel();
		add(panel_possibleSettings, BorderLayout.CENTER);
		panel_possibleSettings.setLayout(new BoxLayout(panel_possibleSettings, BoxLayout.PAGE_AXIS));
		
		SimpleCheckbox chckbxShowCountOf = new SimpleCheckbox("Show count of words");
		chckbxShowCountOf.setSelected(Book.getInstance().showWordCountInSectionLists());
		chckbxShowCountOf.addActionListener(e -> Book.getInstance().setShowWordCountInSectionLists(chckbxShowCountOf.isSelected()));
		panel_possibleSettings.add(chckbxShowCountOf);
		
		SimpleCheckbox chckbxShowDevelopmentStatus = new SimpleCheckbox("Show development status");
		chckbxShowDevelopmentStatus.setSelected(Book.getInstance().showDevStatusinSectionLists());
		chckbxShowDevelopmentStatus.addActionListener(e -> Book.getInstance().setShowDevStatusinSectionLists(chckbxShowDevelopmentStatus.isSelected()));
		panel_possibleSettings.add(chckbxShowDevelopmentStatus);

	}

}
