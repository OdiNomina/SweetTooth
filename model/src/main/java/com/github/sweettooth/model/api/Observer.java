package com.github.sweettooth.model.api;

public interface Observer {
	/*
	 * The interface java.util.Observer and the class java.util.Observable are deprecated since="9".
	 * 
	 * The module model have to know the data-type Observer to be able to call the method update() within the method notifyObservers().
	 * 
	 * The view implements Observer.
	 */
	
	void update();
}
