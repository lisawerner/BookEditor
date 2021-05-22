package GUI.launcherFrame;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.FrameFooter;
import global.Constant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartFrame extends JFrame {
	
	private static StartFrame instance = null;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;

	public static StartFrame getInstance() {
		if(instance == null) {
			instance = new StartFrame();
		}
		return instance;
	}
	
	private StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle(Constant.EDITOR_NAME);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setBounds(10, 10, width-20, height-55);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel centeredBody = new JPanel();
		contentPane.add(centeredBody, BorderLayout.CENTER);
		centeredBody.add(new StartPage());
		
		FrameFooter generalFooter = new FrameFooter();
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
