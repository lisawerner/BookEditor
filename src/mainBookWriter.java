import java.awt.EventQueue;

import GUI_StartFrame.StartFrame;

public class mainBookWriter {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = StartFrame.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
