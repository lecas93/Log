package Log;

import java.util.Date;

//TODO
//setLevel es decir, establecer los niveles
//crear las funciones por cada nivel establecido

public class Logger {

	private Class currentClass;

	private static int currentLevel;
	private static int currentMode;

	private static Bitacora bitacora;
	private String record;

	private static XMLParser xmlParser;

	private Logger(Class currentClass) {
		this.currentClass = currentClass;
	}

	public static Logger getLogger(Class currentClass) {
		Logger log = new Logger(currentClass);
		loadConfig();
		bitacora = Bitacora.getInstance();
		return log;
	}

	private static void loadConfig() {
		xmlParser.loadInfo();
		currentLevel = XMLParser.getLevel();
		currentMode = XMLParser.getMode();
	}

	public void printLevel() {//
		System.out.println("Mode: " + currentMode);
		System.out.println("Level: " + currentLevel);
		System.out.println("File Size: " + xmlParser.getFileSize());
	}

	public void setLevel(Level level) {
		currentLevel = level.ordinal();
	}

	public int getLevel() {
		return currentLevel;
	}

	public void setMode(Mode mode) {
		currentMode = mode.ordinal();
	}

	public int getMode() {
		return currentMode;
	}

	private String getCurrentThreadInformation() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String info = "" + new Date() + " " + currentClass.getSimpleName() + " "
				+ stackTraceElements[3].getMethodName();
		return info;
	}

	private void print(String record) {
		if (currentMode == Mode.DEBUG.ordinal()) {
			System.out.println(record);
		} else {
			bitacora.writeRecord(record);
		}
	}

	public void info(String msg) {
		record = getCurrentThreadInformation() + " INFO: " + msg;
		if (currentLevel == Level.ALL.ordinal() || currentLevel == Level.INFO.ordinal()) {
			print(record);
		}
	}

	public void warning(String msg) {
		record = getCurrentThreadInformation() + " WARNING: " + msg;
		if (currentLevel == Level.ALL.ordinal() || currentLevel == Level.INFO.ordinal()
				|| currentLevel == Level.WARNING.ordinal()) {
			print(record);
		}
	}

	public void error(String msg) {
		record = getCurrentThreadInformation() + " ERROR: " + msg;
		if (currentLevel == Level.ALL.ordinal() || currentLevel == Level.INFO.ordinal()
				|| currentLevel == Level.WARNING.ordinal() || currentLevel == Level.ERROR.ordinal()) {
			print(record);
		}
	}
	
	public void sendEmail() {
		
	}
}
