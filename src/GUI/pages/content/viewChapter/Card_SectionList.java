package GUI.pages.content.viewChapter;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.pages.content.createSection.Page_createSection;
import GUI.pages.content.changeSection.Page_ChangeSection;
import book.Book;
import book.Chapter;
import book.DevelopmentStatus;
import book.Section;

import javax.swing.*;
import java.awt.*;

public class Card_SectionList extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	public Card_SectionList(Chapter chapter) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JButton btn_createSection = new JButton("Create a new text section for that chapter");
		add(btn_createSection);
		btn_createSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_createSection(chapter)));
		
		for(Section section : chapter.getSections()) {
			TransparentPanel panel_section = new TransparentPanel();
			panel_section.setLayout(new FlowLayout(FlowLayout.LEADING));
			add(panel_section);
			
			LinkButton lbl_sectionTitle = new LinkButton(section.getName(),
					e -> BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(section, chapter)));
			panel_section.add(lbl_sectionTitle);
			
			panel_section.add(new SimpleLabel((getAdditionalSectionInformation(section))));
		}
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
