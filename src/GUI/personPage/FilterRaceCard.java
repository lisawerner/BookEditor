package GUI.personPage;

import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.SimpleRadiobutton;
import GUI_components.TransparentPanel;
import book.Book;
import person.Person;
import person.Race;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import GUI.bookeditorFrame.BookEditorFrame;

public class FilterRaceCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final TransparentPanel panel_filterResult;

	public FilterRaceCard() {
		setLayout(new BorderLayout(0, 0));
		
		ButtonGroup btnGroup_races = new ButtonGroup();
		
		TransparentPanel panel_selectFilter = new TransparentPanel();
		add(panel_selectFilter, BorderLayout.NORTH);
		
		SimpleRadiobutton rdbtnPersonWithoutRace = new SimpleRadiobutton("Person without Race");
		btnGroup_races.add(rdbtnPersonWithoutRace);
		rdbtnPersonWithoutRace.addActionListener(e -> showPersonsRace(null));
		panel_selectFilter.add(rdbtnPersonWithoutRace);
		
		this.add(new SimpleLabel("Person with Race:"));
		
		for(Race race : Book.getInstance().getSociety().getRaces()) {
			SimpleRadiobutton rdbtnRace = new SimpleRadiobutton(race.getName());
			rdbtnRace.addActionListener(e -> showPersonsRace(race));
			panel_selectFilter.add(rdbtnRace);
			btnGroup_races.add(rdbtnRace);
		}
		
		panel_filterResult = new TransparentPanel();
		panel_filterResult.setLayout(new GridLayout(5, 0, 5, 5));
		add(panel_filterResult, BorderLayout.CENTER);
	}

	private void showPersonsRace(Race race) {
		panel_filterResult.removeAll();
		panel_filterResult.setLayout(new GridLayout(0, 5, 5, 5));
		
		for(Person person : Book.getInstance().getSociety().getPersonListByRace(race)) {
			LinkButton sectionBTN = new LinkButton(person.getInformation().getName());
			sectionBTN.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person, false)));
			panel_filterResult.add(sectionBTN);
		}
		
		panel_filterResult.revalidate();
		panel_filterResult.repaint();
	}

}
