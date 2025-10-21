package com.github.sweettooth.shared.logging;

import java.io.StringWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
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
			Path installDir = launcherPath.getParent().getParent(); // dist -> SweetTooth
			try {
				boolean runningFromJar = launcherPath.toString().endsWith(".jar");
				if (runningFromJar && Files.isWritable(installDir))
					logsPath = installDir.resolve("logs");
				else
					logsPath = Paths.get(System.getProperty("user.home"), ".SweetTooth", "devLogs");

				Files.createDirectories(logsPath);
//				System.out.println("############## launcherPath " + launcherPath);
//				System.out.println("############## installDir " + installDir);
//				System.out.println("############## logsPath " + logsPath);
			}
			catch(FileAlreadyExistsException ignored) {}
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
            	
            	String fileNamePattern = logsPath.resolve("app-%u.log").toAbsolutePath().toString().replace("\\", "/");
            	FileHandler fileHandler = new FileHandler(fileNamePattern, 1_000_000, 2, true);
            	fileHandler.setEncoding(StandardCharsets.UTF_8.name());
            	fileHandler.setLevel(Level.ALL);
            	fileHandler.setFormatter(new FileFormatter());
            	rootLogger.addHandler(fileHandler);
        }
        catch (Exception ex) {
            System.err.println("Error while configure logging: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
	
	private static class FileFormatter extends Formatter {
		@Override
		public String format(LogRecord record) {
			StringBuffer sb = new StringBuffer();
			
			sb.append(String.format("%1$tF %1$tT [%2$s] - %3$s%n",
		            new Date(record.getMillis()),
		            record.getLevel().getName(),
		            record.getMessage()
		        ));
			
			if (record.getThrown() != null) {
	            StringWriter sw = new StringWriter();
	            PrintWriter pw = new PrintWriter(sw);
	            record.getThrown().printStackTrace(pw);
	            pw.flush();
	            
	            sb.append(sw.toString());
	        }
	        return sb.toString();
		}
	}
}
