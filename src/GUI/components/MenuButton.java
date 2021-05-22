package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuButton extends JButton {
	private static final long serialVersionUID = 1L;

	public MenuButton(String text, ActionListener l) {
		super(text);
		this.addActionListener(l);
		
		changeTheme();
	}
	
	protected void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBorder(BorderFactory.createRaisedBevelBorder());
			setBackground(ThemeList.currentTheme.menuButtonBack);
			setForeground(ThemeList.currentTheme.menuButtonFont);
			revalidate();
			repaint();
		}
	}

}
