package GUI.pages.filterPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import book.Book;
import book.Section;
import GUI.components.LinkButton;
import GUI.components.SimpleCheckbox;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import person.Person;

public class FilterPersonCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private TransparentPanel panel_listOfFilteredSections;
	
	private ArrayList<Person> selectedPersons;
	private boolean selectAND;

	public FilterPersonCard() {
		setLayout(new BorderLayout(5, 5));
		
		selectedPersons = new ArrayList<Person>();
		selectAND = true;
		
		TransparentPanel panel_listOfPersons = new TransparentPanel();
		
		add(panel_listOfPersons, BorderLayout.NORTH);
		panel_listOfPersons.setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_generalSettings = new TransparentPanel();
		panel_listOfPersons.add(panel_generalSettings, BorderLayout.NORTH);
		panel_generalSettings.setLayout(new GridLayout(0, 1, 0, 0));
		
		SimpleCheckbox rdbtnAndOr = new SimpleCheckbox("Select only Sections with all Characters tagged inside");
		panel_generalSettings.add(rdbtnAndOr);
		rdbtnAndOr.setSelected(selectAND);
		rdbtnAndOr.addActionListener(e -> setSelectAND(rdbtnAndOr.isSelected()));
		
		TransparentPanel panel_FilterSettings = new TransparentPanel();
		panel_listOfPersons.add(panel_FilterSettings, BorderLayout.CENTER);
		panel_FilterSettings.setLayout(new GridLayout(0, 3, 5, 5));
		
		
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			SimpleRadiobutton boxbtn_person = new SimpleRadiobutton(person.getInformation().getNickname());
			panel_FilterSettings.add(boxbtn_person);
			boxbtn_person.addActionListener(e -> addPersonToFilterlist(person, boxbtn_person.isSelected()));
		}
		
		panel_listOfFilteredSections = new TransparentPanel();
		add(panel_listOfFilteredSections, BorderLayout.CENTER);
		panel_listOfFilteredSections.setLayout(new GridLayout(0, 5, 5, 5));
	}
	
	private void setSelectAND(boolean selected) {
		selectAND = selected;
		
		filterSectionsByPersons();
	}
	
	private void addPersonToFilterlist(Person person, boolean add) {
		if(add) {
			selectedPersons.add(person);
		} else {
			selectedPersons.remove(person);
		}
		
		filterSectionsByPersons();
	}

	private void filterSectionsByPersons() {		
		panel_listOfFilteredSections.removeAll();
		panel_listOfFilteredSections.setLayout(new GridLayout(0, 5, 5, 5));
		if(selectedPersons.size() > 0) {			
			for(Section section :  Book.getInstance().getTableOfContent().getSectionsByPersons(selectedPersons, selectAND)) {
				LinkButton sectionBTN = new LinkButton(section.getName());
				sectionBTN.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section, Book.getInstance().getTableOfContent().getChapter(section.getParentChapterID()))));
				panel_listOfFilteredSections.add(sectionBTN);
			}
		}
		panel_listOfFilteredSections.revalidate();
		panel_listOfFilteredSections.repaint();
	}

}
