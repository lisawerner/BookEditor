package GUI.pages.society;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;
import book.Book;
import global.ObjectID;
import person.Person;
import person.Race;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Element_RaceSociety extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Element_RaceSociety(Race race) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_parent = new TransparentPanel();
		add(panel_parent);
		panel_parent.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		panel_parent.add(new LinkButton(race.getName(),
				e -> BookEditorFrame.getInstance().openRacePage(race)));
		
		if(race.getSubtypes().isEmpty()){
			TransparentPanel panel_subtypeMemberList = new TransparentPanel();
			panel_subtypeMemberList.setLayout(new FlowLayout(FlowLayout.LEADING));
			add(panel_subtypeMemberList);
			
			SimpleLabel lblMemberListOf = new SimpleLabel("Member-List: ");
			panel_subtypeMemberList.add(lblMemberListOf);
			
			TransparentPanel panel_raceMembers = new TransparentPanel();
			add(panel_raceMembers, BorderLayout.CENTER);
			fillRaceList(panel_raceMembers, race);
			
		} else {
			for(ObjectID typeID : race.getSubtypes()){			
				Race subtype = Book.getInstance().getSociety().getRace(typeID);
				
				TransparentPanel panel_subtypeMemberList = new TransparentPanel();
				panel_subtypeMemberList.setLayout(new FlowLayout(FlowLayout.LEADING));
				add(panel_subtypeMemberList);
				
				SimpleLabel lblMemberListOf = new SimpleLabel("Member-List of: ");
				panel_subtypeMemberList.add(lblMemberListOf);
				
				panel_subtypeMemberList.add(new LinkButton(subtype.getName(),
						e -> BookEditorFrame.getInstance().openRacePage(subtype)));
				
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
		
		ArrayList<Person> representativeList = Book.getInstance().getSociety().getPersonListByRace(race);
		if(representativeList.isEmpty()){
			panel_raceMembers.add(new SimpleLabel("Race does not have any member"));
		} else {					
			for(Person raceMember : representativeList) {
				panel_raceMembers.add(new LinkButton(raceMember.getInformation().getName(),
						e -> BookEditorFrame.getInstance().openPersonPage(raceMember, false)));
				panel_raceMembers.add(new SimpleLabel(","));
			}
		}
	}
	
}
