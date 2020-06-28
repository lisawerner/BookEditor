package GUI.pages.content;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.TransparentPanel;
import GUI.pages.content.createChapter.Page_createChapter;

public class Card_ContentInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_ContentInformation() {
		
		JButton btnCreateNewChapter = new JButton("Create new Chapter");
		btnCreateNewChapter.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_createChapter()));
		add(btnCreateNewChapter);
		


	}

}
