package GUI.sectionPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionTimestamp.SectionTimeEditor;
import GUI_components.InfoButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.DevelopmentStatus;
import book.Section;
import global.UserSettings;

public class SectionInformationCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	private SimpleLabel lblDevStatus;
	private InfoButton hint_devStatus;
	private JButton btnDecreaseDevStatus;
	private InfoButton ibtnDecrease;
	private JButton btnIncreaseDevStatus;
	private InfoButton ibtnIncrease;

	public SectionInformationCard(Section section) {
		my_section = section;
		setLayout(new GridLayout(0, 1, 0, 0));
		
		TransparentPanel panel_timestamp = new TransparentPanel();
		add(panel_timestamp);
		panel_timestamp.setLayout(new BorderLayout(5, 5));
		SimpleLabel lblTimestamp = new SimpleLabel("Timestamp:");
		panel_timestamp.add(lblTimestamp, BorderLayout.WEST);
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionTimeEditor(my_section)));
		panel_timestamp.add(btnChange, BorderLayout.EAST);
		SimpleLabel lblSectionTimestamp = new SimpleLabel("  ");
		if(my_section != null) {
			if(my_section.hasTimestamp()) {
				lblSectionTimestamp.setText(my_section.getTimestamp().toCompleteString());
			}
		} else {
			btnChange.setEnabled(false);
			btnChange.setToolTipText("You have to create a section first.");
		}
		panel_timestamp.add(lblSectionTimestamp, BorderLayout.CENTER);
		
		TransparentPanel panel_devStatus = new TransparentPanel();
		add(panel_devStatus);
		panel_devStatus.setLayout(new BorderLayout(5, 5));
		lblDevStatus = new SimpleLabel("Current Development Status: unkown");
		panel_devStatus.add(lblDevStatus, BorderLayout.WEST);
		TransparentPanel panel_positionDevStatusHint = new TransparentPanel();
		panel_devStatus.add(panel_positionDevStatusHint, BorderLayout.CENTER);
		panel_positionDevStatusHint.setLayout(new BorderLayout(0, 0));
		hint_devStatus = new InfoButton(DevelopmentStatus.getDevStatDescription(-1));
		panel_positionDevStatusHint.add(hint_devStatus, BorderLayout.WEST);
		TransparentPanel panel_changeDevStatus = new TransparentPanel();
		panel_changeDevStatus.setLayout(new BorderLayout(5, 5));
		TransparentPanel panel_changeDevButtons = new TransparentPanel();
		panel_devStatus.add(panel_changeDevStatus, BorderLayout.EAST);
		panel_changeDevStatus.add(panel_changeDevButtons, BorderLayout.CENTER);
		btnDecreaseDevStatus = new JButton("<< Downgrade <<");
		btnDecreaseDevStatus.setEnabled(false);
		panel_changeDevButtons.add(btnDecreaseDevStatus);
		btnDecreaseDevStatus.addActionListener(e -> changeDevStatus(my_section.getDevelopmentStatus() - 1));
		btnIncreaseDevStatus = new JButton(">> Upgrade >>");
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
		
		SimpleLabel lblSectionInfoCounts = new SimpleLabel("Words: ...; Char: ...");
		if(my_section != null) {lblSectionInfoCounts.setText("Words: " + my_section.getCountWords() + "; Char: " + my_section.getText().length());}
		add(lblSectionInfoCounts);
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
			BookEditorFrame.getInstance().switchBody(new SectionPage(my_section));
		}
	}

}
