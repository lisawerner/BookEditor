package GUI.sectionChangePage;

import javax.swing.BoxLayout;

import GUI_components.TransparentPanel;
import book.Section;
import person.Relationship;

public class SectionRelationshipCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	public SectionRelationshipCard(Section section) {
		my_section = section; 
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(new SectionRelationshipItem(this, my_section, null));
		for(Relationship relship : my_section.getRelationships()) {
			add(new SectionRelationshipItem(this, my_section, relship));
		}

	}
	
	public void addRelationship(Relationship newRelationship) {
		this.add(new SectionRelationshipItem(this, my_section, newRelationship));
		revalidate();
		repaint();
	}

}
