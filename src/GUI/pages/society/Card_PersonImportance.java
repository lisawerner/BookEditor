package GUI.pages.society;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.LinkButton;
import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.WrapLayout;
import book.Book;
import person.Person;

import javax.swing.*;
import java.awt.*;

public class Card_PersonImportance extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_PersonImportance() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_main = new TransparentPanel();
		add(panel_main);
		panel_main.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		SimpleLabel lblMainCharacters = new SimpleLabel("Main Characters:");
		panel_main.add(lblMainCharacters);
		
		for(Person person : Book.getInstance().getSociety().getPersonListOfSuperMainCharacters()){
			panel_main.add(new LinkButton(person.getInformation().getName(),
					e -> BookEditorFrame.getInstance().openPersonPage(person, false)));
			panel_main.add(new SimpleLabel(", "));
		}
		
		TransparentPanel panel_important = new TransparentPanel();
		add(panel_important);
		panel_important.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		SimpleLabel lblOtherImportantCharacters = new SimpleLabel("Other important Characters:");
		panel_important.add(lblOtherImportantCharacters);
		
		for(Person person : Book.getInstance().getSociety().getPersonListImportantCharacters()){
			panel_important.add(new LinkButton(person.getInformation().getName(),
					e -> BookEditorFrame.getInstance().openPersonPage(person, false)));
			panel_important.add(new SimpleLabel(", "));
		}
		
		TransparentPanel panel_rest = new TransparentPanel();
		add(panel_rest);
		panel_rest.setLayout(new WrapLayout(FlowLayout.LEADING));
		
		SimpleLabel lblRest = new SimpleLabel("Rest:");
		panel_rest.add(lblRest);

		for(Person person : Book.getInstance().getSociety().getPersonListTheRest()){
			panel_rest.add(new LinkButton(person.getInformation().getName(),
					e -> BookEditorFrame.getInstance().openPersonPage(person, false)));
			panel_rest.add(new SimpleLabel(", "));
		}
		
	}

}
