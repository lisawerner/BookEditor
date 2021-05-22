package GUI.sectionChangePage;

import GUI.components.*;
import book.Book;
import book.Chapter;
import book.Section;
import time.RelativeDate;
import time.Timestamp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TimestampRelativeEditor extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Section my_section;
	
	private int dayOfWeek = 0;
	
	private final SimpleIntegerTextField txt_days;
	private final SimpleIntegerTextField txt_weeks;
	private final SimpleIntegerTextField txt_months;
	private final SimpleIntegerTextField txt_years;
	private final SimpleLabel lblSelectASection;
	private final SimpleLabel lblShouldTheRelated;
	private final JComboBox<ComboItem> comboBox;
	private final SimpleRadiobutton rdbtnBefore;
	private final SimpleRadiobutton rdbtnAfter;
	private final SimpleLabel lblSetTheDistanceTime;
	private final SimpleLabel lblDays;
	private final SimpleLabel lblWeeks;
	private final SimpleLabel lblMonths;
	private final SimpleLabel lblYears;
	private final SimpleLabel lblResult;
	private final SimpleLabel lblWARNING;
	private final SimpleRadiobutton rdbtnNoDay;
	private final SimpleRadiobutton rdbtnMonday;
	private final SimpleRadiobutton rdbtnTuesday;
	private final SimpleRadiobutton rdbtnWednesday;
	private final SimpleRadiobutton rdbtnThursday;
	private final SimpleRadiobutton rdbtnFriday;
	private final SimpleRadiobutton rdbtnSaturday;
	private final SimpleRadiobutton rdbtnSunday;

	public TimestampRelativeEditor(Section openedSection) {
		my_section = openedSection;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		ButtonGroup btnGroup_afterOrBefore = new ButtonGroup();
		
		TransparentPanel panel_selectRelativeSection = new TransparentPanel();
		add(panel_selectRelativeSection);
		panel_selectRelativeSection.setLayout(new GridLayout(0, 1, 5, 5));
		
		lblSelectASection = new SimpleLabel("Select a Section to which the Timestamp is related:");
		panel_selectRelativeSection.add(lblSelectASection);
		
		comboBox = new JComboBox<>();
		panel_selectRelativeSection.add(comboBox);
		for(Chapter chapter : Book.getInstance().getTableOfContent().getChapters()) {			
			for(Section section : chapter.getSections()) {
				if(!my_section.equals(section)) {				
					comboBox.addItem(new ComboItem(section.getName(), section.getID()));
				}
			}
		}
		
		TransparentPanel panel_beforeOrAfter = new TransparentPanel();
		add(panel_beforeOrAfter);
		panel_beforeOrAfter.setLayout(new BorderLayout(5, 5));
		
		lblShouldTheRelated = new SimpleLabel("Should the related time happen before or after the selected section:");
		panel_beforeOrAfter.add(lblShouldTheRelated, BorderLayout.NORTH);
		
		TransparentPanel panel_afterBeforeBODY = new TransparentPanel();
		panel_beforeOrAfter.add(panel_afterBeforeBODY, BorderLayout.WEST);
		panel_afterBeforeBODY.setLayout(new GridLayout(0, 2, 5, 5));
		
		rdbtnBefore = new SimpleRadiobutton("Before selected Section");
		panel_afterBeforeBODY.add(rdbtnBefore);
		btnGroup_afterOrBefore.add(rdbtnBefore);
		
		rdbtnAfter = new SimpleRadiobutton("After selected Section");
		panel_afterBeforeBODY.add(rdbtnAfter);
		btnGroup_afterOrBefore.add(rdbtnAfter);
		
		TransparentPanel panel_setDistance = new TransparentPanel();
		add(panel_setDistance);
		panel_setDistance.setLayout(new BorderLayout(0, 0));
		
		lblSetTheDistanceTime = new SimpleLabel("Set the distance-time to selected Section:");
		panel_setDistance.add(lblSetTheDistanceTime, BorderLayout.NORTH);
		
		TransparentPanel panel_distanceBODY = new TransparentPanel();
		panel_setDistance.add(panel_distanceBODY);
		panel_distanceBODY.setLayout(new GridLayout(0, 4, 5, 5));
		
		lblDays = new SimpleLabel("Days:");
		panel_distanceBODY.add(lblDays);
		
		lblWeeks = new SimpleLabel("Weeks:");
		panel_distanceBODY.add(lblWeeks);
		
		lblMonths = new SimpleLabel("Months:");
		panel_distanceBODY.add(lblMonths);
		
		lblYears = new SimpleLabel("Years:");
		panel_distanceBODY.add(lblYears);
		
		txt_days = new SimpleIntegerTextField();
		panel_distanceBODY.add(txt_days);
		
		txt_weeks = new SimpleIntegerTextField();
		panel_distanceBODY.add(txt_weeks);
		
		txt_months = new SimpleIntegerTextField();
		panel_distanceBODY.add(txt_months);
		
		txt_years = new SimpleIntegerTextField();
		panel_distanceBODY.add(txt_years);
		
		TransparentPanel panel_setWeekday = new TransparentPanel();
		add(panel_setWeekday);
		panel_setWeekday.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblSetConcreteDay = new SimpleLabel("Set concrete Day of the week");
		panel_setWeekday.add(lblSetConcreteDay, BorderLayout.NORTH);
		
		InfoButton btnI = new InfoButton("<html>If you set a specific day of the week, then the timestamp will first add"
				+ "<br/>(or in case of earlier timestamp deduct)"
				+ "<br/>the distance to related timestamp and then take the next day of week.</html>");
		panel_setWeekday.add(btnI, BorderLayout.EAST);
		
		TransparentPanel panel_wekDays = new TransparentPanel();
		panel_setWeekday.add(panel_wekDays, BorderLayout.CENTER);
		panel_wekDays.setLayout(new GridLayout(0, 4, 5, 5));
		
		ButtonGroup btnGroup_dayOfTheWeek = new ButtonGroup();
		
		rdbtnNoDay = new SimpleRadiobutton("-");
		panel_wekDays.add(rdbtnNoDay);
		btnGroup_dayOfTheWeek.add(rdbtnNoDay);
		rdbtnNoDay.addActionListener(e -> setDayOfWeek(-1));
		
		rdbtnMonday = new SimpleRadiobutton("Monday");
		panel_wekDays.add(rdbtnMonday);
		btnGroup_dayOfTheWeek.add(rdbtnMonday);
		rdbtnMonday.addActionListener(e -> setDayOfWeek(2));
		
		rdbtnTuesday = new SimpleRadiobutton("Tuesday");
		panel_wekDays.add(rdbtnTuesday);
		btnGroup_dayOfTheWeek.add(rdbtnTuesday);
		rdbtnTuesday.addActionListener(e -> setDayOfWeek(3));
		
		rdbtnWednesday = new SimpleRadiobutton("Wednesday");
		panel_wekDays.add(rdbtnWednesday);
		btnGroup_dayOfTheWeek.add(rdbtnWednesday);
		rdbtnWednesday.addActionListener(e -> setDayOfWeek(4));
		
		rdbtnThursday = new SimpleRadiobutton("Thursday");
		panel_wekDays.add(rdbtnThursday);
		btnGroup_dayOfTheWeek.add(rdbtnThursday);
		rdbtnThursday.addActionListener(e -> setDayOfWeek(5));
		
		rdbtnFriday = new SimpleRadiobutton("Friday");
		panel_wekDays.add(rdbtnFriday);
		btnGroup_dayOfTheWeek.add(rdbtnFriday);
		rdbtnFriday.addActionListener(e -> setDayOfWeek(6));
		
		rdbtnSaturday = new SimpleRadiobutton("Saturday");
		panel_wekDays.add(rdbtnSaturday);
		btnGroup_dayOfTheWeek.add(rdbtnSaturday);
		rdbtnSaturday.addActionListener(e -> setDayOfWeek(7));
		
		rdbtnSunday = new SimpleRadiobutton("Sunday");
		panel_wekDays.add(rdbtnSunday);
		btnGroup_dayOfTheWeek.add(rdbtnSunday);
		rdbtnSunday.addActionListener(e -> setDayOfWeek(1));
		
		TransparentPanel panel_result = new TransparentPanel();
		add(panel_result);
		panel_result.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblResult = new SimpleLabel("Result:");
		panel_result.add(lblResult);
		
		lblWARNING = new SimpleLabel(" ");
		lblWARNING.setWarning(true);
		add(lblWARNING);
		
		setUpdateListener();
	}
	
	private void setDayOfWeek(int newDay) {
		dayOfWeek = newDay;
		updateResult();
	}

	public void switchEnabled(boolean enable) {
		this.setEnabled(enable);
		lblSelectASection.setEnabled(enable);
		lblShouldTheRelated.setEnabled(enable);
		comboBox.setEnabled(enable);
		rdbtnBefore.setEnabled(enable);
		rdbtnAfter.setEnabled(enable);
		lblSetTheDistanceTime.setEnabled(enable);
		lblDays.setEnabled(enable);
		lblWeeks.setEnabled(enable);
		lblMonths.setEnabled(enable);
		lblYears.setEnabled(enable);
		txt_days.setEnabled(enable);
		txt_weeks.setEnabled(enable);
		txt_months.setEnabled(enable);
		txt_years.setEnabled(enable);
		lblResult.setEnabled(enable);
		rdbtnNoDay.setEnabled(enable);
		rdbtnMonday.setEnabled(enable);
		rdbtnTuesday.setEnabled(enable);
		rdbtnWednesday.setEnabled(enable);
		rdbtnThursday.setEnabled(enable);
		rdbtnFriday.setEnabled(enable);
		rdbtnSaturday.setEnabled(enable);
		rdbtnSunday.setEnabled(enable);
	}
	
	public Timestamp getResult() {
		lblWARNING.setText("");
		
		int distDays = txt_days.getInteger();
		int distWeeks = txt_weeks.getInteger();
		int distMonths = txt_months.getInteger();
		int distYears = txt_years.getInteger();
		if(distDays < 0 || distWeeks < 0 || distMonths < 0 || distYears < 0) {
			lblWARNING.setText("You can not Save! All Distance-Times must equal or greater then 0");
			return null;
		}
		
		boolean isAfter = !rdbtnBefore.isSelected() && rdbtnAfter.isSelected();
		if(!rdbtnBefore.isSelected() && !rdbtnAfter.isSelected()) {
			lblWARNING.setText("You can not Save! You have to select one of this: Before or After");
			return null;
		}
		
		if(comboBox.getSelectedIndex() == -1) {
			lblWARNING.setText("You can not Save! Select any section");
			return null;
		}
		ComboItem item = (ComboItem)comboBox.getSelectedItem();
		Section selectedSection = Book.getInstance().getTableOfContent().getSection(item.getValue());
		if(selectedSection == null) {
			lblWARNING.setText("You can not Save! Select an existing section");
			return null;
		}
		
		RelativeDate relativeResult = new RelativeDate(selectedSection.getTimestampID(), isAfter, distDays, distWeeks, distMonths, distYears, dayOfWeek);
		
		Timestamp my_timestamp;
		if(my_section.getTimestampID() != null){
			my_timestamp = Book.getInstance().getTimeline().getTimestamp(my_section.getTimestampID());
			my_timestamp.setRelativeDate(relativeResult);
		} else {			
			my_timestamp = new Timestamp(relativeResult, my_section.getID());
		}
		return my_timestamp;
	}

	public void activate(RelativeDate unspecificDate) {
		for(int i = 0; i < comboBox.getItemCount(); i++) {
			ComboItem item = (ComboItem)comboBox.getItemAt(i);
			if(unspecificDate.getRelationSection().getID().getIDtoString().equals(item.getValue().getIDtoString())) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}
		rdbtnBefore.setSelected(!unspecificDate.isAfter());
		rdbtnAfter.setSelected(unspecificDate.isAfter());
		txt_days.setText("" + unspecificDate.getDistDays());
		txt_weeks.setText("" + unspecificDate.getDistWeeks());
		txt_months.setText("" + unspecificDate.getDistMonths());
		txt_years.setText("" + unspecificDate.getDistYears());
		switch(unspecificDate.getDayOfWeek()) {
		case 1:
			rdbtnMonday.setSelected(true);
			setDayOfWeek(2);
			break;
		case 2:
			rdbtnTuesday.setSelected(true);
			setDayOfWeek(3);
			break;
		case 3:
			rdbtnWednesday.setSelected(true);
			setDayOfWeek(4);
			break;
		case 4:
			rdbtnThursday.setSelected(true);
			setDayOfWeek(5);
			break;
		case 5:
			rdbtnFriday.setSelected(true);
			setDayOfWeek(6);
			break;
		case 6:
			rdbtnSaturday.setSelected(true);
			setDayOfWeek(7);
			break;
		case 7:
			rdbtnSunday.setSelected(true);
			setDayOfWeek(1);
			break;
		}
		updateResult();
		lblWARNING.setText("");
	}
	
	private void setUpdateListener() {
		txt_days.addActionListener(e -> updateResult());
		txt_weeks.addActionListener(e -> updateResult());
		txt_months.addActionListener(e -> updateResult());
		txt_years.addActionListener(e -> updateResult());
		rdbtnBefore.addActionListener(e -> updateResult());
		rdbtnAfter.addActionListener(e -> updateResult());
		comboBox.addActionListener(e -> updateResult());
		txt_days.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateResult();
			}
			public void removeUpdate(DocumentEvent e) {
				updateResult();
			}
			public void insertUpdate(DocumentEvent e) {
				updateResult();
			}
		});
		txt_weeks.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateResult();
			}
			public void removeUpdate(DocumentEvent e) {
				updateResult();
			}
			public void insertUpdate(DocumentEvent e) {
				updateResult();
			}
		});
		txt_months.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateResult();
			}
			public void removeUpdate(DocumentEvent e) {
				updateResult();
			}
			public void insertUpdate(DocumentEvent e) {
				updateResult();
			}
		});
		txt_years.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateResult();
			}
			public void removeUpdate(DocumentEvent e) {
				updateResult();
			}
			public void insertUpdate(DocumentEvent e) {
				updateResult();
			}
		});
	}
	
	private void updateResult() {
		if(getResult() != null) {
			Timestamp specDate = getResult();
			String resultText = "Result: can not be calculated because related date does not have a timestamp";
			if(specDate != null){				
				resultText = "<html>Result: " + getResult().toCompleteString() + "</html>";
			}
			lblResult.setText(resultText);
		}
	}

}
