package GUI.sortPage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Section;

public class SortChapterElement extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;

	public SortChapterElement(Section section) {
		my_section = section;
		setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_sectionInfo = new TransparentPanel();
		add(panel_sectionInfo, BorderLayout.CENTER);
		panel_sectionInfo.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblChapterSpace = new SimpleLabel("    ??     ");
		if(my_section.isUnsorted()) {
			lblChapterSpace.setText("    ??     ");
		} else {
			if(my_section.isChapter()) {
				lblChapterSpace.setText(">>   ");
			} else {
				lblChapterSpace.setText("         >>>>     ");
			}
		}
		panel_sectionInfo.add(lblChapterSpace, BorderLayout.WEST);
		
		SimpleLabel lblSectionName = new SimpleLabel(my_section.getName());
		panel_sectionInfo.add(lblSectionName, BorderLayout.CENTER);
		lblSectionName.setToolTipText("<html>Preview Text:<br>" + my_section.getShortTextPreview() + "</html>");
		
		TransparentPanel panel_move = new TransparentPanel();
		add(panel_move, BorderLayout.WEST);
		panel_move.setLayout(new BorderLayout(5, 5));
		
		JButton btn_makeToChapter = new JButton("<");
		btn_makeToChapter.setEnabled(!my_section.isChapter() || my_section.isUnsorted());
		btn_makeToChapter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				my_section.makeToChapter();
				BookEditorFrame.getInstance().switchBody(new SortChaptersPage());
				BookEditorFrame.getInstance().reloadMenu(); //TODO: Anstelle des Reload irgendwas, wo der ViewFocus von der Scrollbar nicht verloren geht?
			}
		});
		panel_move.add(btn_makeToChapter, BorderLayout.WEST);
		
		TransparentPanel panel_buttonplacer = new TransparentPanel();
		panel_move.add(panel_buttonplacer, BorderLayout.CENTER);
		panel_buttonplacer.setLayout(new BorderLayout(1, 1));
		
		JButton btn_moveSectionUp = new JButton("^");
		btn_moveSectionUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				my_section.sortUp();
				BookEditorFrame.getInstance().switchBody(new SortChaptersPage());
				BookEditorFrame.getInstance().reloadMenu(); //TODO: Anstelle des Reload irgendwas, wo der ViewFocus von der Scrollbar nicht verloren geht?
			}
		});
		btn_moveSectionUp.setEnabled(!my_section.isFirstSection());
		
		panel_buttonplacer.add(btn_moveSectionUp, BorderLayout.NORTH);
		
		JButton btn_moveSectionDown = new JButton("v");
		btn_moveSectionDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				my_section.sortDown();
				BookEditorFrame.getInstance().switchBody(new SortChaptersPage());
				BookEditorFrame.getInstance().reloadMenu(); //TODO: Anstelle des Reload irgendwas, wo der ViewFocus von der Scrollbar nicht verloren geht?
			}
		});
		panel_buttonplacer.add(btn_moveSectionDown, BorderLayout.SOUTH);
		btn_moveSectionDown.setEnabled(!my_section.isLastSection());
		
		JButton btn_removeChapterStatus = new JButton(">");
		btn_removeChapterStatus.setEnabled(my_section.isChapter() || my_section.isUnsorted());
		btn_removeChapterStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				my_section.removeChapterStatus();
				BookEditorFrame.getInstance().switchBody(new SortChaptersPage());
				BookEditorFrame.getInstance().reloadMenu(); //TODO: Anstelle des Reload irgendwas, wo der ViewFocus von der Scrollbar nicht verloren geht?
			}
		});
		panel_move.add(btn_removeChapterStatus, BorderLayout.EAST);
		
		//TODO: Disable all buttons, which can not be used for that section; if is last one in sorted list, can not move down;
		
		//TODO: Something with Buttons for move completeChapter or Move only Chapter-Section!; I thing both is needed, so do not delete or change singleMove; i thing add another button for completeMove
	}

}
