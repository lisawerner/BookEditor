package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuListButton extends JButton {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @deprecated
	 */
	public MenuListButton(String sectionName) {

		setText(sectionName);
		setHorizontalAlignment(SwingConstants.LEFT);
				
		changeTheme();
	}
	
	public MenuListButton(String entryName, ActionListener a) {		
		String my_text = MenuUtil.clipToLength(entryName);
		
		setText(my_text);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addActionListener(a);
				
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
