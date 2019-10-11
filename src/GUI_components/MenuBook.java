package GUI_components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;

import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.JViewport;
import javax.swing.JScrollPane;

public class MenuBook extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private String frontTag = "<html><div style='text-align: center;'><font size=\"7\">";
	private String backTag = "</size></div></html>";
	
	private String frontTagSub = "<html><font size=\"5\">";
	private String backTagSub = "</size></html>";
	
	private JLabel lblNewLabel;
	private JPanel panel_listOfMainButtons;
	private JSeparator separator;
	private JPanel panel_tableOfContentHeader;
	private JLabel lblChapterlist;
	private JPanel panel_tableOfContent;
	private JScrollPane scrollPane;
	
	private ArrayList<MenuButton> mainButtonList;
	private ArrayList<MenuListButton> sectionButtonList;

	public MenuBook() {
		setLayout(new BorderLayout(20, 20));
		
		JPanel panel_positionTitle = new JPanel();
		panel_positionTitle.setOpaque(false);
		add(panel_positionTitle, BorderLayout.CENTER);
		panel_positionTitle.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel(frontTag + "Menu:" + backTag);
		panel_positionTitle.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_positionMainButtons = new JPanel();
		panel_positionTitle.add(panel_positionMainButtons, BorderLayout.CENTER);
		panel_positionMainButtons.setOpaque(false);
		panel_positionMainButtons.setLayout(new BorderLayout(20, 20));
		
		panel_listOfMainButtons = new JPanel();
		panel_listOfMainButtons.setOpaque(false);
		panel_listOfMainButtons.setLayout(new GridLayout(0, 1, 5, 5));
		panel_positionMainButtons.add(panel_listOfMainButtons, BorderLayout.NORTH);
		
		JPanel panel_positionSeperator = new JPanel();
		panel_positionSeperator.setOpaque(false);
		panel_positionMainButtons.add(panel_positionSeperator, BorderLayout.CENTER);
		panel_positionSeperator.setLayout(new BorderLayout(20, 20));
		
		separator = new JSeparator();
		panel_positionSeperator.add(separator, BorderLayout.NORTH);
		
		JPanel panel_positionTableOfContent = new JPanel();
		panel_positionTableOfContent.setOpaque(false);
		panel_positionSeperator.add(panel_positionTableOfContent, BorderLayout.CENTER);
		panel_positionTableOfContent.setLayout(new BorderLayout(10, 10));
		
		panel_tableOfContentHeader = new JPanel();
		panel_tableOfContentHeader.setOpaque(false);
		panel_positionTableOfContent.add(panel_tableOfContentHeader, BorderLayout.NORTH);
		panel_tableOfContentHeader.setLayout(new GridLayout(0, 1, 5, 5));
		
		lblChapterlist = new JLabel(frontTagSub + "Table of Content:" + backTagSub);
		panel_tableOfContentHeader.add(lblChapterlist);
		
		JPanel panel_positionBODYtableOfContent = new JPanel();
		panel_positionBODYtableOfContent.setOpaque(false);
		panel_positionTableOfContent.add(panel_positionBODYtableOfContent, BorderLayout.CENTER);
		panel_positionBODYtableOfContent.setLayout(new BorderLayout(10, 10));
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_positionBODYtableOfContent.add(scrollPane, BorderLayout.CENTER);
		
		panel_tableOfContent = new JPanel();
		panel_tableOfContent.setLayout(new GridLayout(0, 1, 5, 5));
		
		JViewport viewport = new JViewport();
		viewport.setOpaque(false);
		scrollPane.setViewportView(viewport);
		viewport.setView(panel_tableOfContent);
				
		Component gabLeft = Box.createVerticalStrut(20);
		add(gabLeft, BorderLayout.WEST);
		
		Component gabRight = Box.createVerticalStrut(20);
		add(gabRight, BorderLayout.EAST);
		
		Component gabBottom = Box.createHorizontalStrut(20);
		add(gabBottom, BorderLayout.SOUTH);
		
		Component gabTop = Box.createHorizontalStrut(20);
		add(gabTop, BorderLayout.NORTH);
		
		mainButtonList = new ArrayList<MenuButton>();
		sectionButtonList = new ArrayList<MenuListButton>();
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			//General
			setBackground(ThemeList.currentTheme.menuBackColor);
			//Title and other Text?
			lblNewLabel.setForeground(ThemeList.currentTheme.menuFontColor);
			lblChapterlist.setForeground(ThemeList.currentTheme.menuFontColor);
			//Buttons
			for(MenuButton btn : mainButtonList) {
				btn.changeTheme();
			}
			//Seperator
			separator.setForeground(ThemeList.currentTheme.menuButtonFont);
			separator.setBackground(ThemeList.currentTheme.menuButtonFont);
			//ScrollPane
			scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			panel_tableOfContent.setBackground(ThemeList.currentTheme.menuBackColor);
			// ...
			revalidate();
			repaint();
		} else {

		}
	}
	
	public JButton createMainButton(String text) {
		MenuButton newMainButton = new MenuButton(text);
		panel_listOfMainButtons.add(newMainButton);
		mainButtonList.add(newMainButton);
		return newMainButton;
	}
	
	public JButton createContentButton(String text) {
		MenuButton newMainButton = new MenuButton(text);
		panel_tableOfContentHeader.add(newMainButton);
		mainButtonList.add(newMainButton);
		return newMainButton;
		
	}
	
	public MenuListButton createSectionItem(String text) {
		MenuListButton newMainButton = new MenuListButton(text);
		panel_tableOfContent.add(newMainButton);
		sectionButtonList.add(newMainButton);
		return newMainButton;
		
	}

}
