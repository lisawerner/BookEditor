package time;

import java.time.LocalDate;

import book.Section;
import global.ObjectID;
import global.SerializedObject;

public class Timestamp extends SerializedObject{
	
	// Achtung: Was ist mit Geschichten/Welten, die eine andere Zeitrechnung haben?
	
	private ObjectID my_section;
	
	private SpecificDate my_specificDate;
	private RelativeDate my_relativeDate;
	
	public Timestamp(SpecificDate newSpecificDate, RelativeDate newRelativeDate, ObjectID sectionID) {
		super();
		
		my_section = sectionID;
		
		my_specificDate = newSpecificDate;
		my_relativeDate = newRelativeDate;
	}
	
	public SpecificDate getSpecificDate() {
		if(my_specificDate != null) {
			return my_specificDate;
		}
		if(my_relativeDate != null) {
			return my_relativeDate.generateSpecificDate();
		}
		return null;
	}

	public boolean isSpecificDate() {
		return my_specificDate != null;
	}
	
	public String toString() {
		if(my_specificDate != null) {
			return my_specificDate.toString();
		} else {
			return my_relativeDate.toString();
		}
	}
	
	public String toCompleteString() {
		if(my_specificDate != null) {
			return my_specificDate.toCompleteString();
		} else if(my_relativeDate != null) {
			SpecificDate convertedSpecificDate = my_relativeDate.generateSpecificDate();
			if(convertedSpecificDate != null){				
				return convertedSpecificDate.toCompleteString();			
			}
			return null;
		} else {
			return null;
		}
	}
	
	public boolean greaterThen(Timestamp otherTimestamp) {
		LocalDate my_date = LocalDate.now();
		if(my_specificDate != null) {
			my_date = my_specificDate.getDate();
		} else {
			my_date = my_relativeDate.generateSpecificDate().getDate();
		}
		LocalDate other_date = LocalDate.now();
		if(otherTimestamp.isSpecificDate()) {
			other_date = otherTimestamp.getSpecificDate().getDate();
		} else {
			other_date = otherTimestamp.getSpecificDate().getDate();
		}
		return my_date.isAfter(other_date);
	}
	
	public String getDayOfWeek() {
		if(my_specificDate != null) {
			return my_specificDate.getDayOfWeek();
		} else {
			return my_relativeDate.generateSpecificDate().getDayOfWeek();
		}
	}

	public RelativeDate getUnspecificDate() {
		return my_relativeDate;
	}
	
	public ObjectID getSection(){
		return my_section;
	}

	public Section getRelationSection() {
		return my_relativeDate.getRelationSection();
	}

	public ObjectID getRelationToTimestamp() {
		if(my_relativeDate == null){
			return null;
		} else {
			return my_relativeDate.getRelationID();
		}
	}

}
