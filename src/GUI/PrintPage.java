package GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;

import GUI_components.Page;
import GUI_components.SimpleLabel;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import book.Book;
import global.FileManager;

public class PrintPage extends Page {
	private static final long serialVersionUID = 1L;
	private SimpleLabel lblExportHint;
	
	public PrintPage() {
		super("Print and Export");
		
		StructureCard card_printHints = new StructureCard("Print Hints");
		addCard(card_printHints);
		TransparentPanel panel_hint = new TransparentPanel();
		card_printHints.setBody(panel_hint);
		panel_hint.setLayout(new GridLayout(0, 1, 5, 5));
		SimpleLabel lblChapterSettings = new SimpleLabel("You can change all Settings in 'Book-Settings' in the left navigation menu");
		panel_hint.add(lblChapterSettings);
		//TODO: LinkButton hier positionieren (wozu? zum Book-Settigns??? Weis nicht mehr, was der Kommentar sollte ^^""") 
		SimpleLabel lblBookTitle = new SimpleLabel("Printed Title of the Book: " + Book.getInstance().getTitle());
		panel_hint.add(lblBookTitle);
		SimpleLabel lblTitleSettings = new SimpleLabel("Warning: Title is not set yet! It is only a work title!");
		if(Book.getInstance().isWorkTitle()) {panel_hint.add(lblTitleSettings);}
		lblTitleSettings.setForeground(Color.RED);
		SimpleLabel lblChapter = new SimpleLabel("Chapters will printed with numbers as chapter-title");
		if(Book.getInstance().printChapterName()) {lblChapter.setText("Chapters will printed with their names you add for Table of Content");}
		panel_hint.add(lblChapter);
		SimpleLabel lblSectionless = new SimpleLabel("Warning: You have no Sections and no Text!");
		lblSectionless.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getSections().size() == 0) {panel_hint.add(lblSectionless);}
		SimpleLabel lblSectionUnfinished = new SimpleLabel("Warning: You have Sections, which are not finished! Filter for Sections with DevelopmentStatus != Finish");
		lblSectionUnfinished.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getUnfinishedSections().size() > 0) {panel_hint.add(lblSectionUnfinished);}
		SimpleLabel lblSectionsEmpty = new SimpleLabel("Warning: You have Sections, which have no text! Filter for Sections without text");
		lblSectionsEmpty.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getEmptySections().size() > 0) {panel_hint.add(lblSectionsEmpty);}
		SimpleLabel lblSectionsUnsorted = new SimpleLabel("Warning: You have Sections, which are not sorted inside table of content! Filter for these unsorted Sections");
		lblSectionsUnsorted.setForeground(Color.RED);
		if(Book.getInstance().getSectionList().getUnsortedSections().size() > 0) {panel_hint.add(lblSectionsUnsorted);}
		
		StructureCard card_export = new StructureCard("Export Book");
		addCard(card_export);
		TransparentPanel panel_export = new TransparentPanel();
		card_export.setBody(panel_export);
		panel_export.setLayout(new GridLayout(0, 1, 5, 5));
		lblExportHint = new SimpleLabel(" ");
		panel_export.add(lblExportHint);
		//TODO: Label um Button erweitern "Open Folder"
		JButton btnExportTXT = new JButton("Export Book as .txt");
		//TODO: Speicherort auswählen lassen???
		btnExportTXT.addActionListener(e -> export());
		panel_export.add(btnExportTXT);
		//TODO: Als was noch exportieren? Export as ONE TXT File (only Text, not Notes, Persons, Places, ...)? Export as Multi TXT files (One with the Notes for chapter or for section???)? export to other format(but which??)
		
		StructureCard card_print = new StructureCard("PrettyPrint Book");
		addCard(card_print);
		TransparentPanel panel_print = new TransparentPanel();
		card_print.setBody(panel_print);
		panel_print.setLayout(new GridLayout(0, 1, 5, 5));
		JButton btnPrintPDF = new JButton("Print Book as .pdf");
		btnPrintPDF.setEnabled(false);
		panel_print.add(btnPrintPDF);
//		btnPrintPDF.addActionListener(e -> print());
	}
	
	private void export() {
		boolean exportSuccessfull = Book.getInstance().exportToTXT();
		if(exportSuccessfull) {
			lblExportHint.setText("File '" + Book.getInstance().getTitle() + ".txt' was successfully saved at path: " + FileManager.getSavingPath());
		}
	}

}
