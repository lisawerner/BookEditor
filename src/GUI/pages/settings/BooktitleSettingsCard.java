package GUI.pages.settings;

import book.Book;

import java.awt.GridLayout;

import GUI.components.ComplexeTextfieldSaveable;
import GUI.components.SimpleCheckbox;
import GUI.components.TransparentPanel;

public class BooktitleSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private ComplexeTextfieldSaveable txt_title;
	private SimpleCheckbox rdbtnIsWorkTitle;
	
	public BooktitleSettingsCard() {
		setLayout(new GridLayout(0, 1, 5, 5));

		txt_title = new ComplexeTextfieldSaveable("Title:", Book.getInstance().getTitle(), this::saveTitle, true, true);
		add(txt_title);
		
		rdbtnIsWorkTitle = new SimpleCheckbox("This is only a worktitle. Please remember me changing it, before publishing book.");
		add(rdbtnIsWorkTitle);
		rdbtnIsWorkTitle.setSelected(Book.getInstance().isWorkTitle());
		rdbtnIsWorkTitle.addActionListener(e -> Book.getInstance().setIsWorkTitle(rdbtnIsWorkTitle.isSelected()));
	}
	
	private void saveTitle(){
		String booktitle = txt_title.getText();		
		if(!booktitle.isEmpty()) {
			Book.getInstance().setTitle(booktitle);
		}
	}
}
