package GUI.launcherFrame;

import book.content.Book;
import global.FileManager;
import global.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class StartPage extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 100;
	
	private final JButton btnOpenBook;
	private String selectedBook = "";

	public StartPage() {
		setLayout(new BorderLayout(20, 20));
		
		JScrollPane scrollPane_showExistingBooks = new JScrollPane();
		add(scrollPane_showExistingBooks, BorderLayout.WEST);
		
		JPanel panel_showExistingBooks = new JPanel();
		scrollPane_showExistingBooks.setViewportView(panel_showExistingBooks);
		panel_showExistingBooks.setLayout(new GridLayout(0, 1, 5, 5));
		
		JLabel lblChooseABook = new JLabel("  Choose a book for open  ");
		panel_showExistingBooks.add(lblChooseABook);
		
		boolean selectionDone = false;
		ButtonGroup btngrBooks = new ButtonGroup();
		ArrayList<LoadBookPair> bookList = FileManager.getFileList();
		for(LoadBookPair book : bookList) {
			
			JRadioButton radiobuttonNewCheckBox = new JRadioButton(book.getBookTitle());
			panel_showExistingBooks.add(radiobuttonNewCheckBox);
			btngrBooks.add(radiobuttonNewCheckBox);
			radiobuttonNewCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					selectedBook = book.getFilename();
					//System.out.println("Select book:" + selectedBook);
					btnOpenBook.setEnabled(true);
				}
			});
			
			// Set selection of a book
			if(bookList.size() == 1) {
				// Select one and only
				radiobuttonNewCheckBox.setSelected(true);
				selectedBook = book.getFilename();
				selectionDone = true;
			} else {
				String lastOpenedBookFile = UserSettings.getInstance().getLastOpenedBookFile();
				if("".equals(lastOpenedBookFile)) {
					// Select any (=first) book					
					if(!selectionDone) {
						radiobuttonNewCheckBox.setSelected(true);
						selectedBook = book.getFilename();
						selectionDone = true;
					}
				} else {
					// Select book which was last book.time opened
					if(lastOpenedBookFile.equals(book.getFilename())) {
						radiobuttonNewCheckBox.setSelected(true);
						selectedBook = book.getFilename();
						selectionDone = true;
					}
				}
			}
			
		}
		
		JPanel panel_buttons = new JPanel();
		add(panel_buttons, BorderLayout.EAST);
		Dimension currMaxSize = getMaximumSize();
		panel_buttons.setSize(new Dimension(200, currMaxSize.height));
		panel_buttons.setMaximumSize(new Dimension(200, currMaxSize.height));
		panel_buttons.setPreferredSize(new Dimension(200, 200));
		panel_buttons.setLayout(new GridLayout(0, 1, 10, 10));
		//TODO: Add Preselection-User-Setting
		//TODO: Preselect book, which was last open; Preselect book, which is favorite; Standard preselect: first in List

		btnOpenBook = new JButton("Open Book");
		panel_buttons.add(btnOpenBook);
		btnOpenBook.setEnabled(!bookList.isEmpty() && !selectedBook.equals(""));
		btnOpenBook.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnOpenBook.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnOpenBook.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		JButton btnCreateNewBook = new JButton("Create new Book");
		panel_buttons.add(btnCreateNewBook);
		btnCreateNewBook.addActionListener(e -> StartFrame.getInstance().switchBody(new CreateBookPage()));
		btnCreateNewBook.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnCreateNewBook.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnCreateNewBook.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnOpenBook.addActionListener(e -> {
			Book.getInstance().loadFromFile(selectedBook);
			UserSettings.getInstance().setLastOpenedBookFile(selectedBook);
			StartFrame.getInstance().openBookEditor();
		});
		
	}

}
