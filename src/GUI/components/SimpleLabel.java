package GUI.components;

import GUI.theme.ThemeList;
import global.UserSettings;

import javax.swing.*;
import java.awt.*;

public class SimpleLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	
	public SimpleLabel(String text) {
		super(text);
		setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				setForeground(ThemeList.currentTheme.darkForegroundColor);
			}
			revalidate();
			repaint();
		}
	}
	
	public void setWarning(boolean activateWarning) {
		if(activateWarning) {
			if(ThemeList.currentTheme != null) {			
				setForeground(ThemeList.currentTheme.warningTutorialCardLayout);
			} else {
				setForeground(Color.RED);
			}
			revalidate();
			repaint();
		} else {
			changeTheme();
		}
	}

}
