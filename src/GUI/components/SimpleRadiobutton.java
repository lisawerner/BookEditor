package GUI.components;

import GUI.theme.ThemeList;
import global.UserSettings;

import javax.swing.*;

public class SimpleRadiobutton extends JRadioButton {
	private static final long serialVersionUID = 1L;
	
	public SimpleRadiobutton(String text) {
		super(text);
		setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		
		setOpaque(false);
	
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				setForeground(ThemeList.currentTheme.darkActionFontColor);
			} else {
				setForeground(ThemeList.currentTheme.actionFontColor);
			}
			revalidate();
			repaint();
		}
	}

	public void setWarning(boolean activateWarning) {
		if(activateWarning) {
			setForeground(ThemeList.currentTheme.warningTutorialCardLayout);
		} else {
			changeTheme();
		}
		
	}
}
