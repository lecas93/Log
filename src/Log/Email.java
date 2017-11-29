package Log;

import java.io.File;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

	private String host = "smtp.gmail.com";
	private String to, from, pass;
	private String message = "Ejemplo";

	public Email(String to, String from, String pass) {
		this.to = to;
		this.from = from;
		this.pass = pass;
	}

	public void sendMail() {
		System.out.println("Enviando email...");

		// Set properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		//props.put("mail.debug", true);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
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
			System.out.println("Correo enviado!");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private void generateReportByErrorLevel() {
		try {
			File archivo = new File("archivo.txt");
			Scanner sc = new Scanner(archivo);
			while (sc.hasNext()) {
				System.out.println(sc.next());
			}
			sc.close();
		} catch (Exception e) {

		}
	}

}
