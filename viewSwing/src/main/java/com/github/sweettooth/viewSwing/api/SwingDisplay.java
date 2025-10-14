package com.github.sweettooth.viewSwing.api;

import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.viewSwing.session.SwingGUI;

public interface SwingDisplay extends Runnable {
	
	@SuppressWarnings("exports")
	static SwingDisplay getInstance(ISessionData sessionData) {
		return new SwingGUI(sessionData);
	}
}
