package com.github.SweetTooth.characters;

import java.util.logging.Level;
import java.util.logging.Logger;

//Currently for training purposes only
interface Logged {
	default void error(String message) {
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, message);
	}
	
	default void warn(String message) {
		Logger.getLogger(getClass().getName()).log(Level.WARNING, message);
	}
	
	default void info(String message) {
		Logger.getLogger(getClass().getName()).log(Level.INFO, message);
	}
}
