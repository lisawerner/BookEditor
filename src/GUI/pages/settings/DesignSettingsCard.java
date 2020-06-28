package GUI.pages.settings;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import GUI.bookeditorFrame.BookEditorFrame;
import book.Book;
import global.UserSettings;
import GUI.components.InfoButton;
import GUI.components.SimpleCheckbox;
import GUI.components.SimpleIntegerTextfield;
import GUI.components.SimpleLabel;
import GUI.components.SimpleRadiobutton;
import GUI.components.Theme;
import GUI.components.ThemeList;
import GUI.components.TransparentPanel;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JSeparator;

public class DesignSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Theme my_theme;
	
	private ButtonGroup btngrTheme;
	private TransparentPanel panel_themeList;
	private SimpleRadiobutton rdbtnDark;
	private SimpleRadiobutton rdbtnLight;
	private SimpleLabel lblWarning;
	
	private SimpleCheckbox chckbxActivateHugeDisplay;
	private SimpleLabel lblDisplaySaveHint;
	
	private SimpleIntegerTextfield txt_AreaFontSize;
	private SimpleLabel lbl_fontSizeSaveHint;

	public DesignSettingsCard() {
		
		my_theme = Book.getInstance().getTheme();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		btngrTheme = new ButtonGroup();
		
		ButtonGroup btngrThemeDarkLight = new ButtonGroup();
		
		TransparentPanel panel_changeTheme = new TransparentPanel();
		add(panel_changeTheme, BorderLayout.NORTH);
		panel_changeTheme.setLayout(new BorderLayout(5, 5));
		
		SimpleLabel lblChangeColortheme = new SimpleLabel("Change Color Theme of Editor:");
		panel_changeTheme.add(lblChangeColortheme, BorderLayout.NORTH);
		
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
		rdbtnDefaultTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				my_theme = null;
				enableColorTheme(false);
			}
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
		
		SimpleLabel lblSettingshint = new SimpleLabel("Following setting are for every book project!");
		panel_settingsHintPosition.add(lblSettingshint);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2);
		
		TransparentPanel panel_changeDisplaySettings = new TransparentPanel();
		add(panel_changeDisplaySettings);
		panel_changeDisplaySettings.setLayout(new BorderLayout(0, 0));
		
		SimpleLabel lblChangedisplayhint = new SimpleLabel("<html>If you have a huge Display you can use this setting to view 'two' frames beside at same time.<br/>"
				+ "You should not use this option if you don't have a huge display.</html>");
		panel_changeDisplaySettings.add(lblChangedisplayhint, BorderLayout.NORTH);
		
		chckbxActivateHugeDisplay = new SimpleCheckbox("Activate huge Display Settings");
		panel_changeDisplaySettings.add(chckbxActivateHugeDisplay, BorderLayout.CENTER);
		chckbxActivateHugeDisplay.addActionListener(e -> saveDisplay());
		chckbxActivateHugeDisplay.setSelected(UserSettings.getInstance().getDisplaySettings());
		
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
		
		InfoButton btnInfoFontSize = new InfoButton("<html>You can change the size of the font for textareas."
				+ "<br/>Textareas are formular fields with multiple lines."
				+ "<br/>For example the formular in the section-editor for the content text is a textarea.</html>");
		panel_fontSettings.add(btnInfoFontSize, BorderLayout.EAST);
		
		txt_AreaFontSize = new SimpleIntegerTextfield();
		txt_AreaFontSize.setText("" + UserSettings.getInstance().getTextareaFontSize());
		panel_fontSettings.add(txt_AreaFontSize, BorderLayout.CENTER);
		txt_AreaFontSize.addActionListener(e -> saveTextareaFontsize());
		
		lbl_fontSizeSaveHint = new SimpleLabel(" ");
		panel_fontSettings.add(lbl_fontSizeSaveHint, BorderLayout.SOUTH);
		lbl_fontSizeSaveHint.setWarning(true);
		
		SimpleLabel lblSaveNewFont = new SimpleLabel("Save new Font by Pressing Enter");
		panel_fontSettings.add(lblSaveNewFont, BorderLayout.NORTH);
		
		lastGUIupdate();
	}
	
	private void saveTextareaFontsize() {
		UserSettings.getInstance().setTextAreaSize(txt_AreaFontSize.getInteger());
		lbl_fontSizeSaveHint.setText("Successfully saved font size!");
	}

	private void lastGUIupdate() {
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
			rdbtnTheme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					my_theme = theme;
					enableColorTheme(true);
					save();
				}
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
		UserSettings.getInstance().setDisplaySettings(chckbxActivateHugeDisplay.isSelected());
		if(chckbxActivateHugeDisplay.isSelected()) {
			lblDisplaySaveHint.setText("Display Settings are saved: Now two frames can seen at same time together");
		} else {
			lblDisplaySaveHint.setText("Display Settings are saved: There is always only one frame");
		}
	}

}
