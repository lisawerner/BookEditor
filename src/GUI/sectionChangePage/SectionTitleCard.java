package GUI.sectionChangePage;

import GUI.components.ComplexTextFieldSavable;
import GUI.components.InfoButton;
import GUI.components.TransparentPanel;
import book.Section;

import java.awt.*;

public class SectionTitleCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Section my_section;
	
	private final ComplexTextFieldSavable txt_sectionTitle;

	public SectionTitleCard(Section section) {		
		my_section = section;
		setLayout(new BorderLayout(5, 5));
		
		txt_sectionTitle = new ComplexTextFieldSavable("Section Title:", my_section.getName(), this::save);
		add(txt_sectionTitle);
		
		add(new InfoButton("<html>Title is only shown in table of content and not in Text.<br/>You can change the title every time.</html>"), 
				BorderLayout.EAST);
	}
	
	private void save() {
		if(!txt_sectionTitle.getText().equals("")) {
			my_section.setTitle(txt_sectionTitle.getText());

//			BookEditorFrame.getInstance().reloadMenu();
		}
	}
}
