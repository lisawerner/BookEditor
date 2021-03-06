package GUI.sectionChangePage;

import javax.swing.BoxLayout;

import book.Section;
import GUI.components.TransparentPanel;
import person.Relationship;

public class SectionRelationshipCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
	public SectionRelationshipCard(Section section) {
		my_section = section; 
		
		reloadRelationships(section);
	}
	
	public void addRelationship(Relationship newRelationship) {
		this.add(new SectionRelationshipItem(this, my_section, newRelationship));
		revalidate();
		repaint();
	}

	public void reloadRelationships(Section update) {
		my_section = update; 
		
		this.removeAll();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(new SectionRelationshipItem(this, my_section, null));
		//TODO: Show inherited Relationships from pre Sections!
		for(Relationship relship : my_section.getRelationships()) {
			add(new SectionRelationshipItem(this, my_section, relship));
		}
		
		revalidate();
		repaint();
	}

}
