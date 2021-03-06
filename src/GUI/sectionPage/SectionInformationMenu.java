package GUI.sectionPage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.pages.chapter.Page_viewChapter;
import GUI.pages.society.personEditorPage.Page_PersonEditor;
import GUI.sectionChangePage.SectionEditorPage;
import GUI.worldPage.PlaceEditor;
import book.Book;
import book.Chapter;
import book.DevelopmentStatus;
import book.Section;
import global.UserSettings;
import GUI.components.InfoButton;
import GUI.components.MenuListButton;
import GUI.components.PageMenu;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import person.Person;
import person.Relationship;
import time.Timestamp;
import world.Place;

public class SectionInformationMenu extends PageMenu {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	private Chapter my_parentChapter;
	
	private JButton btnEdit;
	
	private SimpleLabel lblDevStatus;
	private InfoButton hint_devStatus;
	
	public SectionInformationMenu(Section section, Chapter chapter) {
		super("Section Information");
		my_section = section;
		my_parentChapter = chapter;
		
		btnEdit = this.addButtonToTopMenu("Change", e -> openSectionEditor());
		if(my_section == null) {
			btnEdit.setEnabled(false);
			btnEdit.setToolTipText("You have to create a section first.");
		}
		
		//***************************************************************************************************************************
		this.addBetweenTitle("General Information");
		String name = my_section.getName();
		if(name.length() > 17) {
			name = name.substring(0,15) + "...";
			//TODO: W needs more space, then i, so name has cut by 17 instead of 25 letters! Maybe can get string-spacelength instead of letter-count
		}
		this.addText("Name: " + name);
		if(my_section.hasTimestamp()) {
			Timestamp timestamp = Book.getInstance().getTimeline().getTimestamp(section.getTimestampID());
			String timestampString = timestamp.toCompleteString();
			if(timestampString != null){				
				this.addText("Timestamp: " + timestampString);		
			} else {
				Section timeSection = timestamp.getUnspecificDate().getRelationSection();
				if(timeSection != null){					
					this.addText("Timestamp relates to: ");
					this.addLinkedListButton(new MenuListButton(timeSection.getName(), e -> this.openOtherSection(timeSection)));
				} else {
					this.addText("Timestamp: ??");
				}
			}
		} else {
			this.addText("Timestamp: ??");
		}
		this.addText("Parent Chapter:");
		this.addLinkedListButton(new MenuListButton(my_parentChapter.getTitle(), e -> BookEditorFrame.getInstance().switchBody(new Page_viewChapter(my_parentChapter))));
		
		//***************************************************************************************************************************
		this.addBetweenTitle("Current Development Status:");
		TransparentPanel devStatusPanel = new TransparentPanel();
		devStatusPanel.setLayout(new BorderLayout(2, 2));
		this.addComplexMenuItem(devStatusPanel);
		lblDevStatus = new SimpleLabel("");
		devStatusPanel.add(lblDevStatus, BorderLayout.CENTER);
		hint_devStatus = new InfoButton(DevelopmentStatus.getDevStatDescription(-1));
		hint_devStatus.setMenuInfo();
		devStatusPanel.add(hint_devStatus, BorderLayout.WEST);
		if(my_section != null) {
			changeDevStatus(my_section.getDevelopmentStatus());
		}
		
		//***************************************************************************************************************************
		this.addBetweenTitle("Comments:");
		ArrayList<String> splittedNotes = splitNotes();
		for(String notePart : splittedNotes) {
			this.addText(notePart);
		}

		//***************************************************************************************************************************
		this.addBetweenTitle("Person Tags:");
		if(my_section != null) {
			List<Person> personTags = my_section.getPersonByTag();
			for(Person person : personTags) {
				this.addLinkedListButton(new MenuListButton(person.getInformation().getNickname(), e -> openPersonEditor(person)));
			}
		}
		
		//***************************************************************************************************************************
		this.addBetweenTitle("Switched Relationships:");
		if(my_section != null) {
			ArrayList<Relationship> relationshipTags = my_section.getRelationships();
			for(Relationship relationship : relationshipTags) {
				this.addText(relationship.getSwitchToString());
			}
		}
		
		this.addBetweenTitle("Place Tags:");
		if(my_section != null) {
		List<Place> placeTags = my_section.getPelaceByTag();
			for(Place tag : placeTags) {
				this.addLinkedListButton(new MenuListButton(tag.getName(), e -> openPlaceEditor(tag)));
			}
		}

	}
	
	private void openOtherSection(Section otherSection) {
		BookEditorFrame.getInstance().switchBody(new SectionPage(otherSection, Book.getInstance().getTableOfContent().getChapter(otherSection.getParentChapterID())));
	}

	private void openPlaceEditor(Place place) {
		if(UserSettings.getInstance().getDisplaySettings()) {
			BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_parentChapter), new PlaceEditor(place, true));
		} else {
			BookEditorFrame.getInstance().openPlacePage(place, false);	
		}
	}

	private void openPersonEditor(Person person) {
		if(UserSettings.getInstance().getDisplaySettings()) {
			BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_parentChapter), new Page_PersonEditor(person, true));
		} else {
			BookEditorFrame.getInstance().openPersonPage(person, false);	
		}
	}

	private void openSectionEditor() {
		if(UserSettings.getInstance().getDisplaySettings()) {
			BookEditorFrame.getInstance().switchBody(new SectionPage(my_section, my_parentChapter), new SectionEditorPage(my_section, my_parentChapter));
		} else {
			BookEditorFrame.getInstance().switchBody(new SectionEditorPage(my_section, my_parentChapter));			
		}
	}

	private ArrayList<String> splitNotes() {
		ArrayList<String> splittedNotes = new ArrayList<String>();
		String notes = my_section.getNotes();
		int noteLength = notes.length();
		//TODO: W needs more space, then i, so notes has cut by 17 instead of 25 letters! Maybe can get string-spacelength instead of letter-count
		while(noteLength > 0) {
			if(noteLength > 20) {
				//TODO: Den Text irgendwie sinnvoll trennen o.O; Eigentlich wären ja textArea mit wrap-Funktion sinnvoll, aber dann geht das GridLayout nicht mehr...
				String newPart = notes.substring(0,18) + "...";
				splittedNotes.add(newPart);
				notes = notes.substring(18);
				noteLength = notes.length();
			} else {
				splittedNotes.add(notes);
				noteLength = 0;
			}
		}
		return splittedNotes;
	}

	private void changeDevStatus(int newDevStatus) {
		lblDevStatus.setText(my_section.getDevelopmentStatusToString());
		hint_devStatus.setToolTipText(my_section.getDevelopmentStatusDescription());
	}
}
