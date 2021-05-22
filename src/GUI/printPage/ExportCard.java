package GUI.printPage;

import java.awt.GridLayout;

import javax.swing.JButton;

import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import global.FileManager;

public class ExportCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final SimpleLabel lblExportHint;

	public ExportCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		lblExportHint = new SimpleLabel(" ");
		add(lblExportHint);
		//TODO: Extend label with button "Open Folder"
		
		JButton btnExportTXT = new JButton("Export Book as .txt");
		//TODO: Let choose a storage location???
		btnExportTXT.addActionListener(e -> export());
		add(btnExportTXT);
		//TODO: Are there more export possibilities?
		// Export as ONE TXT File (only Text, not Notes, Persons, Places, ...)?
		// Export as Multi TXT files (One with the Notes for chapter or for section???)?
		// Export to other format(but which??)
		
	}
	
	private void export() {
		boolean exportSuccessful = Book.getInstance().exportToTXT();
		if(exportSuccessful) {
			lblExportHint.setText("File '" + Book.getInstance().getTitle() + ".txt' was successfully saved at path: " + FileManager.getSavingPath());
		}
	}

}
