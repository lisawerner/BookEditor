package GUI.components;

import javax.swing.JRadioButton;

import global.UserSettings;

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
		} else {
//			System.out.println("Change Theme in Component to: Default");
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
