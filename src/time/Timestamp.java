package time;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import global.ObjectID;
import global.SerializedObject;

public class Timestamp extends SerializedObject{
	
	// Achtung: Was ist mit Geschichten/Welten, die eine andere Zeitrechnung haben?
	
	//General attributes
	private ObjectID my_section;

	//Specific attributes
	private LocalDate my_date;
	private boolean hasConcreteYear;
	
	//Attribut to generate an unspecific timestamp which relates to another timestamp
	private RelativeDate my_relativeDate;
	
	public Timestamp(ObjectID sectionID, int specificDay, int specificMonth, int specificYear, boolean isAnnoDomini, boolean setConcreteYear) {
		super();
		
		my_section = sectionID;
		
		my_relativeDate = null;

		hasConcreteYear = setConcreteYear;
		if(!hasConcreteYear){
			specificYear = LocalDate.now().getYear();
		}
		if(!isAnnoDomini){
			specificYear = -specificYear;
		}
	    my_date = LocalDate.of(specificYear, specificMonth, specificDay);
	    
	}
	
	public Timestamp(ObjectID sectionID, ObjectID relatesToTimestamp, boolean newIsAfter, int distDays, int distWeeks, int distMonths, int distYears, int dayOfWeek, boolean setConcreteYear){
		my_section = sectionID;
		
		my_relativeDate = new RelativeDate(relatesToTimestamp, newIsAfter, distDays, distWeeks, distMonths, distYears, dayOfWeek);
		my_date = my_relativeDate.generateSpecificDate().getDate();
		
		hasConcreteYear = setConcreteYear;
	}
	
	public Timestamp(RelativeDate newRelativeDate, ObjectID sectionID) {
		super();
		
		my_section = sectionID;
		
		my_relativeDate = newRelativeDate;
		my_date = my_relativeDate.generateSpecificDate().getDate();
	}
	

	public void setRelativeDate(RelativeDate newRelativeDate) {		
		my_relativeDate = newRelativeDate;
		
		my_date = my_relativeDate.generateSpecificDate().getDate();
	}
	
	public boolean greaterThen(Timestamp otherTimestamp) {
		return my_date.isAfter(otherTimestamp.getDate());
	}

	public RelativeDate getUnspecificDate() {
		return my_relativeDate;
	}
	
	public ObjectID getSection(){
		return my_section;
	}
	
	public int getYear() {
		return my_date.getYear();
	}

	public Month getMonth() {
		return my_date.getMonth();
	}

	public int getDay() {
		return my_date.getDayOfMonth();
	}
	
	public boolean isAnnoDomini() {
		if(my_date == null){
			return true;
		}
		return my_date.getYear() >= 0;
	}
	
	public boolean hasConcreteYear(){
		return hasConcreteYear;
	}
	
	public String toString() {
		if(my_date == null){
			return "";
		}
		return my_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
	
	public String toCompleteString() {		
		String result = toString();
		if(isAnnoDomini()) {
			result += " (" + getDayOfWeek() + ")";
		} else {
			result += " (before christ)";
		}
		return result;
	}

	public String getDayOfWeek() {
		if(my_date == null){
			return "";
		}
		return my_date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
	}

	public LocalDate getDate() {
		return my_date;
	}

	public boolean isSpecificDate() {
		return my_relativeDate == null;
	}

	public void setSectionID(ObjectID sectionID) {
		my_section = sectionID;
	}

}
