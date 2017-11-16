package Test;

public class Logger {

	private static Logger log;

	private Logger() {
	}

	public static Logger getInstance() {
		if (log == null) {
			log = new Logger();
		}
		return log;
	}
}
