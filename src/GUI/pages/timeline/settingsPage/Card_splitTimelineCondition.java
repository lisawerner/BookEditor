package GUI.pages.timeline.settingsPage;

import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import book.Book;
import time.SplitCondition;

import javax.swing.*;

public class Card_splitTimelineCondition extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_splitTimelineCondition() {
		
		ButtonGroup btnGroup_splits = new ButtonGroup();
		
		SimpleRadiobutton rdbtnSplitNot = new SimpleRadiobutton("do not split");
		add(rdbtnSplitNot);
		btnGroup_splits.add(rdbtnSplitNot);
		rdbtnSplitNot.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.NONE));
		rdbtnSplitNot.setSelected(Book.getInstance().getTimeline().getSplitCondition() == SplitCondition.NONE);
		
		SimpleRadiobutton rdbtnSplitByYears = new SimpleRadiobutton("split by years");
		add(rdbtnSplitByYears);
		btnGroup_splits.add(rdbtnSplitByYears);
		rdbtnSplitByYears.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.YEAR));
		rdbtnSplitByYears.setSelected(Book.getInstance().getTimeline().getSplitCondition() == SplitCondition.YEAR);
		
		SimpleRadiobutton rdbtnSplitByMonths = new SimpleRadiobutton("split by months");
		add(rdbtnSplitByMonths);
		btnGroup_splits.add(rdbtnSplitByMonths);
		rdbtnSplitByMonths.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.MONTH));
		rdbtnSplitByMonths.setSelected(Book.getInstance().getTimeline().getSplitCondition() == SplitCondition.MONTH);
		rdbtnSplitByMonths.setEnabled(false);

		SimpleRadiobutton rdbtnSplitBySpecificDates = new SimpleRadiobutton("split by specific dates");
		add(rdbtnSplitBySpecificDates);
		btnGroup_splits.add(rdbtnSplitBySpecificDates);
		rdbtnSplitBySpecificDates.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.SPECIFIC));
		rdbtnSplitBySpecificDates.setSelected(Book.getInstance().getTimeline().getSplitCondition() == SplitCondition.SPECIFIC);
		rdbtnSplitBySpecificDates.setEnabled(false);
	}

}
