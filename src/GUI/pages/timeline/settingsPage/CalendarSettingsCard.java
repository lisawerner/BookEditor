package GUI.pages.timeline.settingsPage;

import GUI.components.InfoButton;
import GUI.components.SimpleLabel;
import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import book.content.Book;

import javax.swing.*;
import java.awt.*;

public class CalendarSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final SimpleRadiobutton rdbtnGregorianCalendar;

	public CalendarSettingsCard() {
		setLayout(new GridLayout(0, 1, 10, 10));
				
		SimpleLabel lblIfYouWant = new SimpleLabel("If you want to work with Timeline-Features, choose following options:");
		add(lblIfYouWant);
		
		ButtonGroup btngrCalendarType = new ButtonGroup();
				
		TransparentPanel panel_timelineCalendarType = new TransparentPanel();
		add(panel_timelineCalendarType);
		panel_timelineCalendarType.setLayout(new GridLayout(1, 0, 10, 10));
		rdbtnGregorianCalendar = new SimpleRadiobutton("Use: Gregorian Calendar");
		rdbtnGregorianCalendar.setSelected(Book.getInstance().getTimeline().useGregorianCalendar());
		btngrCalendarType.add(rdbtnGregorianCalendar);
		panel_timelineCalendarType.add(rdbtnGregorianCalendar);
		rdbtnGregorianCalendar.addActionListener(e -> Book.getInstance().getTimeline().changeCalendarSettings(rdbtnGregorianCalendar.isSelected()));
		
		TransparentPanel rdbtnWithInfoBtn = new TransparentPanel();
		panel_timelineCalendarType.add(rdbtnWithInfoBtn);
		rdbtnWithInfoBtn.setLayout(new BorderLayout(0,0));
		SimpleRadiobutton rdbtnCustomCalendar = new SimpleRadiobutton("Use: Custom Calendar(s)");
		btngrCalendarType.add(rdbtnCustomCalendar);
		rdbtnCustomCalendar.setEnabled(false);
		rdbtnWithInfoBtn.add(rdbtnCustomCalendar, BorderLayout.CENTER);
		
		InfoButton btnI = new InfoButton("Custom Calendar is not available at the moment.");
		rdbtnWithInfoBtn.add(btnI, BorderLayout.WEST);
		
		//TODO: Default? = Gregorian!
		//TODO: Change/Save/Hint Label
	}
}
