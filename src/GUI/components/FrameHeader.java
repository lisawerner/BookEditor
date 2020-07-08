package GUI.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.theme.ThemeList;

public class FrameHeader extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel my_label;
	
	private String frontTag = "<html><div style='text-align: center;'><font size=\"9\">";
	private String backTag = "</size></div></html>";

	public FrameHeader(String newText) {
		
		my_label = new JLabel(frontTag + newText + backTag);
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
		} else {
//			System.out.println("Change Theme in Component to: Default");
		}
	}
	
	public void setText(String newText) {
		my_label.setText(frontTag + newText + backTag);
	}
	
}
