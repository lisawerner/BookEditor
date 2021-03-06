package GUI.settingsPage;

import java.awt.GridLayout;

import book.Book;
import GUI.components.SimpleCheckbox;
import GUI.components.TransparentPanel;

public class PrintSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private SimpleCheckbox rdbtnPrintChaptername;

	public PrintSettingsCard() {
		setLayout(new GridLayout(1, 0, 5, 5));
	
		rdbtnPrintChaptername = new SimpleCheckbox("Select this for printing Chapter Name as set in Table of Content (Otherwise chapter-name will print as number)");
		rdbtnPrintChaptername.setSelected(Book.getInstance().printChapterName());
		add(rdbtnPrintChaptername);
		rdbtnPrintChaptername.addActionListener(e -> Book.getInstance().changePrintSettings(rdbtnPrintChaptername.isSelected()));
		
		//TODO: Add Label which shows save/notsave or what ever happen, when rdbtn is clicked
	}

}
