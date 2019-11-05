package GUI.settingsPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.InfoButton;
import GUI_components.SimpleCheckbox;
import GUI_components.SimpleLabel;
import GUI_components.SimpleRadiobutton;
import GUI_components.Theme;
import GUI_components.ThemeList;
import GUI_components.TransparentPanel;
import book.Book;
import global.UserSettings;

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
		
		TransparentPanel panel_changeDisplaySettings = new TransparentPanel();
		panel_changeDisplaySettings.setLayout(new GridLayout(0, 1, 0, 0));
		add(panel_changeDisplaySettings);
		
		SimpleLabel lblChangedisplayhint = new SimpleLabel("<html>If you have a huge Display you can use this setting to view 'two' frames beside at same time.<br/>"
				+ "You should not use this option if you don't have a huge display!</html>");
		panel_changeDisplaySettings.add(lblChangedisplayhint);
		
		chckbxActivateHugeDisplay = new SimpleCheckbox("Activate huge Display Settings");
		panel_changeDisplaySettings.add(chckbxActivateHugeDisplay);
		chckbxActivateHugeDisplay.addActionListener(e -> saveDisplay());
		chckbxActivateHugeDisplay.setSelected(UserSettings.getInstance().getDisplaySettings());
		
		lblDisplaySaveHint = new SimpleLabel(" ");
		lblDisplaySaveHint.setWarning(true);
		panel_changeDisplaySettings.add(lblDisplaySaveHint);
		
		lastGUIupdate();
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
