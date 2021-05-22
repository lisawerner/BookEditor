package GUI.components;

import GUI.frame.menu.MainMenu;
import GUI.theme.ThemeList;

import javax.swing.*;
import java.awt.*;

public class FrameMenu extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final JLabel lbl_menuHeader;
	private static final String FRONT_HEADER_TAG = "<html><div style='text-align: center;'><font size=\"7\">";
	private static final String BACK_HEADER_TAG = "</size></div></html>";

	private final TransparentPanel panel_submenu;

	public FrameMenu() {
		setLayout(new BorderLayout(0, 0));
		
		Component rigidArea = Box.createRigidArea(new Dimension(171, 10)); //Set width higher if menu resizes again because of new huge titles
		add(rigidArea, BorderLayout.NORTH);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(10, 10));
		add(rigidArea_1, BorderLayout.WEST);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(10, 10));
		add(rigidArea_2, BorderLayout.EAST);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(10, 10));
		add(rigidArea_3, BorderLayout.SOUTH);

		TransparentPanel menuBody = new TransparentPanel();
		add(menuBody, BorderLayout.CENTER);
		menuBody.setLayout(new BoxLayout(menuBody, BoxLayout.PAGE_AXIS));
		
		TransparentPanel panel_header = new TransparentPanel();
		menuBody.add(panel_header);
		panel_header.setLayout(new BoxLayout(panel_header, BoxLayout.LINE_AXIS));
		lbl_menuHeader = new JLabel(FRONT_HEADER_TAG + "Menu:" + BACK_HEADER_TAG);
		panel_header.add(lbl_menuHeader);

		TransparentPanel panel_mainMenu = new TransparentPanel();
		menuBody.add(panel_mainMenu);
		panel_mainMenu.setLayout(new BorderLayout(5, 5));
		panel_mainMenu.add(new MainMenu(), BorderLayout.NORTH);
		
		panel_submenu = new TransparentPanel();
		panel_mainMenu.add(panel_submenu, BorderLayout.CENTER);
		panel_submenu.setLayout(new BorderLayout(0, 0));
		
		TransparentPanel panel_submenuList = new TransparentPanel();
		menuBody.add(panel_submenuList);
		panel_submenuList.setLayout(new BorderLayout(0, 0));

		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			//General
			setBackground(ThemeList.currentTheme.menuBackColor);
			//Header texts
			lbl_menuHeader.setForeground(ThemeList.currentTheme.menuFontColor);
			//...
			revalidate();
			repaint();
		}
	}
		
	public void changeSubmenuTo(TransparentPanel submenu){
		removeSubmenu();
		panel_submenu.setLayout(new BorderLayout(0, 0));
		panel_submenu.add(submenu, BorderLayout.CENTER);
		panel_submenu.revalidate();
		panel_submenu.repaint();
	}

	public void removeSubmenu() {
		panel_submenu.removeAll();
		panel_submenu.revalidate();
		panel_submenu.repaint();
	}

}
