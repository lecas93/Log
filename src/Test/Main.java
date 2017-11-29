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
		
		//Estableciendo el nivel y el modo desde código
		logger.setLevel(Level.INFO);
		logger.setMode(Mode.DEBUG);

		//Función que sirve para imprimir en consola la configuracion actual de Log
		logger.printLogStatus();

		otroMetodo();
		otroMetodo2();
		yoloMetodo();
		
		//Envio de email normal
		//El email contendrá todos los regitros del log del dia actual
		logger.sendEmail();
		
		//Envio de email por nivel de error
		//El email solo contendrá los regitros del log del nivel especificado del dia actual
		logger.sendEmail(Level.WARNING);
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
