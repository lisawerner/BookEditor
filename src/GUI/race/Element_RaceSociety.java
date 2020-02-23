package GUI.race;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import global.ObjectID;
import person.Person;
import person.Race;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Element_RaceSociety extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Element_RaceSociety(Race race) {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		TransparentPanel panel_parent = new TransparentPanel();
		add(panel_parent);
		panel_parent.setLayout(new BoxLayout(panel_parent, BoxLayout.X_AXIS));
		
		LinkButton btnLinkbuttonrace = new LinkButton(race.getName());
		panel_parent.add(btnLinkbuttonrace);
		btnLinkbuttonrace.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(race)));
		
		if(race.getSubtypes().isEmpty()){
			TransparentPanel panel_subtypeMemberlist = new TransparentPanel();
			panel_subtypeMemberlist.setLayout(new BoxLayout(panel_subtypeMemberlist, BoxLayout.LINE_AXIS));
			add(panel_subtypeMemberlist);
			
			SimpleLabel lblMemberListOf = new SimpleLabel("Member-List: ");
			panel_subtypeMemberlist.add(lblMemberListOf);
			
			TransparentPanel panel_raceMembers = new TransparentPanel();
			add(panel_raceMembers, BorderLayout.CENTER);
			panel_raceMembers.setLayout(new FlowLayout(FlowLayout.LEADING));
			//panel_raceMembers.setLayout(new GridLayout(0, 5, 2, 2));
			
			Component rigidArea = Box.createRigidArea(new Dimension(20, 0));
			panel_raceMembers.add(rigidArea, BorderLayout.WEST);
			
			for(Person raceMember : Book.getInstance().getSociety().getPersonListByRace(race)) {				
				LinkButton btnPerson = new LinkButton(raceMember.getInformation().getName());
				panel_raceMembers.add(btnPerson);
				btnPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(raceMember, false)));
				panel_raceMembers.add(new SimpleLabel(","));
			}
		} else {
			for(ObjectID typeID : race.getSubtypes()){			
				Race subtype = Book.getInstance().getSociety().getRace(typeID);
				
				TransparentPanel panel_subtypeMemberlist = new TransparentPanel();
				panel_subtypeMemberlist.setLayout(new BoxLayout(panel_subtypeMemberlist, BoxLayout.LINE_AXIS));
				add(panel_subtypeMemberlist);
				
				SimpleLabel lblMemberListOf = new SimpleLabel("Member-List of: ");
				panel_subtypeMemberlist.add(lblMemberListOf);
				
				LinkButton btnSubtype = new LinkButton(subtype.getName());
				panel_subtypeMemberlist.add(btnSubtype);
				btnSubtype.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_EditRace(subtype)));
				
				TransparentPanel panel_raceMembers = new TransparentPanel();
				add(panel_raceMembers, BorderLayout.CENTER);
				panel_raceMembers.setLayout(new FlowLayout(FlowLayout.LEADING));
				//panel_raceMembers.setLayout(new GridLayout(0, 5, 2, 2));
				
				Component rigidArea = Box.createRigidArea(new Dimension(20, 0));
				panel_raceMembers.add(rigidArea, BorderLayout.WEST);
				
				ArrayList<Person> representantiveList = Book.getInstance().getSociety().getPersonListByRace(subtype);
				if(representantiveList.isEmpty()){
					panel_raceMembers.add(new SimpleLabel("Race does not have any member"));
				} else {					
					for(Person raceMember : representantiveList) {
						LinkButton btnPerson = new LinkButton(raceMember.getInformation().getName());
						panel_raceMembers.add(btnPerson);
						btnPerson.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(raceMember, false)));
						panel_raceMembers.add(new SimpleLabel(","));
					}
				}
			}
		}
		
		
	}
	
}
