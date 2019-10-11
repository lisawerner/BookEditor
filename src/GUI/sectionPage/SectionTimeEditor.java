package GUI.sectionPage;

import book.Book;
import book.Section;
import global.UserSettings;
import time.SpecificDate;
import time.Timestamp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.InfoButton;
import GUI_components.LinkButton;
import GUI_components.Page;
import GUI_components.PageBody;
import GUI_components.Pair;
import GUI_components.SimpleCheckbox;
import GUI_components.SimpleRadiobutton;
import GUI_components.SimpleTextfield;
import GUI_components.StructureCard;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SectionTimeEditor extends Page {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	private SpecificDate my_specificTimestamp;
	
	private SimpleTextfield txt_year;
	private JComboBox<Pair> cmb_month;
	private JComboBox<String> cmb_day;
	private JLabel lblDayWarning;
	private SimpleRadiobutton rdbtnSpecificTimestamp;
	private SimpleRadiobutton rdbtnUnspecificTimestamp;
	private JLabel lblEnterADate;
	private JLabel lblMonth;
	private JLabel lblDay;
	private JLabel lblYear;
	private SimpleCheckbox chckbxAnnoDomini;
	private String dayOfWeek;
	private JLabel lblDayOfWeek;

	public SectionTimeEditor(Section section) {
		super("Change Timestamp of Section: " + section.getName());
		my_section = section;
		if(my_section.hasTimestamp()) {
			if(my_section.getTimestamp().isSpecificDate()) {
				my_specificTimestamp = my_section.getTimestamp().getSpecificDate();
			}
		}
		
		PageBody my_body = new PageBody();
		this.changeBody(my_body);
		
		if(UserSettings.getInstance().getTutorial().sortSectionsAndChapters && !UserSettings.getInstance().getTutorial().setTimestamps) {			
			my_body.addStructureCard(new TutorialCard(9, false));
		}
		
		//*****************************************************************************************************
		StructureCard card_information = new StructureCard("Helpful Information");
		my_body.addStructureCard(card_information);
		
		TransparentPanel panel_preSection = new TransparentPanel();
		card_information.setBody(panel_preSection);
		System.out.println();
		Section preSection = Book.getInstance().getSectionList().getPreSection(my_section);
		if(preSection != null) {			
			panel_preSection.setLayout(new BoxLayout(panel_preSection, BoxLayout.LINE_AXIS));
			panel_preSection.add(new JLabel("Section before '"));
			LinkButton btnPresection = new LinkButton(preSection.getName());
			btnPresection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new SectionPage(preSection)));
			panel_preSection.add(btnPresection);
			panel_preSection.add(new JLabel("' has Timestamp: " + preSection.getTimestamp().toString()));
		}

		
		//*****************************************************************************************************
		StructureCard card_setTimestamp = new StructureCard("Set Timestamp for Section '" + my_section.getName() + "'");
		my_body.addStructureCard(card_setTimestamp);
		//TODO: Change Forumlar, if Custom Calendar is selected!
		
		TransparentPanel panel_BODY = new TransparentPanel();
		card_setTimestamp.setBody(panel_BODY);
		panel_BODY.setLayout(new GridLayout(1, 0, 20, 20));
		
		ButtonGroup btngrTimestampType = new ButtonGroup();
		
		TransparentPanel panel_unspecific = new TransparentPanel();
		panel_BODY.add(panel_unspecific);
		panel_unspecific.setLayout(new BorderLayout(10, 10));
		
		rdbtnUnspecificTimestamp = new SimpleRadiobutton("Unspecific Timestamp");
		rdbtnUnspecificTimestamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchSpecificAndUnspecific();
			}
		});
		btngrTimestampType.add(rdbtnUnspecificTimestamp);
		panel_unspecific.add(rdbtnUnspecificTimestamp, BorderLayout.NORTH);
		
		TransparentPanel panel_unspecificBODY = new TransparentPanel();
		panel_unspecific.add(panel_unspecificBODY, BorderLayout.CENTER);
		
		JLabel lblComingSoon = new JLabel("Coming soon");
		panel_unspecificBODY.add(lblComingSoon);
		
		TransparentPanel panel_specific = new TransparentPanel();
		panel_BODY.add(panel_specific);
		panel_specific.setLayout(new BorderLayout(10, 10));
		
		rdbtnSpecificTimestamp = new SimpleRadiobutton("Specific Timestamp");
		rdbtnSpecificTimestamp.setSelected(my_specificTimestamp != null);
		rdbtnSpecificTimestamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchSpecificAndUnspecific();
			}
		});
		btngrTimestampType.add(rdbtnSpecificTimestamp);
		panel_specific.add(rdbtnSpecificTimestamp, BorderLayout.NORTH);
		
		
		chckbxAnnoDomini = new SimpleCheckbox("Anno Domini");
		if(my_specificTimestamp != null) {
			chckbxAnnoDomini.setSelected(my_specificTimestamp.isAnnoDomini());		
		} else {
			chckbxAnnoDomini.setSelected(true);			
		}
		
		
		TransparentPanel panel_specificBODY = new TransparentPanel();
		panel_specific.add(panel_specificBODY, BorderLayout.CENTER);
		panel_specificBODY.setLayout(new BorderLayout(10, 10));
		
		lblEnterADate = new JLabel("Enter a Date:");
		panel_specificBODY.add(lblEnterADate, BorderLayout.NORTH);
		
		TransparentPanel panel_specificPlacer = new TransparentPanel();
		panel_specificBODY.add(panel_specificPlacer, BorderLayout.CENTER);
		panel_specificPlacer.setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_enterDate = new TransparentPanel();
		panel_specificPlacer.add(panel_enterDate, BorderLayout.NORTH);
		panel_enterDate.setLayout(new GridLayout(0, 3, 5, 5));
		
		lblDay = new JLabel("Day:");
		panel_enterDate.add(lblDay);
		
		lblMonth = new JLabel("Month:");
		panel_enterDate.add(lblMonth);
		
		lblYear = new JLabel("Year");
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
		lblDayOfWeek = new JLabel("<html>&#8594;" + dayOfWeek + "</html>");
		panel_enterDate.add(lblDayOfWeek);
		
		JLabel lblEmpty = new JLabel(" ");
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
		
		lblDayWarning = new JLabel(" ");
		panel_DayWarning.add(lblDayWarning, BorderLayout.NORTH);
		lblDayWarning.setForeground(Color.RED);
		
		InfoButton btnSpecificHint = new InfoButton("<html>Possible days will change, if you choose month and/or year.<br/>"
				+ "If no year is entered: Current year is taken for calculate possible days.<br/>"
				+ "Do not use negative Values for Year. You can use checkbox for mark, that is not anno domini.</html>");
		panel_specificBODY.add(btnSpecificHint, BorderLayout.EAST);
		
		
		//*****************************************************************************************************
		TransparentPanel panel_FOOTER = new TransparentPanel();
		my_body.setFooter(panel_FOOTER);
		panel_FOOTER.setLayout(new BorderLayout(5, 5));
		
		JButton btnSaveTimestamp = new JButton("Save Timestamp");
		panel_FOOTER.add(btnSaveTimestamp, BorderLayout.SOUTH);
		
		JLabel lblSaveWarning = new JLabel(" ");
		panel_FOOTER.add(lblSaveWarning, BorderLayout.NORTH);
		btnSaveTimestamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean canSave = true;
				lblSaveWarning.setText(" ");
				if(rdbtnUnspecificTimestamp.isSelected()) {
					
				} else if(rdbtnSpecificTimestamp.isSelected()) {
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
						lblSaveWarning.setText("You can only set Month an Day together! (Year is mostly optional)");
					}
					
					if(canSave) {						
						Timestamp newTimestamp = new Timestamp(true, new SpecificDate(currentSelectedDay, currentSelectedMonth, currentSelectedYear, chckbxAnnoDomini.isSelected()));
						my_section.setTimestamp(newTimestamp);
						Book.getInstance().save();
						BookEditorFrame.getInstance().switchBody(new SectionPage(my_section));
					}
				} else {
					//TODO: Add Warning-Question-Window, before SAVING NOTHING?! :D
					lblSaveWarning.setText("You have selected Nothing! -> Timestamp will remove from Section!");
				}
			}
		});
		
		switchSpecificAndUnspecific();
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
	
	private void switchSpecificAndUnspecific() {
		//System.out.println("Set Specific Time: " + rdbtnSpecificTimestamp.isSelected());
		cmb_month.setEnabled(rdbtnSpecificTimestamp.isSelected());
		cmb_day.setEnabled(rdbtnSpecificTimestamp.isSelected());
		txt_year.setEnabled(rdbtnSpecificTimestamp.isSelected());
		lblEnterADate.setEnabled(rdbtnSpecificTimestamp.isSelected());
		lblMonth.setEnabled(rdbtnSpecificTimestamp.isSelected());
		lblDay.setEnabled(rdbtnSpecificTimestamp.isSelected());
		lblYear.setEnabled(rdbtnSpecificTimestamp.isSelected());
		chckbxAnnoDomini.setEnabled(rdbtnSpecificTimestamp.isSelected());
		//System.out.println("Set UNspecific Time: " + rdbtnUnspecificTimestamp.isSelected());

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
}

