package GUI.pages.society.filterPage;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import book.content.Book;
import book.person.Person;
import book.person.Race;

import javax.swing.*;
import java.awt.*;

public class Card_FilterRace extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final TransparentPanel panel_filterResult;

	public Card_FilterRace() {
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
			panel_filterResult.add(new LinkButton(person.getInformation().getName(),
					e -> BookEditorFrame.getInstance().openPersonPage(person, false)));
		}
		
		panel_filterResult.revalidate();
		panel_filterResult.repaint();
	}

}
