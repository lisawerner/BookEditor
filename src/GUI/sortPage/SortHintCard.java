package GUI.sortPage;

import GUI_components.SimpleLabel;
import GUI_components.TransparentPanel;

public class SortHintCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public SortHintCard() {
		//TODO: Make this Card to a "Hiding-StructureCard", so User can hide it. Hiding-Setting will Save in Tutorial-File for Whole Editor (not for single Book!); Maybe Tutorial-Cards will have different color-Theme
				
		SimpleLabel lblHint = new SimpleLabel("<html>Use &lt; for making a Chapter and > for making a Subsection.<br>"
				+ "Use ^ for moving up and v for moving down.<br/>"
				+ "Left places Sections with '>>' are already a chapter; Right places Sections with '>>>>' are Subsections.<br/>"
				+ "<br/>"
				+ "Changing anything saves automatically.</html>");
		this.add(lblHint);

	}

}
