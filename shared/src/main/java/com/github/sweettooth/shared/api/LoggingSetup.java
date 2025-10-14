package com.github.sweettooth.shared.api;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingSetup {
	public static Path logsPath;
	
	public static void initialize(Class<?> launcher) {
        createLogsDirectory(launcher);
        configureLogging();
    }
	
	private static void createLogsDirectory(Class<?> launcher) {
		try {
			Path launcherPath = Paths.get(launcher.getProtectionDomain().getCodeSource().getLocation().toURI());
			
			Path projectRoot = launcherPath // bin
					.getParent() // launcher
					.getParent(); // project root
			logsPath = projectRoot.resolve("_LOGFILES");
//			System.out.println("############## launcherPath " + launcherPath);
//			System.out.println("############## projectRoot " + projectRoot);
//			System.out.println("############## logsPath " + logsPath);
			try {
				Files.createDirectory(logsPath);
			} catch(FileAlreadyExistsException ignored) {}
		}
		catch(Exception ex) {
			System.err.println("Error while creating the log directory: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private static void configureLogging() {
		try {
            	LogManager.getLogManager().reset();
            	Logger rootLogger = Logger.getLogger("");
            	rootLogger.setLevel(Level.INFO);
            	
            	ConsoleHandler consoleHandler = new ConsoleHandler();
            	consoleHandler.setLevel(Level.INFO);
            	consoleHandler.setFormatter(new SimpleFormatter());
            	rootLogger.addHandler(consoleHandler);
            	
            	String fileNamePattern = logsPath.resolve("/app-%u.log").toString();
            	FileHandler fileHandler = new FileHandler(fileNamePattern, 1_000_000, 2, true);
            	fileHandler.setLevel(Level.INFO);
            	fileHandler.setFormatter(new SimpleFormatter());
            	rootLogger.addHandler(fileHandler);
        }
        catch (Exception ex) {
            System.err.println("Error while configure logging: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
	
	private LoggingSetup(){}
}
