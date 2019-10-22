package GUI.worldPage;

import javax.swing.BoxLayout;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.sectionPage.SectionPage;
import GUI_components.LinkButton;
import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import world.Place;

public class PlaceTagCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Place my_place;

	public PlaceTagCard(Place place) {
		my_place = place;
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		for(Section section : Book.getInstance().getSectionList().getSections()) {
			if(section.hasTag(my_place.getID())) {
				LinkButton btnSectionTag = new LinkButton(section.getName());
				btnSectionTag.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(section)));
				add(btnSectionTag);
				add(new SimpleLabel(";  "));
			}
		}

	}

}
