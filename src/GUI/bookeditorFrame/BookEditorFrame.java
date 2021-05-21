package GUI.bookeditorFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_components.BookFooter;
import GUI_components.HeaderBook;
import GUI_components.Page;
import GUI_components.ThemeList;
import GUI_components.TransparentPanel;
import book.Book;
import global.Constant;

public class BookEditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static BookEditorFrame instance = null;
	
	private final JPanel contentPane;
	private final TransparentPanel contentBody;
	
	private HeaderBook lblBookTitle;
	private EditorMenu panel_menu;
	private final BookFooter panel_footer;

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
		
		lblBookTitle = new HeaderBook(Book.getInstance().getTitle());
		contentPane.add(lblBookTitle, BorderLayout.NORTH);
		
		panel_menu = new EditorMenu();
		contentPane.add(panel_menu, BorderLayout.WEST);
		
		contentBody = new TransparentPanel();
		contentPane.add(contentBody, BorderLayout.CENTER);
		contentBody.setLayout(new GridLayout(1, 0, 5, 5));
		switchBody(new HomePage());
		
		panel_footer = new BookFooter();
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
		reloadMenu();
		panel_footer.changeTheme();
		changeTheme();
		revalidate();
		repaint();
	}
	
	public void reloadMenu() {
		BorderLayout layout = (BorderLayout)contentPane.getLayout();
		if(layout.getLayoutComponent(BorderLayout.WEST) != null) {			
			contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
		}
		panel_menu = new EditorMenu();
		contentPane.add(panel_menu, BorderLayout.WEST);
		revalidate();
		repaint();
	}
	
	public void updateBookTitle() {
		BorderLayout layout = (BorderLayout)contentPane.getLayout();
		contentPane.remove(layout.getLayoutComponent(BorderLayout.NORTH));
		lblBookTitle = new HeaderBook(Book.getInstance().getTitle());
		contentPane.add(lblBookTitle, BorderLayout.NORTH);
		revalidate();
		repaint();
	}

}
