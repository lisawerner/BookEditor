package GUI_StartFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI_components.BookFooter;

public class StartFrame extends JFrame {
	
	private static StartFrame instance = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JPanel centeredBody;

	public static StartFrame getInstance() {
		if(instance == null) {
			instance = new StartFrame();
		}
		return instance;
	}
	
	private StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Book Writer");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setBounds(10, 10, width-20, height-55);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		centeredBody = new JPanel();
		contentPane.add(centeredBody, BorderLayout.CENTER);
		centeredBody.add(new StartPage());
		
		BookFooter generalFooter = new BookFooter();
		contentPane.add(generalFooter, BorderLayout.SOUTH);
	}
	
	public void switchBody(JPanel newBody){
		contentPane.removeAll();
		contentPane.add(newBody);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void openBookEditor() {
		BookEditorFrame frame = BookEditorFrame.getInstance();
		frame.setVisible(true);
		this.setVisible(false);
	}

}
