package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import global.UserSettings;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;

public class HelpFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private final JLabel lblRestarttutorialdone;

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
		contentPane = new JPanel();
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
		
		lblRestarttutorialdone = new JLabel("  ");
		lblRestarttutorialdone.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblRestarttutorialdone);
		
		JLabel lblYouCanGet = new JLabel("You can get more help under: https://..."); //TODO: Link hier einfügen!
		lblYouCanGet.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblYouCanGet);
		
		//TODO: Was soll noch auf die Hilfe?
	}
	
	private void restartTutorial() {
		UserSettings.getInstance().restartTutorial();
		lblRestarttutorialdone.setText("Tutorial is restarted now");
	}

}
