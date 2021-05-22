package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;

public class MenuSeparator extends JSeparator {
	private static final long serialVersionUID = 1L;

	public MenuSeparator() {
		changeTheme();
	}
	
	private void changeTheme(){
		if(ThemeList.currentTheme != null) {			
			setForeground(ThemeList.currentTheme.menuButtonFont);
			setBackground(ThemeList.currentTheme.menuButtonFont);
		}
	}

}
