package GUI.sectionChangePage;

import GUI_components.Page;
import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import book.Section;
import global.Tag;
import global.UserSettings;
import person.Person;
import person.Relationship;
import world.Place;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class SectionEditorPage extends Page {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	private TransparentPanel card_relationship_body;

	public SectionEditorPage( Section section) {
		super("Edit Section: " + section.getName());
		my_section = section;
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			addCard(new TutorialCard(9, false));
		}
		if(UserSettings.getInstance().getTutorial().addFurtherPersons && !UserSettings.getInstance().getTutorial().tagPersonToSection) {			
			addCard(new TutorialCard(14, false));
		}
		if(UserSettings.getInstance().getTutorial().setMapDependencies && !UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline) {			
			addCard(new TutorialCard(19, false));
		}
		
		//********************************************************************************
		StructureCard card_sectionTitle = new StructureCard("Section Title");
		addCard(card_sectionTitle);
		card_sectionTitle.setBody(new SectionTitleCard(my_section));
		
		
		//********************************************************************************
		StructureCard card_timestamp = new StructureCard("Change Timestamp");
		addCard(card_timestamp);
		card_timestamp.setBody(new SectionTimestampCard(my_section));
		
		
		//********************************************************************************
		StructureCard card_personTags = new StructureCard("Change Person-Tags");
		addCard(card_personTags);

		TransparentPanel panel_personTagList = new TransparentPanel();
		card_personTags.setBody(panel_personTagList);
		for(Person person : Book.getInstance().getSociety().getPersonList()) {
			SimpleCheckbox chckbxPerson = new SimpleCheckbox(person.getName());
			panel_personTagList.add(chckbxPerson);			
			chckbxPerson.setSelected(my_section.hasTag(person.getID()));
			chckbxPerson.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxPerson.isSelected()) {			
						//System.out.println("Click: Is Selected now");
						my_section.addTag(new Tag(person.getID(), person.getClass().getName()));
					} else {
						//System.out.println("Click: Is NOOOTTT Selected anymore");
						my_section.removeTag(person.getID());
					}
				}
			});
		}
		

		//********************************************************************************
		StructureCard card_Relationships = new StructureCard("Change Relationships");
		addCard(card_Relationships);
		card_relationship_body = new TransparentPanel();
		card_relationship_body.setLayout(new BoxLayout(card_relationship_body, BoxLayout.PAGE_AXIS));
		card_Relationships.setBody(card_relationship_body);
		card_relationship_body.add(new SectionRelationships(this, my_section, null));
		for(Relationship relship : my_section.getRelationships()) {
			card_relationship_body.add(new SectionRelationships(this, my_section, relship));
		}


		//********************************************************************************
		StructureCard card_placeTags = new StructureCard("Change Place-Tags");
		addCard(card_placeTags);
		// &#8627; -> Arrow
		TransparentPanel panel_placeTagList = new TransparentPanel();
		card_placeTags.setBody(panel_placeTagList);
		panel_placeTagList.setLayout(new BoxLayout(panel_placeTagList, BoxLayout.PAGE_AXIS));
		for(Place place : Book.getInstance().getWorld().getPlaces()) {
			if(place.getParentRef() == null) {
				TransparentPanel parentPlacePanel = new TransparentPanel();
				parentPlacePanel.setLayout(new GridLayout(0, 1, 5, 5));
				panel_placeTagList.add(parentPlacePanel);
				SimpleCheckbox chckbxPlace = new SimpleCheckbox(place.getName());
				parentPlacePanel.add(chckbxPlace);			
				chckbxPlace.setSelected(my_section.hasTag(place.getID()));
				chckbxPlace.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(chckbxPlace.isSelected()) {			
							//System.out.println("Click: Is Selected now");
							my_section.addTag(new Tag(place.getID(), place.getClass().getName()));
						} else {
							//System.out.println("Click: Is NOOOTTT Selected anymore");
							my_section.removeTag(place.getID());
						}
					}
				});
				addChildren(parentPlacePanel, place, "&emsp; &#8627;");

			}
		}		
		
		
		//********************************************************************************
		JButton btnBackToSection = new JButton("Back to Section");
		btnBackToSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book.getInstance().save();
				if(!UserSettings.getInstance().getTutorial().tagPersonToSection) {
					UserSettings.getInstance().getTutorial().tagPersonToSection = true;
					UserSettings.getInstance().save();
				}
				BookEditorFrame.getInstance().switchBody(new SectionPage(my_section));
			}
		});
		setFooter(btnBackToSection);

	}

	public void addRelationship(Relationship newRelationship) {
		card_relationship_body.add(new SectionRelationships(this, my_section, newRelationship));
		revalidate();
		repaint();
	}
	
	private void addChildren(TransparentPanel parentPanel, Place parent, String distance) {
		for(Place child : parent.getChildrenObject()) {
			TransparentPanel distancePanel = new TransparentPanel();
			distancePanel.setLayout(new BorderLayout(5, 5));
			parentPanel.add(distancePanel);
			SimpleLabel lblDistance = new SimpleLabel("<html>" + distance + "</html>");
			distancePanel.add(lblDistance, BorderLayout.WEST);
			SimpleCheckbox chckbxPlace = new SimpleCheckbox(child.getName());
			distancePanel.add(chckbxPlace, BorderLayout.CENTER);			
			chckbxPlace.setSelected(my_section.hasTag(child.getID()));
			chckbxPlace.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxPlace.isSelected()) {			
						//System.out.println("Click: Is Selected now");
						my_section.addTag(new Tag(child.getID(), child.getClass().getName()));
					} else {
						//System.out.println("Click: Is NOOOTTT Selected anymore");
						my_section.removeTag(child.getID());
					}
				}
			});
			String newDistance = "&emsp; " + distance;
			addChildren(parentPanel, child, newDistance);
		}
	}

}
