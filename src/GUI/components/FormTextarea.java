package GUI.components;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormTextarea extends FormInput {
	private static final long serialVersionUID = 1L;
	
	private SimpleLabel lblNamelabel;
	
	private SimpleTextarea textArea;
	private String originalText;

	public FormTextarea(String name, String text) {
		setLayout(new BorderLayout(2, 2));
		
		lblNamelabel = new SimpleLabel(name);
		add(lblNamelabel, BorderLayout.NORTH);
		
		textArea = new SimpleTextarea(text);
		add(textArea, BorderLayout.CENTER);

		originalText = text;
	
		textArea.addKeyListener(new KeyAdapter() {
	        @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            		parentForm.callAction();
                }
            }

	    });
		
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.checkAllSavebilities();
			}
			public void removeUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.checkAllSavebilities();
			}
			public void insertUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.checkAllSavebilities();
			}
		});
	}	
	
	public String getText(){
		return textArea.getText();
	}
	
	protected void checkChanges(){
		boolean hasChanges = !originalText.equals(textArea.getText());
		lblNamelabel.setWarning(hasChanges);
		textArea.setWarning(hasChanges);
	}
	
	@Override
	protected void actionWasCalled(){
		textArea.setWarning(false);
		lblNamelabel.setWarning(false);
		originalText = textArea.getText();
	}
	

}
