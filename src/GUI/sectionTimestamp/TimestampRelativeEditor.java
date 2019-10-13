package GUI.sectionTimestamp;

import GUI_components.ComboItem;
import GUI_components.SimpleIntegerTextfield;
import GUI_components.SimpleRadiobutton;
import GUI_components.TransparentPanel;
import book.Book;
import book.Section;
import time.RelativeDate;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;


public class TimestampRelativeEditor extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	private SimpleIntegerTextfield txt_days;
	private SimpleIntegerTextfield txt_weeks;
	private SimpleIntegerTextfield txt_months;
	private SimpleIntegerTextfield txt_years;
	private JLabel lblSelectASection;
	private JLabel lblShouldTheRelated;
	private JComboBox<ComboItem> comboBox;
	private SimpleRadiobutton rdbtnBefore;
	private SimpleRadiobutton rdbtnAfter;
	private JLabel lblSetTheDistancetime;
	private JLabel lblDays;
	private JLabel lblWeeks;
	private JLabel lblMonths;
	private JLabel lblYears;
	private JLabel lblResult;
	private JLabel lblWARNING;

	public TimestampRelativeEditor(Section openedSection) {
		my_section = openedSection;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		
		ButtonGroup btngroup_afterORbefore = new ButtonGroup();
		
		TransparentPanel panel_selectRelativeSection = new TransparentPanel();
		add(panel_selectRelativeSection);
		panel_selectRelativeSection.setLayout(new GridLayout(0, 1, 5, 5));
		
		lblSelectASection = new JLabel("Select a Section to which the Timestamp is related:");
		panel_selectRelativeSection.add(lblSelectASection);
		
		comboBox = new JComboBox<ComboItem>();
		panel_selectRelativeSection.add(comboBox);
		for(Section section : Book.getInstance().getSectionList().getSections()) {
			if(!my_section.equals(section)) {				
				comboBox.addItem(new ComboItem(section.getName(), section.getID()));
			}
		}
		
		TransparentPanel panel_beforeOrAfter = new TransparentPanel();
		add(panel_beforeOrAfter);
		panel_beforeOrAfter.setLayout(new BorderLayout(5, 5));
		
		lblShouldTheRelated = new JLabel("Should the related time happen gefore or after the selected section:");
		panel_beforeOrAfter.add(lblShouldTheRelated, BorderLayout.NORTH);
		
		TransparentPanel panel_afterBeforeBODY = new TransparentPanel();
		panel_beforeOrAfter.add(panel_afterBeforeBODY, BorderLayout.WEST);
		panel_afterBeforeBODY.setLayout(new GridLayout(0, 2, 5, 5));
		
		rdbtnBefore = new SimpleRadiobutton("Before selected Section");
		panel_afterBeforeBODY.add(rdbtnBefore);
		btngroup_afterORbefore.add(rdbtnBefore);
		
		rdbtnAfter = new SimpleRadiobutton("After selected Section");
		panel_afterBeforeBODY.add(rdbtnAfter);
		btngroup_afterORbefore.add(rdbtnAfter);
		
		TransparentPanel panel_setDistance = new TransparentPanel();
		add(panel_setDistance);
		panel_setDistance.setLayout(new BorderLayout(0, 0));
		
		lblSetTheDistancetime = new JLabel("Set the distance-time to selected Section:");
		panel_setDistance.add(lblSetTheDistancetime, BorderLayout.NORTH);
		
		TransparentPanel panel_distanceBODY = new TransparentPanel();
		panel_setDistance.add(panel_distanceBODY);
		panel_distanceBODY.setLayout(new GridLayout(0, 4, 5, 5));
		
		lblDays = new JLabel("Days:");
		panel_distanceBODY.add(lblDays);
		
		lblWeeks = new JLabel("Weeks:");
		panel_distanceBODY.add(lblWeeks);
		
		lblMonths = new JLabel("Months:");
		panel_distanceBODY.add(lblMonths);
		
		lblYears = new JLabel("Years:");
		panel_distanceBODY.add(lblYears);
		
		txt_days = new SimpleIntegerTextfield();
		panel_distanceBODY.add(txt_days);
		
		txt_weeks = new SimpleIntegerTextfield();
		panel_distanceBODY.add(txt_weeks);
		
		txt_months = new SimpleIntegerTextfield();
		panel_distanceBODY.add(txt_months);
		
		txt_years = new SimpleIntegerTextfield();
		panel_distanceBODY.add(txt_years);
		
		TransparentPanel panel_result = new TransparentPanel();
		add(panel_result);
		panel_result.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblResult = new JLabel("Result:");
		panel_result.add(lblResult);
		
		lblWARNING = new JLabel(" ");
		lblWARNING.setForeground(Color.RED);
		add(lblWARNING);
		
		
		txt_days.addActionListener(e -> updateRsult());
		txt_weeks.addActionListener(e -> updateRsult());
		txt_months.addActionListener(e -> updateRsult());
		txt_years.addActionListener(e -> updateRsult());
		rdbtnBefore.addActionListener(e -> updateRsult());
		rdbtnAfter.addActionListener(e -> updateRsult());
		comboBox.addActionListener(e -> updateRsult());
	}
	
	public void switchEnabled(boolean enable) {
		this.setEnabled(enable);
		lblSelectASection.setEnabled(enable);
		lblShouldTheRelated.setEnabled(enable);
		comboBox.setEnabled(enable);
		rdbtnBefore.setEnabled(enable);
		rdbtnAfter.setEnabled(enable);
		lblSetTheDistancetime.setEnabled(enable);
		lblDays.setEnabled(enable);
		lblWeeks.setEnabled(enable);
		lblMonths.setEnabled(enable);
		lblYears.setEnabled(enable);
		txt_days.setEnabled(enable);
		txt_weeks.setEnabled(enable);
		txt_months.setEnabled(enable);
		txt_years.setEnabled(enable);
		lblResult.setEnabled(enable);
	}
	
	public RelativeDate getResult() {
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
		Section selectedSection = Book.getInstance().getSectionList().getSection(item.getValue());
		if(selectedSection == null) {
			lblWARNING.setText("You can not Save! Select an existing section");
			return null;
		}
		
		return new RelativeDate(selectedSection.getID(), isAfter, distDays, distWeeks, distMonths, distYears);
	}

	public void activate(RelativeDate unspecificDate) {
		for(int i = 0; i < comboBox.getItemCount(); i++) {
			ComboItem item = (ComboItem)comboBox.getItemAt(i);
			if(unspecificDate.getRelatedSectionID().getIDtoString().equals(item.getValue().getIDtoString())) {
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
		lblResult.setText("<html>Result: " + unspecificDate.toString() + " &#8594; " + getResult().generateSpecificDate().getDayOfWeek() + "</html>");
		lblWARNING.setText("");
	}
	
	private void updateRsult() {
		if(getResult() != null) {
			lblResult.setText("<html>Result: " + getResult().toString() + " &#8594; " + getResult().generateSpecificDate().getDayOfWeek() + "</html>");
		}
	}

}
