package com.github.sweettooth.viewSwing.api;

import com.github.sweettooth.model.api.gameSession.IGameSession;
import com.github.sweettooth.viewSwing.session.SwingGUI;

public interface SwingDisplay {
	
	@SuppressWarnings("exports")
	static SwingDisplay getInstance(IGameSession sessionData) {
		return new SwingGUI(sessionData);
	}
	
	void start();
}
