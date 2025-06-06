package com.github.sweettooth.model.api;

public interface Subject {
	// --- ModelInterfaces extend Subject (models implement Subject).
	
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
}
