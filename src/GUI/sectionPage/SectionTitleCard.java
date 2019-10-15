package GUI.sectionPage;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.InfoButton;
import GUI_components.SimpleLabel;
import GUI_components.SimpleTextfield;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import global.UserSettings;

public class SectionTitleCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	private SimpleTextfield txt_sectionTitle;
	private SimpleLabel lbl_saveWarning;
	private SimpleLabel lblChapterTitle;


	public SectionTitleCard(Section section) {
		my_section = section;
		setLayout(new BorderLayout(5, 5));
		
		
		txt_sectionTitle = new SimpleTextfield();
		if(my_section != null) {txt_sectionTitle.setText(my_section.getName());}
		add(txt_sectionTitle);
		
		lblChapterTitle = new SimpleLabel("Section Title:");
		add(lblChapterTitle, BorderLayout.WEST);
		
		InfoButton btnNewButton = new InfoButton("<html>Title is only shown in table of content and not in Text.<br/>You can change the title every time.</html>");
		add(btnNewButton, BorderLayout.EAST);
		
		TransparentPanel panel_footer = new TransparentPanel();
		add(panel_footer, BorderLayout.SOUTH);
		panel_footer.setLayout(new BorderLayout(0, 0));
		
		JButton btnSaveChapter = new JButton("Create new Section");
		if(my_section != null) {btnSaveChapter.setText("Save new Title");}
		panel_footer.add(btnSaveChapter, BorderLayout.SOUTH);
		
		lbl_saveWarning = new SimpleLabel(" ");
		panel_footer.add(lbl_saveWarning, BorderLayout.NORTH);
		lbl_saveWarning.setForeground(Color.RED);
		
		btnSaveChapter.addActionListener(e -> save());

	}
	
	private void save() {
		lbl_saveWarning.setText(" ");
		lbl_saveWarning.setForeground(Color.RED);
		boolean canSave = true;
		
		setWarningEnterName(false);
		if(txt_sectionTitle.getText().equals("")) {
			lbl_saveWarning.setText("Can not save section. Please enter a title for placing it in table of content.");
			setWarningEnterName(true);
			canSave = false;
		}
		
		if(canSave) {
			lbl_saveWarning.setForeground(Color.BLACK);
			if(my_section == null) {
				my_section = new Section(txt_sectionTitle.getText());
				Book.getInstance().getSectionList().addSection(my_section);
				if(!UserSettings.getInstance().getTutorial().createFirstSection) {
					UserSettings.getInstance().getTutorial().createFirstSection = true;
					UserSettings.getInstance().save();
				}
			} else {
				my_section.setTitle(txt_sectionTitle.getText());
				Book.getInstance().getSectionList().editSection(my_section);
			}
			BookEditorFrame.getInstance().reloadMenu();
			BookEditorFrame.getInstance().switchBody(new SectionPage(my_section));
		}
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_sectionTitle.setBorder(BorderFactory.createLineBorder(Color.RED));
			lblChapterTitle.setForeground(Color.RED);
		} else {
			txt_sectionTitle.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			lblChapterTitle.setForeground(Color.BLACK);
		}
	}

}
