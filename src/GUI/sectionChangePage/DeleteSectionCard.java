package GUI.sectionChangePage;

import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sortPage.SortChaptersPage;

public class DeleteSectionCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;


	public DeleteSectionCard(Section section) {
		my_section = section;
		
		SimpleLabel lblYouCanNot = new SimpleLabel(" ");
		if(!"".equals(my_section.getText())) {
			lblYouCanNot.setText("<html>You can not delete a section with a content! </br>Remove text first.</html>");
		}
		add(lblYouCanNot);

		JButton btnDeleteThisSection = new JButton("Delete this section");
		btnDeleteThisSection.addActionListener(e -> deleteSection());
		btnDeleteThisSection.setEnabled("".equals(my_section.getText()));
		add(btnDeleteThisSection);
		
	}


	private void deleteSection() {
		int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      // Nothing to do
		    } else if (response == JOptionPane.YES_OPTION) {
		      Book.getInstance().getSectionList().deleteSection(my_section);
		      BookEditorFrame.getInstance().reloadMenu();
		      BookEditorFrame.getInstance().switchBody(new SortChaptersPage());
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      // Nothing to do
		    }
	}

}
