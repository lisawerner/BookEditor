package person;

import java.util.ArrayList;

import book.Book;
import global.ObjectID;
import global.SerializedObject;

public class Race extends SerializedObject {
	
	private String my_name;
	
	private ArrayList<ObjectID> my_representative;
	
	private String my_notes;
	
	public Race(String newRaceName, String newNotes) {
		super();
		
		my_name = newRaceName;
		my_notes = newNotes;
		
		my_representative = new ArrayList<ObjectID>();		
	}

	public String getName() {
		return my_name;
	}

	public String getNotes() {
		return my_notes;
	}

	public void addRepresentative(ObjectID personID) {
		my_representative.add(personID);
	}

	public void removeRepresentative(ObjectID personID) {
		my_representative.remove(personID);
	}
	
	public ArrayList<ObjectID> getRepresentative(){
		return my_representative;
	}

	public void edit(String newRaceName, String newNotes) {
		my_name = newRaceName;
		my_notes = newNotes;
		
		Book.getInstance().save();
	}

}
