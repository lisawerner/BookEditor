package time;

import java.util.Date;

public class Timestamp {
	
	//Ideen:
	// Timestamp unbekannt!!! (Einfach, wenn kein Tag gesetzt ist?)
	// Ungefährer Abstand zu einem anderen Timestamp/Section
	// Konkretes Datum
	
	// Achtung: Was ist mit Geschichten/Welten, die eine andere Zeitrechnung haben?
	
	private boolean isSpecificTimestamp;
	private SpecificDate my_specificDate;
	
	public Timestamp(boolean specific, SpecificDate newSpecificDate) {
		isSpecificTimestamp = specific;
		if(isSpecificTimestamp) {			
			my_specificDate = newSpecificDate;
		} else {
			my_specificDate = null;
		}

	}
	
	public SpecificDate getSpecificDate() {
		if(isSpecificTimestamp) {
			return my_specificDate;
		}
		return null;
	}

	public boolean isSpecificDate() {
		return isSpecificTimestamp;
	}
	
	public String toString() {
		if(isSpecificTimestamp) {
			return my_specificDate.toString();
		}
		return "";
	}
	
	public boolean greaterThen(Timestamp otherTimestamp) {
		Date my_date = new Date();
		if(isSpecificTimestamp) {
			my_date = my_specificDate.getDate();
		} else {
			//TODO
		}
		Date other_date = new Date();
		if(otherTimestamp.isSpecificDate()) {
			other_date = otherTimestamp.getSpecificDate().getDate();
		} else {
			//TODO
		}
		return my_date.after(other_date);
	}
	

}
