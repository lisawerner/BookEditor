package time;

import java.util.Date;

import book.Book;
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
		Date my_date = new Date();
		if(my_specificDate != null) {
			my_date = my_specificDate.getDate();
		} else {
			my_date = my_relativeDate.generateSpecificDate().getDate();
		}
		Date other_date = new Date();
		if(otherTimestamp.isSpecificDate()) {
			other_date = otherTimestamp.getSpecificDate().getDate();
		} else {
			other_date = otherTimestamp.getSpecificDate().getDate();
		}
		return my_date.after(other_date);
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
		if(my_specificDate != null){
			return null;
		} else if(my_relativeDate != null){
			Section relatedSection = Book.getInstance().getTableOfContent().getSection(my_relativeDate.getRelatedSectionID());		
			return relatedSection;
		} else {
			return null;
		}
	}
	

}
