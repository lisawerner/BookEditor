package GUI.settingsPage;

import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.SimpleTextField;
import GUI_components.TransparentPanel;
import book.Book;

import javax.swing.*;
import java.awt.*;

public class BookTitleSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final SimpleTextField txt_title;
	private final SimpleCheckbox rdbtnIsWorkTitle;
	private final SimpleLabel lblTitle;
	
	public BookTitleSettingsCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel panel_setTitle = new TransparentPanel();
		add(panel_setTitle);
		panel_setTitle.setLayout(new BorderLayout(5, 5));
		
		lblTitle = new SimpleLabel("Title:");
		panel_setTitle.add(lblTitle, BorderLayout.WEST);
		
		txt_title = new SimpleTextField(Book.getInstance().getTitle());
		panel_setTitle.add(txt_title, BorderLayout.CENTER);
		
		rdbtnIsWorkTitle = new SimpleCheckbox("This is only a work title. Please remember me changing it, before publishing book.");
		add(rdbtnIsWorkTitle);
		rdbtnIsWorkTitle.setSelected(Book.getInstance().isWorkTitle());
		
		//****************************************************************************************************************************************
		//****************************************************************************************************************************************
		TransparentPanel footer = new TransparentPanel();
		add(footer, BorderLayout.SOUTH);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblWarning = new SimpleLabel(" ");
		lblWarning.setWarning(true);
		footer.add(lblWarning);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(e -> {
			lblWarning.setText(" ");
			setWarningEnterName(false);
			boolean canSave = true;

			String bookTitle = txt_title.getText();
			boolean isWorkTitled = rdbtnIsWorkTitle.isSelected();

			if("".equals(bookTitle)) {
				lblWarning.setText("You have to enter an book title!");
				canSave = false;
				setWarningEnterName(true);
			}

			if(canSave) {
				Book.getInstance().changeBookTitleSettings(bookTitle, isWorkTitled);
			}
		});
		footer.add(btnSave);
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_title.setWarning(true);
			lblTitle.setWarning(true);
		} else {
			txt_title.setWarning(false);
			lblTitle.setWarning(false);
		}
	}

}
