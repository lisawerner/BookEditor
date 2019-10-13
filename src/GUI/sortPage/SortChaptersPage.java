package GUI.sortPage;

import GUI_components.Page;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import book.Section;
import global.UserSettings;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class SortChaptersPage extends Page {
	private static final long serialVersionUID = 1L;
	
	public SortChaptersPage() {
		super("Sort your chapters and sections");
		
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(8, true));
		}
		
		
		
		//TODO: Make this Card to a "Tutorial-StructureCard", so User can hide it. Hiding-Setting will Save in Tutorial-File for Whole Editor (not for single Book!); Maybe Tutorial-Cards will have different color-Theme
		StructureCard card_hintPanel = new StructureCard("Hint");
		addCard(card_hintPanel);
		JLabel lblHint = new JLabel("<html>Use &lt; for making a Chapter and > for making a Subsection.<br>"
				+ "Use ^ for moving up and v for moving down.<br/>"
				+ "Left places Sections with '>>' are already a chapter; Right places Sections with '>>>>' are Subsections.<br/>"
				+ "<br/>"
				+ "Changing anything saves automatically.</html>");
		card_hintPanel.setBody(lblHint);
		
		
		//***************************************************************************************************************************
		StructureCard card_sortPanel = new StructureCard("Sort your Story");
		addCard(card_sortPanel);
		
		TransparentPanel panel_bodyForSorting = new TransparentPanel();
		card_sortPanel.setBody(panel_bodyForSorting);
		panel_bodyForSorting.setLayout(new BoxLayout(panel_bodyForSorting, BoxLayout.PAGE_AXIS));
//		panel_bodyForSorting.setLayout(new GridLayout(0, 1, 5, 5));	
		for(Section section : Book.getInstance().getSectionList().getSections()) {			
			panel_bodyForSorting.add(new SortChapterElement(section));
		}
				
	}

}
