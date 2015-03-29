package voxelengine.log;

import java.util.ArrayList;

/**
 * A log manager that keeps a list of requested loggers.
 * @author X1000
 *
 */
public class LogManager {

	public static ArrayList<Logger> loggers = new ArrayList<Logger>();
	
	private LogManager()
	{
		
	}
	
	public static Logger requestLogger(String logger)
	{
		Logger log = new Logger(logger);
		loggers.add(log);
		
		return log;
	}
	
	public static void clearLoggers()
	{
		loggers.clear();
	}
	
}

