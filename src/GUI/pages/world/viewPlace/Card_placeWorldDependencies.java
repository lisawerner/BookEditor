package GUI.pages.world.viewPlace;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import global.ObjectID;
import GUI.components.ComboItem;
import GUI.components.LinkButton;
import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import world.Place;

public class Card_placeWorldDependencies extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;
	
	private SimpleLabel lblParentSaveHint;
	private SimpleCheckbox chckbxHasParent;
	private JComboBox<ComboItem> cmbox_possibleParents;

	public Card_placeWorldDependencies(Place place) {
		my_place = place;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_placedIn = new TransparentPanel();
		add(panel_placedIn);
		panel_placedIn.setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_savePlace = new TransparentPanel();
		add(panel_savePlace);
		panel_savePlace.setLayout(new BorderLayout(0, 0));
		
		lblParentSaveHint = new SimpleLabel(" ");
		lblParentSaveHint.setWarning(true);
		panel_savePlace.add(lblParentSaveHint, BorderLayout.WEST);
				
		chckbxHasParent = new SimpleCheckbox("Has Parent");
		chckbxHasParent.setEnabled(my_place != null);
		chckbxHasParent.setSelected(my_place.getParentRef() != null);
		chckbxHasParent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbox_possibleParents.setEnabled(chckbxHasParent.isSelected());
				save();
			}
		});
		panel_placedIn.add(chckbxHasParent, BorderLayout.WEST);
		
		cmbox_possibleParents = new JComboBox<ComboItem>();
		panel_placedIn.add(cmbox_possibleParents, BorderLayout.CENTER);
		for(Place p : Book.getInstance().getWorld().getPossibleParentsList(my_place)) {
			if(my_place != null) {				
				if(!p.equals(my_place)) {
					cmbox_possibleParents.addItem(new ComboItem(p.getName(), p.getID()));	
				}
			} else {
				cmbox_possibleParents.addItem(new ComboItem(p.getName(), p.getID()));				
			}
		}
		cmbox_possibleParents.setEnabled(chckbxHasParent.isSelected());
		if(my_place.getParentRef() != null) {
			for (int i = 0; i < cmbox_possibleParents.getItemCount(); i++){
				ComboItem item = (ComboItem)cmbox_possibleParents.getItemAt(i);
				if (my_place.getParentRef().getIDtoString().equals(item.getValue().getIDtoString())){
					cmbox_possibleParents.setSelectedIndex(i);
					break;
				}
			}
		}
		cmbox_possibleParents.addActionListener(e -> save());
		
		TransparentPanel panel_viewChildrenDependecies = new TransparentPanel();
		add(panel_viewChildrenDependecies);
		panel_viewChildrenDependecies.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblChildren = new SimpleLabel("This place has following children (=Places inside):");
		panel_viewChildrenDependecies.add(lblChildren, BorderLayout.NORTH);
		
		//TODO: Add to Layout.CENTER a change/save/hint/warning Label
		
		TransparentPanel panel_childrenList = new TransparentPanel();
		panel_viewChildrenDependecies.add(panel_childrenList, BorderLayout.CENTER);
		panel_childrenList.setLayout(new BoxLayout(panel_childrenList, BoxLayout.LINE_AXIS));
		
		if(my_place != null) {
			List<Place> childrenInfos = my_place.getChildrenObject();
			if(childrenInfos.size() == 0) {
				SimpleLabel lblChildInfo = new SimpleLabel("---");
				panel_childrenList.add(lblChildInfo);
			} else {
				for(Place childInfo : childrenInfos) {
					panel_childrenList.add(new LinkButton(childInfo.getName(),
							e -> BookEditorFrame.getInstance().openPlacePage(childInfo, false)));
					panel_childrenList.add(new SimpleLabel(";  "));
				}				
			}
		}

	}
	
	private void save() {
		ObjectID newParent = null;
		if(chckbxHasParent.isSelected()) {
			newParent = ((ComboItem) cmbox_possibleParents.getSelectedItem()).getValue();
			lblParentSaveHint.setText("Parent '" + Book.getInstance().getWorld().getPlace(newParent).getName() + "' was saved.");
		}
		my_place.setParent(newParent);
	}

}
