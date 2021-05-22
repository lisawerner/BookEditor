package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;

public class FrameHeader extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private final JLabel my_label;
	
	private static final String FRONT_TAG = "<html><div style='text-align: center;'><font size=\"9\">";
	private static final String BACK_TAG = "</size></div></html>";

	public FrameHeader(String newText) {
		
		my_label = new JLabel(FRONT_TAG + newText + BACK_TAG);
		add(my_label);
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			my_label.setForeground(ThemeList.currentTheme.headerFontColor);
			setBackground(ThemeList.currentTheme.headerBackColor);
			my_label.revalidate();
			my_label.repaint();
			revalidate();
			repaint();
		}
	}
	
	public void setText(String newText) {
		my_label.setText(FRONT_TAG + newText + BACK_TAG);
	}
	
}
