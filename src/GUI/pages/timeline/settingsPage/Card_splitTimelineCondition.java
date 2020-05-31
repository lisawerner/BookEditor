package GUI.pages.timeline.settingsPage;

import javax.swing.ButtonGroup;

import GUI.components.SimpleRadiobutton;
import GUI.components.TransparentPanel;
import book.Book;
import time.SplitCondition;

public class Card_splitTimelineCondition extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public Card_splitTimelineCondition() {
		
		ButtonGroup btngroup_splitts = new ButtonGroup();
		
		SimpleRadiobutton rdbtnSplitNot = new SimpleRadiobutton("do not split");
		add(rdbtnSplitNot);
		btngroup_splitts.add(rdbtnSplitNot);
		rdbtnSplitNot.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.NONE));
		rdbtnSplitNot.setSelected(Book.getInstance().getTimeline().getSplitConditon() == SplitCondition.NONE);
		
		SimpleRadiobutton rdbtnSplittByYears = new SimpleRadiobutton("splitt by years");
		add(rdbtnSplittByYears);
		btngroup_splitts.add(rdbtnSplittByYears);
		rdbtnSplittByYears.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.YEAR));
		rdbtnSplittByYears.setSelected(Book.getInstance().getTimeline().getSplitConditon() == SplitCondition.YEAR);
		
		SimpleRadiobutton rdbtnSplitByMonths = new SimpleRadiobutton("split by months");
		add(rdbtnSplitByMonths);
		btngroup_splitts.add(rdbtnSplitByMonths);
		rdbtnSplitByMonths.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.MONTH));
		rdbtnSplitByMonths.setSelected(Book.getInstance().getTimeline().getSplitConditon() == SplitCondition.MONTH);
		rdbtnSplitByMonths.setEnabled(false);

		SimpleRadiobutton rdbtnSplitBySpecificDates = new SimpleRadiobutton("split by specific dates");
		add(rdbtnSplitBySpecificDates);
		btngroup_splitts.add(rdbtnSplitBySpecificDates);
		rdbtnSplitBySpecificDates.addActionListener(e -> Book.getInstance().getTimeline().setSplitCondition(SplitCondition.SPECIFIC));
		rdbtnSplitBySpecificDates.setSelected(Book.getInstance().getTimeline().getSplitConditon() == SplitCondition.SPECIFIC);
		rdbtnSplitBySpecificDates.setEnabled(false);
	}

}
