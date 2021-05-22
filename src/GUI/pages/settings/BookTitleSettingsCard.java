package GUI.pages.settings;

import GUI.components.ComplexTextFieldSavable;
import GUI.components.SimpleCheckbox;
import GUI.components.TransparentPanel;
import book.Book;

import java.awt.*;

public class BookTitleSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final ComplexTextFieldSavable txt_title;
	private final SimpleCheckbox rdbtnIsWorkTitle;
	
	public BookTitleSettingsCard() {
		setLayout(new GridLayout(0, 1, 5, 5));

		txt_title = new ComplexTextFieldSavable("Title:", Book.getInstance().getTitle(), this::saveTitle);
		add(txt_title);
		
		rdbtnIsWorkTitle = new SimpleCheckbox("This is only a work title. Please remember me changing it, before publishing book.");
		add(rdbtnIsWorkTitle);
		rdbtnIsWorkTitle.setSelected(Book.getInstance().isWorkTitle());
		rdbtnIsWorkTitle.addActionListener(e -> Book.getInstance().setIsWorkTitle(rdbtnIsWorkTitle.isSelected()));
	}
	
	private void saveTitle(){
		String bookTitle = txt_title.getText();
		if(!bookTitle.isEmpty()) {
			Book.getInstance().setTitle(bookTitle);
		}
	}
}
