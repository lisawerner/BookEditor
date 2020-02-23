package GUI.personPage;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.race.Page_EditRace;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import person.Person;
import person.Race;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class SociatyRaceElement extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public SociatyRaceElement(Race race) {
		setLayout(new BorderLayout(0, 0));
		
		TransparentPanel panel_race = new TransparentPanel();
		add(panel_race, BorderLayout.NORTH);
		panel_race.setLayout(new BoxLayout(panel_race, BoxLayout.Y_AXIS));
		
		LinkButton btnLinkbuttonrace = new LinkButton(race.getName());
		panel_race.add(btnLinkbuttonrace);
		btnLinkbuttonrace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(race)));
		
		SimpleLabel lblMemberListOf = new SimpleLabel("Member-List: ");
		panel_race.add(lblMemberListOf);
		
		TransparentPanel panel_raceMembers = new TransparentPanel();
		add(panel_raceMembers, BorderLayout.CENTER);
		panel_raceMembers.setLayout(new FlowLayout(FlowLayout.LEADING));
		//panel_raceMembers.setLayout(new GridLayout(0, 5, 2, 2));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, BorderLayout.WEST);
		
		for(Person raceMember : Book.getInstance().getSociety().getPersonListByRace(race)) {				
			LinkButton btnPerson = new LinkButton(raceMember.getInformation().getName());
			panel_raceMembers.add(btnPerson);
			btnPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(raceMember, false)));
			panel_raceMembers.add(new SimpleLabel(","));
		}
	}

}
