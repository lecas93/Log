package Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

//TODO
//setLevel es decir, establecer los niveles
//crear las funciones por cada nivel establecido

public class Logger {

	private static Logger log;

	private static Logger log2;
	private Class currentClass;
	//public String id;

	public static enum Level {
		INFO, WARNING, SEVERE
	}

	private static int currentLevel = 0;

	private Logger() {
	}

	/*
	public static Logger getInstance() {
		if (log == null) {
			log = new Logger();
		}
		return log;
	}
	*/

	// Con este metodo NO se debe aplicar el patron Singleton
	public static Logger getLogger(Class myClass) {
		Logger log = new Logger();
		log.setCurrentClass(myClass);
		//log.id = myClass.getName() + new Random().nextInt();
		return log;
	}

	private void setCurrentClass(Class myClass) {
		currentClass = myClass;
	}

	public void printId() {
		//System.out.println(id);
	}
	
	public void printLevel() {
		System.out.println("Current level: " + currentLevel);
	}

	public static void setLevel(Level level) {
		currentLevel = level.ordinal();
		System.out.println("Current level: " + currentLevel);
	}

	private void printCurrentState() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		System.out.println(
				"" + new Date() + " " + currentClass.getSimpleName() + " " + stackTraceElements[3].getMethodName());
	}

	public void info(String msg) {
		if (currentClass != null) {
			printCurrentState();
			System.out.println("INFO: " + msg);
		}
	}

	public void warning(String msg) {
		printCurrentState();
		System.out.println("WARNING: " + msg);
	}

	public void severe(String msg) {
		printCurrentState();
		System.out.println("SEVERE: " + msg);
	}
}
