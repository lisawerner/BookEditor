package GUI.sectionChangePage;

import java.awt.BorderLayout;
import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.InfoButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Chapter;
import book.DevelopmentStatus;
import book.Section;
import global.UserSettings;

public class SectionDevstatusCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Section my_section;
	private final Chapter my_parentChapter;
	
	private final SimpleLabel lblDevStatus;
	private final InfoButton hint_devStatus;
	private final JButton btnDecreaseDevStatus;
	private InfoButton ibtnDecrease;
	private final JButton btnIncreaseDevStatus;
	private InfoButton ibtnIncrease;

	public SectionDevstatusCard(Section section, Chapter chapter) {
		my_section = section;
		my_parentChapter = chapter;
		setLayout(new BorderLayout(5, 5));
		
		lblDevStatus = new SimpleLabel("Current Development Status: unkown");
		add(lblDevStatus, BorderLayout.WEST);
		TransparentPanel panel_positionDevStatusHint = new TransparentPanel();
		add(panel_positionDevStatusHint, BorderLayout.CENTER);
		panel_positionDevStatusHint.setLayout(new BorderLayout(0, 0));
		hint_devStatus = new InfoButton(DevelopmentStatus.getDevStatDescription(-1));
		panel_positionDevStatusHint.add(hint_devStatus, BorderLayout.WEST);
		TransparentPanel panel_changeDevStatus = new TransparentPanel();
		panel_changeDevStatus.setLayout(new BorderLayout(5, 5));
		TransparentPanel panel_changeDevButtons = new TransparentPanel();
		add(panel_changeDevStatus, BorderLayout.EAST);
		panel_changeDevStatus.add(panel_changeDevButtons, BorderLayout.CENTER);
		btnDecreaseDevStatus = new JButton("<<");
//		btnDecreaseDevStatus = new JButton("<< Downgrade <<");
		btnDecreaseDevStatus.setEnabled(false);
		panel_changeDevButtons.add(btnDecreaseDevStatus);
		btnDecreaseDevStatus.addActionListener(e -> changeDevStatus(my_section.getDevelopmentStatus() - 1));
		btnIncreaseDevStatus = new JButton(">>");
//		btnIncreaseDevStatus = new JButton(">> Upgrade >>");
		panel_changeDevButtons.add(btnIncreaseDevStatus);
		btnIncreaseDevStatus.setEnabled(false);
		btnIncreaseDevStatus.addActionListener(e -> changeDevStatus(my_section.getDevelopmentStatus() + 1));
		if(my_section != null) {
			lblDevStatus.setText("Current Development Status: " + my_section.getDevelopmentStatusToString());
			hint_devStatus.setText(my_section.getDevelopmentStatusDescription());
			btnDecreaseDevStatus.setEnabled(my_section.getDevelopmentStatus() >= 0);
			btnDecreaseDevStatus.setToolTipText(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() - 1));
			btnIncreaseDevStatus.setEnabled(my_section.getDevelopmentStatus() < 4);
			btnIncreaseDevStatus.setToolTipText(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() + 1));
			ibtnDecrease = new InfoButton(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() - 1));
			ibtnIncrease = new InfoButton(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() + 1));
			panel_changeDevStatus.add(ibtnDecrease, BorderLayout.WEST);
			panel_changeDevStatus.add(ibtnIncrease, BorderLayout.EAST);
		}
	}
	
	
	private void changeDevStatus(int newDevStatus) {
		my_section.changeDevStatus(newDevStatus);
		lblDevStatus.setText("Current Development Status: " + my_section.getDevelopmentStatusToString());
		hint_devStatus.setToolTipText(my_section.getDevelopmentStatusDescription());
		btnDecreaseDevStatus.setToolTipText(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() - 1));
		btnIncreaseDevStatus.setToolTipText(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() + 1));
		ibtnDecrease.setToolTipText(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() - 1));
		ibtnIncrease.setToolTipText(DevelopmentStatus.getDevStatDescription(my_section.getDevelopmentStatus() + 1));
		btnDecreaseDevStatus.setEnabled(my_section.getDevelopmentStatus() >= 0);
		btnIncreaseDevStatus.setEnabled(my_section.getDevelopmentStatus() < 4);
		if(!UserSettings.getInstance().getTutorial().setDevelopmentStatus) {
			UserSettings.getInstance().getTutorial().setDevelopmentStatus = true;
			UserSettings.getInstance().save();
			BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_parentChapter));
		}
	}

}
