package GUI_StartFrame;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import GUI_components.HeaderBook;
import GUI_components.InfoButton;
import GUI_components.SimpleLabel;
import GUI_components.SimpleTextfield;
import book.Book;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class CreateBookPage extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private SimpleTextfield txt_newTitle;
	private JCheckBox chckbxRememberWorktitle;
	private SimpleLabel lblSaveWarning;
	private SimpleLabel lblNewTitle;
	
	public CreateBookPage() {
		setLayout(new BorderLayout(10, 10));
		
		JPanel panel_footer = new JPanel();
		add(panel_footer, BorderLayout.SOUTH);
		panel_footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblSavingHInt = new SimpleLabel("You have to fill every field with an *.");
		panel_footer.add(lblSavingHInt);
		
		lblSaveWarning = new SimpleLabel(" ");
		panel_footer.add(lblSaveWarning);
		lblSaveWarning.setForeground(Color.RED);
		
		JButton btnSpeichern = new JButton("Save");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSaveWarning.setText(" ");
				boolean canSave = true;
				
				setWarningEnterName(false);
				String title = txt_newTitle.getText();				
				if(title.equals("")) {
					lblSaveWarning.setText("WARNING: Can not save book, because no title was entered!");
					setWarningEnterName(true);
					canSave = false;
				}
				
				boolean isWorktitle = chckbxRememberWorktitle.isSelected();
				
				if(canSave) {
					Book.getInstance().createNewBook(title, isWorktitle);
					StartFrame.getInstance().openBookEditor();
				}
			}
		});
		
		panel_footer.add(btnSpeichern);
		
		//TODO: Schrift zentrieren
		HeaderBook lblCreateANew = new HeaderBook("Create a new Book");
		add(lblCreateANew, BorderLayout.NORTH);
		
		JPanel panel_body = new JPanel();
		add(panel_body, BorderLayout.CENTER);
		panel_body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_enterDataHere = new JPanel();
		panel_body.add(panel_enterDataHere);
		panel_enterDataHere.setLayout(new BorderLayout(5, 5));
		
		lblNewTitle = new SimpleLabel("* Enter Book Title:");
		panel_enterDataHere.add(lblNewTitle, BorderLayout.WEST);
		
		InfoButton btn_newTitleInfo = new InfoButton("You can change the title at any time.");
		panel_enterDataHere.add(btn_newTitleInfo, BorderLayout.EAST);
		
		txt_newTitle = new SimpleTextfield();
		panel_enterDataHere.add(txt_newTitle, BorderLayout.CENTER);
		txt_newTitle.setPreferredSize(new Dimension(200, txt_newTitle.getHeight()));
		
		chckbxRememberWorktitle = new JCheckBox("This is only a worktitle. Please remind me to change it, before publishing the book.");
		panel_enterDataHere.add(chckbxRememberWorktitle, BorderLayout.SOUTH);

	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_newTitle.setWarning(warning);
			lblNewTitle.setWarning(warning);
		} else {
			txt_newTitle.setWarning(warning);
			lblNewTitle.setWarning(warning);
		}
	}

}
