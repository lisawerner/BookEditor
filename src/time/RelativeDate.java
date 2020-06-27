package time;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import book.Book;
import book.Section;
import global.ObjectID;

public class RelativeDate {
	
	private ObjectID isRelatedToTimestamp;
	
	private boolean isAfter;
	private int my_daysDistance;
	private int my_weeksDistance;
	private int my_monthsDistance;
	private int my_yearsDistance;
	private int my_dayOfTheWeek;
	
	public RelativeDate(ObjectID relatesToTimestamp, boolean newIsAfter, int distDays, int distWeeks, int distMonths, int distYears, int dayOfWeek) {
		isRelatedToTimestamp = relatesToTimestamp;
		isAfter = newIsAfter;
		my_daysDistance = distDays;
		my_weeksDistance = distWeeks;
		my_monthsDistance = distMonths;
		my_yearsDistance = distYears;
		my_dayOfTheWeek = dayOfWeek;
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
	
	public int getDayOfWeek() {
		return my_dayOfTheWeek;
	}

	public Timestamp generateSpecificDate() {
		if(isRelatedToTimestamp == null){
			System.out.println("Relation to timestamp is null");
			return null;
		}
		System.out.println(">>Relation to timestamp is NOT null");
		Timestamp relatedDate = Book.getInstance().getTimeline().getTimestamp(isRelatedToTimestamp);
		if(relatedDate == null){
			System.out.println("Parent timestamp is not specific");
			return null;
		}
		System.out.println("Parent timestamp is found and specific");
		LocalDate parentDate = relatedDate.getDate();
		int myYear = parentDate.getYear();
		int myMonth = parentDate.getMonth().getValue();
		int myDay = parentDate.getDayOfMonth();
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
		
		//TODO: DONT SAVE THAT! you will delete the relation to the section!
		return new Timestamp(null, myDay, myMonth, myYear, isAnnoDomini, relatedDate.hasConcreteYear());
	}

	public ObjectID getRelationID() {
		return isRelatedToTimestamp;
	}

	public Section getRelationSection() {
		if(isRelatedToTimestamp == null){
			return null;
		}
		ObjectID parentRel = Book.getInstance().getTimeline().getTimestamp(isRelatedToTimestamp).getSection();
		if(parentRel != null){			
			return Book.getInstance().getTableOfContent().getSection(parentRel);
		}
		return null;
	}
	
	public LocalDate fix(ArrayList<Timestamp> copyList){
		if(isRelatedToTimestamp == null){
//			System.out.println("!!Relation to timestamp is null");
			return null;
		}
//		System.out.println(">>Relation to timestamp is NOT null");
		
		Timestamp relatedDate = copyList.stream().filter(timestamp -> timestamp.getID().equals(isRelatedToTimestamp)).findAny().get();
		if(relatedDate == null){
//			System.out.println("!! !!Parent timestamp is NOT specific");
			return null;
		}
//		System.out.println(">> >>Select specific parent");
		
		LocalDate parentDate = relatedDate.getDate();
		if(parentDate == null){
//			System.out.println("!! !! !! Date of ParentTimestamp was not set yet");
			return null;
		}
//		System.out.println(">> >> >>ParentTimestamp get date!");
		
		LocalDate ownNewDate = parentDate;
		if(isAfter) {
			ownNewDate = ownNewDate.plusDays(my_daysDistance);			
		} else {
			ownNewDate = ownNewDate.minusDays(my_daysDistance);
		}
		
		if(isAfter) {
			ownNewDate = ownNewDate.plusWeeks(my_weeksDistance);			
		} else {
			ownNewDate = ownNewDate.minusWeeks(my_weeksDistance);
		}
		
		if(isAfter) {
			ownNewDate = ownNewDate.plusMonths(my_monthsDistance);			
		} else {
			ownNewDate = ownNewDate.minusMonths(my_monthsDistance);
		}
		
		if(isAfter) {
			ownNewDate = ownNewDate.plusYears(my_yearsDistance);			
		} else {
			ownNewDate = ownNewDate.minusYears(my_yearsDistance);
		}
		
		if(my_dayOfTheWeek != 0){
//			System.out.println("!! Day of week .......");
			
			if(isAfter){				
				int currentDayOfWeek = ownNewDate.getDayOfWeek().getValue();
				int difference = 0;
				if(currentDayOfWeek < my_dayOfTheWeek) {					
					difference = my_dayOfTheWeek - currentDayOfWeek;
				} else {
					difference = (7 - currentDayOfWeek) + my_dayOfTheWeek;
				}
				ownNewDate = ownNewDate.plusDays(difference);
			} else{
				System.out.println("Shit... day of week BEFORE!");
			}
			
		}
		
		System.out.println("Change done!");
		return ownNewDate;
	}
	
}
