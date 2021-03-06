package GUI.pages.society;

import book.Book;
import global.ObjectID;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import person.Person;
import javax.swing.JSeparator;
import GUI.bookeditorFrame.BookEditorFrame;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Card_SocietyFamiliar extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_SocietyFamiliar() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(Person firstAncestor : Book.getInstance().getSociety().getPersonListFirstAncestors()) {	
			
			TransparentPanel panel_familyTree = new TransparentPanel();
			add(panel_familyTree);
			panel_familyTree.setLayout(new GridLayout(0, 1, 0, 0));
			
			addPerson(panel_familyTree, firstAncestor.getID(), "");
						
			JSeparator firstSeparator = new JSeparator();
			add(firstSeparator);
		}
		
	}

	private void addPerson(TransparentPanel panel_parent, ObjectID childID, String depth) {
		TransparentPanel panel_child = new TransparentPanel();
		panel_child.setLayout(new BoxLayout(panel_child, BoxLayout.LINE_AXIS));
		panel_parent.add(panel_child);
		
		panel_child.add(new SimpleLabel(depth));
		
		Person child = Book.getInstance().getSociety().getPerson(childID);
		
		LinkButton btnChild = new LinkButton(child.getInformation().getName());
		btnChild.addActionListener(e -> BookEditorFrame.getInstance().openPersonPage(child, false));
		panel_child.add(btnChild);
		
		if(child.getInformation().isDeadBeforeBookStart()) {
			String deathTime = "&dagger;";
			if(!"".equals(child.getInformation().getDeathTime())) {
				deathTime += " Since " + child.getInformation().getDeathTime() + " years";
			}
			panel_child.add(new SimpleLabel("<html>" + deathTime + "</html>"));
		}
		
		String newDepth = depth + "      >>  ";
		addDescendents(panel_parent, child.getFamiliarRelation().getChildren(), newDepth);
		newDepth = depth + "      ??  ";
		addDescendents(panel_parent, child.getFamiliarRelation().getDistantDescendant(), newDepth);
	}

	private void addDescendents(TransparentPanel panel_parent, ArrayList<ObjectID> personList, String depth) {
		for(ObjectID childID : personList) {
			addPerson(panel_parent, childID, depth);
		}
	}
}
