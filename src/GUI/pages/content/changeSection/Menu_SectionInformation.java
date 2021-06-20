package GUI.pages.content.changeSection;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import GUI.pages.content.viewChapter.Page_viewChapter;
import GUI.pages.society.personEditorPage.Page_PersonEditor;
import GUI.pages.world.viewPlace.Page_viewPlace;
import GUI.sectionChangePage.SectionEditorPage;
import book.content.Book;
import book.content.Chapter;
import book.content.DevelopmentStatus;
import book.content.Section;
import global.UserSettings;
import book.person.Person;
import book.person.Relationship;
import book.time.Timestamp;
import book.world.Place;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Menu_SectionInformation extends PageMenu {
	private static final long serialVersionUID = 1L;

	private final Section my_section;
	private final Chapter my_parentChapter;

	private final SimpleLabel lblDevStatus;
	private final InfoButton hint_devStatus;
	
	public Menu_SectionInformation(Section section, Chapter chapter) {
		super("Section Information");
		my_section = section;
		my_parentChapter = chapter;

		this.addButtonToTopMenu("Change", e -> openSectionEditor());
		
		//***************************************************************************************************************************
		this.addBetweenTitle("General Information");
		String name = my_section.getName();
		if(name.length() > 17) {
			name = name.substring(0,15) + "...";
			//TODO: W needs more space, then i, so name has cut by 17 instead of 25 letters! Maybe can get string-space-length instead of letter-count
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
		changeDevStatus();

		//***************************************************************************************************************************
		this.addBetweenTitle("Comments:");
		ArrayList<String> splitNotes = splitNotes();
		for(String notePart : splitNotes) {
			this.addText(notePart);
		}

		//***************************************************************************************************************************
		this.addBetweenTitle("Person Tags:");
		List<Person> personTags = my_section.getPersonByTag();
		for(Person person : personTags) {
			this.addLinkedListButton(new MenuListButton(person.getInformation().getNickname(), e -> openPersonEditor(person)));
		}

		//***************************************************************************************************************************
		this.addBetweenTitle("Switched Relationships:");
		ArrayList<Relationship> relationshipTags = my_section.getRelationships();
		for(Relationship relationship : relationshipTags) {
			this.addText(relationship.getSwitchToString());
		}

		this.addBetweenTitle("Place Tags:");
		List<Place> placeTags = my_section.getPlaceByTag();
		for(Place tag : placeTags) {
			this.addLinkedListButton(new MenuListButton(tag.getName(), e -> openPlaceEditor(tag)));
		}

	}
	
	private void openOtherSection(Section otherSection) {
		BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(otherSection, Book.getInstance().getTableOfContent().getChapter(otherSection.getParentChapterID())));
	}

	private void openPlaceEditor(Place place) {
		if(UserSettings.getInstance().getDisplaySettings()) {
			BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(my_section, my_parentChapter), new Page_viewPlace(place, true));
		} else {
			BookEditorFrame.getInstance().openPlacePage(place, false);	
		}
	}

	private void openPersonEditor(Person person) {
		if(UserSettings.getInstance().getDisplaySettings()) {
			BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(my_section, my_parentChapter), new Page_PersonEditor(person, true));
		} else {
			BookEditorFrame.getInstance().openPersonPage(person, false);	
		}
	}

	private void openSectionEditor() {
		if(UserSettings.getInstance().getDisplaySettings()) {
			BookEditorFrame.getInstance().switchBody(new Page_ChangeSection(my_section, my_parentChapter), new SectionEditorPage(my_section, my_parentChapter));
		} else {
			BookEditorFrame.getInstance().switchBody(new SectionEditorPage(my_section, my_parentChapter));
		}
	}

	private ArrayList<String> splitNotes() {
		ArrayList<String> splitNotes = new ArrayList<>();
		String notes = my_section.getNotes();
		int noteLength = notes.length();
		//TODO: W needs more space, then i, so notes has cut by 17 instead of 25 letters! Maybe can get string-space-length instead of letter-count
		while(noteLength > 0) {
			if(noteLength > 20) {
				//TODO: Is it possible to split the text mor useful?? o.O
				// A text area with wrap-Function could be useful, but it does not work with the GridLayout...
				String newPart = notes.substring(0,18) + "...";
				splitNotes.add(newPart);
				notes = notes.substring(18);
				noteLength = notes.length();
			} else {
				splitNotes.add(notes);
				noteLength = 0;
			}
		}
		return splitNotes;
	}

	private void changeDevStatus() {
		lblDevStatus.setText(my_section.getDevelopmentStatusToString());
		hint_devStatus.setToolTipText(my_section.getDevelopmentStatusDescription());
	}
}
