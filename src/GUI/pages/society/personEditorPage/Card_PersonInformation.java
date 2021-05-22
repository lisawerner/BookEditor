package GUI.pages.society.personEditorPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import book.Book;
import global.ObjectID;
import global.UserSettings;
import person.Person;
import person.Race;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Card_PersonInformation extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person;
	
	private final SimpleTextField txt_name;
	private final SimpleLabel lblName;
	private final SimpleTextField txtNickname;
	
	private final SimpleTextField txt_age;
	private final SimpleRadiobutton rdbtnAgeBookStart;
	private final SimpleRadiobutton rdbtnAgeFirstAppearance;
	private final SimpleCheckbox checkboxDiedBeforeThe;
	private final SimpleLabel lblDeathForHow;
	private final SimpleTextField txt_deathInYears;
	
	private final SimpleTextarea txt_notes;
	private final SimpleRadiobutton rdbtn_superMain;
	private final SimpleRadiobutton rdbtn_onlyOften;
	private final SimpleRadiobutton rdbtn_somebody;
	
	private ObjectID selectedRace = null;
	
	private final SimpleLabel lblWarning;
	
	public Card_PersonInformation(Person person) {
		my_person = person;
		if(Book.getInstance().getSociety().isRaceSystemActivated()) {
			if(my_person != null) {				
				selectedRace = my_person.getInformation().getRace();
			}
		}
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel changeName_body = new TransparentPanel();
		changeName_body.setLayout(new BorderLayout(5, 5));
		this.add(changeName_body);
		
		TransparentPanel panel_nameTextFields = new TransparentPanel();
		changeName_body.add(panel_nameTextFields, BorderLayout.CENTER);
		panel_nameTextFields.setLayout(new GridLayout(0, 1, 2, 2));
		
		txt_name = new SimpleTextField();
		panel_nameTextFields.add(txt_name);
		if(my_person != null) {txt_name.setText(my_person.getInformation().getName());}
		txt_name.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void removeUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void insertUpdate(DocumentEvent e) {
				updateSaveHint();
			}
		});
		
		TransparentPanel panel_nameLabels = new TransparentPanel();
		changeName_body.add(panel_nameLabels, BorderLayout.WEST);
		panel_nameLabels.setLayout(new GridLayout(0, 1, 2, 2));
		
		lblName = new SimpleLabel("Name:");
		panel_nameLabels.add(lblName);
		
		SimpleLabel lblNickname = new SimpleLabel("Nickname:");
		panel_nameLabels.add(lblNickname);
		
		
		txtNickname = new SimpleTextField();
		panel_nameTextFields.add(txtNickname);
		if(my_person != null) {
			txtNickname.setText(my_person.getInformation().getRealNickname());}
		txtNickname.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void removeUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void insertUpdate(DocumentEvent e) {
				updateSaveHint();
			}
		});
		
		InfoButton hint_nickname = new InfoButton("<html>If it is possible always the nickname will shown instead of the full name<br/>"
				+ "everywhere outside the Person-Menu.</html>");
		changeName_body.add(hint_nickname, BorderLayout.EAST);
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel panel_mainStatus = new TransparentPanel();
		this.add(panel_mainStatus);
		panel_mainStatus.setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_setStatus = new TransparentPanel();
		panel_setStatus.setLayout(new BoxLayout(panel_setStatus, BoxLayout.LINE_AXIS));
		panel_mainStatus.add(panel_setStatus);
		
		ButtonGroup btnGroup_mainStatus = new ButtonGroup();
		rdbtn_superMain = new SimpleRadiobutton("Most important Main-Character");
		if(my_person != null) {rdbtn_superMain.setSelected(my_person.getInformation().isSuperMainChar());}
		btnGroup_mainStatus.add(rdbtn_superMain);
		panel_setStatus.add(rdbtn_superMain);
		rdbtn_superMain.addActionListener(e -> updateSaveHint());
		rdbtn_onlyOften = new SimpleRadiobutton("Characters with main-role");
		if(my_person != null) {rdbtn_onlyOften.setSelected(my_person.getInformation().isFrequentlyChar());}
		btnGroup_mainStatus.add(rdbtn_onlyOften);
		panel_setStatus.add(rdbtn_onlyOften);
		rdbtn_onlyOften.addActionListener(e -> updateSaveHint());
		rdbtn_somebody = new SimpleRadiobutton("Somebody");
		if(my_person == null) {rdbtn_somebody.setSelected(true);}
		if(my_person != null) {rdbtn_somebody.setSelected(my_person.getInformation().isSomebody());}
		btnGroup_mainStatus.add(rdbtn_somebody);
		panel_setStatus.add(rdbtn_somebody);
		rdbtn_somebody.addActionListener(e -> updateSaveHint());
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel panel_age = new TransparentPanel();
		panel_age.setLayout(new BorderLayout(0, 0));
		this.add(panel_age);
		
		SimpleLabel lblAge = new SimpleLabel("Age:");
		panel_age.add(lblAge, BorderLayout.NORTH);
		
		txt_age = new SimpleTextField();
		if(my_person != null) {txt_age.setText(my_person.getInformation().getAge());}
		panel_age.add(txt_age, BorderLayout.CENTER);
		txt_age.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void removeUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void insertUpdate(DocumentEvent e) {
				updateSaveHint();
			}
		});
		
		TransparentPanel panel_ageValues = new TransparentPanel();
		panel_age.add(panel_ageValues, BorderLayout.SOUTH);
		panel_ageValues.setLayout(new GridLayout(0, 1, 0, 0));
		
		ButtonGroup btngrAge = new ButtonGroup();
		
		rdbtnAgeBookStart = new SimpleRadiobutton("...at beginning of the book");
		if(my_person != null) {
			rdbtnAgeBookStart.setSelected(my_person.getInformation().getAgeBookStart());}
		panel_ageValues.add(rdbtnAgeBookStart);
		btngrAge.add(rdbtnAgeBookStart);
		rdbtnAgeBookStart.addActionListener(e -> updateSaveHint());
		
		rdbtnAgeFirstAppearance = new SimpleRadiobutton("... when person appear first time in book");
		if(my_person != null) {
			rdbtnAgeFirstAppearance.setSelected(my_person.getInformation().getAgeFirstAppear());}
		panel_ageValues.add(rdbtnAgeFirstAppearance);
		btngrAge.add(rdbtnAgeFirstAppearance);
		rdbtnAgeFirstAppearance.addActionListener(e -> updateSaveHint());
		
		//*********************************************************************************
		//*********************************************************************************
		
		TransparentPanel panel_death = new TransparentPanel();
		add(panel_death);
		panel_death.setLayout(new BorderLayout(2, 2));
		
		checkboxDiedBeforeThe = new SimpleCheckbox("Died before the beginning of the book");
		if(my_person != null) {
			checkboxDiedBeforeThe.setSelected(my_person.getInformation().isDeadBeforeBookStart());}
		panel_death.add(checkboxDiedBeforeThe, BorderLayout.NORTH);
		checkboxDiedBeforeThe.addActionListener(e -> enableDeathPanel());
		
		lblDeathForHow = new SimpleLabel("Death for how many years:");
		panel_death.add(lblDeathForHow, BorderLayout.WEST);
		
		txt_deathInYears = new SimpleTextField();
		panel_death.add(txt_deathInYears, BorderLayout.CENTER);
		
		enableDeathPanel();
		if(my_person != null) {
			if(my_person.getInformation().isDeadBeforeBookStart()) {
				txt_deathInYears.setText(my_person.getInformation().getDeathTime());
			}
		}
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel panel_skills = new TransparentPanel();
		this.add(panel_skills);
		panel_skills.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblNotesSkills = new SimpleLabel("Notes (Skills, Job, ...):");
		panel_skills.add(lblNotesSkills, BorderLayout.NORTH);
		
		txt_notes = new SimpleTextarea();
		if(my_person != null) {txt_notes.setText(my_person.getInformation().getNotes());}
		panel_skills.add(txt_notes, BorderLayout.CENTER);
		txt_notes.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void removeUpdate(DocumentEvent e) {
				updateSaveHint();
			}
			public void insertUpdate(DocumentEvent e) {
				updateSaveHint();
			}
		});
		
		
		//*********************************************************************************
		TransparentPanel panel_raceSelection = new TransparentPanel();
		if(Book.getInstance().getSociety().isRaceSystemActivated()) {add(panel_raceSelection);}
		
		SimpleLabel lblCharacterRace = new SimpleLabel("Character Race:");
		panel_raceSelection.add(lblCharacterRace);
		
		ButtonGroup btnGroup_races = new ButtonGroup();
		
		for(Race race : Book.getInstance().getSociety().getRaces()) {
			TransparentPanel panel_singleRace = new TransparentPanel();
			panel_singleRace.setLayout(new BoxLayout(panel_singleRace, BoxLayout.LINE_AXIS));
			panel_raceSelection.add(panel_singleRace);
			
			SimpleRadiobutton checkbox_race = new SimpleRadiobutton(race.getName());
			ObjectID parentRace = race.getParentRace();
			if(parentRace != null){
				checkbox_race.setText("[" + Book.getInstance().getSociety().getRace(parentRace).getName() + ":] " + race.getName());
			}
			panel_singleRace.add(checkbox_race);
			btnGroup_races.add(checkbox_race);
			checkbox_race.addActionListener(e -> setRace(race));
			if(my_person != null) {				
				if(my_person.getInformation().getRace() != null) {				
					checkbox_race.setSelected(my_person.getInformation().getRace().equals(race.getID()));
				}
			}
			panel_singleRace.add(new LinkButton("<html>&#8663</html>",
					e -> BookEditorFrame.getInstance().openRacePage(race)));
		}
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel footer = new TransparentPanel();
		this.add(footer);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		lblWarning = new SimpleLabel(" ");
		lblWarning.setWarning(true);
		footer.add(lblWarning);
		
		JButton btnSave = new JButton("Save Person");
		btnSave.addActionListener(e -> save());
		footer.add(btnSave);

	}
		
	private void setRace(Race race) {
		selectedRace = race.getID();
	}

	private void setWarningEnterName(boolean warning) {
		txt_name.setWarning(warning);
		lblName.setWarning(warning);
	}
	
	private void updateSaveHint() {
		lblWarning.setText(" ");
		
		if(my_person != null) {
			if(!txt_name.getText().equals(my_person.getInformation().getName())
					|| !txtNickname.getText().equals(my_person.getInformation().getRealNickname())
					|| !txt_age.getText().equals(my_person.getInformation().getAge())
					|| !txt_notes.getText().equals(my_person.getInformation().getNotes())
					|| (rdbtn_superMain.isSelected() && !my_person.getInformation().isSuperMainChar())
					||(rdbtn_onlyOften.isSelected() && !my_person.getInformation().isFrequentlyChar())
					||(rdbtn_somebody.isSelected() && !my_person.getInformation().isSomebody())
					||(rdbtnAgeBookStart.isSelected() && !my_person.getInformation().getAgeBookStart())
					||(rdbtnAgeFirstAppearance.isSelected() && !my_person.getInformation().getAgeFirstAppear())
					) {
				lblWarning.setText("Attention: Unsaved changes!");
			}
		}
	}
	
	private void enableDeathPanel() {
		txt_deathInYears.setEnabled(checkboxDiedBeforeThe.isSelected());
		lblDeathForHow.setEnabled(checkboxDiedBeforeThe.isSelected());
	}
	
	private void save() {
		lblWarning.setText(" ");
		boolean canSave = true;
		String newAge = txt_age.getText();
		boolean ageFromBookStart = rdbtnAgeBookStart.isSelected();
		boolean ageFirstEnter = rdbtnAgeFirstAppearance.isSelected();
		String newNickname = txtNickname.getText();
		
		if(!"".equals(newAge)) {
			if(!ageFromBookStart && !ageFirstEnter) {
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
				my_person = new Person(name, newNickname,
						newAge, ageFromBookStart, ageFirstEnter, checkboxDiedBeforeThe.isSelected(), txt_deathInYears.getText(),
						rdbtn_superMain.isSelected(), rdbtn_onlyOften.isSelected(), 
						txt_notes.getText(), selectedRace);
				Book.getInstance().getSociety().addPerson(my_person);
				if(!UserSettings.getInstance().getTutorial().createFirstPerson) {
					UserSettings.getInstance().getTutorial().createFirstPerson = true;
					UserSettings.getInstance().save();
				}
				BookEditorFrame.getInstance().openPersonPage(my_person, false);
				
			} else {
				my_person.getInformation().editInformation(name, newNickname, 
						newAge, ageFromBookStart, ageFirstEnter, checkboxDiedBeforeThe.isSelected(), txt_deathInYears.getText(),
						rdbtn_superMain.isSelected(), rdbtn_onlyOften.isSelected(), 
						txt_notes.getText(), selectedRace, my_person.getID());
			}
			lblWarning.setText("<html>" + saveMessage + "<br>Successfully saved</html>");
		}	
	}

}
