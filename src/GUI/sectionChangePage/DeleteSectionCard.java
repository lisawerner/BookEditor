package GUI.sectionChangePage;

import book.Book;
import book.Chapter;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.pages.content.Page_sortContent;
import GUI.sectionPage.SectionPage;

public class DeleteSectionCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	private Chapter my_parentChapter;

	public DeleteSectionCard(Section section, Chapter chapter) {
		my_section = section;
		my_parentChapter = chapter;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		boolean hasNoContent = !"".equals(my_section.getText());
		ArrayList<Section> relatedSection = Book.getInstance().getTableOfContent().getAllSectionsWhichUsesThatSectionAsTimeRelation(my_section.getID());
		boolean hasTimeRelations = !relatedSection.isEmpty();
		boolean isDeletable = !hasNoContent & !hasTimeRelations;
		
		SimpleLabel lblYouCanNot = new SimpleLabel(" ");
		if(hasNoContent) {
			lblYouCanNot.setText("<html>You can not delete a section with a content! </br>Remove text first.</html>");
		}
		add(lblYouCanNot);

		TransparentPanel timePanel = new TransparentPanel();
		timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.PAGE_AXIS));
		add(timePanel);
		if(hasTimeRelations) {
			timePanel.add(new SimpleLabel("You can not delete that section, because following sections use it as relation for their timestamps:"));
			add(lblYouCanNot);
			for(Section relationships : relatedSection){
				timePanel.add(new LinkButton(relationships.getName(),
						e -> BookEditorFrame.getInstance().switchBody(new SectionPage(relationships, Book.getInstance().getTableOfContent().getChapter(relationships.getParentChapterID())))));
			}
		}

		JButton btnDeleteThisSection = new JButton("Delete this section");
		btnDeleteThisSection.addActionListener(e -> deleteSection());
		btnDeleteThisSection.setEnabled(isDeletable);
		add(btnDeleteThisSection);
		
	}


	private void deleteSection() {
		int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      // Nothing to do
		    } else if (response == JOptionPane.YES_OPTION) {
		    	my_parentChapter.removeSection(my_section);
//		    	BookEditorFrame.getInstance().reloadMenu();
		    	BookEditorFrame.getInstance().switchBody(new Page_sortContent());
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      // Nothing to do
		    }
	}

}
