package GUI.components;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import GUI.theme.ThemeList;

public class MenuListButton extends JButton {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @deprecated
	 */
	public MenuListButton(String sectionName) {		
		String my_text = sectionName;
		
		setText(my_text);
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
		} else {
//			System.out.println("Change Theme in Component to: Default");
		}
	}
	
}
