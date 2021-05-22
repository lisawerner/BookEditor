package GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormButton extends TransparentPanel{
	private static final long serialVersionUID = 1L;
	
	private final JButton btnButton;
	private final SimpleLabel lblWarning;
	
	public FormButton(String buttonText, ActionListener action){
		setLayout(new GridLayout(0, 1, 2, 2));
		
		lblWarning = new SimpleLabel(" ");
		lblWarning.setWarning(true);
		add(lblWarning);
		
		btnButton = new JButton(buttonText);
		add(btnButton);
		btnButton.addActionListener(action);
		btnButton.setEnabled(false);
	}

	public void activate() {
		lblWarning.setText(" ");
		btnButton.setEnabled(true);
	}

	public void deactivate(String newWarningText) {
		lblWarning.setText(newWarningText);
		btnButton.setEnabled(false);
	}

}
