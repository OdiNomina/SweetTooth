package com.github.sweettooth.model.settings;

import com.github.sweettooth.model.locations.Location;

public class InternSettings {
	/* Integer.valueOf() Änderung der Konstanten erzwingt keine neue Übersetzung von abhängigen Klassen
	 * (s. Java Insel - 6.6.4 Eincompilierte Belegungen der Klassenvariablen).
	 */
	public final static int GAME_DURATION_DAYS = Integer.valueOf(30);
	public final static Location HOMETOWN = Location.BRONX;
	public final static double START_CASH = Double.valueOf(200);
	
	public final static int MAX_SNACKS = Integer.valueOf(100); 
	
	public final static int MAX_GIFT_TYPES = Integer.valueOf(3);
	public final static int MAX_GIFT_QUANTITY = Integer.valueOf(10);
	
	public final static double BANK_MIN_BALANCE = Double.valueOf(-100);
	public final static double BANK_INTEREST_CREDIT_PERCENT = Double.valueOf(1.5);
	public final static double BANK_INTEREST_DEBT_PERCENT = Double.valueOf(3);
	public final static double LOANSHARK_INTEREST_DEBT_PERCENT = Double.valueOf(5);
	
	public final static double TRAVEL_COSTS = Double.valueOf(5);;
}	
	
