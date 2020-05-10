package GUI.race;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import global.ObjectID;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;
import person.Person;
import person.Race;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class Element_RaceSociety extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Element_RaceSociety(Race race) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_parent = new TransparentPanel();
		add(panel_parent);
		panel_parent.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		LinkButton btnLinkbuttonrace = new LinkButton(race.getName());
		panel_parent.add(btnLinkbuttonrace);
		btnLinkbuttonrace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(race)));
		
		if(race.getSubtypes().isEmpty()){
			TransparentPanel panel_subtypeMemberlist = new TransparentPanel();
			panel_subtypeMemberlist.setLayout(new FlowLayout(FlowLayout.LEADING));
			add(panel_subtypeMemberlist);
			
			SimpleLabel lblMemberListOf = new SimpleLabel("Member-List: ");
			panel_subtypeMemberlist.add(lblMemberListOf);
			
			TransparentPanel panel_raceMembers = new TransparentPanel();
			add(panel_raceMembers, BorderLayout.CENTER);
			fillRaceList(panel_raceMembers, race);
			
		} else {
			for(ObjectID typeID : race.getSubtypes()){			
				Race subtype = Book.getInstance().getSociety().getRace(typeID);
				
				TransparentPanel panel_subtypeMemberlist = new TransparentPanel();
				panel_subtypeMemberlist.setLayout(new FlowLayout(FlowLayout.LEADING));
				add(panel_subtypeMemberlist);
				
				SimpleLabel lblMemberListOf = new SimpleLabel("Member-List of: ");
				panel_subtypeMemberlist.add(lblMemberListOf);
				
				LinkButton btnSubtype = new LinkButton(subtype.getName());
				panel_subtypeMemberlist.add(btnSubtype);
				btnSubtype.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(subtype)));
				
				TransparentPanel panel_raceMembers = new TransparentPanel();
				add(panel_raceMembers, BorderLayout.CENTER);
				fillRaceList(panel_raceMembers, subtype);
				
			}
		}
	}
	
	private void fillRaceList(TransparentPanel panel_raceMembers, Race race){
		//panel_raceMembers.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		//panel_raceMembers.setLayout(new GridLayout(0, 5, 2, 2));
		
//		panel_raceMembers.setPreferredSize(new Dimension(100, 200));
		
//		Dimension d = panel_raceMembers.getPreferredSize();
//		d.width = 10;
//		panel_raceMembers.setPreferredSize(d);
		panel_raceMembers.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 0));
		panel_raceMembers.add(rigidArea, BorderLayout.WEST);
		
		ArrayList<Person> representantiveList = Book.getInstance().getSociety().getPersonListByRace(race);
		if(representantiveList.isEmpty()){
			panel_raceMembers.add(new SimpleLabel("Race does not have any member"));
		} else {					
			for(Person raceMember : representantiveList) {
				LinkButton btnPerson = new LinkButton(raceMember.getInformation().getName());
				panel_raceMembers.add(btnPerson);
				btnPerson.addActionListener(e -> BookEditorFrame.getInstance().openPersonPage(raceMember, false));
				panel_raceMembers.add(new SimpleLabel(","));
			}
		}
	}
	
}
