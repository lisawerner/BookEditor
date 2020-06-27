package GUI.launcherFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import book.Book;

import java.util.ArrayList;

import global.FileManager;
import global.UserSettings;

import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;


public class StartPage extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private int btn_width = 200;
	private int btn_hight = 100;
	
	private JButton btnOpenBook;
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
		ArrayList<LoadBookPair> booklist = FileManager.getFileList();
		for(LoadBookPair book : booklist) {
			
			JRadioButton radiobtnNewCheckBox = new JRadioButton(book.getBookTitle());
			panel_showExistingBooks.add(radiobtnNewCheckBox);
			btngrBooks.add(radiobtnNewCheckBox);
			radiobtnNewCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					selectedBook = book.getFilename();
					//System.out.println("Select book:" + selectedBook);
					btnOpenBook.setEnabled(true);
				}
			});
			
			// Set selection of a book
			if(booklist.size() == 1) {
				// Select one and only
				radiobtnNewCheckBox.setSelected(true);
				selectedBook = book.getFilename();
				selectionDone = true;
			} else {
				String lastOpenedBookfile = UserSettings.getInstance().getLastOpenedBookfile();
				if("".equals(lastOpenedBookfile)) {
					// Select any (=first) book					
					if(!selectionDone) {
						radiobtnNewCheckBox.setSelected(true);
						selectedBook = book.getFilename();
						selectionDone = true;
					}
				} else {
					// Select book which was last time opened
					if(lastOpenedBookfile.equals(book.getFilename())) {
						radiobtnNewCheckBox.setSelected(true);
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
		btnOpenBook.setEnabled(!booklist.isEmpty() && !selectedBook.equals(""));
		btnOpenBook.setSize(new Dimension(btn_width, btn_hight));
		btnOpenBook.setPreferredSize(new Dimension(btn_width, btn_hight));
		btnOpenBook.setMaximumSize(new Dimension(btn_width, btn_hight));
		
		JButton btnCreateNewBook = new JButton("Create new Book");
		panel_buttons.add(btnCreateNewBook);
		btnCreateNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartFrame.getInstance().switchBody(new CreateBookPage());
			}
		});
		btnCreateNewBook.setSize(new Dimension(btn_width, btn_hight));
		btnCreateNewBook.setPreferredSize(new Dimension(btn_width, btn_hight));
		btnCreateNewBook.setMaximumSize(new Dimension(btn_width, btn_hight));
		btnOpenBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Book.getInstance().loadFromFile(selectedBook);
				UserSettings.getInstance().setLastOpenedBookfile(selectedBook);
				StartFrame.getInstance().openBookEditor();
			}
		});
		
	}

}
