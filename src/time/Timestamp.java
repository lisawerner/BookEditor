package time;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import book.Section;
import global.ObjectID;
import global.SerializedObject;

public class Timestamp extends SerializedObject{
	
	// Achtung: Was ist mit Geschichten/Welten, die eine andere Zeitrechnung haben?
	
	private LocalDate my_date;
	private boolean hasConcreteYear;
	
	private ObjectID my_section;
	
	private RelativeDate my_relativeDate;
	
	public Timestamp(int specificDay, int specificMonth, int specificYear, boolean isAnnoDomini, boolean setConcreteYear) {
		super();
		
		if(!isAnnoDomini){
			specificYear = -specificYear;
		}
	    my_date = LocalDate.of(specificYear, specificMonth, specificDay);
	    
	    hasConcreteYear = setConcreteYear;
	}
	
	public Timestamp(RelativeDate newRelativeDate, ObjectID sectionID) {
		super();
		
		my_section = sectionID;
		
		my_relativeDate = newRelativeDate;
	}
	
	public boolean greaterThen(Timestamp otherTimestamp) {
		LocalDate ownDate = LocalDate.now();
		if(my_relativeDate == null) {
			ownDate = my_date;
		} else {
			ownDate = my_relativeDate.generateSpecificDate().getDate();
		}
		LocalDate other_date = otherTimestamp.getDate();

		return ownDate.isAfter(other_date);
	}

	public RelativeDate getUnspecificDate() {
		return my_relativeDate;
	}
	
	public ObjectID getSection(){
		return my_section;
	}

	public Section getRelationSection() {
		if(my_relativeDate != null){			
			return my_relativeDate.getRelationSection();
		}
		return null;
	}

	public ObjectID getRelationToTimestamp() {
		if(my_relativeDate == null){
			return null;
		} else {
			return my_relativeDate.getRelationID();
		}
	}
	
	public int getYear() {
		if(my_relativeDate != null){
			return my_relativeDate.generateSpecificDate().getYear();
		}
		return my_date.getYear();
	}

	public Month getMonth() {
		if(my_relativeDate != null){
			return my_relativeDate.generateSpecificDate().getMonth();
		}
		return my_date.getMonth();
	}

	public int getDay() {
		if(my_relativeDate != null){
			return my_relativeDate.generateSpecificDate().getDate().getDayOfMonth();
		}
		return my_date.getDayOfMonth();
	}
	
	public boolean isAnnoDomini() {
		if(my_relativeDate != null){
			return my_relativeDate.generateSpecificDate().getYear() >= 0;
		}
		return my_date.getYear() >= 0;
	}
	
	public boolean hasConcreteYear(){
		return hasConcreteYear;
	}
	
	public String toString() {
		if(my_relativeDate == null) {
			return my_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		} else {
			return my_relativeDate.toString();
		}
	}
	
	public String toCompleteString() {		
		if(my_relativeDate == null) {
			String result = toString();
			if(isAnnoDomini()) {
				result += " (" + getDayOfWeek() + ")";
			} else {
				result += " (before christ)";
			}
			return result;
		} else if(my_relativeDate != null) {
			Timestamp convertedSpecificDate = my_relativeDate.generateSpecificDate();
			if(convertedSpecificDate != null){				
				return convertedSpecificDate.toCompleteString();			
			}
			return null;
		} else {
			return null;
		}
	}

	public String getDayOfWeek() {
		if(my_relativeDate == null) {
			return my_date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
		} else {
			return my_relativeDate.generateSpecificDate().getDayOfWeek();
		}
	}

	public LocalDate getDate() {
		if(my_relativeDate != null){
			Timestamp rel = my_relativeDate.generateSpecificDate();
			if(rel != null ){
				return rel.getDate();
			} else {
				return null;
			}
		}
		return my_date;
	}

	public boolean isSpecificDate() {
		return my_relativeDate == null;
	}

//	public void fix() {
//		my_date = my_specificDate.getDate();
//		hasConcreteYear = my_specificDate.hasConcreteYear();
//	}

}
