package GUI.timelinePages;

import GUI_components.SimpleCheckbox;
import GUI_components.TransparentPanel;
import book.Book;
import time.TimelineSettings;

public class FilterCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final SimpleCheckbox checkbox_filterMainCharacters;

	public FilterCard() {
		TimelineSettings my_settings = Book.getInstance().getTimeline().getSettings();
		
		//That's so useless...
		checkbox_filterMainCharacters = new SimpleCheckbox("Filter for main characters");
		checkbox_filterMainCharacters.addActionListener(e -> filterForMainCharacters());
		if(my_settings != null) {
			checkbox_filterMainCharacters.setSelected(my_settings.getMainCharacterFilter());}
		add(checkbox_filterMainCharacters);
		//TODO: Filter for specific MainCharacter!!! Because nearly in every chapter, there are main characters!!!
		
	}
	
	private void filterForMainCharacters() {
		Book.getInstance().getTimeline().getSettings().setMainCharacterFilter(checkbox_filterMainCharacters.isSelected());
	}

}
