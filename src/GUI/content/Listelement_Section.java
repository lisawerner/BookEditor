package GUI.content;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Chapter;
import book.Section;
import java.awt.GridLayout;

public class Listelement_Section extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	private Chapter my_parentChapter;
	
	private SimpleLabel lblChapterSpace;

	public Listelement_Section(Section section, Chapter chapter, Listelement_Chapter parentBody) {
		my_section = section;
		my_parentChapter = chapter;
		setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_sectionInfo = new TransparentPanel();
		add(panel_sectionInfo, BorderLayout.CENTER);
		panel_sectionInfo.setLayout(new BoxLayout(panel_sectionInfo, BoxLayout.LINE_AXIS));
		
		lblChapterSpace = new SimpleLabel(" >>>>     ");
		panel_sectionInfo.add(lblChapterSpace);
		
		LinkButton lblSectionName = new LinkButton(my_section.getName());
		panel_sectionInfo.add(lblSectionName);
		lblSectionName.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_parentChapter)));
		lblSectionName.setToolTipText("<html>Preview Text:<br>" + my_section.getShortTextPreview() + "</html>");
		
		TransparentPanel panel_move = new TransparentPanel();
		add(panel_move, BorderLayout.WEST);
		panel_move.setLayout(new GridLayout(0, 1, 1, 1));
		
		JButton btn_moveSectionUp = new JButton("^");
		panel_move.add(btn_moveSectionUp);
		btn_moveSectionUp.addActionListener(e -> moveSection(true, parentBody));
		btn_moveSectionUp.setEnabled(!my_parentChapter.isFirstSection(my_section));
		
		JButton btn_moveSectionDown = new JButton("v");
		panel_move.add(btn_moveSectionDown);
		btn_moveSectionDown.addActionListener(e -> moveSection(false, parentBody));
		btn_moveSectionDown.setEnabled(!my_parentChapter.isLastSection(my_section));
	}
	
	private void moveSection(boolean moveSectionUp, Listelement_Chapter parentBody) {
		my_parentChapter.moveSection(my_section, moveSectionUp);
		parentBody.reload();
		BookEditorFrame.getInstance().reloadMenu();
	}
}
