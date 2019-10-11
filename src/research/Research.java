package research;

import global.ObjectID;

public class Research {
	// And other Notes
	//TODO: Notizfeld für WhatEver man muss z.B. Notizen für Recherchen einbauen. Das sind ja keine direkten Notizen für den Text selbst oder so.
	// Als Tag aufbauen. Also einzeln anlegen, wie Place/Person und an Section antaggen, weil manche Research werden vlt. mehrfach benötigt?
	
	private ObjectID my_uID;
	
	public Research(String newNoteText) {
		my_uID = new ObjectID(this.getClass().getName());
		
	}
	
	public ObjectID getID() {
		return my_uID;
	}
}
