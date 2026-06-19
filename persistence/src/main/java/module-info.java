module com.github.sweettooth.persistence {
	exports com.github.sweettooth.persistence.api to com.github.sweettooth.model;
	exports com.github.sweettooth.persistence.api.sharedDAO to com.github.sweettooth.model;
	// temporarily
	exports com.github.sweettooth.persistence.jdbc to com.github.sweettooth.utilityApp;
	
	requires java.sql;
	requires com.github.sweettooth.shared;
}