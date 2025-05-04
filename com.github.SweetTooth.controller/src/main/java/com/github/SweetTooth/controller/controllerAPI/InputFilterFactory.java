package com.github.SweetTooth.controller.controllerAPI;

import com.github.SweetTooth.controller.TextBoxInputFilters.*;
import com.googlecode.lanterna.gui2.InputFilter;

class InputFilterFactory {
	private LanternaController controller;
	
	InputFilterFactory(LanternaController controller){
		this.controller = controller;
	}
	
	InputFilter createInputFilter(String textBoxName) {
		switch(textBoxName) {
			case "seekQuantity": return new SeekQuantityInputFilter(textBoxName, controller);
			case "buyQuantity": return new DealInputFilter(textBoxName, controller);
			case "sellQuantity": return new DealInputFilter(textBoxName, controller);
			case "deposit": return new FinancesInputFilter(textBoxName, controller);
			case "withdraw": return new FinancesInputFilter(textBoxName, controller);
			case "lend": return new FinancesInputFilter(textBoxName, controller);
			case "giveBack": return new FinancesInputFilter(textBoxName, controller);
			default: return null;
		}
	}
}
