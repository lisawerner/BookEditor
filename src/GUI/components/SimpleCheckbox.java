package GUI.components;

import GUI.theme.ThemeList;
import global.UserSettings;

import javax.swing.*;

public class SimpleCheckbox extends JCheckBox {
	private static final long serialVersionUID = 1L;

	public SimpleCheckbox(String text) {
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
	
	//TODO: add to all Active-Components (TextField, Textarea, Radiobutton, Checkbox, Buttons, ComboBoxes, ...) warning(true/false) and change Border to Red or Foreground to Red
}
