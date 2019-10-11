package GUI_components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class MenuPage extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private String frontTag = "<html><div style='text-align: left;'><font size=\"5\">     ";
	private String backTag = "</size></div></html>";
	
	private JPanel bodyBetweenPageGabPlacer;
	private JLabel lblMenutitle;
	private TransparentPanel my_body;
	private TransparentPanel lastAddedSubMenu;
	
	public MenuPage(String menuTitle) {
		setLayout(new BorderLayout(10, 10));
		setOpaque(false);
		
		bodyBetweenPageGabPlacer = new JPanel();
		add(bodyBetweenPageGabPlacer, BorderLayout.CENTER);
		bodyBetweenPageGabPlacer.setLayout(new BorderLayout(10, 10));
		
		TransparentPanel bodyForInternGabs = new TransparentPanel();
		bodyBetweenPageGabPlacer.add(bodyForInternGabs, BorderLayout.NORTH);
		bodyForInternGabs.setLayout(new BorderLayout(0, 0));
		
		TransparentPanel panel_placeMenuTitle = new TransparentPanel();
		bodyForInternGabs.add(panel_placeMenuTitle, BorderLayout.CENTER);
		panel_placeMenuTitle.setLayout(new BorderLayout(10, 10));
		
		lblMenutitle = new JLabel(frontTag + menuTitle + backTag);
		panel_placeMenuTitle.add(lblMenutitle, BorderLayout.NORTH);
		
		TransparentPanel panel_placeBodyToTop = new TransparentPanel();
		panel_placeMenuTitle.add(panel_placeBodyToTop, BorderLayout.CENTER);
		panel_placeBodyToTop.setLayout(new BorderLayout(0, 0));
		
		my_body = new TransparentPanel();
		panel_placeBodyToTop.add(my_body, BorderLayout.NORTH);
		my_body.setLayout(new BorderLayout(5, 5));
				
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		bodyForInternGabs.add(rigidArea, BorderLayout.NORTH);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		bodyForInternGabs.add(rigidArea_1, BorderLayout.SOUTH);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		bodyForInternGabs.add(rigidArea_2, BorderLayout.WEST);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		bodyForInternGabs.add(rigidArea_3, BorderLayout.EAST);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		add(verticalStrut_2, BorderLayout.EAST);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.SOUTH);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.NORTH);
		
		lastAddedSubMenu = new TransparentPanel();
		my_body.add(lastAddedSubMenu);
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			bodyBetweenPageGabPlacer.setBackground(ThemeList.currentTheme.menuBackColor);
			lblMenutitle.setForeground(ThemeList.currentTheme.menuFontColor);
			revalidate();
			repaint();
		} else {

		}
	}
	
	public void addSubmenu(Submenu submenu) {
		TransparentPanel submenupanel = new TransparentPanel();
		lastAddedSubMenu.add(submenupanel, BorderLayout.CENTER);
		submenupanel.setLayout(new BorderLayout(10, 10));
		
		int titleWidth = (int) lblMenutitle.getPreferredSize().getWidth();
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(titleWidth - 15, 3));
		if(ThemeList.currentTheme != null) {			
			separator.setForeground(ThemeList.currentTheme.menuButtonFont);
			separator.setBackground(ThemeList.currentTheme.menuButtonFont);
		}
		submenupanel.add(separator, BorderLayout.NORTH);
		
		TransparentPanel placesubmenu = new TransparentPanel();
		submenupanel.add(placesubmenu);
		placesubmenu.setLayout(new BorderLayout(10, 10));
		
		placesubmenu.add(submenu, BorderLayout.NORTH);
		lastAddedSubMenu = placesubmenu;
	}

}
