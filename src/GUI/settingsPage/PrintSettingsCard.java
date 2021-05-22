package GUI.settingsPage;

import java.awt.GridLayout;

import GUI_components.SimpleCheckbox;
import GUI_components.TransparentPanel;
import book.Book;

public class PrintSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final SimpleCheckbox rdbtnPrintChapterName;

	public PrintSettingsCard() {
		setLayout(new GridLayout(1, 0, 5, 5));
	
		rdbtnPrintChapterName = new SimpleCheckbox("Select this for printing Chapter Name as set in Table of Content (Otherwise chapter-name will print as number)");
		rdbtnPrintChapterName.setSelected(Book.getInstance().printChapterName());
		add(rdbtnPrintChapterName);
		rdbtnPrintChapterName.addActionListener(e -> Book.getInstance().changePrintSettings(rdbtnPrintChapterName.isSelected()));
		
		//TODO: Add Label which shows save/not save or what ever happen, when rdbtn is clicked
	}

}
