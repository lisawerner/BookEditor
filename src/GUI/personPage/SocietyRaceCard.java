package GUI.personPage;

import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import person.Person;
import person.Race;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;

import GUI.bookeditorFrame.BookEditorFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class SocietyRaceCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public SocietyRaceCard() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Race race : Book.getInstance().getSociety().getRaces()) {			
			TransparentPanel panel_race_overview = new TransparentPanel();
			add(panel_race_overview);
			panel_race_overview.setLayout(new BorderLayout(0, 0));
			
			TransparentPanel panel_race = new TransparentPanel();
			panel_race_overview.add(panel_race, BorderLayout.NORTH);
			panel_race.setLayout(new BoxLayout(panel_race, BoxLayout.X_AXIS));
			
			SimpleLabel lblMemberListOf = new SimpleLabel("Member List of Race: ");
			panel_race.add(lblMemberListOf);
			
			LinkButton btnLinkbuttonrace = new LinkButton(race.getName());
			panel_race.add(btnLinkbuttonrace);
			btnLinkbuttonrace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new RaceEditorPage(race)));
			
			TransparentPanel panel_raceMembers = new TransparentPanel();
			panel_race_overview.add(panel_raceMembers, BorderLayout.CENTER);
			panel_raceMembers.setLayout(new GridLayout(0, 5, 2, 2));
			
			for(Person raceMember : Book.getInstance().getSociety().getPersonListByRace(race)) {				
				LinkButton btnPerson = new LinkButton(raceMember.getInformation().getName());
				panel_raceMembers.add(btnPerson);
				btnPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(raceMember, false)));
			}
			
			JSeparator separator = new JSeparator();
			add(separator);
		}
		

	}

}
