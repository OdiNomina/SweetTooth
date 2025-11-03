module com.github.sweettooth.shared {
	exports com.github.sweettooth.shared.api;
	exports com.github.sweettooth.shared.api.util;
	exports com.github.sweettooth.shared.api.logging;
	
	requires transitive java.logging;
}