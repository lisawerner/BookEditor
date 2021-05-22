package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PageMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String FRONT_TAG = "<html><div style='text-align: left;'><font size=\"5\">     ";
	private static final String BACK_TAG = "</size></div></html>";
	
	private final JPanel backgroundColorBody;
	private final JLabel lblMenuTitle;
	private final TransparentPanel my_body;
	private final JScrollPane submenu;
	private final JPanel submenuBody;

	public PageMenu(String menuTitle) {
		setLayout(new BorderLayout(10, 10));
		setOpaque(false);
		
		backgroundColorBody = new JPanel();
		add(backgroundColorBody, BorderLayout.CENTER);
		backgroundColorBody.setLayout(new BorderLayout(10, 10));
		
		TransparentPanel position_MenuTitle = new TransparentPanel();
		backgroundColorBody.add(position_MenuTitle, BorderLayout.CENTER);
		position_MenuTitle.setLayout(new BorderLayout(10, 10));
		
		lblMenuTitle = new JLabel(FRONT_TAG + menuTitle + BACK_TAG);
		position_MenuTitle.add(lblMenuTitle, BorderLayout.NORTH);
		
		TransparentPanel position_mainBody = new TransparentPanel();
		position_MenuTitle.add(position_mainBody, BorderLayout.CENTER);
		position_mainBody.setLayout(new BorderLayout(20, 20));
		
		my_body = new TransparentPanel();
		position_mainBody.add(my_body, BorderLayout.NORTH);
		my_body.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel position_submenu = new TransparentPanel();
		position_mainBody.add(position_submenu, BorderLayout.CENTER);
		position_submenu.setLayout(new BorderLayout(0, 0));
		
		
		//TODO: The scroll bar is never activated... Why? o.O
		submenu = new JScrollPane();
		position_submenu.add(submenu);
		submenu.setOpaque(false);
		submenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
				submenuBody = new JPanel();
				submenuBody.setLayout(new GridLayout(0, 1, 5, 5));
				
				JViewport viewport = new JViewport();
				viewport.setOpaque(false);
				submenu.setViewportView(viewport);
				viewport.setView(submenuBody);
				
		Component rigidArea = Box.createRigidArea(new Dimension(10, 10));
		backgroundColorBody.add(rigidArea, BorderLayout.NORTH);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(10, 10));
		backgroundColorBody.add(rigidArea_2, BorderLayout.WEST);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(10, 10));
		backgroundColorBody.add(rigidArea_3, BorderLayout.EAST);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(10, 10));
		backgroundColorBody.add(rigidArea_1, BorderLayout.SOUTH);
		
		Component gabBetweenMenuAndRightPageEnd = Box.createRigidArea(new Dimension(10, 10));
		add(gabBetweenMenuAndRightPageEnd, BorderLayout.EAST);
		
				
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			backgroundColorBody.setBackground(ThemeList.currentTheme.menuBackColor);
			lblMenuTitle.setForeground(ThemeList.currentTheme.menuFontColor);
			//Submenu
			submenu.setViewportBorder(BorderFactory.createEmptyBorder());
			submenu.setBorder(BorderFactory.createEmptyBorder());
			submenuBody.setBackground(ThemeList.currentTheme.menuBackColor);
			//
			revalidate();
			repaint();
		}
	}
	
	public MenuButton addButtonToTopMenu(String text, ActionListener l) {
		MenuButton newMainButton = new MenuButton(text, l);
		my_body.add(newMainButton);
		revalidate();
		repaint();
		return newMainButton;
	}
	
	public void addBetweenTitle(String newTitle) {
		JSeparator separator = new JSeparator();
		if(ThemeList.currentTheme != null) {			
			separator.setForeground(ThemeList.currentTheme.menuButtonFont);
			separator.setBackground(ThemeList.currentTheme.menuButtonFont);
		}
		submenuBody.add(separator);
		
		submenuBody.add(new SimpleLabel(newTitle));
	}
	
	public void addLinkedListButton(MenuListButton listButton) {
		submenuBody.add(listButton);
	}
	
	public void addText(String text) {
		submenuBody.add(new MenuText("<html>" + text + "</html>"));
	}
	
	protected void addComplexMenuItem(TransparentPanel complexMenuItem) {
		submenuBody.add(complexMenuItem);
	}

}
