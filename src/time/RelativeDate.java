package time;

import java.util.Calendar;
import java.util.Date;

import book.Book;
import book.Section;
import global.ObjectID;

public class RelativeDate {
	
	private ObjectID relatedToSection;
	//TODO: Change -> should refere to another timestamp and not to another section!
	//TODO: maybe switch relation... do not save single child date... save parent date which are the giver for the specific date...
	
	private boolean isAfter;
	private int my_daysDistance;
	private int my_weeksDistance;
	private int my_monthsDistance;
	private int my_yearsDistance;
	private int my_dayOfTheWeek;
	
	public RelativeDate(ObjectID selectedSection, boolean newIsAfter, int distDays, int distWeeks, int distMonths, int distYears, int dayOfWeek) {
		relatedToSection = selectedSection;
		isAfter = newIsAfter;
		my_daysDistance = distDays;
		my_weeksDistance = distWeeks;
		my_monthsDistance = distMonths;
		my_yearsDistance = distYears;
		my_dayOfTheWeek = dayOfWeek;
	}
	
	public String toString() {	
		Section relatedSection = Book.getInstance().getTableOfContent().getSection(relatedToSection);
		if(!relatedSection.hasTimestamp()){
			return "Related to Section: " + relatedSection.getName() +" (which has no Timestamp set)";
		}
		SpecificDate spec = generateSpecificDate();
		if(spec != null){			
			return spec.toString();
		} else {
			return "no specific date available";
		}
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
	
	public int getDayOfWeek() {
		return my_dayOfTheWeek;
	}	

	public SpecificDate generateSpecificDate() {
		Section relatedSection = Book.getInstance().getTableOfContent().getSection(relatedToSection);
		if(relatedSection == null){
			return null;
		}
		if(!relatedSection.hasTimestamp()){
			return null;
		}
		SpecificDate relatedDate = Book.getInstance().getTimeline().getTimestamp(relatedSection.getTimestampID()).getSpecificDate();
		if(relatedDate == null){
			return null;
		}
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
			if(my_dayOfTheWeek > 0) {
				int currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				int difference = 0;
				if(currentDayOfWeek < my_dayOfTheWeek) {					
					difference = my_dayOfTheWeek - currentDayOfWeek;
				} else {
					difference = (7 - currentDayOfWeek) + my_dayOfTheWeek;
				}
				cal.add(Calendar.DATE, difference); // Adding x days
				cal.get(Calendar.DAY_OF_WEEK);
			}
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
			
			if(my_dayOfTheWeek > 0) {
				int currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) -1;
				int difference = 0;
				if(currentDayOfWeek < my_dayOfTheWeek) {					
					difference = my_dayOfTheWeek - currentDayOfWeek;
				} else {
					difference = (7 - currentDayOfWeek) + my_dayOfTheWeek;
				}
				cal.add(Calendar.DATE, difference); // Adding x days
			}
		}
		myYear = cal.get(Calendar.YEAR);
		myMonth = cal.get(Calendar.MONTH);
		myDay = cal.get(Calendar.DAY_OF_MONTH);

		if(!isAnnoDomini) {
			myYear = Math.abs(myYear);
		}
			    
		return new SpecificDate(myDay, myMonth, myYear, isAnnoDomini);
	}
	
}
