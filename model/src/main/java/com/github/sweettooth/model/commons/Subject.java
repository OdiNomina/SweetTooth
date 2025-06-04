package com.github.sweettooth.model.commons;

import com.github.sweettooth.model.api.Observer;

public interface Subject {
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
}
