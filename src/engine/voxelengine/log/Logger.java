package voxelengine.log;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;
import java.util.Objects;

/**
 * A class that wraps around the System.out print stream.
 * @author X1000
 *
 */
public final class Logger {

	String name = "Anonymous";
	private boolean formatted = true;

	/**
	 * 
	 * Creates a logger
	 * 
	 * @param name
	 *            The Logging Name
	 * @param formatted
	 *            Whether the messages should be formatted
	 */
	public Logger(String name, final boolean formatted) {
		this.name = name;
		this.formatted = formatted;
	}

	/**
	 * 
	 * Creates a formatted logger
	 * 
	 * @param name
	 */
	public Logger(String name) {
		this(name, true);
	}

	public void fill() {
		info("");
	}

	public void err(String message) {
		info(message, System.err);
	}
	
	public void err(String message, Object... format)
	{
		info(String.format(message, format), System.err);
	}

	public void info(String message) {
		info(message, System.out);
	}
	
	public void info(String message, Object... format)
	{
		info(String.format(message, format));
	}

	public void info(String message, PrintStream stream) {

		if (formatted)
			logFormat(message, Objects.requireNonNull(stream));
		else
			stream.println(message);
		// System.out.println(String.format("[%s][%s] %s", new
		// Date(System.currentTimeMillis()).toString(),
		// Thread.currentThread().getName(), message));
	}

	public void logFormat(String mess, PrintStream stream) {

		Calendar cal = new Builder().setInstant(new Date(System.currentTimeMillis())).build();
		stream.println(String.format("[%s] [%s] [%s]: %s", String.format("%s:%s:%s", cal.get(Calendar.HOUR) == 0 ? 12 : cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)), Thread.currentThread().getName(), name, mess));
	}
}
