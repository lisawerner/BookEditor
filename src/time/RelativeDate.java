package time;

import java.util.Calendar;
import java.util.Date;

import book.Book;
import book.Section;
import global.ObjectID;

public class RelativeDate {
	
	private ObjectID relatedToSection;
	private boolean isAfter;
	private int my_daysDistance;
	private int my_weeksDistance;
	private int my_monthsDistance;
	private int my_yearsDistance;
	
	public RelativeDate(ObjectID selectedSection, boolean newIsAfter, int distDays, int distWeeks, int distMonths, int distYears) {
		relatedToSection = selectedSection;
		isAfter = newIsAfter;
		my_daysDistance = distDays;
		my_weeksDistance = distWeeks;
		my_monthsDistance = distMonths;
		my_yearsDistance = distYears;
	}
	
	public String toString() {	
		Section relatedSection = Book.getInstance().getSectionList().getSection(relatedToSection);
		if(!relatedSection.hasTimestamp()){
			return "Related to Section: " + relatedSection.getName() +" (which has no Timestamp set)";
		}
		return generateSpecificDate().toString();
	}

	public boolean isAfter() {
		return isAfter;
	}

	public int getDistDays() {
		return my_daysDistance;
	}

	public int getDistWeeks() {
		return my_weeksDistance;
	}

	public int getDistMonths() {
		return my_monthsDistance;
	}

	public int getDistYears() {
		return my_yearsDistance;
	}
	
	public ObjectID getRelatedSectionID() {
		return relatedToSection;
	}

	public SpecificDate generateSpecificDate() {
		Section relatedSection = Book.getInstance().getSectionList().getSection(relatedToSection);
		if(!relatedSection.hasTimestamp()){
			return null;
		}
		SpecificDate relatedDate = relatedSection.getTimestamp().getSpecificDate();
		int myYear = relatedDate.getYear();
		int myMonth = relatedDate.getMonth();
		int myDay = relatedDate.getDay();
		int addDays = my_daysDistance + (7 * my_weeksDistance);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, myDay);
		cal.set(Calendar.MONTH, myMonth);
		
		boolean isAnnoDomini = relatedDate.isAnnoDomini();
		
		if(isAfter) {
			if(relatedDate.isAnnoDomini()) {				
				myYear += my_yearsDistance;
			} else {
				myYear -= my_yearsDistance;
				if(myYear < 0) {
					isAnnoDomini = false;
				}
			}
		    cal.set(Calendar.YEAR, myYear);
		    cal.add(Calendar.MONTH, my_monthsDistance);
			cal.add(Calendar.DATE, addDays); // Adding x days
			myYear = cal.get(Calendar.YEAR);
			myMonth = cal.get(Calendar.MONTH);
			myDay = cal.get(Calendar.DAY_OF_MONTH);
		} else {
			if(relatedDate.isAnnoDomini()) {				
				myYear -= my_yearsDistance;
				if(myYear >= 0) {
					isAnnoDomini = true;
				}
			} else {
				myYear += my_yearsDistance;
			}
		    cal.set(Calendar.YEAR, myYear);
		    cal.add(Calendar.MONTH, -my_monthsDistance);
			cal.add(Calendar.DATE, -addDays); // Adding x days
			myYear = cal.get(Calendar.YEAR);
			myMonth = cal.get(Calendar.MONTH);
			myDay = cal.get(Calendar.DAY_OF_MONTH);
		}

		if(!isAnnoDomini) {
			myYear = Math.abs(myYear);
		}
	    
		return new SpecificDate(myDay, myMonth, myYear, isAnnoDomini);
	}	

}
