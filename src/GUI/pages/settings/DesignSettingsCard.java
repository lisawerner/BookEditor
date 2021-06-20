package GUI.pages.settings;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.*;
import GUI.theme.Theme;
import GUI.theme.ThemeList;
import book.content.Book;
import global.UserSettings;

import javax.swing.*;
import java.awt.*;

public class DesignSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Theme my_theme;
	
	private final ButtonGroup btngrTheme;
	private final TransparentPanel panel_themeList;
	private final SimpleRadiobutton rdbtnDark;
	private final SimpleRadiobutton rdbtnLight;
	private final SimpleLabel lblWarning;
	
	private final SimpleCheckbox checkboxActivateHugeDisplay;
	private final SimpleLabel lblDisplaySaveHint;
	
	private final SimpleIntegerTextField txt_AreaFontSize;
	private final SimpleLabel lbl_fontSizeSaveHint;

	public DesignSettingsCard() {
		
		my_theme = Book.getInstance().getTheme();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		btngrTheme = new ButtonGroup();
		
		ButtonGroup btngrThemeDarkLight = new ButtonGroup();
		
		TransparentPanel panel_changeTheme = new TransparentPanel();
		add(panel_changeTheme, BorderLayout.NORTH);
		panel_changeTheme.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblChangeColorTheme = new SimpleLabel("Change Color Theme of Editor:");
		panel_changeTheme.add(lblChangeColorTheme, BorderLayout.NORTH);
		
		TransparentPanel panel_DarkLight = new TransparentPanel();
		panel_changeTheme.add(panel_DarkLight, BorderLayout.WEST);
		panel_DarkLight.setLayout(new GridLayout(0, 1, 5, 5));
		
		rdbtnDark = new SimpleRadiobutton("DarkTheme");
		btngrThemeDarkLight.add(rdbtnDark);
		rdbtnDark.addActionListener(e -> save());
		panel_DarkLight.add(rdbtnDark);
		
		rdbtnLight = new SimpleRadiobutton("Light Theme");
		btngrThemeDarkLight.add(rdbtnLight);
		rdbtnLight.addActionListener(e -> save());
		panel_DarkLight.add(rdbtnLight);
		

		
		InfoButton btnThemeInfo = new InfoButton("You can choose different Themes for every book.");
		panel_changeTheme.add(btnThemeInfo, BorderLayout.EAST);
		
		lblWarning = new SimpleLabel(" ");
		panel_changeTheme.add(lblWarning, BorderLayout.SOUTH);
		lblWarning.setWarning(true);
		
		panel_themeList = new TransparentPanel();
		panel_changeTheme.add(panel_themeList, BorderLayout.CENTER);
		
		panel_themeList.setLayout(new GridLayout(0, 5, 5, 5));
		SimpleRadiobutton rdbtnDefaultTheme = new SimpleRadiobutton("Default");
		rdbtnDefaultTheme.setSelected(my_theme == null);
		rdbtnDefaultTheme.addActionListener(e -> {
			my_theme = null;
			enableColorTheme(false);
		});
		btngrTheme.add(rdbtnDefaultTheme);
		panel_themeList.add(rdbtnDefaultTheme);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea);
		
		JSeparator separator = new JSeparator();
		add(separator);
		
		TransparentPanel panel_settingsHintPosition = new TransparentPanel();
		add(panel_settingsHintPosition);
		panel_settingsHintPosition.setLayout(new GridLayout(0, 1, 0, 0));
		
		SimpleLabel lblSettingsHint = new SimpleLabel("Following setting are for every book project!");
		panel_settingsHintPosition.add(lblSettingsHint);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2);
		
		TransparentPanel panel_changeDisplaySettings = new TransparentPanel();
		add(panel_changeDisplaySettings);
		panel_changeDisplaySettings.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblChangeDisplayHint = new SimpleLabel("<html>If you have a huge Display you can use this setting to view 'two' frames beside at same book.time.<br/>"
				+ "You should not use this option if you don't have a huge display.</html>");
		panel_changeDisplaySettings.add(lblChangeDisplayHint, BorderLayout.NORTH);
		
		checkboxActivateHugeDisplay = new SimpleCheckbox("Activate huge Display Settings");
		panel_changeDisplaySettings.add(checkboxActivateHugeDisplay, BorderLayout.CENTER);
		checkboxActivateHugeDisplay.addActionListener(e -> saveDisplay());
		checkboxActivateHugeDisplay.setSelected(UserSettings.getInstance().getDisplaySettings());
		
		lblDisplaySaveHint = new SimpleLabel(" ");
		lblDisplaySaveHint.setWarning(true);
		panel_changeDisplaySettings.add(lblDisplaySaveHint, BorderLayout.SOUTH);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1);
		
		TransparentPanel panel_fontSettings = new TransparentPanel();
		add(panel_fontSettings);
		panel_fontSettings.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblSetFontSize = new SimpleLabel("Set font size:");
		panel_fontSettings.add(lblSetFontSize, BorderLayout.WEST);
		
		InfoButton btnInfoFontSize = new InfoButton("<html>You can change the size of the font for text areas."
				+ "<br/>Text areas are form fields with multiple lines."
				+ "<br/>For example the form in the section-editor for the content text is a textarea.</html>");
		panel_fontSettings.add(btnInfoFontSize, BorderLayout.EAST);
		
		txt_AreaFontSize = new SimpleIntegerTextField();
		txt_AreaFontSize.setText("" + UserSettings.getInstance().getTextareaFontSize());
		panel_fontSettings.add(txt_AreaFontSize, BorderLayout.CENTER);
		txt_AreaFontSize.addActionListener(e -> saveTextareaFontSize());
		
		lbl_fontSizeSaveHint = new SimpleLabel(" ");
		panel_fontSettings.add(lbl_fontSizeSaveHint, BorderLayout.SOUTH);
		lbl_fontSizeSaveHint.setWarning(true);
		
		SimpleLabel lblSaveNewFont = new SimpleLabel("Save new Font by Pressing Enter");
		panel_fontSettings.add(lblSaveNewFont, BorderLayout.NORTH);
		
		lastGuiUpdate();
	}
	
	private void saveTextareaFontSize() {
		UserSettings.getInstance().setTextAreaSize(txt_AreaFontSize.getInteger());
		lbl_fontSizeSaveHint.setText("Successfully saved font size!");
	}

	private void lastGuiUpdate() {
		if(my_theme != null) {
			rdbtnDark.setSelected(my_theme.darkTheme);
		}
		if(my_theme != null) {			
			rdbtnLight.setSelected(!my_theme.darkTheme);
		}
		enableColorTheme(my_theme != null);
		for(Theme theme : ThemeList.getThemes()) {			
			SimpleRadiobutton rdbtnTheme = new SimpleRadiobutton(theme.themeName);
			if(my_theme != null) {				
				if(theme.themeName.equals(my_theme.themeName)) {
					rdbtnTheme.setSelected(true);
				}
			}
			rdbtnTheme.addActionListener(e -> {
				my_theme = theme;
				enableColorTheme(true);
				save();
			});
			btngrTheme.add(rdbtnTheme);
			panel_themeList.add(rdbtnTheme);
		}
	}
	
	public void enableColorTheme(boolean enable) {
		rdbtnDark.setEnabled(enable);
		rdbtnLight.setEnabled(enable);
	}

	private Theme getTheme() {
		if(my_theme != null) {
			if(!rdbtnDark.isSelected() && !rdbtnLight.isSelected()) {
				return null;
			}
			my_theme.darkTheme = rdbtnDark.isSelected() && !rdbtnLight.isSelected();
		}
		return my_theme;
	}

	private boolean canSave() {
		return (my_theme != null && (rdbtnDark.isSelected() || rdbtnLight.isSelected())) || (my_theme == null);
	}
	
	private void save() {
		if(!canSave()) {
			lblWarning.setText("Can not change, because something is wrong with theme-settings!");
		}
		Theme theme = getTheme();
		
		if(canSave()) {
			if(!UserSettings.getInstance().getTutorial().chooseFirstColorTheme) {						
				if(theme != null) {
					UserSettings.getInstance().getTutorial().chooseFirstColorTheme = true;
					UserSettings.getInstance().save();
				}
			}
			Book.getInstance().changeBookTheme(theme);
			
			//*******************************************************
			//REPAINT FRAME!!!
			BookEditorFrame.getInstance().updateBookTitle();
			ThemeList.setCurrentTheme(theme);
			BookEditorFrame.getInstance().repaintFrame();
			BookEditorFrame.getInstance().switchBody(new BookSettingsPage());
		}
	}
	
	private void saveDisplay() {
		UserSettings.getInstance().setDisplaySettings(checkboxActivateHugeDisplay.isSelected());
		if(checkboxActivateHugeDisplay.isSelected()) {
			lblDisplaySaveHint.setText("Display Settings are saved: Now two frames can seen at same book.time together");
		} else {
			lblDisplaySaveHint.setText("Display Settings are saved: There is always only one frame");
		}
	}

}
