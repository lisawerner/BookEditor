package GUI.bookeditorFrame;

import GUI.components.*;
import GUI.frame.menu.*;
import GUI.pages.content.Page_sortContent;
import GUI.pages.content.viewChapter.Page_viewChapter;
import GUI.pages.notes.createNote.Page_createNote;
import GUI.pages.notes.viewNote.Page_singleNote;
import GUI.pages.notesPage.Page_viewNotes;
import GUI.pages.settings.BookSettingsPage;
import GUI.pages.society.Page_ViewSociety;
import GUI.pages.society.personEditorPage.Page_PersonEditor;
import GUI.pages.society.raceCreate.Page_createRace;
import GUI.pages.society.raceView.Page_EditRace;
import GUI.pages.timeline.Page_ViewTimeline;
import GUI.pages.world.createPlace.Page_createPlace;
import GUI.pages.world.viewPlace.Page_viewPlace;
import GUI.printPage.PrintPage;
import GUI.sectionPage.SectionPage;
import GUI.theme.ThemeList;
import GUI.worldPage.ViewWorldMapPage;
import book.Book;
import book.Chapter;
import book.Section;
import global.Constant;
import notes.GeneralNote;
import person.Person;
import person.Race;
import time.Timestamp;
import world.Place;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookEditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static BookEditorFrame instance = null;
	
	private final JPanel contentPane;
	private final TransparentPanel contentBody;
	
	private FrameHeader lblBookTitle;
	private FrameMenu panel_mainMenu;
	private final FrameFooter panel_footer;

	public BookEditorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle(Constant.EDITOR_NAME);
		
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
		//TODO: Problem: Changes in the second frame will not be saved... Maybe a reload is required? o.O
		closeSecondFrame.addActionListener(e -> switchBody(mainBody));
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
	
	public void openCreateNotePage(){
		switchBody(new Page_createNote());
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
	
	public void openSectionPage(Section section, Chapter chapter){
		switchBody(new SectionPage(section, chapter));
		panel_mainMenu.changeSubmenuTo(new ContentMenu());
	}

	public void openTimelinePage(Timestamp startDate) {
		switchBody(new Page_ViewTimeline(startDate));
		panel_mainMenu.changeSubmenuTo(new TimelineMenu());
	}

	public void openWorldPage() {
		switchBody(new ViewWorldMapPage());
		panel_mainMenu.changeSubmenuTo(new PlaceMenu());
	}
	
	public void openPlacePage(Place place, boolean isSecondFrame) {
		switchBody(new Page_viewPlace(place, isSecondFrame));
		panel_mainMenu.changeSubmenuTo(new PlaceMenu());
	}
	
	public void openCreatePlacePage() {
		switchBody(new Page_createPlace());
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
	
	public void openRacePage(Race race){
		switchBody(new Page_EditRace(race));
		panel_mainMenu.changeSubmenuTo(new SocietyMenu());
	}
	
	public void openCreateRacePage(){
		switchBody(new Page_createRace());
		panel_mainMenu.changeSubmenuTo(new SocietyMenu());
	}

}
