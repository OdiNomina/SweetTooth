package com.github.sweettooth.shared.api.util;

/**
 * <pre>
 * The interface java.util.Observer and the class java.util.Observable are deprecated since="9".
 * 
 * The model module must know the Observer data type in order to call its update() method.
 * (For example, in the Observer pattern within the notifyObservers() methods of the model.)
 * </pre>
 */
public interface Observer {
	/**
	 * Must be implemented by all observers, such as views, so that they are able to update their individual display elements.
	 */
	void update();
}
