package GUI.pages.content.createSection;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.ComplexTextFieldSavable;
import GUI.components.InfoButton;
import GUI.components.TransparentPanel;
import GUI.sectionPage.SectionPage;
import book.Chapter;
import book.Section;
import global.UserSettings;

import java.awt.*;

public class Card_createSectionByTitle extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Chapter my_parentChapter;
	
	private final ComplexTextFieldSavable txt_sectionTitle;

	public Card_createSectionByTitle(Chapter chapter) {
		my_parentChapter = chapter;
		setLayout(new BorderLayout(5, 5));
		
		txt_sectionTitle = new ComplexTextFieldSavable("Section Title:", "", this::save);
		add(txt_sectionTitle);
		
		add(new InfoButton("<html>Title is only shown in table of content and not in Text.<br/>You can change the title every time.</html>"),
				BorderLayout.EAST);
	}
	
	private void save() {		
		if(!txt_sectionTitle.getText().isEmpty()) {

			Section newSection = new Section(my_parentChapter, txt_sectionTitle.getText());
			my_parentChapter.addSection(newSection);
			if(!UserSettings.getInstance().getTutorial().createFirstSection) {
				UserSettings.getInstance().getTutorial().createFirstSection = true;
				UserSettings.getInstance().save();
			}
			BookEditorFrame.getInstance().switchBody(new SectionPage(newSection, my_parentChapter));

//			BookEditorFrame.getInstance().reloadMenu();
		}
	}
}

