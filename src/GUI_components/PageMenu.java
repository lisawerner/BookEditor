package GUI_components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JViewport;

public class PageMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private String frontTag = "<html><div style='text-align: left;'><font size=\"5\">     ";
	private String backTag = "</size></div></html>";
	
	private JPanel backgroundColorBody;
	private JLabel lblMenutitle;
	private TransparentPanel my_body;
	private JScrollPane submenu;
	private JPanel submenuBody;

	public PageMenu(String menuTitle) {
		setLayout(new BorderLayout(10, 10));
		setOpaque(false);
		
		backgroundColorBody = new JPanel();
		add(backgroundColorBody, BorderLayout.CENTER);
		backgroundColorBody.setLayout(new BorderLayout(10, 10));
		
		TransparentPanel position_MenuTitle = new TransparentPanel();
		backgroundColorBody.add(position_MenuTitle, BorderLayout.CENTER);
		position_MenuTitle.setLayout(new BorderLayout(10, 10));
		
		lblMenutitle = new JLabel(frontTag + menuTitle + backTag);
		position_MenuTitle.add(lblMenutitle, BorderLayout.NORTH);
		
		TransparentPanel position_mainBody = new TransparentPanel();
		position_MenuTitle.add(position_mainBody, BorderLayout.CENTER);
		position_mainBody.setLayout(new BorderLayout(20, 20));
		
		my_body = new TransparentPanel();
		position_mainBody.add(my_body, BorderLayout.NORTH);
		my_body.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel position_submenu = new TransparentPanel();
		position_mainBody.add(position_submenu, BorderLayout.CENTER);
		position_submenu.setLayout(new BorderLayout(20, 20));
		
		
		//TODO: Die ScrollBar wird nicht aktiviert.... Prüfen warum das so ist o.O
		submenu = new JScrollPane();
		position_submenu.add(submenu, BorderLayout.NORTH);
		submenu.setOpaque(false);
		submenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		submenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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
			lblMenutitle.setForeground(ThemeList.currentTheme.menuFontColor);
			//Submenu
			submenu.setViewportBorder(BorderFactory.createEmptyBorder());
			submenu.setBorder(BorderFactory.createEmptyBorder());
			submenuBody.setBackground(ThemeList.currentTheme.menuBackColor);
			//
			revalidate();
			repaint();
		} else {

		}
	}
	
	public MenuButton addButtonToTopMenu(String text) {
		MenuButton newMainButton = new MenuButton(text);
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
	
	public MenuListButton addLinkedListButton(String text) {
		MenuListButton newListItem = new MenuListButton(text);
		submenuBody.add(newListItem);
		return newListItem;
	}
	
	public void addText(String text) {
		submenuBody.add(new MenuText("<html>" + text + "</html>"));
	}

}
