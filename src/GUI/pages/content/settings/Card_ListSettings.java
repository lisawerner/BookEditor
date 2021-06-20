package GUI.pages.content.settings;

import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.content.Book;

import javax.swing.*;

public class Card_ListSettings extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ListSettings() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(new SimpleLabel("Show additional information for sections in list views:"));

		SimpleCheckbox checkboxShowCountOfWords = new SimpleCheckbox("Show count of words");
		checkboxShowCountOfWords.setSelected(Book.getInstance().getTableOfContent().showWordCountInSectionLists());
		checkboxShowCountOfWords.addActionListener(e -> Book.getInstance().getTableOfContent().setShowWordCountInSectionLists(checkboxShowCountOfWords.isSelected()));
		add(checkboxShowCountOfWords);
		
		SimpleCheckbox checkboxShowDevelopmentStatus = new SimpleCheckbox("Show development status");
		checkboxShowDevelopmentStatus.setSelected(Book.getInstance().getTableOfContent().showDevStatusInSectionLists());
		checkboxShowDevelopmentStatus.addActionListener(e -> Book.getInstance().getTableOfContent().setShowDevStatusInSectionLists(checkboxShowDevelopmentStatus.isSelected()));
		add(checkboxShowDevelopmentStatus);

		add(new SimpleLabel("Show additional information for chapters in list views:"));
		
		SimpleCheckbox checkboxShowSumWords = new SimpleCheckbox("Show summary of words");
		checkboxShowSumWords.setSelected(Book.getInstance().getTableOfContent().showWordSumInChapterLists());
		checkboxShowSumWords.addActionListener(e -> Book.getInstance().getTableOfContent().setShowWordSumInChapterLists(checkboxShowSumWords.isSelected()));
		add(checkboxShowSumWords);
		
	}

}
