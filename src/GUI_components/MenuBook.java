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
	
	private static final String FRONT_TAG = "<html><div style='text-align: center;'><font size=\"7\">";
	private static final String BACK_TAG = "</size></div></html>";
	
	private static final String FRONT_TAG_SUB = "<html><font size=\"5\">";
	private static final String BACK_TAG_SUB = "</size></html>";
	
	private final JLabel lblNewLabel;
	private final JPanel panel_listOfMainButtons;
	private final JSeparator separator;
	private final JPanel panel_tableOfContentHeader;
	private final JLabel lblChapterList;
	private final JPanel panel_tableOfContent;
	private final JScrollPane scrollPane;
	
	private final ArrayList<MenuButton> mainButtonList;
	private final ArrayList<MenuListButton> sectionButtonList;

	public MenuBook() {
		setLayout(new BorderLayout(20, 20));
		
		JPanel panel_positionTitle = new JPanel();
		panel_positionTitle.setOpaque(false);
		add(panel_positionTitle, BorderLayout.CENTER);
		panel_positionTitle.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel(FRONT_TAG + "Menu:" + BACK_TAG);
		panel_positionTitle.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_positionMainButtons = new JPanel();
		panel_positionTitle.add(panel_positionMainButtons, BorderLayout.CENTER);
		panel_positionMainButtons.setOpaque(false);
		panel_positionMainButtons.setLayout(new BorderLayout(20, 20));
		
		panel_listOfMainButtons = new JPanel();
		panel_listOfMainButtons.setOpaque(false);
		panel_listOfMainButtons.setLayout(new GridLayout(0, 1, 5, 5));
		panel_positionMainButtons.add(panel_listOfMainButtons, BorderLayout.NORTH);
		
		JPanel panel_positionSeparator = new JPanel();
		panel_positionSeparator.setOpaque(false);
		panel_positionMainButtons.add(panel_positionSeparator, BorderLayout.CENTER);
		panel_positionSeparator.setLayout(new BorderLayout(20, 20));
		
		separator = new JSeparator();
		panel_positionSeparator.add(separator, BorderLayout.NORTH);
		
		JPanel panel_positionTableOfContent = new JPanel();
		panel_positionTableOfContent.setOpaque(false);
		panel_positionSeparator.add(panel_positionTableOfContent, BorderLayout.CENTER);
		panel_positionTableOfContent.setLayout(new BorderLayout(10, 10));
		
		panel_tableOfContentHeader = new JPanel();
		panel_tableOfContentHeader.setOpaque(false);
		panel_positionTableOfContent.add(panel_tableOfContentHeader, BorderLayout.NORTH);
		panel_tableOfContentHeader.setLayout(new GridLayout(0, 1, 5, 5));
		
		lblChapterList = new JLabel(FRONT_TAG_SUB + "Table of Content:" + BACK_TAG_SUB);
		panel_tableOfContentHeader.add(lblChapterList);
		
		JPanel panel_positionBodyTableOfContent = new JPanel();
		panel_positionBodyTableOfContent.setOpaque(false);
		panel_positionTableOfContent.add(panel_positionBodyTableOfContent, BorderLayout.CENTER);
		panel_positionBodyTableOfContent.setLayout(new BorderLayout(10, 10));
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_positionBodyTableOfContent.add(scrollPane, BorderLayout.CENTER);
		
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
		
		mainButtonList = new ArrayList<>();
		sectionButtonList = new ArrayList<>();
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			//General
			setBackground(ThemeList.currentTheme.menuBackColor);
			//Title and other Text?
			lblNewLabel.setForeground(ThemeList.currentTheme.menuFontColor);
			lblChapterList.setForeground(ThemeList.currentTheme.menuFontColor);
			//Buttons
			for(MenuButton btn : mainButtonList) {
				btn.changeTheme();
			}
			//Separator
			separator.setForeground(ThemeList.currentTheme.menuButtonFont);
			separator.setBackground(ThemeList.currentTheme.menuButtonFont);
			//ScrollPane
			scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			panel_tableOfContent.setBackground(ThemeList.currentTheme.menuBackColor);
			// ...
			revalidate();
			repaint();
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
