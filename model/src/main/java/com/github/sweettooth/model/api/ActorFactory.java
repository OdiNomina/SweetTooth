package com.github.sweettooth.model.api;

import com.github.sweettooth.model.characters.Bank;
import com.github.sweettooth.model.characters.LoanShark;
import com.github.sweettooth.model.characters.Player;

public abstract class ActorFactory {
	public ActorFactory(){}
	
	public static Interactable createInteractable(String interactableName) {
		Interactable ia = null;
		
		switch(interactableName.toLowerCase()) {
			case ("bank") -> ia = new Bank();
			case ("loanshark") -> ia = new LoanShark();
		}
		return ia;
	}
	
	public static Playable createPlayer() {
		return new Player(null);
	}
}
