package GUI.pages.content;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Chapter;
import book.DevelopmentStatus;
import book.Section;

import javax.swing.*;
import java.awt.*;

public class ListElement_Section extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final Section my_section;
	private final Chapter my_parentChapter;

	public ListElement_Section(Section section, Chapter chapter, ListElement_Chapter parentBody) {
		my_section = section;
		my_parentChapter = chapter;
		setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_sectionInfo = new TransparentPanel();
		add(panel_sectionInfo, BorderLayout.CENTER);
		panel_sectionInfo.setLayout(new BoxLayout(panel_sectionInfo, BoxLayout.LINE_AXIS));

		SimpleLabel lblChapterSpace = new SimpleLabel(" >>>>     ");
		panel_sectionInfo.add(lblChapterSpace);
		
		LinkButton lblSectionName = new LinkButton(my_section.getName(),
				e -> BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_parentChapter)));
		panel_sectionInfo.add(lblSectionName);
		lblSectionName.setToolTipText("<html>Preview Text:<br>" + my_section.getShortTextPreview() + "</html>");
		
		panel_sectionInfo.add(new SimpleLabel((getAdditionalSectionInformation(section))));
		
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
	
	private void moveSection(boolean moveSectionUp, ListElement_Chapter parentBody) {
		my_parentChapter.moveSection(my_section, moveSectionUp);
		parentBody.reload();
//		BookEditorFrame.getInstance().reloadMenu();
	}
	
	private String getAdditionalSectionInformation(Section section){
		String additionalInformation = "";
		if(Book.getInstance().getTableOfContent().showWordCountInSectionLists()){
			additionalInformation += "    (Words: " + section.getCountWords() + ";";
			if(!Book.getInstance().getTableOfContent().showDevStatusInSectionLists()){
				additionalInformation += ")";
			}
		}
		if(Book.getInstance().getTableOfContent().showDevStatusInSectionLists()){
			if(additionalInformation.isEmpty()){
				additionalInformation +=  "    (";
			}
			additionalInformation += " Status: " + DevelopmentStatus.getDevStatus(section.getDevelopmentStatus()) + ")";
		}
		return additionalInformation;
	}
}
