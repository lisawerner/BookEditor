package notes;

import global.SerializedObject;

public class PersonalTag extends SerializedObject {
	
	private final String my_name;
	
	public PersonalTag(String tagName) {
		super();
		
		my_name = tagName;
	}
	
	public String getTagName() {
		return my_name;
	}

}
