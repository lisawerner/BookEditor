package GUI.sectionTimestamp;

import book.Book;
import book.Section;
import global.UserSettings;
import time.RelativeDate;
import time.SpecificDate;
import time.Timestamp;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.Page;
import GUI_components.SimpleLabel;
import GUI_components.SimpleRadiobutton;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SectionTimeEditor extends Page {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;

	
	private SimpleRadiobutton rdbtnSpecificTimestamp;
	private SimpleRadiobutton rdbtnUnspecificTimestamp;

	private TimestampSpecificEditor panel_specificBODY;
	private TimestampRelativeEditor panel_unspecificBODY;

	public SectionTimeEditor(Section section) {
		super("Change Timestamp of Section: " + section.getName());
		my_section = section;
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			addCard(new TutorialCard(9, false));
		}
		
		//*****************************************************************************************************
		StructureCard card_information = new StructureCard("Helpful Information");
		addCard(card_information);
		
		TransparentPanel panel_preSection = new TransparentPanel();
		card_information.setBody(panel_preSection);
		System.out.println();
		Section preSection = Book.getInstance().getSectionList().getPreSection(my_section);
		if(preSection != null) {		
			if(preSection.hasTimestamp()) {				
				panel_preSection.setLayout(new BoxLayout(panel_preSection, BoxLayout.LINE_AXIS));
				panel_preSection.add(new SimpleLabel("Section before '"));
				LinkButton btnPresection = new LinkButton(preSection.getName());
				btnPresection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(preSection)));
				panel_preSection.add(btnPresection);
				panel_preSection.add(new SimpleLabel("' has Timestamp: " + preSection.getTimestamp().toCompleteString()));
			}
		}

		
		//*****************************************************************************************************
		StructureCard card_setTimestamp = new StructureCard("Set Timestamp for Section '" + my_section.getName() + "'");
		addCard(card_setTimestamp);
		//TODO: Change Forumlar, if Custom Calendar is selected!
		
		TransparentPanel panel_BODY = new TransparentPanel();
		card_setTimestamp.setBody(panel_BODY);
		panel_BODY.setLayout(new GridLayout(1, 0, 20, 20));
		
		ButtonGroup btngrTimestampType = new ButtonGroup();
		
		TransparentPanel panel_unspecific = new TransparentPanel();
		panel_BODY.add(panel_unspecific);
		panel_unspecific.setLayout(new BorderLayout(10, 10));
		
		rdbtnUnspecificTimestamp = new SimpleRadiobutton("Unspecific Timestamp");
		rdbtnUnspecificTimestamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchSpecificAndUnspecific();
			}
		});
		btngrTimestampType.add(rdbtnUnspecificTimestamp);
		panel_unspecific.add(rdbtnUnspecificTimestamp, BorderLayout.NORTH);
		
		panel_unspecificBODY = new TimestampRelativeEditor(my_section);
		if(my_section.hasTimestamp()) {
			if(!my_section.getTimestamp().isSpecificDate()) {
				panel_unspecificBODY.activate(my_section.getTimestamp().getUnspecificDate());
				rdbtnUnspecificTimestamp.setSelected(true);
				panel_unspecificBODY.switchEnabled(true);
			}
		}
		panel_unspecific.add(panel_unspecificBODY, BorderLayout.CENTER);
		
		//*****************************************************************************************************
		
		TransparentPanel panel_specific = new TransparentPanel();
		panel_BODY.add(panel_specific);
		panel_specific.setLayout(new BorderLayout(10, 10));
		
		rdbtnSpecificTimestamp = new SimpleRadiobutton("Specific Timestamp");
		rdbtnSpecificTimestamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchSpecificAndUnspecific();
			}
		});
		btngrTimestampType.add(rdbtnSpecificTimestamp);
		panel_specific.add(rdbtnSpecificTimestamp, BorderLayout.NORTH);
				
		panel_specificBODY = new TimestampSpecificEditor(my_section);
		panel_specific.add(panel_specificBODY, BorderLayout.CENTER);
		
		if(my_section.hasTimestamp()) {
			if(my_section.getTimestamp().isSpecificDate()) {
				rdbtnSpecificTimestamp.setSelected(true);
				panel_specificBODY.switchEnabled(true);
			}
		}
			
		//*****************************************************************************************************
		TransparentPanel panel_FOOTER = new TransparentPanel();
		setFooter(panel_FOOTER);
		panel_FOOTER.setLayout(new BorderLayout(5, 5));
		
		JButton btnSaveTimestamp = new JButton("Save Timestamp");
		panel_FOOTER.add(btnSaveTimestamp, BorderLayout.SOUTH);
		
		SimpleLabel lblSaveWarning = new SimpleLabel(" ");
		panel_FOOTER.add(lblSaveWarning, BorderLayout.NORTH);
		btnSaveTimestamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					BookEditorFrame.getInstance().switchBody(new SectionPage(my_section));
				}
			}
		});
		
		switchSpecificAndUnspecific();
	}
	

	
	private void switchSpecificAndUnspecific() {
		//System.out.println("Set Specific Time: " + rdbtnSpecificTimestamp.isSelected());
		panel_specificBODY.switchEnabled(rdbtnSpecificTimestamp.isSelected());
		//System.out.println("Set UNspecific Time: " + rdbtnUnspecificTimestamp.isSelected());
		panel_unspecificBODY.switchEnabled(rdbtnUnspecificTimestamp.isSelected());
	}
	
}

