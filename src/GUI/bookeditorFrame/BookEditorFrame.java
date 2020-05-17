package GUI.bookeditorFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.pages.chapter.Page_viewChapter;
import GUI.pages.content.Page_sortContent;
import GUI.pages.notesPage.Page_singleNote;
import GUI.pages.notesPage.Page_viewNotes;
import GUI.pages.society.Page_ViewSociety;
import GUI.pages.society.personEditorPage.Page_PersonEditor;
import GUI.printPage.PrintPage;
import GUI.settingsPage.BookSettingsPage;
import GUI.timelinePages.Page_ViewTimeline;
import GUI.worldPage.PlaceEditor;
import GUI.worldPage.ViewWorldmapPage;
import book.Book;
import book.Chapter;
import global.Constant;
import notes.GeneralNote;
import person.Person;
import world.Place;
import GUI.components.FrameFooter;
import GUI.components.FrameHeader;
import GUI.components.FrameMenu;
import GUI.components.Page;
import GUI.components.ThemeList;
import GUI.components.TransparentPanel;
import GUI.frame.menu.ContentMenu;
import GUI.frame.menu.NotesMenu;
import GUI.frame.menu.PlaceMenu;
import GUI.frame.menu.SocietyMenu;
import GUI.frame.menu.TimelineMenu;

public class BookEditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static BookEditorFrame instance = null;
	
	private JPanel contentPane;
	private TransparentPanel contentBody;
	
	private FrameHeader lblBookTitle;
	private FrameMenu panel_mainMenu;
	private FrameFooter panel_footer;

	public BookEditorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle(Constant.editorname);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setBounds(10, 10, width-20, height-55);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);
		
		ThemeList.setCurrentTheme(Book.getInstance().getTheme());
		changeTheme();
		
		lblBookTitle = new FrameHeader(Book.getInstance().getTitle());
		contentPane.add(lblBookTitle, BorderLayout.NORTH);
				
		panel_mainMenu = new FrameMenu();
		contentPane.add(panel_mainMenu, BorderLayout.WEST);
		
		contentBody = new TransparentPanel();
		contentPane.add(contentBody, BorderLayout.CENTER);
		contentBody.setLayout(new GridLayout(1, 0, 5, 5));
		switchBody(new HomePage());
		
		panel_footer = new FrameFooter();
		contentPane.add(panel_footer, BorderLayout.SOUTH);
	}
	
	public static BookEditorFrame getInstance() {
		if(instance == null) {
			instance = new BookEditorFrame();
		}
		return instance;
	}
		
	public void switchBody(Page newBody){
		contentBody.removeAll();
		contentBody.setLayout(new GridLayout(1, 0, 5, 5));
		
		contentBody.add(newBody);

		revalidate();
		repaint();
	}
	
	public void switchBody(Page mainBody, Page secondBody) {
		contentBody.removeAll();
		contentBody.setLayout(new GridLayout(1, 0, 5, 5));
		
		contentBody.add(mainBody);
		
		TransparentPanel secondFrame = new TransparentPanel();
		secondFrame.setLayout(new BorderLayout(5, 5));
		secondFrame.add(secondBody, BorderLayout.CENTER);
		JButton closeSecondFrame = new JButton("Close Second Frame");
		closeSecondFrame.addActionListener(e -> switchBody(mainBody)); //TODO: Problem: Die Änderungen im secondFrame werden dabei nicht übernommen... Da muss irgendwie ein reload stattfinden o.O
		secondFrame.add(closeSecondFrame, BorderLayout.NORTH);
		contentBody.add(secondFrame);
		
		revalidate();
		repaint();
		
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				contentPane.setBackground(ThemeList.currentTheme.darkBackgroundColor);
			} else {				
				contentPane.setBackground(ThemeList.currentTheme.lightBackgroundColor);
			}
		}
	}
	
	public void repaintFrame() {
		updateBookTitle();
		updateBookMenu();
		panel_footer.changeTheme();
		changeTheme();
		revalidate();
		repaint();
	}
		
	public void updateBookTitle() {
		BorderLayout layout = (BorderLayout)contentPane.getLayout();
		contentPane.remove(layout.getLayoutComponent(BorderLayout.NORTH));
		lblBookTitle = new FrameHeader(Book.getInstance().getTitle());
		contentPane.add(lblBookTitle, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
	
	private void updateBookMenu(){
		panel_mainMenu.removeAll();
		panel_mainMenu = new FrameMenu();
		contentPane.add(panel_mainMenu, BorderLayout.WEST);
		panel_mainMenu.revalidate();
		panel_mainMenu.repaint();
	}
	
	public void openPrintPage(){
		switchBody(new PrintPage());
		panel_mainMenu.removeSubmenu();
	}

	public void openSettingsPage() {
		switchBody(new BookSettingsPage());
		panel_mainMenu.removeSubmenu();
	}

	public void openNotesListPage() {
		switchBody(new Page_viewNotes());
		panel_mainMenu.changeSubmenuTo(new NotesMenu());
	}
	
	public void openNotePage(GeneralNote note){
		switchBody(new Page_singleNote(note));
		panel_mainMenu.changeSubmenuTo(new NotesMenu());
	}

	public void openStartPage() {
		switchBody(new HomePage());
		panel_mainMenu.removeSubmenu();
	}

	public void openTableOfContentPage() {
		switchBody(new Page_sortContent());
		panel_mainMenu.changeSubmenuTo(new ContentMenu());
	}
	
	public void openChapterPage(Chapter chapter){
		switchBody(new Page_viewChapter(chapter));
		panel_mainMenu.changeSubmenuTo(new ContentMenu());
	}

	public void openTimelinePage() {
		switchBody(new Page_ViewTimeline());
		panel_mainMenu.changeSubmenuTo(new TimelineMenu());
	}

	public void openWorldPage() {
		switchBody(new ViewWorldmapPage());
		panel_mainMenu.changeSubmenuTo(new PlaceMenu());
	}
	
	public void openPlacePage(Place place, boolean isSecondFrame) {
		switchBody(new PlaceEditor(place, isSecondFrame));
		panel_mainMenu.changeSubmenuTo(new PlaceMenu());
	}

	public void openSocietyPage() {
		switchBody(new Page_ViewSociety());
		panel_mainMenu.changeSubmenuTo(new SocietyMenu());
	}
	
	public void openPersonPage(Person person, boolean isSecondFrame){
		switchBody(new Page_PersonEditor(person, isSecondFrame));
		panel_mainMenu.changeSubmenuTo(new SocietyMenu());
	}

}
