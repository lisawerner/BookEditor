package GUI.launcherFrame;

import GUI.components.FrameHeader;
import GUI.components.InfoButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextField;
import book.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBookPage extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final SimpleTextField txt_newTitle;
	private final JCheckBox checkboxRememberWorkTitle;
	private final SimpleLabel lblSaveWarning;
	private final SimpleLabel lblNewTitle;
	
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
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
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
				
				boolean isWorkTitle = checkboxRememberWorkTitle.isSelected();
				
				if(canSave) {
					Book.getInstance().createNewBook(title, isWorkTitle);
					StartFrame.getInstance().openBookEditor();
				}
			}
		});
		
		panel_footer.add(btnSave);
		
		//TODO: Center the text
		FrameHeader lblCreateANew = new FrameHeader("Create a new Book");
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
		
		txt_newTitle = new SimpleTextField();
		panel_enterDataHere.add(txt_newTitle, BorderLayout.CENTER);
		txt_newTitle.setPreferredSize(new Dimension(200, txt_newTitle.getHeight()));
		
		checkboxRememberWorkTitle = new JCheckBox("This is only a work title. Please remind me to change it, before publishing the book.");
		panel_enterDataHere.add(checkboxRememberWorkTitle, BorderLayout.SOUTH);

	}
	
	private void setWarningEnterName(boolean warning) {
		txt_newTitle.setWarning(warning);
		lblNewTitle.setWarning(warning);
	}

}
