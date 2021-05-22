package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;

public class MenuSubHeader extends JLabel {
	private static final long serialVersionUID = 1L;

	private static final String FRONT_TAG = "<html><div style='text-align: left;'><font size=\"5\">     ";
	private static final String BACK_TAG = "</size></div></html>";
	
	public MenuSubHeader(String subMenuHeader) {
		this.setText(FRONT_TAG + subMenuHeader + BACK_TAG);
		changeTheme();
	}

	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setForeground(ThemeList.currentTheme.menuFontColor);
			revalidate();
			repaint();
		}
	}
}
