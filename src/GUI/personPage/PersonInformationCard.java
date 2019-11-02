package GUI.personPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.SimpleLabel;
import GUI_components.SimpleRadiobutton;
import GUI_components.SimpleTextarea;
import GUI_components.SimpleTextfield;
import GUI_components.TransparentPanel;
import book.Book;
import global.UserSettings;
import person.Person;

public class PersonInformationCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person = null;
	
	private SimpleTextfield txt_name;
	private SimpleLabel lblName;
	
	private SimpleTextfield txt_age;
	private SimpleRadiobutton rdbtnAgeBookstart;
	private SimpleRadiobutton rdbtnAgeFirstAppeareance;
	
	public PersonInformationCard(Person person) {
		my_person = person;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel changeName_body = new TransparentPanel();
		changeName_body.setLayout(new BorderLayout(5, 5));
		this.add(changeName_body);
		
		lblName = new SimpleLabel("Name:");
		changeName_body.add(lblName, BorderLayout.WEST);
		
		txt_name = new SimpleTextfield();
		if(my_person != null) {txt_name.setText(my_person.getInformation().getName());}
		changeName_body.add(txt_name, BorderLayout.CENTER);
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel panel_mainStatus = new TransparentPanel();
		this.add(panel_mainStatus);
		panel_mainStatus.setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_setStatus = new TransparentPanel();
		panel_setStatus.setLayout(new BoxLayout(panel_setStatus, BoxLayout.LINE_AXIS));
		panel_mainStatus.add(panel_setStatus);
		
		ButtonGroup btngroup_mainstatus = new ButtonGroup();
		SimpleRadiobutton rdbtn_superMain = new SimpleRadiobutton("Most important Main-Character");
		if(my_person != null) {rdbtn_superMain.setSelected(my_person.getInformation().isSuperMainChar());}
		btngroup_mainstatus.add(rdbtn_superMain);
		panel_setStatus.add(rdbtn_superMain);
		SimpleRadiobutton rdbtn_onlyOften = new SimpleRadiobutton("Charckters with main-role");
		if(my_person != null) {rdbtn_onlyOften.setSelected(my_person.getInformation().isFrequentlyChar());}
		btngroup_mainstatus.add(rdbtn_onlyOften);
		panel_setStatus.add(rdbtn_onlyOften);
		SimpleRadiobutton rdbtn_somebody = new SimpleRadiobutton("Somebody");
		if(my_person != null) {rdbtn_somebody.setSelected(my_person.getInformation().isSomebody());}
		btngroup_mainstatus.add(rdbtn_somebody);
		panel_setStatus.add(rdbtn_somebody);
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel panel_age = new TransparentPanel();
		panel_age.setLayout(new BorderLayout(0, 0));
		this.add(panel_age);
		
		SimpleLabel lblAge = new SimpleLabel("Age:");
		panel_age.add(lblAge, BorderLayout.NORTH);
		
		txt_age = new SimpleTextfield();
		if(my_person != null) {txt_age.setText(my_person.getInformation().getAge());}
		panel_age.add(txt_age, BorderLayout.CENTER);
		
		TransparentPanel panel_ageValues = new TransparentPanel();
		panel_age.add(panel_ageValues, BorderLayout.SOUTH);
		panel_ageValues.setLayout(new GridLayout(0, 1, 0, 0));
		
		ButtonGroup btngrAge = new ButtonGroup();
		
		rdbtnAgeBookstart = new SimpleRadiobutton("...at beginning of the book");
		if(my_person != null) {rdbtnAgeBookstart.setSelected(my_person.getInformation().getAgeBookStart());}
		panel_ageValues.add(rdbtnAgeBookstart);
		btngrAge.add(rdbtnAgeBookstart);
		
		rdbtnAgeFirstAppeareance = new SimpleRadiobutton("... when person appear first time in book");
		if(my_person != null) {rdbtnAgeFirstAppeareance.setSelected(my_person.getInformation().getAgeFirstAppear());}
		panel_ageValues.add(rdbtnAgeFirstAppeareance);
		btngrAge.add(rdbtnAgeFirstAppeareance);		
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel panel_skills = new TransparentPanel();
		this.add(panel_skills);
		panel_skills.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblNotesskills = new SimpleLabel("Notes (Skills, Job, ...):");
		panel_skills.add(lblNotesskills, BorderLayout.NORTH);
		
		SimpleTextarea txt_notes = new SimpleTextarea();
		if(my_person != null) {txt_notes.setText(my_person.getInformation().getNotes());}
		panel_skills.add(txt_notes, BorderLayout.CENTER);
		
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel footer = new TransparentPanel();
		this.add(footer);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblWarning = new SimpleLabel(" ");
		lblWarning.setWarning(true);
		footer.add(lblWarning);
		
		JButton btnSave = new JButton("Save Person");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblWarning.setText(" ");
				boolean canSave = true;
				String newAge = txt_age.getText();
				boolean ageFromBookstart = rdbtnAgeBookstart.isSelected();
				boolean ageFirstEnter = rdbtnAgeFirstAppeareance.isSelected();
				
				if(!"".equals(newAge)) {
					if(!ageFromBookstart && !ageFirstEnter) {
						canSave = false;
						lblWarning.setText("WARNING: Can not save person, because age is set, but not the relation to timeline!");
					}
				}
				
				setWarningEnterName(false);
				String name = txt_name.getText();				
				if(name.equals("")) {
					lblWarning.setText("WARNING: Can not save person, because no name was entered!");
					setWarningEnterName(true);
					canSave = false;
				}
				
				if(canSave) {
					String saveMessage = "";
					if(my_person == null) {
						my_person = new Person(name, newAge, ageFromBookstart, ageFirstEnter, rdbtn_superMain.isSelected(), rdbtn_onlyOften.isSelected(), 
								txt_notes.getText());
						Book.getInstance().getSociety().addPerson(my_person);
						if(!UserSettings.getInstance().getTutorial().createFirstPerson) {
							UserSettings.getInstance().getTutorial().createFirstPerson = true;
							UserSettings.getInstance().save();
						}
						BookEditorFrame.getInstance().switchBody(new PersonEditorPage(my_person));
						
					} else {
						my_person.getInformation().editInformation(name, newAge, ageFromBookstart, ageFirstEnter, rdbtn_superMain.isSelected(), rdbtn_onlyOften.isSelected(), 
								txt_notes.getText());
					}
					lblWarning.setText("<html>" + saveMessage + "<br>Successfully saved</html>");
				}	
			}
		});
		footer.add(btnSave);

	}
		
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_name.setWarning(warning);
			lblName.setWarning(warning);
		} else {
			txt_name.setWarning(warning);
			lblName.setWarning(warning);
		}
	}

}
