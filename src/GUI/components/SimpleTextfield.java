package GUI.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class SimpleTextfield extends JTextField{
	private static final long serialVersionUID = 1L;
	
	public SimpleTextfield() {
		
		changeTheme();
	}
	
	public SimpleTextfield(String text) {
		super(text);
		
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
