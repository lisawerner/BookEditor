package GUI.worldPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.ComboItem;
import GUI_components.InfoButton;
import GUI_components.LinkButton;
import GUI_components.Page;
import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.SimpleTextfield;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import book.Section;
import global.ObjectID;
import global.UserSettings;
import world.Place;
import java.awt.GridLayout;
import javax.swing.JComboBox;

public class PlaceEditor extends Page {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	private SimpleTextfield txt_placename;
	private SimpleLabel lblPlacename;
	private SimpleTextfield txtPlacetype;
	private JComboBox<ComboItem> comboBox;
	private SimpleCheckbox chckbxHasParent;
	private TransparentPanel panel_taglist;
	
	public PlaceEditor(Place place) {
		super("World, States, Regions, Citys, Places, ...");
		my_place = place;
		
		if(UserSettings.getInstance().getTutorial().viewPersons && !UserSettings.getInstance().getTutorial().createFirstPlace) {			
			addCard(new TutorialCard(17, false));
		}
		if(UserSettings.getInstance().getTutorial().createFirstPlace && !UserSettings.getInstance().getTutorial().setMapDependencies) {			
			addCard(new TutorialCard(18, false));
		}
		
		//****************************************************************************************
		StructureCard card_changeName = new StructureCard("Change Place Name");
		this.addCard(card_changeName);
		
		TransparentPanel panel_title = new TransparentPanel();
		panel_title.setLayout(new BorderLayout(5, 5));
		card_changeName.setBody(panel_title);
		lblPlacename = new SimpleLabel("Placename:");
		panel_title.add(lblPlacename, BorderLayout.WEST);
		
		txt_placename = new SimpleTextfield();
		if(my_place != null) {txt_placename.setText(my_place.getName());}
		panel_title.add(txt_placename, BorderLayout.CENTER);
		
	

		//****************************************************************************************
		StructureCard card_changePlacetype = new StructureCard("Change Type of Place");
		this.addCard(card_changePlacetype);

		TransparentPanel panel_placetype = new TransparentPanel();
		card_changePlacetype.setBody(panel_placetype);
		panel_placetype.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblPlacetype = new SimpleLabel("Place-Type:");
		panel_placetype.add(lblPlacetype, BorderLayout.NORTH);
		
		InfoButton btnPlaceTypeInfo = new InfoButton("<html>Enter something like city, state, village, parc, house, disco, ...<br/>"
				+ "Can changed every time.</html>");
		panel_placetype.add(btnPlaceTypeInfo, BorderLayout.EAST);
		
		txtPlacetype = new SimpleTextfield();
		panel_placetype.add(txtPlacetype, BorderLayout.CENTER);
		
		
		//****************************************************************************************
		StructureCard card_hasParent = new StructureCard("Is part of another Place");
		this.addCard(card_hasParent);
		
		TransparentPanel panel_placedIn = new TransparentPanel();
		card_hasParent.setBody(panel_placedIn);
		panel_placedIn.setLayout(new BorderLayout(5, 5));
				
		chckbxHasParent = new SimpleCheckbox("Has Parent");
		chckbxHasParent.setEnabled(my_place != null);
		chckbxHasParent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(chckbxHasParent.isSelected());
			}
		});
		panel_placedIn.add(chckbxHasParent, BorderLayout.WEST);
		
		comboBox = new JComboBox<ComboItem>();
		panel_placedIn.add(comboBox, BorderLayout.CENTER);
		
		//****************************************************************************************
		StructureCard card_hasChildren = new StructureCard("Has following places inside");
		this.addCard(card_hasChildren);
				
		TransparentPanel panel_childrenList = new TransparentPanel();
		card_hasChildren.setBody(panel_childrenList);
		panel_childrenList.setLayout(new BoxLayout(panel_childrenList, BoxLayout.LINE_AXIS));
		
		if(my_place != null) {
			ArrayList<Place> childrenInfos = my_place.getChildrenObject();
			if(childrenInfos.size() == 0) {
				SimpleLabel lblChildInfo = new SimpleLabel("---");
				panel_childrenList.add(lblChildInfo);
			} else {
				for(Place childInfo : childrenInfos) {
					LinkButton lblChildInfo = new LinkButton(childInfo.getName());
					lblChildInfo.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new PlaceEditor(childInfo)));
					panel_childrenList.add(lblChildInfo);
					panel_childrenList.add(new SimpleLabel(";  "));
				}				
			}
		}
		
		
		
		//****************************************************************************************
		StructureCard card_wasTagged = new StructureCard("Place was tagged in Section(s)");
		this.addCard(card_wasTagged);		
		
		panel_taglist = new TransparentPanel();
		panel_taglist.setLayout(new BoxLayout(panel_taglist, BoxLayout.LINE_AXIS));
		card_wasTagged.setBody(panel_taglist);
		
		
		//****************************************************************************************
		TransparentPanel footer = new TransparentPanel();
		this.setFooter(footer);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblWarning = new SimpleLabel(" ");
		lblWarning.setForeground(Color.RED);
		footer.add(lblWarning, BorderLayout.SOUTH);		

		JButton btnSave = new JButton("Save Place");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblWarning.setText(" ");
				boolean canSave = true;
				
				setWarningEnterName(false);
				String name = txt_placename.getText();				
				if(name.equals("")) {
					lblWarning.setText("WARNING: Can not save place, because no name was entered!");
					setWarningEnterName(true);
					canSave = false;
				}
				
				ObjectID newParent = null;
				if(chckbxHasParent.isSelected()) {
					newParent = ((ComboItem) comboBox.getSelectedItem()).getValue();
				}
								
				if(canSave) {
					if(my_place == null) {
						my_place = new Place(name, txtPlacetype.getText(), newParent);
						Book.getInstance().getWorld().addPlace(my_place);
						if(!UserSettings.getInstance().getTutorial().createFirstPlace) {
							UserSettings.getInstance().getTutorial().createFirstPlace = true;
							UserSettings.getInstance().save();
						}
						BookEditorFrame.getInstance().switchBody(new PlaceEditor(my_place));
					} else {
						my_place.editPlace(name, txtPlacetype.getText(), newParent);
						Book.getInstance().getWorld().changePlace(my_place.getID(), my_place);
						BookEditorFrame.getInstance().switchBody(new PlaceEditor(my_place));
					}
				}
			}
		});
		footer.add(btnSave, BorderLayout.SOUTH);

		//****************************************************************************************
		setInput();
		setMenu(new PlaceMenu());
	}
	
	private void setInput() {
		for(Place p : Book.getInstance().getWorld().getPlaces()) {
			if(my_place != null) {				
				if(!p.equals(my_place)) {
					comboBox.addItem(new ComboItem(p.getName(), p.getID()));	
				}
			} else {
				comboBox.addItem(new ComboItem(p.getName(), p.getID()));				
			}
		}
		
		if(my_place != null) {			
			txtPlacetype.setText(my_place.getType());
			
			chckbxHasParent.setSelected(my_place.getParentRef() != null);
			
			if(my_place.getParentRef() != null) {
				for (int i = 0; i < comboBox.getItemCount(); i++){
					ComboItem item = (ComboItem)comboBox.getItemAt(i);
					if (my_place.getParentRef().getIDtoString().equals(item.getValue().getIDtoString())){
						comboBox.setSelectedIndex(i);
						break;
					}
				}
				
			}
			for(Section section : Book.getInstance().getSectionList().getSections()) {
				if(section.hasTag(my_place.getID())) {
					LinkButton btnSectionTag = new LinkButton(section.getName());
					btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section)));
					panel_taglist.add(btnSectionTag);
					panel_taglist.add(new SimpleLabel(";  "));
				}
			}
		}
		comboBox.setEnabled(chckbxHasParent.isSelected());
	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_placename.setBorder(BorderFactory.createLineBorder(Color.RED));
			lblPlacename.setForeground(Color.RED);
		} else {
			txt_placename.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblPlacename.setForeground(Color.BLACK);
		}
	}


}
