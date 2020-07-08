package GUI.components;

import javax.swing.JLabel;

import GUI.theme.ThemeList;

public class MenuText extends JLabel {
	private static final long serialVersionUID = 1L;
	
	public MenuText(String text) {
		super(text);
		
		changeTheme();
	}
	
	protected void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBackground(ThemeList.currentTheme.menuBackColor);
			setForeground(ThemeList.currentTheme.menuFontColor);
			revalidate();
			repaint();
		} else {
//			System.out.println("Change Theme in Component to: Default");
		}
	}

}
