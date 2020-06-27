package GUI.sectionChangePage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import book.Chapter;
import book.Section;
import global.UserSettings;
import GUI.components.InfoButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextfield;
import GUI.components.TransparentPanel;

public class SectionTitleCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	private Chapter my_parentChapter;
	
	private SimpleTextfield txt_sectionTitle;
	private SimpleLabel lbl_saveWarning;
	private SimpleLabel lblChapterTitle;


	public SectionTitleCard(Section section, Chapter chapter) {
		my_section = section;
		my_parentChapter = chapter;
		setLayout(new BorderLayout(5, 5));
		
		
		txt_sectionTitle = new SimpleTextfield();
		if(my_section != null) {txt_sectionTitle.setText(my_section.getName());}
		add(txt_sectionTitle);
		txt_sectionTitle.addKeyListener(new KeyAdapter() {
	        @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                	save();
                }
            }

	    });
		
		lblChapterTitle = new SimpleLabel("Section Title:");
		add(lblChapterTitle, BorderLayout.WEST);
		
		InfoButton btnNewButton = new InfoButton("<html>Title is only shown in table of content and not in Text.<br/>You can change the title every time.</html>");
		add(btnNewButton, BorderLayout.EAST);
		
		TransparentPanel panel_footer = new TransparentPanel();
		add(panel_footer, BorderLayout.SOUTH);
		panel_footer.setLayout(new GridLayout(0, 1, 2, 2));
		
		SimpleLabel lbl_saveHint = new SimpleLabel("You can also save by pushing 'STRG+S' when the curser is inside the textarea!");
		panel_footer.add(lbl_saveHint, BorderLayout.NORTH);
		
		JButton btnSaveChapter = new JButton("Create new Section");
		if(my_section != null) {btnSaveChapter.setText("Save new Title");}
		panel_footer.add(btnSaveChapter, BorderLayout.SOUTH);
		
		lbl_saveWarning = new SimpleLabel(" ");
		panel_footer.add(lbl_saveWarning, BorderLayout.NORTH);
		lbl_saveWarning.setWarning(true);
		
		btnSaveChapter.addActionListener(e -> save());

	}
	
	private void save() {
		lbl_saveWarning.setText(" ");
		lbl_saveWarning.setWarning(true);
		boolean canSave = true;
		
		setWarningEnterName(false);
		if(txt_sectionTitle.getText().equals("")) {
			lbl_saveWarning.setText("Can not save section. Please enter a title for placing it in table of content.");
			setWarningEnterName(true);
			canSave = false;
		}
		
		if(canSave) {
			lbl_saveWarning.setWarning(false);
			if(my_section == null) {
				my_section = new Section(my_parentChapter, txt_sectionTitle.getText());
				my_parentChapter.addSection(my_section);
				if(!UserSettings.getInstance().getTutorial().createFirstSection) {
					UserSettings.getInstance().getTutorial().createFirstSection = true;
					UserSettings.getInstance().save();
				}
				BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_parentChapter));
			} else {
				my_section.setTitle(txt_sectionTitle.getText());
				lbl_saveWarning.setWarning(false);
				lbl_saveWarning.setText("New title " + my_section.getName() + " is saved!");
			}
//			BookEditorFrame.getInstance().reloadMenu();
		}
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_sectionTitle.setWarning(warning);
			lblChapterTitle.setWarning(warning);
		} else {
			txt_sectionTitle.setWarning(warning);
			lblChapterTitle.setWarning(warning);
		}
	}

}
