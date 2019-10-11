package GUI_components;

import javax.swing.JRadioButton;

public class SimpleRadiobutton extends JRadioButton {
	private static final long serialVersionUID = 1L;
	
	public SimpleRadiobutton(String text) {
		super(text);
		
		setOpaque(false);
	
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setForeground(ThemeList.currentTheme.actionFontColor);
			revalidate();
			repaint();
		} else {
			System.out.println("Change Theme in Component to: Default");
		}
	}
	
	//TODO: add to all Active-Components (Textfield, Textarea, Radiobutton, Checkbox, Buttons, Comboboxes, ...) warning(true/false) and change Border to Red or Foreground to Red

}
