package time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpecificDate {
	
	private int my_specificDay;
	private int my_specificMonth;
	private int my_specificYear;
	private boolean isAnnoDomini;
	
	public SpecificDate(int newSpecificDay, int newSpecificMonth, int newSpecificYear, boolean newCalender) {
		my_specificDay = newSpecificDay;
		my_specificMonth = newSpecificMonth;
		my_specificYear = newSpecificYear;
		isAnnoDomini = newCalender;
	}

	public boolean isYearSet() {
		return my_specificYear >= 0;
	}

	public int getYear() {
		return my_specificYear;
	}

	public int getMonth() {
		return my_specificMonth;
	}

	public int getDay() {
		return my_specificDay;
	}
	
	public boolean isAnnoDomini() {
		return isAnnoDomini;
	}
	
	public String toString() {
		String result = "";
		if(my_specificDay > 0) {
			int correctMonth = my_specificMonth + 1;
			result += my_specificDay + "." + correctMonth;
		}
		if(isYearSet()) {
			if(!result.equals("")) {
				result += ".";
			}
			result += my_specificYear;
		}
		return result;
	}
	
	public String toCompleteString() {
		String result = toString();
		if(isAnnoDomini) {
			result += " (" + getDayOfWeek() + ")";
		} else {
			result += " (before christ)";
		}
		return result;
	}

	public Date getDate() {
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, my_specificDay);
	    cal.set(Calendar.MONTH, my_specificMonth);
	    cal.set(Calendar.YEAR, my_specificYear);
	    Date my_date = cal.getTime();
		return my_date;
	}

	public String getDayOfWeek() {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		return simpleDateformat.format(getDate());
	}

}
