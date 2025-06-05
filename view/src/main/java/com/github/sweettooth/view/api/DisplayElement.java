package com.github.sweettooth.view.api;

import java.io.IOException;

public interface DisplayElement {
	void start() throws IOException, InterruptedException;
	void stop() throws IOException;
}
