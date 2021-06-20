package GUI.pages.world.viewPlace;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import book.content.Book;
import global.ObjectID;
import book.world.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Card_placeWorldDependencies extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Place my_place;
	
	private final SimpleLabel lblParentSaveHint;
	private final SimpleCheckbox checkboxHasParent;
	private final JComboBox<ComboItem> cmbox_possibleParents;

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
				
		checkboxHasParent = new SimpleCheckbox("Has Parent");
		checkboxHasParent.setEnabled(my_place != null);
		checkboxHasParent.setSelected(my_place.getParentRef() != null);
		checkboxHasParent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbox_possibleParents.setEnabled(checkboxHasParent.isSelected());
				save();
			}
		});
		panel_placedIn.add(checkboxHasParent, BorderLayout.WEST);
		
		cmbox_possibleParents = new JComboBox<>();
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
		cmbox_possibleParents.setEnabled(checkboxHasParent.isSelected());
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
		
		TransparentPanel panel_viewChildrenDependencies = new TransparentPanel();
		add(panel_viewChildrenDependencies);
		panel_viewChildrenDependencies.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblChildren = new SimpleLabel("This place has following children (=Places inside):");
		panel_viewChildrenDependencies.add(lblChildren, BorderLayout.NORTH);
		
		//TODO: Add to Layout.CENTER a change/save/hint/warning Label
		
		TransparentPanel panel_childrenList = new TransparentPanel();
		panel_viewChildrenDependencies.add(panel_childrenList, BorderLayout.CENTER);
		panel_childrenList.setLayout(new BoxLayout(panel_childrenList, BoxLayout.LINE_AXIS));
		
		if(my_place != null) {
			List<Place> childrenInfo = my_place.getChildrenObject();
			if(childrenInfo.size() == 0) {
				SimpleLabel lblChildInfo = new SimpleLabel("---");
				panel_childrenList.add(lblChildInfo);
			} else {
				for(Place childInfo : childrenInfo) {
					panel_childrenList.add(new LinkButton(childInfo.getName(),
							e -> BookEditorFrame.getInstance().openPlacePage(childInfo, false)));
					panel_childrenList.add(new SimpleLabel(";  "));
				}				
			}
		}

	}
	
	private void save() {
		ObjectID newParent = null;
		if(checkboxHasParent.isSelected()) {
			newParent = ((ComboItem) cmbox_possibleParents.getSelectedItem()).getValue();
			lblParentSaveHint.setText("Parent '" + Book.getInstance().getWorld().getPlace(newParent).getName() + "' was saved.");
		}
		my_place.setParent(newParent);
	}

}
