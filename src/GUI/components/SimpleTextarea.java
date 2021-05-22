package GUI.components;

import GUI.theme.ThemeList;
import global.UserSettings;

import javax.swing.*;
import java.awt.*;

public class SimpleTextarea extends JTextArea{
	private static final long serialVersionUID = 1L;
	
	public SimpleTextarea() {
		setWrapStyleWord(true);
		setLineWrap(true);
		setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		
		changeTheme();
	}
	
	public SimpleTextarea(String text) {
		super(text);
		setWrapStyleWord(true);
		setLineWrap(true);
		setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		
		changeTheme();
	}

	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.actionBorderColor));
			revalidate();
			repaint();
		} else {
			setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
	}

	public void setWarning(boolean hasChanges) {
		//TODO: add to all Active-Components (TextField, Textarea, Radiobutton, Checkbox, Buttons, ComboBoxes, ...) warning(true/false) and change Border to Red or Foreground to Red
	}
	
}
