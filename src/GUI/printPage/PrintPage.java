package GUI.printPage;

import GUI.components.Page;
import GUI.components.StructureCard;

public class PrintPage extends Page {
	private static final long serialVersionUID = 1L;
	
	
	public PrintPage() {
		super("Print and Export");
		
		addCard(new StructureCard("Print Hints", new PrintHintCard()));
		
		addCard(new StructureCard("Export Book", new ExportCard()));

		addCard(new StructureCard("PrettyPrint Book", new PrettyPrintCard()));
	}
	


}
