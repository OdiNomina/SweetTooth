module com.github.sweettooth.model {
	exports com.github.sweettooth.model.api;
	exports com.github.sweettooth.model.api.gameEvents;
	exports com.github.sweettooth.model.api.settings;
	exports com.github.sweettooth.model.api.snacks;
	exports com.github.sweettooth.model.api.viewAPI;
	
	requires transitive com.github.sweettooth.shared;
}