module com.github.sweettooth.view {
	exports com.github.sweettooth.view.api;
	
	requires java.desktop;
	requires com.github.sweettooth.model;
	requires com.github.sweettooth.controllerLanterna;
	requires com.github.sweettooth.controllerSwing;
	requires com.github.sweettooth.shared;
	requires com.googlecode.lanterna;
}