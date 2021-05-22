package GUI.printPage;

import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import book.Book;
import global.FileManager;

import javax.swing.*;
import java.awt.*;

public class ExportCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final SimpleLabel lblExportHint;

	public ExportCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		lblExportHint = new SimpleLabel(" ");
		add(lblExportHint);
		//TODO: Extend the label with a Button for "Open Folder"
		
		JButton btnExportTXT = new JButton("Export Book as .txt");
		//TODO: Let the user chose the storage location???
		btnExportTXT.addActionListener(e -> export());
		add(btnExportTXT);
		//TODO: Save also as something else?
		// Export as ONE TXT File (only Text, not Notes, Persons, Places, ...)?
		// Export as Multi TXT files (One with the Notes for chapter or for section???)? export to other format(but which??)
		
	}
	
	private void export() {
		boolean exportSuccessful = Book.getInstance().exportToTXT();
		if(exportSuccessful) {
			lblExportHint.setText("File '" + Book.getInstance().getTitle() + ".txt' was successfully saved at path: " + FileManager.getSavingPath());
		}
	}

}
