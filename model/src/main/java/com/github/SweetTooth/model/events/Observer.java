package com.github.sweettooth.model.events;

import java.io.IOException;

public interface Observer {
	void addObserver();
	void removeObserver();
	void updateObserver();
	void stopObserver() throws IOException;
}
