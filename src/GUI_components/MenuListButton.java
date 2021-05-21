package GUI_components;

import javax.swing.*;

public class MenuListButton extends JButton {
	private static final long serialVersionUID = 1L;
	


	public MenuListButton(String sectionName) {
		setText(sectionName);
		setHorizontalAlignment(SwingConstants.LEFT);
				
		changeTheme();
	}
	
	protected void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setForeground(ThemeList.currentTheme.menuListButtonFont);
			setBackground(ThemeList.currentTheme.menuBackColor);
			setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.menuButtonBack));
			revalidate();
			repaint();
		}
	}
	
}
