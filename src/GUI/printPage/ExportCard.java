package GUI.printPage;

import java.awt.GridLayout;

import javax.swing.JButton;

import book.Book;
import global.FileManager;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;

public class ExportCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private SimpleLabel lblExportHint;

	public ExportCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		lblExportHint = new SimpleLabel(" ");
		add(lblExportHint);
		//TODO: Label um Button erweitern "Open Folder"
		
		JButton btnExportTXT = new JButton("Export Book as .txt");
		//TODO: Speicherort auswählen lassen???
		btnExportTXT.addActionListener(e -> export());
		add(btnExportTXT);
		//TODO: Als was noch exportieren? Export as ONE TXT File (only Text, not Notes, Persons, Places, ...)? Export as Multi TXT files (One with the Notes for chapter or for section???)? export to other format(but which??)
		
	}
	
	private void export() {
		boolean exportSuccessfull = Book.getInstance().exportToTXT();
		if(exportSuccessfull) {
			lblExportHint.setText("File '" + Book.getInstance().getTitle() + ".txt' was successfully saved at path: " + FileManager.getSavingPath());
		}
	}

}
