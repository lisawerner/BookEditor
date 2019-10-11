package GUI_components;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class SimpleTextfield extends JTextField{
	private static final long serialVersionUID = 1L;
	
	public SimpleTextfield() {
		
		changeTheme();
	}
	
	public SimpleTextfield(String text) {
		super(text);
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.actionBorderColor));
			revalidate();
			repaint();
		} else {
//			System.out.println("Change Theme in Component to: Default");
		}
	}
	
	//TODO: add to all Active-Components (Textfield, Textarea, Radiobutton, Checkbox, Buttons, Comboboxes, ...) warning(true/false) and change Border to Red or Foreground to Red

}
