package time;

import java.util.Date;

import book.Book;
import book.Section;

public class Timestamp {
	
	//Ideas:
	// Timestamp unknown!!! (Simple, when no timestamp is set?)
	// (ca) Distance to another Timestamp/Section
	// Concrete date
	
	// Attention: What is about stories/worlds with another calender then gregorian?
	
	private final SpecificDate my_specificDate;
	private final RelativeDate my_relativeDate;
	
	public Timestamp(SpecificDate newSpecificDate, RelativeDate newRelativeDate) {
		my_specificDate = newSpecificDate;
		my_relativeDate = newRelativeDate;
	}
	
	public boolean hasDate(){
		return my_specificDate != null || my_relativeDate != null;
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
		Date my_date;
		if(my_specificDate != null) {
			my_date = my_specificDate.getDate();
		} else {
			my_date = my_relativeDate.generateSpecificDate().getDate();
		}
		Date other_date;
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

	public Section getRelationSection() {
		if(my_specificDate != null){
			return null;
		} else if(my_relativeDate != null){
			return Book.getInstance().getTableOfContent().getSection(my_relativeDate.getRelatedSectionID());
		} else {
			return null;
		}
	}
	

}
