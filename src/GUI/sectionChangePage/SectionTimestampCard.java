package GUI.sectionChangePage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.SimpleRadiobutton;
import GUI_components.TransparentPanel;
import book.Book;
import book.Chapter;
import book.Section;
import time.RelativeDate;
import time.SpecificDate;
import time.Timestamp;

import javax.swing.*;
import java.awt.*;

public class SectionTimestampCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final Section my_section;

	private final SimpleRadiobutton rdbtnSpecificTimestamp;
	private final SimpleRadiobutton rdbtnUnspecificTimestamp;

	private final TimestampSpecificEditor panel_specificBODY;
	private final TimestampRelativeEditor panel_unspecificBODY;
	
	public SectionTimestampCard(Section section, Chapter chapter) {
		my_section = section;
		setLayout(new BorderLayout(10, 10));
		//TODO: Change Formulary, if Custom Calendar is selected!
		
		
		//*****************************************************************************************************************
		TransparentPanel panel_helpfulInformationInNorth = new TransparentPanel();
		add(panel_helpfulInformationInNorth, BorderLayout.NORTH);
		Section preSection = my_section.getPreSection();
		if(preSection != null) {			
			if(preSection.hasTimestamp()) {				
				panel_helpfulInformationInNorth.setLayout(new BoxLayout(panel_helpfulInformationInNorth, BoxLayout.LINE_AXIS));
				panel_helpfulInformationInNorth.add(new SimpleLabel("Section before '"));
				LinkButton btnPreSection = new LinkButton(preSection.getName());
				btnPreSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(preSection, chapter)));
				panel_helpfulInformationInNorth.add(btnPreSection);
				panel_helpfulInformationInNorth.add(new SimpleLabel("' has Timestamp: " + preSection.getTimestamp().toCompleteString()));
			}
		}
		
		ButtonGroup btngrTimestampType = new ButtonGroup();

		//*****************************************************************************************************
		TransparentPanel panel_FOOTER = new TransparentPanel();
		add(panel_FOOTER, BorderLayout.SOUTH);
		panel_FOOTER.setLayout(new BorderLayout(5, 5));
		
		JButton btnSaveTimestamp = new JButton("Save Timestamp");
		panel_FOOTER.add(btnSaveTimestamp, BorderLayout.SOUTH);
		
		SimpleLabel lblSaveWarning = new SimpleLabel(" ");
		panel_FOOTER.add(lblSaveWarning, BorderLayout.NORTH);
		
		TransparentPanel panel_newBody = new TransparentPanel();
		add(panel_newBody, BorderLayout.CENTER);
		panel_newBody.setLayout(new BorderLayout(10, 10));
		
		TransparentPanel panel_switchBetweenTimestampTypes = new TransparentPanel();
		panel_switchBetweenTimestampTypes.setLayout(new GridLayout(1, 0, 5, 5));
		panel_newBody.add(panel_switchBetweenTimestampTypes, BorderLayout.NORTH);
		
		rdbtnUnspecificTimestamp = new SimpleRadiobutton("Unspecific Timestamp");
		panel_switchBetweenTimestampTypes.add(rdbtnUnspecificTimestamp);
		rdbtnUnspecificTimestamp.addActionListener(e -> switchSpecificAndUnspecific());
		btngrTimestampType.add(rdbtnUnspecificTimestamp);
		
		rdbtnSpecificTimestamp = new SimpleRadiobutton("Specific Timestamp");
		panel_switchBetweenTimestampTypes.add(rdbtnSpecificTimestamp);
		rdbtnSpecificTimestamp.addActionListener(e -> switchSpecificAndUnspecific());
		btngrTimestampType.add(rdbtnSpecificTimestamp);
		
		TransparentPanel panel_switchableBody = new TransparentPanel();
		panel_newBody.add(panel_switchableBody, BorderLayout.CENTER);
		
		panel_unspecificBODY = new TimestampRelativeEditor(my_section);
		panel_switchableBody.add(panel_unspecificBODY);
		
		panel_specificBODY = new TimestampSpecificEditor(my_section);
		panel_switchableBody.add(panel_specificBODY);
		btnSaveTimestamp.addActionListener(e -> {
			boolean canSave = true;
			lblSaveWarning.setText(" ");

			SpecificDate specificDate = null;
			RelativeDate relativeDate = null;
			if(rdbtnUnspecificTimestamp.isSelected()) {
				relativeDate = panel_unspecificBODY.getResult();
			} else if(rdbtnSpecificTimestamp.isSelected()) {
				specificDate = panel_specificBODY.getResult();
			} else {
				//TODO: Add Warning-Question-Window, before SAVING NOTHING?! :D
				lblSaveWarning.setText("You have selected Nothing! -> Timestamp will remove from Section!");
				canSave = false;
			}

			if(canSave) {
				Timestamp newTimestamp = new Timestamp(specificDate, relativeDate);
				my_section.setTimestamp(newTimestamp);
				Book.getInstance().save();
				lblSaveWarning.setText("New Timestamp was successfully saved!");
			}
		});
		
		if(my_section.hasTimestamp()) {
			if(!my_section.getTimestamp().isSpecificDate()) {
				panel_unspecificBODY.activate(my_section.getTimestamp().getUnspecificDate());
				rdbtnUnspecificTimestamp.setSelected(true);
				panel_unspecificBODY.switchEnabled(true);
			}
		}
		
		if(my_section.hasTimestamp()) {
			if(my_section.getTimestamp().isSpecificDate()) {
				rdbtnSpecificTimestamp.setSelected(true);
				panel_specificBODY.switchEnabled(true);
			}
		}
		
		switchSpecificAndUnspecific();
	}

	private void switchSpecificAndUnspecific() {
		//System.out.println("Set Specific Time: " + rdbtnSpecificTimestamp.isSelected());
		panel_specificBODY.setVisible(rdbtnSpecificTimestamp.isSelected());
		//System.out.println("Set not specific Time: " + rdbtnUnspecificTimestamp.isSelected());
		panel_unspecificBODY.setVisible(rdbtnUnspecificTimestamp.isSelected());
	}
	
}
