package GUI.sectionPage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import GUI.bookeditorFrame.BookEditorFrame;
import GUI.personPage.PersonEditorPage;
import GUI.sectionChangePage.SectionEditorPage;
import GUI.worldPage.PlaceEditor;
import GUI_components.InfoButton;
import GUI_components.PageMenu;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.DevelopmentStatus;
import book.Section;
import person.Person;
import person.Relationship;
import world.Place;

public class SectionInformationMenu extends PageMenu {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	private JButton btnEdit;
	
	private SimpleLabel lblDevStatus;
	private InfoButton hint_devStatus;
	
	public SectionInformationMenu(Section section) {
		super("Section Information");
		my_section = section;
		
		
		btnEdit = this.addButtonToTopMenu("Change");
		if(my_section == null) {
			btnEdit.setEnabled(false);
			btnEdit.setToolTipText("You have to create a section first.");
		}
		btnEdit.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionEditorPage(my_section)));
		
		//***************************************************************************************************************************
		this.addBetweenTitle("General Information");
		String name = my_section.getName();
		if(name.length() > 17) {
			name = name.substring(0,15) + "...";
			//TODO: W needs more space, then i, so name has cut by 17 instead of 25 letters! Maybe can get string-spacelength instead of letter-count
		}
		this.addText("Name: " + name);
		if(my_section.hasTimestamp()) {
			this.addText("Timestamp: " + my_section.getTimestamp().toCompleteString());
		} else {
			this.addText("Timestamp: ??");
		}
		
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
		this.addBetweenTitle("Person Tags:");
		if(my_section != null) {
			List<Person> personTags = my_section.getPersonByTag();
			for(Person person : personTags) {
				JButton personButton = this.addLinkedListButton(person.getInformation().getName());
				personButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PersonEditorPage(person)));
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
				JButton placeButton = this.addLinkedListButton(tag.getName());
				placeButton.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(tag)));
			}
		}

	}
	
	private void changeDevStatus(int newDevStatus) {
		lblDevStatus.setText(my_section.getDevelopmentStatusToString());
		hint_devStatus.setToolTipText(my_section.getDevelopmentStatusDescription());
	}
}
