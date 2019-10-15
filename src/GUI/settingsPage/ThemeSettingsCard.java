package GUI.settingsPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;

import GUI_components.InfoButton;
import GUI_components.SimpleRadiobutton;
import GUI_components.Theme;
import GUI_components.ThemeList;
import GUI_components.TransparentPanel;
import book.Book;

public class ThemeSettingsCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Theme my_theme;
	
	private SimpleRadiobutton rdbtnDark;
	private SimpleRadiobutton rdbtnLight;

	public ThemeSettingsCard() {
		
		my_theme = Book.getInstance().getTheme();
		
		setLayout(new BorderLayout(5, 5));
		
		TransparentPanel panel_themeList = new TransparentPanel();
		add(panel_themeList, BorderLayout.CENTER);
		
		ButtonGroup btngrTheme = new ButtonGroup();
		
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
		add(btnThemeInfo, BorderLayout.EAST);
		
		ButtonGroup btngrThemeDarkLight = new ButtonGroup();
		
		TransparentPanel panel_DarkLight = new TransparentPanel();
		add(panel_DarkLight, BorderLayout.WEST);
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

	}
	
	public void enableColorTheme(boolean enable) {
		rdbtnDark.setEnabled(enable);
		rdbtnLight.setEnabled(enable);
	}

	public Theme getTheme() {
		if(my_theme != null) {
			if(!rdbtnDark.isSelected() && !rdbtnLight.isSelected()) {
				return null;
			}
			my_theme.darkTheme = rdbtnDark.isSelected() && !rdbtnLight.isSelected();
		}
		return my_theme;
	}

	public boolean canSave() {
		return (my_theme != null && (rdbtnDark.isSelected() || rdbtnLight.isSelected())) || (my_theme == null);
	}

}
