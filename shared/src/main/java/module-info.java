module com.github.sweettooth.shared {
	exports com.github.sweettooth.shared.api.util;
	exports com.github.sweettooth.shared.api.logging;
	exports com.github.sweettooth.shared.api.gameControl;
	
	requires transitive java.logging;
}