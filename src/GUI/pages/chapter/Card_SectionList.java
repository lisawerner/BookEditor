package GUI.pages.chapter;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionChangePage.CreateSectionPage;
import GUI.sectionPage.SectionPage;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import book.Chapter;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.TransparentPanel;

public class Card_SectionList extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	public Card_SectionList(Chapter chapter) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JButton btn_createSection = new JButton("Create a new textsection for that chapter");
		add(btn_createSection);
		btn_createSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new CreateSectionPage(chapter)));
		
		for(Section section : chapter.getSections()) {
			TransparentPanel panel_section = new TransparentPanel();
			panel_section.setLayout(new FlowLayout(FlowLayout.LEADING));
			add(panel_section);
			
			LinkButton lbl_sectionTitle = new LinkButton(section.getName());
			lbl_sectionTitle.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, chapter)));
			panel_section.add(lbl_sectionTitle);
		}

	}

}
