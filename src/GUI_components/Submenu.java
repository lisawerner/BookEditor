package GUI_components;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Submenu extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private String frontTag = "<html><div style='text-align: left;'><font size=\"4\">     ";
	private String backTag = "</size></div></html>";
	
	private JLabel lblSubmenutitle;
	private TransparentPanel my_buttonBody;
	private SubmenuList my_listBody;
	private TransparentPanel panel_placeBody;

	public Submenu(String title) {
		setLayout(new BorderLayout(5, 5));
		
		lblSubmenutitle = new JLabel(frontTag + title + backTag);
		add(lblSubmenutitle, BorderLayout.NORTH);
		
		panel_placeBody = new TransparentPanel();
		add(panel_placeBody, BorderLayout.CENTER);
		panel_placeBody.setLayout(new BorderLayout(0, 0));
		
		my_buttonBody = new TransparentPanel();
		panel_placeBody.add(my_buttonBody, BorderLayout.NORTH);
		my_buttonBody.setLayout(new GridLayout(0, 1, 5, 5));
				
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			lblSubmenutitle.setForeground(ThemeList.currentTheme.menuFontColor);
			revalidate();
			repaint();
		} else {

		}
	}
	
	public JButton addButton(String text) {
		MenuButton newMainButton = new MenuButton(text);
		my_buttonBody.add(newMainButton);
		return newMainButton;
	}

	public void addText(String text) {
		my_buttonBody.add(new MenuText("<html>" + text + "</html>"));
	}
	
	public void activateList() {
		my_listBody = new SubmenuList();
		panel_placeBody.add(my_listBody, BorderLayout.CENTER);
		
		revalidate();
		repaint();
	}
	
	public JButton addListButton(String text) {
		MenuListButton newMainButton = new MenuListButton(text);
		my_listBody.addItem(newMainButton);
		return newMainButton;
	}

}
