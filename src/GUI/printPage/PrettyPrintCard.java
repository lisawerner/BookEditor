package GUI.printPage;

import java.awt.GridLayout;

import javax.swing.JButton;

import GUI_components.TransparentPanel;

public class PrettyPrintCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public PrettyPrintCard() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		JButton btnPrintPDF = new JButton("Print Book as .pdf");
		btnPrintPDF.setEnabled(false);
		add(btnPrintPDF);
//		btnPrintPDF.addActionListener(e -> print());
	}

}
