package Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
	private static Logger logger;

	private static Email email;

	private String host = "smtp.gmail.com";
	private String to, from, pass;
	private String message = "";

	private String ruta;
	private int fileCounter;

	private Email() {
		String emailInfo[] = XMLParser.getEmailInfo();
		this.to = emailInfo[0];
		this.from = emailInfo[1];
		this.pass = emailInfo[2];
	}

	public static synchronized Email getInstance() {
		if (email == null) {
			email = new Email();
			logger = Logger.getLogger(Email.class);
		}
		return email;
	}

	public void sendMail() {
		generateDefaultReport();
		_sendEmail();
	}

	public void sendEmail(Level level) {
		switch (level.ordinal()) {
		case 0:
			generateReportByErrorLevel("ALL");
			break;
		case 1:
			generateReportByErrorLevel("INFO");
			break;
		case 2:
			generateReportByErrorLevel("WARNING");
			break;
		case 3:
			generateReportByErrorLevel("ERROR");
			break;
		}
		_sendEmail();
	}

	private void _sendEmail() {
		// Set properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.debug", true);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("username", from);
		props.put("password", pass);

		// Get session
		Session session = Session.getInstance(props);

		try {
			// Instantiate a message
			Message msg = new MimeMessage(session);

			// Set the FROM message
			msg.setFrom(new InternetAddress(from));

			// The recipients can be more than one so we use an array but you can
			// use 'new InternetAddress(to)' for only one address.
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);

			// Set the message subject and date we sent it.
			msg.setSubject("Email from JavaMail test");
			msg.setSentDate(new Date());

			// Set message content
			msg.setText(message);

			// Send the message
			Transport transport = session.getTransport("smtp");
			transport.connect(from, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (MessagingException mex) {
			logger.error(mex.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private void generateDefaultReport() {
		try {
			fileCounter = 1;
			message = "";
			checkFiles();
		} catch (Exception e) {

		}
	}

	private void generateReportByErrorLevel(String errorLevel) {
		try {
			fileCounter = 1;
			message = "";
			checkFiles(errorLevel);
		} catch (Exception e) {

		}
	}

	private void checkFiles() {
		boolean exist = checkRuta();
		if (exist) {
			generateMessage();
			fileCounter++;
			checkFiles();
		}
	}

	private void checkFiles(String errorLevel) {
		boolean exist = checkRuta();
		if (exist) {
			generateMessage(errorLevel);
			fileCounter++;
			checkFiles();
		}
	}

	private boolean checkRuta() {
		String[] date = new Date().toString().split(" ");
		String subFolder = date[2] + "-" + date[1] + "-" + date[5];
		ruta = Bitacora.getLogDirectory() + subFolder + "/" + subFolder + "_#" + fileCounter + ".txt";
		File archivo = new File(ruta);
		return archivo.exists();
	}

	private void generateMessage() {
		try {
			File archivo = new File(ruta);
			Scanner sc = new Scanner(archivo);
			while (sc.hasNextLine()) {
				message += sc.nextLine() + "\n";
			}
			sc.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	private void generateMessage(String errorLevel) {
		try {
			File archivo = new File(ruta);
			Scanner sc = new Scanner(archivo);
			while (sc.hasNextLine()) {
				String s = sc.nextLine() + "\n";
				if (s.contains(errorLevel)) {
					message += s;
				}
			}
			sc.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

}
