package GUI.components;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ComplexeTextfieldSaveable extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Runnable saveFunction;
	
	private SimpleLabel lblTextfieldlabel;
	
	private SimpleTextfield textField;
	private String originalText;
	
	private JButton btnSave;

	private SimpleLabel lblSaveWarning;

	/**
	 * Creates a text field with following additions:
	 * - an own label for naming the text field
	 * - a button for saving
	 * - a label for warnings and hints if the input is empty
	 * - and a shortcut STRG+S for saving without the button
	 * 
	 * @param textfieldLabel
	 * @param text set text if the text field already has any input
	 * @param newSaveFunction
	 */
	public ComplexeTextfieldSaveable(String textfieldLabel, String text, Runnable newSaveFunction) {
		setLayout(new BorderLayout(5, 5));
		
		saveFunction = newSaveFunction;
		
		lblTextfieldlabel = new SimpleLabel(textfieldLabel);
		add(lblTextfieldlabel, BorderLayout.WEST);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(e -> callSave());
		btnSave.setEnabled(false);
		add(btnSave, BorderLayout.EAST);
		
		textField = new SimpleTextfield(text);
		add(textField, BorderLayout.CENTER);
		originalText = text;
		
		lblSaveWarning = new SimpleLabel(" ");		
		lblSaveWarning.setWarning(true);
		add(lblSaveWarning, BorderLayout.SOUTH);
		
		textField.addKeyListener(new KeyAdapter() {
	        @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {              		
                	callSave();
                }
            }

	    });
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				checkChanges();
			}
			public void removeUpdate(DocumentEvent e) {
				checkChanges();
			}
			public void insertUpdate(DocumentEvent e) {
				checkChanges();
			}
		});
	}
	
	protected boolean checkSavebility(){
		if(textField.getText().isEmpty()){
			lblSaveWarning.setText("Enter text before saving");
			return false;
		} else {                		
			lblSaveWarning.setText(" ");
			return true;
		}
	}	
	
	public String getText(){
		return textField.getText();
	}
	
	protected void checkChanges(){
		boolean hasChanges = !originalText.equals(textField.getText());
		lblTextfieldlabel.setWarning(hasChanges);
		textField.setWarning(hasChanges);
		btnSave.setEnabled(hasChanges && checkSavebility());
	}
	
	private void callSave(){
		if(checkSavebility()){                		
			saveFunction.run();
    		lblSaveWarning.setText(" ");
    		textField.setWarning(false);
    		btnSave.setEnabled(false);
    		lblTextfieldlabel.setWarning(false);
    		originalText = textField.getText();
    	}
	}

}
