package GUI.personPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import GUI_components.SimpleRadiobutton;
import GUI_components.SimpleTextfield;
import GUI_components.TransparentPanel;
import person.Person;

public class AgeCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Person my_person = null;
	
	private SimpleTextfield txt_age;
	private SimpleRadiobutton rdbtnAgeBookstart;
	private SimpleRadiobutton rdbtnAgeFirstAppeareance;
	
	public AgeCard(Person person) {
		my_person = person;
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblAge = new JLabel("Age:");
		add(lblAge, BorderLayout.NORTH);
		
		txt_age = new SimpleTextfield();
		if(my_person != null) {txt_age.setText(my_person.getAge());}
		add(txt_age, BorderLayout.CENTER);
		
		TransparentPanel panel_ageValues = new TransparentPanel();
		add(panel_ageValues, BorderLayout.SOUTH);
		panel_ageValues.setLayout(new GridLayout(0, 1, 0, 0));
		
		ButtonGroup btngrAge = new ButtonGroup();
		
		rdbtnAgeBookstart = new SimpleRadiobutton("...at beginning of the book");
		if(my_person != null) {rdbtnAgeBookstart.setSelected(my_person.getAgeBookStart());}
		panel_ageValues.add(rdbtnAgeBookstart);
		btngrAge.add(rdbtnAgeBookstart);
		
		rdbtnAgeFirstAppeareance = new SimpleRadiobutton("... when person appear first time in book");
		if(my_person != null) {rdbtnAgeFirstAppeareance.setSelected(my_person.getAgeFirstAppear());}
		panel_ageValues.add(rdbtnAgeFirstAppeareance);
		btngrAge.add(rdbtnAgeFirstAppeareance);

	}

	public String getAge() {
		return txt_age.getText();
	}
	
	public boolean getAgeBookstart() {
		return rdbtnAgeBookstart.isSelected();
	}
	
	public boolean getAgeCharacterAppear() {
		return rdbtnAgeFirstAppeareance.isSelected();
	}

}
