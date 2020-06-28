package GUI.pages.content.settings;

import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.Book;
import javax.swing.BoxLayout;

public class Card_ListSettings extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ListSettings() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(new SimpleLabel("Show additional information for sections in list views:"));

		SimpleCheckbox chckbxShowCountOfWords = new SimpleCheckbox("Show count of words");
		chckbxShowCountOfWords.setSelected(Book.getInstance().showWordCountInSectionLists());
		chckbxShowCountOfWords.addActionListener(e -> Book.getInstance().setShowWordCountInSectionLists(chckbxShowCountOfWords.isSelected()));
		add(chckbxShowCountOfWords);
		
		SimpleCheckbox chckbxShowDevelopmentStatus = new SimpleCheckbox("Show development status");
		chckbxShowDevelopmentStatus.setSelected(Book.getInstance().showDevStatusinSectionLists());
		chckbxShowDevelopmentStatus.addActionListener(e -> Book.getInstance().setShowDevStatusinSectionLists(chckbxShowDevelopmentStatus.isSelected()));
		add(chckbxShowDevelopmentStatus);

		add(new SimpleLabel("Show additional information for chapters in list views:"));
		
		SimpleCheckbox chckbxShowSumWords = new SimpleCheckbox("Show summary of words");
		chckbxShowSumWords.setSelected(Book.getInstance().showWordSumInChapterLists());
		chckbxShowSumWords.addActionListener(e -> Book.getInstance().setShowWordSumInChapterLists(chckbxShowSumWords.isSelected()));
		add(chckbxShowSumWords);
		
	}

}
