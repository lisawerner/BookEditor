package GUI.sectionChangePage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import book.Book;
import book.Section;
import GUI.components.InfoButton;
import GUI.components.Pair;
import GUI.components.SimpleCheckbox;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextfield;
import GUI.components.TransparentPanel;
import time.SpecificDate;

public class TimestampSpecificEditor extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	private SpecificDate my_specificTimestamp;
	
	private SimpleTextfield txt_year;
	private JComboBox<Pair> cmb_month;
	private JComboBox<String> cmb_day;
	private SimpleLabel lblDayWarning;
	private SimpleLabel lblEnterADate;
	private SimpleLabel lblMonth;
	private SimpleLabel lblDay;
	private SimpleLabel lblYear;
	private SimpleCheckbox chckbxAnnoDomini;
	private String dayOfWeek;
	private SimpleLabel lblDayOfWeek;

	public TimestampSpecificEditor(Section openedSection) {
		my_section = openedSection;
		if(my_section.hasTimestamp()) {
			time.Timestamp time = Book.getInstance().getTimeline().getTimestamp(my_section.getTimestampID());
			if(time.isSpecificDate()) {
				my_specificTimestamp = time.getSpecificDate();
			}
		}
		
		setLayout(new BorderLayout(10, 10));
		
		lblEnterADate = new SimpleLabel("Enter a Date:");
		add(lblEnterADate, BorderLayout.NORTH);
		
		TransparentPanel panel_specificPlacer = new TransparentPanel();
		add(panel_specificPlacer, BorderLayout.CENTER);
		panel_specificPlacer.setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_enterDate = new TransparentPanel();
		panel_specificPlacer.add(panel_enterDate, BorderLayout.NORTH);
		panel_enterDate.setLayout(new GridLayout(0, 3, 5, 5));
		
		lblDay = new SimpleLabel("Day:");
		panel_enterDate.add(lblDay);
		
		lblMonth = new SimpleLabel("Month:");
		panel_enterDate.add(lblMonth);
		
		lblYear = new SimpleLabel("Year");
		panel_enterDate.add(lblYear);
		
		cmb_month = new JComboBox<Pair>();
	    String[] months = new DateFormatSymbols().getMonths();
	    for (int i = 0; i < months.length; i++) {
	      String month = months[i];
	      cmb_month.addItem(new Pair(month, i));
	    }
	    if(my_specificTimestamp != null) {
	    	for (int i = 0; i < cmb_month.getItemCount(); i++){
				Pair item = cmb_month.getItemAt(i);
				if (item.getValue() == my_specificTimestamp.getMonth()){
					cmb_month.setSelectedIndex(i);
					break;
				}
			}
	    }
	    cmb_month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reloadDays();
				reloadDayOfWeek();
			}
		});
		lblDayOfWeek = new SimpleLabel("<html>&#8594;" + dayOfWeek + "</html>");
		chckbxAnnoDomini = new SimpleCheckbox("Anno Domini");
		if(my_specificTimestamp != null) {
			chckbxAnnoDomini.setSelected(my_specificTimestamp.isAnnoDomini());
			if(!my_specificTimestamp.isAnnoDomini()) {
				lblDayOfWeek.setText("Not Possible for before christ");
			}
		} else {
			chckbxAnnoDomini.setSelected(true);			
		}
	    
		cmb_day = new JComboBox<String>();
		cmb_day.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reloadDayOfWeek();
			}
		});
		panel_enterDate.add(cmb_day);
		panel_enterDate.add(cmb_month);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		YearMonth yearMonthObject = YearMonth.of(year, 1);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		for (int i = 1; i < daysInMonth + 1; i++) {
			String day = ""+ i;
			cmb_day.addItem(day);
		}
		
	    if(my_specificTimestamp != null) {
	    	for (int i = 0; i < cmb_day.getItemCount(); i++){
				int item = Integer.parseInt(cmb_day.getItemAt(i).toString());
				if (item == my_specificTimestamp.getDay()){
					cmb_day.setSelectedIndex(i);
					break;
				}
			}
	    }
	    
		txt_year = new SimpleTextfield();
		if(my_specificTimestamp != null) {
			if(my_specificTimestamp.isYearSet()) {				
				txt_year.setText(my_specificTimestamp.getYear() + "");
			}
		}
		txt_year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reloadDays();
				reloadDayOfWeek();
			}
		});
		txt_year.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				reloadDays();
//				reloadDayOfWeek();
			}
			public void removeUpdate(DocumentEvent e) {
				reloadDays();
//				reloadDayOfWeek();
			}
			public void insertUpdate(DocumentEvent e) {
				reloadDays();
//				reloadDayOfWeek();
			}
		});
		panel_enterDate.add(txt_year);
		
		if(my_specificTimestamp != null) {
			dayOfWeek = my_specificTimestamp.getDayOfWeek();
		}

		panel_enterDate.add(lblDayOfWeek);
		
		SimpleLabel lblEmpty = new SimpleLabel(" ");
		panel_enterDate.add(lblEmpty);
	
		panel_enterDate.add(chckbxAnnoDomini);
		chckbxAnnoDomini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!chckbxAnnoDomini.isSelected()) {
					lblDayOfWeek.setText("Not Possible for before christ");
					lblDayOfWeek.setEnabled(false);
				} else {
					lblDayOfWeek.setEnabled(true);
					reloadDayOfWeek();
				}
			}
		});
		
		TransparentPanel panel_DayWarning = new TransparentPanel();
		panel_enterDate.add(panel_DayWarning);
		panel_DayWarning.setLayout(new BorderLayout(5, 5));
		
		lblDayWarning = new SimpleLabel(" ");
		panel_DayWarning.add(lblDayWarning, BorderLayout.NORTH);
		lblDayWarning.setWarning(true);
		
		InfoButton btnSpecificHint = new InfoButton("<html>Possible days will change, if you choose month and/or year.<br/>"
				+ "If no year is entered: Current year is taken for calculate possible days.<br/>"
				+ "Do not use negative Values for Year. You can use checkbox for mark, that is not anno domini.</html>");
		add(btnSpecificHint, BorderLayout.EAST);
		

	}
	
	private void reloadDays() {
		lblDayWarning.setText(" ");
		
		int currentMonth = 1;
		if(cmb_month.getSelectedIndex() != -1) {			
			Pair currentSelection = (Pair) cmb_month.getSelectedItem();
			currentMonth = currentSelection.getValue() + 1;
		}
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if(yearIsValid()){
			currentYear = Integer.parseInt(txt_year.getText());
		}
		
		int selected = 0;
		if(cmb_day.getSelectedIndex() != -1) {
			selected = Integer.parseInt(cmb_day.getSelectedItem().toString());
		}
		
		cmb_day.removeAllItems();
		YearMonth yearMonthObject = YearMonth.of(currentYear, currentMonth);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		for (int i = 1; i < daysInMonth + 1; i++) {
			String day = ""+ i;
			cmb_day.addItem(day);
		}
		
		if(selected != 0) {
			if(selected > daysInMonth) {
				selected = daysInMonth;
				lblDayWarning.setText("Warning: Value of Day has changed, because new settet Month+Year does not have so many days!");
			}
			for (int i = 0; i < cmb_day.getItemCount(); i++){
				String item = cmb_day.getItemAt(i).toString();
				String selectedString = selected + "";
				if (selectedString.equals(item)){
					cmb_day.setSelectedIndex(i);
					break;
				}
			}
		}
	}
	
	private boolean yearIsValid() {
		//TODO: Check if it is a possible year?!
		// -> Only Numbers, no letters
		// -> Do NOT cast to four letters, because it can be 500a.d., so in the middleage, or it can be 1.2056, so in 10.000 years or 200b.c.
		try { 
	        Integer.parseInt(txt_year.getText()); 
	    } catch(NumberFormatException e) { 
	    	removeInvalidChar();
	        return false; 
	    } catch(NullPointerException e) {
	    	removeInvalidChar();
	        return false;
	    }
		if(txt_year.getText().equals("")) {
			return false;
		}
		//TODO: Warning-Label which says "Thats no Integer"
		return true;
	}
	
	private void removeInvalidChar() {
		//TODO: Restrict SimpleTextfield to Integer input only
//		String sub = txt_year.getText();
//		String newString = sub.substring(0,sub.length());
//		txt_year.setText(newString);
	}
	
	private void reloadDayOfWeek() {
		if(chckbxAnnoDomini.isSelected()) {			
			Calendar cal = Calendar.getInstance();
			int currentDay = -1;
			if(cmb_day.getSelectedItem() != null) {
				currentDay = Integer.parseInt(cmb_day.getSelectedItem().toString());
			}
			cal.set(Calendar.DAY_OF_MONTH, currentDay);
			cal.set(Calendar.MONTH, ((Pair) cmb_month.getSelectedItem()).getValue());
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			if(yearIsValid()){
				currentYear = Integer.parseInt(txt_year.getText());
			}
			cal.set(Calendar.YEAR, currentYear);
			Date my_date = cal.getTime();
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
			dayOfWeek = simpleDateformat.format(my_date);
			
			if(lblDayOfWeek != null) {    	
				lblDayOfWeek.setText("<html>&#8594;" + dayOfWeek + "</html>");
				lblDayOfWeek.revalidate();
				lblDayOfWeek.repaint();
			}
		}
	}

	public void switchEnabled(boolean selected) {
		cmb_month.setEnabled(selected);
		cmb_day.setEnabled(selected);
		txt_year.setEnabled(selected);
		lblEnterADate.setEnabled(selected);
		lblMonth.setEnabled(selected);
		lblDay.setEnabled(selected);
		lblYear.setEnabled(selected);
		chckbxAnnoDomini.setEnabled(selected);
		lblDayOfWeek.setEnabled(selected);
	}

	public SpecificDate getResult() {
		int currentSelectedDay = -1;
		if(cmb_day.getSelectedIndex() != -1) {
			currentSelectedDay = Integer.parseInt(cmb_day.getSelectedItem().toString());
		}
		int currentSelectedMonth = -1;
		if(cmb_month.getSelectedIndex() != -1) {	
			currentSelectedMonth = ((Pair) cmb_month.getSelectedItem()).getValue();
		}
		int currentSelectedYear = -1;
		if(yearIsValid()){
			currentSelectedYear = Integer.parseInt(txt_year.getText());
		}
		if((currentSelectedDay == -1 && currentSelectedMonth != -1) || (currentSelectedDay != -1 && currentSelectedMonth == -1)) {
			lblDayWarning.setText("You can only set Month an Day together! (Year is mostly optional)");
			return null;
		}
		
		return new SpecificDate(currentSelectedDay, currentSelectedMonth, currentSelectedYear, chckbxAnnoDomini.isSelected());
	}

}
