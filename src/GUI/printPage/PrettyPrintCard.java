package GUI.printPage;

import GUI_components.TransparentPanel;

import javax.swing.*;
import java.awt.*;

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
