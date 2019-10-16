package time;

import java.util.Date;

public class Timestamp {
	
	//Ideen:
	// Timestamp unbekannt!!! (Einfach, wenn kein Tag gesetzt ist?)
	// Ungefährer Abstand zu einem anderen Timestamp/Section
	// Konkretes Datum
	
	// Achtung: Was ist mit Geschichten/Welten, die eine andere Zeitrechnung haben?
	
	private SpecificDate my_specificDate;
	private RelativeDate my_relativeDate;
	
	public Timestamp(SpecificDate newSpecificDate, RelativeDate newRelativeDate) {
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
		} else {
			return my_relativeDate.generateSpecificDate().toCompleteString();
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
	

}
