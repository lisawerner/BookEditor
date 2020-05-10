package GUI.timelinePage;

import book.Book;
import GUI.components.SimpleCheckbox;
import GUI.components.TransparentPanel;
import time.TimelineSettings;

public class TimelineFilterCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private TimelineSettings my_settings;
	
	private SimpleCheckbox chbox_filterMainCharacters;

	public TimelineFilterCard() {
		my_settings = Book.getInstance().getTimeline().getSettings();
		
		//Thats so useless...
		chbox_filterMainCharacters = new SimpleCheckbox("Filter for Maincharacters");
		chbox_filterMainCharacters.addActionListener(e -> filterForMainCharacters());
		if(my_settings != null) {chbox_filterMainCharacters.setSelected(my_settings.getMaincharacterFilter());}
		add(chbox_filterMainCharacters);
		//TODO: Filter for specific MainCharacter!!! Because nearly in every chapter, there are maincharacters!!!
		
	}
	
	private void filterForMainCharacters() {
		Book.getInstance().getTimeline().getSettings().setMaincharacterFilter(chbox_filterMainCharacters.isSelected());
	}

}
