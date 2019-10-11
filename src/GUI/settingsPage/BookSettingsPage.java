package GUI.settingsPage;

import java.awt.Color;

import javax.swing.JLabel;

import GUI.bookeditorFrame.BookEditorFrame;

import javax.swing.JButton;
import java.awt.GridLayout;

import GUI_components.Page;
import GUI_components.Theme;
import GUI_components.ThemeList;
import GUI_components.TransparentPanel;
import book.Book;
import global.UserSettings;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookSettingsPage extends Page {
	private static final long serialVersionUID = 1L;

	


	public BookSettingsPage() {
		super("Book Settings");
				
		SettingsStandardBody my_body = new SettingsStandardBody();
		this.changeBody(my_body);
		
		TransparentPanel footer = new TransparentPanel();
		my_body.setFooter(footer);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		JLabel lblWarning = new JLabel(" ");
		lblWarning.setForeground(Color.RED);
		footer.add(lblWarning);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblWarning.setText(" ");
//				setWarningEnterName(false);
				boolean canSave = true;
				
				String booktitle = my_body.getBookTitle();
				boolean isGregorian = my_body.isGregorianIsSelected();
				boolean isWorkTitled = my_body.isWorkTitle();
				boolean printChaptername = my_body.printChaptername();
				Theme theme = my_body.getSelectedTheme();
				
				if("".equals(booktitle)) {
					lblWarning.setText("You have to enter an booktitle!");
					canSave = false;
//					setWarningEnterName(true);
				}
							
				if(canSave) {
					Book.getInstance().changeBookSettings(booktitle, isWorkTitled, isGregorian, printChaptername, theme);
					if(!UserSettings.getInstance().getTutorial().chooseFirstColorTheme) {						
						if(theme != null) {
							UserSettings.getInstance().getTutorial().chooseFirstColorTheme = true;
							UserSettings.getInstance().save();
						}
					}
					BookEditorFrame.getInstance().updateBookTitle();
					ThemeList.setCurrentTheme(theme);
					BookEditorFrame.getInstance().repaintFrame();
					BookEditorFrame.getInstance().switchBody(new BookSettingsPage());
				}
			}
		});
		footer.add(btnSave);

	}
	
//	private void setWarningEnterName(boolean warning) {
//		if(warning) {
//			txt_title.setBorder(BorderFactory.createLineBorder(Color.RED));
//			lblTitle.setForeground(Color.RED);
//		} else {
//			txt_title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//			lblTitle.setForeground(Color.BLACK);
//		}
//	}

}
