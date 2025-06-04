package com.github.sweettooth.model.api;

public class Settings {
	public Settings(){}
	
	/* Änderung der Konstanten erzwingt keine neue Übersetzung von abhängigen Klassen
	 * (s. Java Insel - 6.6.4 Eincompilierte Belegungen der Klassenvariablen).
	 */
	public final static int MAX_SNACKS = Integer.valueOf(100); 
	public final static String CURRENCY = "€";
	public final static double INTEREST_CREDIT_PERCENT = Double.valueOf(2);
	public final static double INTEREST_DEBT_PERCENT = Double.valueOf(5);
	public final static double BANK_MIN_BALANCE = Double.valueOf(-100);
	
}
