module com.github.sweettooth.persistence {
	exports com.github.sweettooth.persistence.api to com.github.sweettooth.model;
	exports com.github.sweettooth.persistence.api.sharedDAO to com.github.sweettooth.model;
	
	requires java.sql;
	requires com.github.sweettooth.shared;
}