package GUI.components;

import javax.swing.JCheckBox;

import GUI.theme.ThemeList;
import global.UserSettings;

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
		} else {
		}
	}
	
	//TODO: add to all Active-Components (Textfield, Textarea, Radiobutton, Checkbox, Buttons, Comboboxes, ...) warning(true/false) and change Border to Red or Foreground to Red
}
