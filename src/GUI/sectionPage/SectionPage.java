package GUI.sectionPage;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI.worldPage.WorldPage;
import GUI_components.InfoButton;
import GUI_components.MenuPage;
import GUI_components.Page;
import GUI_components.SimpleTextfield;
import GUI_components.StructureCard;
import GUI_components.Submenu;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import book.Section;
import global.UserSettings;
import person.Person;
import person.Relationship;
import world.Place;

import java.util.ArrayList;

public class SectionPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	private SimpleTextfield txt_sectionTitle;
	private JLabel lbl_saveWarning;
	private JLabel lblChapterTitle;
	private JButton btnEdit;
	
	public SectionPage(Section section) {
		super("Edit Section");
		my_section = section;
				
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			addCard(new TutorialCard(4, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstSection && !UserSettings.getInstance().getTutorial().setDevelopmentStatus) {		
			if(my_section != null) {				
				addCard(new TutorialCard(6, false));
			}
		}
		if(UserSettings.getInstance().getTutorial().setDevelopmentStatus && !UserSettings.getInstance().getTutorial().sortSectionsAndChapters) {			
			addCard(new TutorialCard(7, false));
		}
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			if(my_section != null) {
				addCard(new TutorialCard(9, false));
			}
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			addCard(new TutorialCard(13, false));
		}
		if(UserSettings.getInstance().getTutorial().tagPersonToSection && !UserSettings.getInstance().getTutorial().viewPersons) {			
			addCard(new TutorialCard(15, true));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			addCard(new TutorialCard(19, false));
		}
		
		//****************************************************************************************
		StructureCard card_sectionTitle = new StructureCard("Section Title");
		addCard(card_sectionTitle);
				
		TransparentPanel panel_changeTitle = new TransparentPanel();
		card_sectionTitle.setBody(panel_changeTitle);
		panel_changeTitle.setLayout(new BorderLayout(5, 5));
		
		txt_sectionTitle = new SimpleTextfield();
		if(my_section != null) {txt_sectionTitle.setText(my_section.getName());}
		panel_changeTitle.add(txt_sectionTitle);
		
		lblChapterTitle = new JLabel("Section Title:");
		panel_changeTitle.add(lblChapterTitle, BorderLayout.WEST);
		
		InfoButton btnNewButton = new InfoButton("<html>Title is only shown in table of content and not in Text.<br/>You can change the title every time.</html>");
		panel_changeTitle.add(btnNewButton, BorderLayout.EAST);
		
		TransparentPanel panel_footer = new TransparentPanel();
		panel_changeTitle.add(panel_footer, BorderLayout.SOUTH);
		panel_footer.setLayout(new BorderLayout(0, 0));
		
		JButton btnSaveChapter = new JButton("Create new Section");
		if(my_section != null) {btnSaveChapter.setText("Save new Title");}
		panel_footer.add(btnSaveChapter, BorderLayout.SOUTH);
		
		lbl_saveWarning = new JLabel(" ");
		panel_footer.add(lbl_saveWarning, BorderLayout.NORTH);
		lbl_saveWarning.setForeground(Color.RED);
		
		btnSaveChapter.addActionListener(e -> save());

		
		//****************************************************************************************
		StructureCard card_sectionFurtherInformation = new StructureCard("Section Information");
		if(my_section != null) {			
			addCard(card_sectionFurtherInformation);
		}
		card_sectionFurtherInformation.setBody(new SectionInformationCard(my_section));
		//****************************************************************************************
		StructureCard card_sectionText = new StructureCard("Section Content");
		if(my_section != null) {			
			addCard(card_sectionText);
		}
		card_sectionText.setBody(new EditSectiontextCard(my_section));
		//****************************************************************************************


		//****************************************************************************************
		MenuPage menu_tags = new MenuPage("Tags of this Section");
		add(menu_tags, BorderLayout.EAST);

		Submenu submenu_changeTags = new Submenu("");
		menu_tags.addSubmenu(submenu_changeTags);
		btnEdit = submenu_changeTags.addButton("Change");
		if(my_section == null) {
			btnEdit.setEnabled(false);
			btnEdit.setToolTipText("You have to create a section first.");
		}
		btnEdit.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionTagEditor(my_section)));
		
		Submenu submenu_personTags = new Submenu("Person Tags:");
		menu_tags.addSubmenu(submenu_personTags);
		submenu_personTags.activateList();
		if(my_section != null) {
			ArrayList<Person> personTags = my_section.getPersonByTag();
			for(Person person : personTags) {
				JButton personButton = submenu_personTags.addListButton(person.getName());
				personButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
			}
		}
		
		Submenu submenu_relationshipsTags = new Submenu("Switched Relationships:");
		menu_tags.addSubmenu(submenu_relationshipsTags);
		if(my_section != null) {
			ArrayList<Relationship> relationshipTags = my_section.getRelationships();
			for(Relationship relationship : relationshipTags) {
				submenu_relationshipsTags.addText(relationship.getSwitchToString(Book.getInstance()));
			}
		}

		Submenu submenu_placeTags = new Submenu("Place Tags:");
		menu_tags.addSubmenu(submenu_placeTags);
		submenu_placeTags.activateList();
		if(my_section != null) {
		ArrayList<Place> placeTags = my_section.getPelaceByTag();
			for(Place tag : placeTags) {
				JButton placeButton = submenu_placeTags.addListButton(tag.getName());
				placeButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new WorldPage(tag)));
				//TODO: Open THIS place
			}
		}
	}
	
	private void save() {
		//TODO: Do this also, when STRG+S is clicked!
		lbl_saveWarning.setText(" ");
		lbl_saveWarning.setForeground(Color.RED);
		boolean canSave = true;
		
		setWarningEnterName(false);
		if(txt_sectionTitle.getText().equals("")) {
			lbl_saveWarning.setText("Can not save section. Please enter a title for placing it in table of content.");
			setWarningEnterName(true);
			canSave = false;
		}
		
		if(canSave) {
			lbl_saveWarning.setForeground(Color.BLACK);
			if(my_section == null) {
				my_section = new Section(txt_sectionTitle.getText());
				Book.getInstance().getSectionList().addSection(my_section);
				if(!UserSettings.getInstance().getTutorial().createFirstSection) {
					UserSettings.getInstance().getTutorial().createFirstSection = true;
					UserSettings.getInstance().save();
				}
			} else {
				my_section.setTitle(txt_sectionTitle.getText());
				Book.getInstance().getSectionList().editSection(my_section);
			}
			btnEdit.setEnabled(true);
			btnEdit.setToolTipText("");
			lbl_saveWarning.setText("Section was successfully saved");
			BookEditorFrame.getInstance().reloadMenu();
			BookEditorFrame.getInstance().switchBody(new SectionPage(my_section));
		}
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_sectionTitle.setBorder(BorderFactory.createLineBorder(Color.RED));
			lblChapterTitle.setForeground(Color.RED);
		} else {
			txt_sectionTitle.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			lblChapterTitle.setForeground(Color.BLACK);
		}
	}


}
