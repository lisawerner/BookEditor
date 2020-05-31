package time;

import book.Book;

public class TimelineSettings {
	
	private SplitCondition my_splitCondition;
	
	private boolean filter_maincharacters;
	
	public TimelineSettings() {
		my_splitCondition = SplitCondition.NONE;
		
		filter_maincharacters = false;
	}

	public void setMaincharacterFilter(boolean newFilter) {
		filter_maincharacters = newFilter;
		Book.getInstance().save();
	}
	
	public boolean getMaincharacterFilter() {
		return filter_maincharacters;
	}
	
	public void setSplitCondition(SplitCondition newCondition){
		my_splitCondition = newCondition;
		Book.getInstance().save();
	}
	
	public SplitCondition getSplitConditon(){
		return my_splitCondition;
	}

	
}
