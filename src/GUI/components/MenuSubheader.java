package GUI.components;

import javax.swing.JLabel;

import GUI.theme.ThemeList;

public class MenuSubheader extends JLabel {
	private static final long serialVersionUID = 1L;

	private String frontTag = "<html><div style='text-align: left;'><font size=\"5\">     ";
	private String backTag = "</size></div></html>";
	
	public MenuSubheader(String submenuheader) {
		this.setText(frontTag + submenuheader + backTag);
		changeTheme();
	}

	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setForeground(ThemeList.currentTheme.menuFontColor);
			revalidate();
			repaint();
		} else {

		}
	}
}
