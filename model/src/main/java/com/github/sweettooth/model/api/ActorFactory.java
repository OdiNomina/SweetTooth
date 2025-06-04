package com.github.sweettooth.model.api;

import com.github.sweettooth.model.apiView.FinanciallyInteractable;
import com.github.sweettooth.model.apiView.Playable;
import com.github.sweettooth.model.characters.Bank;
import com.github.sweettooth.model.characters.LoanShark;
import com.github.sweettooth.model.characters.Player;

public abstract class ActorFactory {
	public ActorFactory(){}
	
	public static FinanciallyInteractable createInteractable(String interactableName) {
		FinanciallyInteractable fi = null;
		
		switch(interactableName.toLowerCase()) {
			case ("bank") -> fi = new Bank();
			case ("loanshark") -> fi = new LoanShark();
		}
		return fi;
	}
	
	public static Playable createPlayer() {
		return new Player(null);
	}
}
