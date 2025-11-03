module com.github.sweettooth.model {
	exports com.github.sweettooth.model.api;
	exports com.github.sweettooth.model.api.characters;
	exports com.github.sweettooth.model.api.gameEvents;
	exports com.github.sweettooth.model.api.settings;
	exports com.github.sweettooth.model.api.gameSession;
	exports com.github.sweettooth.model.api.snacks;
	
	requires transitive com.github.sweettooth.shared;
}