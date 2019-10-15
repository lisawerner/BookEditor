package GUI.personPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.Page;
import GUI_components.SimpleLabel;
import GUI_components.SimpleRadiobutton;
import GUI_components.SimpleTextarea;
import GUI_components.SimpleTextfield;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import book.Section;
import global.ObjectID;
import global.UserSettings;
import person.Person;
import person.Relationship;

public class PersonEditorPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Person my_person = null;
	
	private AgeCard personsAgeCard;
	private SimpleTextfield txt_name;

	private SimpleLabel lblName;

	public PersonEditorPage(Person person) {
		super("Persons, Relationships, ...");
		
		my_person = person;
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().createFirstPerson) {			
			addCard(new TutorialCard(11, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPerson && !UserSettings.getInstance().getTutorial().addFurtherPersons) {			
			addCard(new TutorialCard(12, true));
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			addCard(new TutorialCard(13, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			addCard(new TutorialCard(15, true));
		}
		
		//************************************************************************
		StructureCard card_name = new StructureCard("Person Name");
		this.addCard(card_name);
		
		TransparentPanel changeName_body = new TransparentPanel();
		card_name.setBody(changeName_body);
		changeName_body.setLayout(new BorderLayout(5, 5));
		
		lblName = new SimpleLabel("Name:");
		changeName_body.add(lblName, BorderLayout.WEST);
		
		txt_name = new SimpleTextfield();
		if(my_person != null) {txt_name.setText(my_person.getName());}
		changeName_body.add(txt_name, BorderLayout.CENTER);
		
		
		//*********************************************************************************
		StructureCard card_age = new StructureCard("Person Age");
		this.addCard(card_age);
		personsAgeCard = new AgeCard(my_person);
		card_age.setBody(personsAgeCard);
		
		//*********************************************************************************
		StructureCard card_mainStatus = new StructureCard("Character Status");
		this.addCard(card_mainStatus);
		
		TransparentPanel panel_mainStatus = new TransparentPanel();
		card_mainStatus.setBody(panel_mainStatus);
		panel_mainStatus.setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_setStatus = new TransparentPanel();
		panel_setStatus.setLayout(new BoxLayout(panel_setStatus, BoxLayout.LINE_AXIS));
		panel_mainStatus.add(panel_setStatus);
		
		ButtonGroup btngroup_mainstatus = new ButtonGroup();
		SimpleRadiobutton rdbtn_superMain = new SimpleRadiobutton("Most important Main-Character");
		if(my_person != null) {rdbtn_superMain.setSelected(my_person.isSuperMainChar());}
		btngroup_mainstatus.add(rdbtn_superMain);
		panel_setStatus.add(rdbtn_superMain);
		SimpleRadiobutton rdbtn_onlyOften = new SimpleRadiobutton("Charckters with main-role");
		if(my_person != null) {rdbtn_onlyOften.setSelected(my_person.isFrequentlyChar());}
		btngroup_mainstatus.add(rdbtn_onlyOften);
		panel_setStatus.add(rdbtn_onlyOften);
		SimpleRadiobutton rdbtn_somebody = new SimpleRadiobutton("Somebody");
		if(my_person != null) {rdbtn_somebody.setSelected(my_person.isSomebody());}
		btngroup_mainstatus.add(rdbtn_somebody);
		panel_setStatus.add(rdbtn_somebody);
		
		//*********************************************************************************
		StructureCard card_Notes = new StructureCard("Notes about Person");
		this.addCard(card_Notes);
		
		TransparentPanel panel_skills = new TransparentPanel();
		card_Notes.setBody(panel_skills);
		panel_skills.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblNotesskills = new SimpleLabel("Notes (Skills, Job, ...):");
		panel_skills.add(lblNotesskills, BorderLayout.NORTH);
		
		SimpleTextarea txt_notes = new SimpleTextarea();
		if(my_person != null) {txt_notes.setText(my_person.getNotes());}
		panel_skills.add(txt_notes, BorderLayout.CENTER);

		//*********************************************************************************
		StructureCard card_tagRefs = new StructureCard("Person was tagged in following sections");
		if(my_person != null) {this.addCard(card_tagRefs);}
			
		TransparentPanel panel_sectionRefList = new TransparentPanel();
		card_tagRefs.setBody(panel_sectionRefList);
		panel_sectionRefList.setLayout(new BoxLayout(panel_sectionRefList, BoxLayout.LINE_AXIS));
		
		if(my_person != null) {			
			for(Section section : Book.getInstance().getSectionList().getSections()) {
				if(section.hasTag(my_person.getID())) {					
					LinkButton btnSectionTag = new LinkButton(section.getName());
					btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section)));
					panel_sectionRefList.add(btnSectionTag);
					panel_sectionRefList.add(new SimpleLabel(";  "));
				}
			}
		}
		
		
		//*********************************************************************************
		StructureCard card_Relationships = new StructureCard("Person has following Relationships");
		if(my_person != null) {this.addCard(card_Relationships);}
		
		TransparentPanel relationListPanel = new TransparentPanel();
		card_Relationships.setBody(relationListPanel);
		relationListPanel.setLayout(new GridLayout(0, 1, 5, 5));
		
		if(my_person != null) {			
			for(ObjectID relID : my_person.getRelationships()) {	
				Relationship relship = Book.getInstance().getRelationship(relID);
				TransparentPanel relationPanel = new TransparentPanel();
				relationListPanel.add(relationPanel);
				relationPanel.setLayout(new BoxLayout(relationPanel, BoxLayout.LINE_AXIS));
				relationPanel.add(new SimpleLabel("Has Relationship '" + relship.getDescribingRelationshipType() + "' with Person "));
				Person relPerson = Book.getInstance().getPerson(relship.getOtherPerson(my_person.getID()));
				LinkButton lbtnRelPerson = new LinkButton(relPerson.getName());
				relationPanel.add(lbtnRelPerson);
				lbtnRelPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(relPerson)));
			}
		}
		
		
		//*********************************************************************************
		//*********************************************************************************
		TransparentPanel footer = new TransparentPanel();
		this.setFooter(footer);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblWarning = new SimpleLabel(" ");
		lblWarning.setForeground(Color.RED);
		footer.add(lblWarning);

		JButton btnSave = new JButton("Save Person");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblWarning.setText(" ");
				boolean canSave = true;
				String newAge = personsAgeCard.getAge();
				boolean ageFromBookstart = personsAgeCard.getAgeBookstart();
				boolean ageFirstEnter = personsAgeCard.getAgeBookstart();
				
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
						Book.getInstance().addPerson(my_person);
						if(!UserSettings.getInstance().getTutorial().createFirstPerson) {
							UserSettings.getInstance().getTutorial().createFirstPerson = true;
							UserSettings.getInstance().save();
						}
						BookEditorFrame.getInstance().switchBody(new PersonEditorPage(my_person));
						
					} else {
						my_person.editPerson(name, newAge, ageFromBookstart, ageFirstEnter, rdbtn_superMain.isSelected(), rdbtn_onlyOften.isSelected(), 
								txt_notes.getText());
						Book.getInstance().changePerson(my_person.getID(), my_person);
						BookEditorFrame.getInstance().switchBody(new PersonEditorPage(my_person));
					}
					lblWarning.setText("<html>" + saveMessage + "<br>Successfully saved</html>");
				}	
			}
		});
		footer.add(btnSave);
		
		setMenu(new PersonMenu());
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_name.setBorder(BorderFactory.createLineBorder(Color.RED));
			lblName.setForeground(Color.RED);
		} else {
			txt_name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblName.setForeground(Color.BLACK);
		}
	}

}
