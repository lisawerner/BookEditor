package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;

public class MenuText extends JLabel {
	private static final long serialVersionUID = 1L;
	
	public MenuText(String text) {
		super(text);
		
		changeTheme();
	}
	
	protected void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBackground(ThemeList.currentTheme.menuBackColor);
			setForeground(ThemeList.currentTheme.menuFontColor);
			revalidate();
			repaint();
		}
	}

}
