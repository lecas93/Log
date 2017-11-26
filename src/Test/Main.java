package Test;

import Log.Logger;
import Log.Email;
import Log.Level;;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		//Logger logger = Logger.getInstance();
		Logger.setLevel(Level.INFO);
		
		otroMetodo();
		otroMetodo2();
		yoloMetodo();
		
		Logger log2 = Logger.getLogger(Main.class);
		log2.info("Hola");
		
		Logger log3 = Logger.getLogger(Main.class);
		log3.info("Hola de nuevo");
		
		Logger log4 = Logger.getLogger(Main.class);
		log4.warning("Este es un warning");
		
		log4.printLevel();
		log3.printLevel();
		log2.printLevel();
		
		Logger.setLevel(Level.WARNING);
		
		log4.printLevel();
		log3.printLevel();
		log2.printLevel();
		
		Logger.setLevel(Level.ERROR);
		
		log4.printLevel();
		log3.printLevel();
		log2.printLevel();
		*/
		//Email email = new Email();
		//email.sendMail2();
	}
	
	public static void otroMetodo() {
		Logger log2 = Logger.getLogger(Main.class);
		log2.info("Hola");
	}
	
	public static void otroMetodo2() {
		Logger log2 = Logger.getLogger(Main.class);
		log2.info("Hola de nuevo");
	}
	
	public static void yoloMetodo() {
		Logger log2 = Logger.getLogger(Main.class);
		log2.warning("Este es un warning");
	}

}
