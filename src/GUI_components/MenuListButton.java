package GUI_components;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MenuListButton extends JButton {
	private static final long serialVersionUID = 1L;
	


	public MenuListButton(String sectionName) {		
		String my_text = sectionName;
		
		setText(my_text);
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
		} else {
			System.out.println("Change Theme in Component to: Default");
		}
	}
	
}
