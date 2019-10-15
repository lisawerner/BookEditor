package GUI.settingsPage;

import java.awt.BorderLayout;
import java.awt.Color;

import GUI.bookeditorFrame.BookEditorFrame;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;

import GUI_components.InfoButton;
import GUI_components.Page;
import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.SimpleRadiobutton;
import GUI_components.SimpleTextfield;
import GUI_components.StructureCard;
import GUI_components.Theme;
import GUI_components.ThemeList;
import GUI_components.TransparentPanel;
import GUI_components.TutorialCard;
import book.Book;
import global.UserSettings;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookSettingsPage extends Page {
	private static final long serialVersionUID = 1L;

	private Theme my_theme;
	
	private SimpleTextfield txt_title;
	private SimpleCheckbox rdbtnIsWorkTitle;
	private SimpleLabel lblTitle;
	private SimpleCheckbox rdbtnPrintChaptername;
	private SimpleRadiobutton rdbtnDark;
	private SimpleRadiobutton rdbtnLight;

	public BookSettingsPage() {
		super("Book Settings");
		
		my_theme = Book.getInstance().getTheme();
		
		//******************************************************************************************************************************************
		
		if(!UserSettings.getInstance().getTutorial().chooseFirstColorTheme) {			
			addCard(new TutorialCard(2, false));
		}
		if(UserSettings.getInstance().getTutorial().chooseFirstColorTheme && !UserSettings.getInstance().getTutorial().createFirstSection) {			
			addCard(new TutorialCard(3, false));
		}
		
		//******************************************************************************************************************************************
		//********************************************************************
		//CARD change Book Title
		StructureCard card_changeBookTitle = new StructureCard("Change Book Title");
		addCard(card_changeBookTitle);
		
		TransparentPanel changeBookTitle_body = new TransparentPanel();
		card_changeBookTitle.setBody(changeBookTitle_body);
		changeBookTitle_body.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel panel_setTitle = new TransparentPanel();
		changeBookTitle_body.add(panel_setTitle);
		panel_setTitle.setLayout(new BorderLayout(5, 5));
		
		lblTitle = new SimpleLabel("Title:");
		panel_setTitle.add(lblTitle, BorderLayout.WEST);
		
		txt_title = new SimpleTextfield(Book.getInstance().getTitle());
		panel_setTitle.add(txt_title, BorderLayout.CENTER);
		
		rdbtnIsWorkTitle = new SimpleCheckbox("This is only a worktitle. Please remember me changing it, before publishing book.");
		changeBookTitle_body.add(rdbtnIsWorkTitle);
		rdbtnIsWorkTitle.setSelected(Book.getInstance().isWorkTitle());
		//********************************************************************
		
		
		//********************************************************************
		//CARD change Timeline Settings
		StructureCard card_TimelineSettings = new StructureCard("Change Timeline-Settings");
		addCard(card_TimelineSettings);
		
		TransparentPanel timelineSettings_body = new TransparentPanel();
		card_TimelineSettings.setBody(timelineSettings_body);
		timelineSettings_body.setLayout(new GridLayout(0, 1, 10, 10));
				
		SimpleLabel lblIfYouWant = new SimpleLabel("If you want to work with Timeline-Features, choose following options:");
		timelineSettings_body.add(lblIfYouWant);
		
		ButtonGroup btngrCalendarType = new ButtonGroup();
				
		TransparentPanel panel_timelineCalendarType = new TransparentPanel();
		timelineSettings_body.add(panel_timelineCalendarType);
		panel_timelineCalendarType.setLayout(new GridLayout(1, 0, 10, 10));
		SimpleRadiobutton rdbtnGregorianCalendar = new SimpleRadiobutton("Use: Gregorian Calendar");
		rdbtnGregorianCalendar.setSelected(Book.getInstance().useGregorianCalendar());
		btngrCalendarType.add(rdbtnGregorianCalendar);
		panel_timelineCalendarType.add(rdbtnGregorianCalendar);
		
		TransparentPanel rdbtnWithInfoBtn = new TransparentPanel();
		panel_timelineCalendarType.add(rdbtnWithInfoBtn);
		rdbtnWithInfoBtn.setLayout(new BorderLayout(0,0));
		SimpleRadiobutton rdbtnCustomCalendar = new SimpleRadiobutton("Use: Custom Calendar(s)");
		btngrCalendarType.add(rdbtnCustomCalendar);
		rdbtnCustomCalendar.setEnabled(false);
		rdbtnWithInfoBtn.add(rdbtnCustomCalendar, BorderLayout.CENTER);
		
		InfoButton btnI = new InfoButton("Custom Calendar is not available at the moment.");
		rdbtnWithInfoBtn.add(btnI, BorderLayout.WEST);
		//********************************************************************
		
		
		//********************************************************************
		//CARD change Color-Theme
		//TODO: WARNIGN! ERROR: When first select Theme and then select Default again -> Can not set Theme to default again -> Error appeared
		StructureCard card_ThemeSettings = new StructureCard("Change Color Theme of Editor:");
		addCard(card_ThemeSettings);
		
		TransparentPanel themeSettings_body = new TransparentPanel();
		themeSettings_body.setLayout(new BorderLayout(5, 5));
		card_ThemeSettings.setBody(themeSettings_body);
		
		TransparentPanel panel_themeList = new TransparentPanel();
		themeSettings_body.add(panel_themeList, BorderLayout.CENTER);
		
		ButtonGroup btngrTheme = new ButtonGroup();
		
		panel_themeList.setLayout(new GridLayout(0, 3, 5, 5));
		SimpleRadiobutton rdbtnDefaultTheme = new SimpleRadiobutton("Default");
		rdbtnDefaultTheme.setSelected(my_theme == null);
		rdbtnDefaultTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				my_theme = null;
				enableColorTheme(false);
			}
		});
		btngrTheme.add(rdbtnDefaultTheme);
		panel_themeList.add(rdbtnDefaultTheme);
		for(Theme theme : ThemeList.getThemes()) {			
			SimpleRadiobutton rdbtnTheme = new SimpleRadiobutton(theme.themeName);
			if(my_theme != null) {				
				if(theme.themeName.equals(my_theme.themeName)) {
					rdbtnTheme.setSelected(true);
				}
			}
			rdbtnTheme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					my_theme = theme;
					enableColorTheme(true);
				}
			});
			btngrTheme.add(rdbtnTheme);
			panel_themeList.add(rdbtnTheme);
		}
		
		InfoButton btnThemeInfo = new InfoButton("You can choose different Themes for every book.");
		themeSettings_body.add(btnThemeInfo, BorderLayout.EAST);
		
		ButtonGroup btngrThemeDarkLight = new ButtonGroup();
		
		TransparentPanel panel_DarkLight = new TransparentPanel();
		themeSettings_body.add(panel_DarkLight, BorderLayout.WEST);
		panel_DarkLight.setLayout(new GridLayout(0, 1, 5, 5));
		
		rdbtnDark = new SimpleRadiobutton("DarkTheme");
		btngrThemeDarkLight.add(rdbtnDark);
		if(my_theme != null) {
			rdbtnDark.setSelected(my_theme.darkTheme);
		}
		panel_DarkLight.add(rdbtnDark);
		
		rdbtnLight = new SimpleRadiobutton("Light Theme");
		btngrThemeDarkLight.add(rdbtnLight);
		if(my_theme != null) {			
			rdbtnLight.setSelected(!my_theme.darkTheme);
		}
		panel_DarkLight.add(rdbtnLight);

		enableColorTheme(my_theme != null);
		//********************************************************************
		
		
		//********************************************************************
		//CARD change Print Settings
		StructureCard card_PrintSettings = new StructureCard("Change Print Settings:");
		addCard(card_PrintSettings);

		TransparentPanel printSettings_body = new TransparentPanel();
		printSettings_body.setLayout(new GridLayout(1, 0, 5, 5));
		card_PrintSettings.setBody(printSettings_body);
		
		rdbtnPrintChaptername = new SimpleCheckbox("Select this for printing Chapter Name as set in Table of Content (Otherwise chapter-name will print as number)");
		rdbtnPrintChaptername.setSelected(Book.getInstance().printChapterName());
		printSettings_body.add(rdbtnPrintChaptername);
		//********************************************************************
		
		TransparentPanel footer = new TransparentPanel();
		setFooter(footer);
		footer.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblWarning = new SimpleLabel(" ");
		lblWarning.setForeground(Color.RED);
		footer.add(lblWarning);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblWarning.setText(" ");
				setWarningEnterName(false);
				boolean canSave = true;
				
				String booktitle = txt_title.getText();
				boolean isWorkTitled = rdbtnIsWorkTitle.isSelected();
				boolean isGregorian = rdbtnIsWorkTitle.isSelected();
				boolean printChaptername = rdbtnPrintChaptername.isSelected();
				Theme theme = my_theme;
				
				if("".equals(booktitle)) {
					lblWarning.setText("You have to enter an booktitle!");
					canSave = false;
					setWarningEnterName(true);
				}
				
				if(my_theme != null) {
					if(!rdbtnDark.isSelected() && !rdbtnLight.isSelected()) {
						lblWarning.setText("If you choose a Theme, you have to choose dark or light also!");
						canSave = false;
					}
					theme.darkTheme = rdbtnDark.isSelected() && !rdbtnLight.isSelected();
				}
				
							
				if(canSave) {
					Book.getInstance().changeBookSettings(booktitle, isWorkTitled, isGregorian, printChaptername, theme);
					if(!UserSettings.getInstance().getTutorial().chooseFirstColorTheme) {						
						if(theme != null) {
							UserSettings.getInstance().getTutorial().chooseFirstColorTheme = true;
							UserSettings.getInstance().save();
						}
					}
					BookEditorFrame.getInstance().updateBookTitle();
					ThemeList.setCurrentTheme(theme);
					BookEditorFrame.getInstance().repaintFrame();
					BookEditorFrame.getInstance().switchBody(new BookSettingsPage());
				}
			}
		});
		footer.add(btnSave);

	}
	
	private void setWarningEnterName(boolean warning) {
		if(warning) {
			txt_title.setBorder(BorderFactory.createLineBorder(Color.RED));
			lblTitle.setForeground(Color.RED);
		} else {
			txt_title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblTitle.setForeground(Color.BLACK);
		}
	}
	
	public void enableColorTheme(boolean enable) {
		rdbtnDark.setEnabled(enable);
		rdbtnLight.setEnabled(enable);
	}

}
