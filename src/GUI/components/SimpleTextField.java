package GUI.components;

import GUI.theme.ThemeList;
import global.UserSettings;

import javax.swing.*;
import java.awt.*;

public class SimpleTextField extends JTextField{
	private static final long serialVersionUID = 1L;
	
	public SimpleTextField() {
		setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		
		changeTheme();
	}
	
	public SimpleTextField(String text) {
		super(text);
		setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.actionBorderColor));
			revalidate();
			repaint();
		} else {
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}
	
	public void setWarning(boolean activateWarning) {
		if(activateWarning) {
			setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.warningTutorialCardLayout));
			revalidate();
			repaint();
		} else {
			changeTheme();
		}
	}
	
}
