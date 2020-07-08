package GUI.components;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import GUI.theme.ThemeList;

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
		} else {
//			System.out.println("Change Theme in Component to: Default");
		}
	}

}
