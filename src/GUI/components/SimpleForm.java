package GUI.components;

import java.util.ArrayList;

public class SimpleForm {
	
	private final ArrayList<FormInput> my_inputs;
	
	private final FormButton btnSave;
	private final Runnable saveFunction;
	
	public SimpleForm(Runnable newSaveFunction, FormButton newSaveButton){
		my_inputs = new ArrayList<>();
		saveFunction = newSaveFunction;

		btnSave = newSaveButton;
	}
	
	public void addInput(FormInput newInput){
		my_inputs.add(newInput);
		newInput.setParentForm(this);
	}
	
	protected void callAction(){
		if(checkAllSavebilities()){                		
			saveFunction.run();
    		for(FormInput currentInput : my_inputs){
    			currentInput.actionWasCalled();
    		}
    		btnSave.deactivate("Successfully saved");
    	}
	}
	
	protected boolean checkAllSavebilities(){
		for(FormInput currentInput : my_inputs){			
			if(!currentInput.checkSavebility()){
				btnSave.deactivate("Enter text before saving");
				return false;
			}
		}
		btnSave.activate();
		return true;
	}
	
	
	

}
