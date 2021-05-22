package GUI;

import global.UserSettings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HelpFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel lblRestartTutorialDone;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpFrame frame = new HelpFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HelpFrame() {
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 20, 20));
		
		JLabel lblHelp = new JLabel("Help");
		panel.add(lblHelp);
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnRestartTutorial = new JButton("Restart Tutorial");
		btnRestartTutorial.addActionListener(e -> restartTutorial());
		panel.add(btnRestartTutorial);
		
		lblRestartTutorialDone = new JLabel("  ");
		lblRestartTutorialDone.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblRestartTutorialDone);
		
		JLabel lblYouCanGet = new JLabel("You can get more help under: https://..."); //TODO: Add link here!
		lblYouCanGet.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblYouCanGet);
		
		//TODO: What else could be part of the help?
	}
	
	private void restartTutorial() {
		UserSettings.getInstance().restartTutorial();
		lblRestartTutorialDone.setText("Tutorial is restarted now");
	}

}
