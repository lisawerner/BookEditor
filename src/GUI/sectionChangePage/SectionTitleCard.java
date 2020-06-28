package GUI.sectionChangePage;

import java.awt.BorderLayout;

import book.Section;
import GUI.components.ComplexeTextfieldSaveable;
import GUI.components.InfoButton;
import GUI.components.TransparentPanel;

public class SectionTitleCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	private ComplexeTextfieldSaveable txt_sectionTitle;

	public SectionTitleCard(Section section) {		
		my_section = section;
		setLayout(new BorderLayout(5, 5));
		
		txt_sectionTitle = new ComplexeTextfieldSaveable("Section Title:", my_section.getName(), this::save, true, true);
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
