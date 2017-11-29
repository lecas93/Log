package Test;

import Log.Logger;
import Log.Mode;
import Log.Email;
import Log.Level;;

public class Main {

	static Logger logger;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		logger = Logger.getLogger(Main.class);

		// logger.setLevel(Level.INFO);
		// logger.setMode(Mode.DEBUG);

		logger.printLogStatus();

		otroMetodo();
		otroMetodo2();
		yoloMetodo();
		
		MyClass m = new MyClass();
		
		//logger.sendEmail();

		/*
		 * Logger log2 = Logger.getLogger(Main.class); log2.info("Hola");
		 * 
		 * Logger log3 = Logger.getLogger(Main.class); log3.info("Hola de nuevo");
		 * 
		 * Logger log4 = Logger.getLogger(Main.class);
		 * log4.warning("Este es un warning");
		 */

		//Email email = new Email();
		//email.sendMail();
	}

	public void nuevoMetodo() {
		logger.info("Este es otro info");
	}

	public static void otroMetodo() {
		logger.info("Este es un info");
	}

	public static void otroMetodo2() {
		logger.error("Este es un error");
	}

	public static void yoloMetodo() {
		logger.warning("Este es un warning");
	}

}
