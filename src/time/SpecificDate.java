package time;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class SpecificDate {
	
	private LocalDate my_date;
	private boolean hasConcreteYear;
	
	public SpecificDate(int specificDay, int specificMonth, int specificYear, boolean isAnnoDomini, boolean setConcreteYear) {
		if(!isAnnoDomini){
			specificYear = -specificYear;
		}
	    my_date = LocalDate.of(specificYear, specificMonth, specificDay);
	    
	    hasConcreteYear = setConcreteYear;
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
		return my_date.getYear() >= 0;
	}
	
	public boolean hasConcreteYear(){
		return hasConcreteYear;
	}
	
	public String toString() {
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
		return my_date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
	}

	public LocalDate getDate() {
		return my_date;
	}

}
