package GUI.sectionChangePage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import GUI.pages.content.viewSection.Page_ViewSection;
import book.Book;
import book.Chapter;
import book.Section;
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
		//TODO: Change Form, if Custom Calendar is selected!
		
		
		//*****************************************************************************************************************
		TransparentPanel panel_helpfulInformationInNorth = new TransparentPanel();
		add(panel_helpfulInformationInNorth, BorderLayout.NORTH);
		Section preSection = my_section.getPreSection();
		if(preSection != null) {			
			if(preSection.hasTimestamp()) {				
				panel_helpfulInformationInNorth.setLayout(new BoxLayout(panel_helpfulInformationInNorth, BoxLayout.LINE_AXIS));
				panel_helpfulInformationInNorth.add(new SimpleLabel("Section before '"));
				panel_helpfulInformationInNorth.add(new LinkButton(preSection.getName(),
						e -> BookEditorFrame.getInstance().switchBody(new Page_ViewSection(preSection, chapter))));
				panel_helpfulInformationInNorth.add(new SimpleLabel("' has Timestamp: " + Book.getInstance().getTimeline().getTimestamp(preSection.getTimestampID()).toCompleteString()));
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
		panel_switchableBody.setLayout(new GridLayout(1,0,5,5));
		
		panel_unspecificBODY = new TimestampRelativeEditor(my_section);
		panel_switchableBody.add(panel_unspecificBODY);
		
		panel_specificBODY = new TimestampSpecificEditor(my_section);
		panel_switchableBody.add(panel_specificBODY);
		btnSaveTimestamp.addActionListener(e -> {
			boolean canSave = true;
			lblSaveWarning.setText(" ");

			Timestamp newTimestamp = null;
			if(rdbtnUnspecificTimestamp.isSelected()) {
				newTimestamp = panel_unspecificBODY.getResult();
			} else if(rdbtnSpecificTimestamp.isSelected()) {
				newTimestamp = panel_specificBODY.getResult();
			} else {
				//TODO: Add Warning-Question-Window, before SAVING NOTHING?! :D
				lblSaveWarning.setText("You have selected Nothing! -> Timestamp will remove from Section!");
				canSave = false;
			}

			if(canSave) {
				if(my_section.getTimestampID() == null){
					//Save new one? Then save also in Timeline of Book!
					Book.getInstance().getTimeline().addTimestamp(newTimestamp);
				} else if(!my_section.getTimestampID().equals(newTimestamp.getID())){
					//Save new one because of switch between specific and relative? Then delete old one ans save new one in Timeline of Book!
					Book.getInstance().getTimeline().addTimestamp(newTimestamp);
					Book.getInstance().getTimeline().removeTimestamp(my_section.getTimestampID());
				}
				my_section.setTimestampID(newTimestamp.getID());
				Book.getInstance().save();
				lblSaveWarning.setText("New Timestamp was successfully saved!");
			}
		});
		
		if(my_section.hasTimestamp()) {
			Timestamp time = Book.getInstance().getTimeline().getTimestamp(my_section.getTimestampID());
			if(!time.isSpecificDate()) {
				panel_unspecificBODY.activate(time.getUnspecificDate());
				rdbtnUnspecificTimestamp.setSelected(true);
			} else {
				rdbtnSpecificTimestamp.setSelected(true);
			}
		}
		
		switchSpecificAndUnspecific();
	}

	private void switchSpecificAndUnspecific() {
		panel_specificBODY.switchEnabled(rdbtnSpecificTimestamp.isSelected());
		panel_unspecificBODY.switchEnabled(rdbtnUnspecificTimestamp.isSelected());
	}
	
}
