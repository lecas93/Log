package Log;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

	public Email() {
	}

	public void sendMail() {
		String host = "smtp.gmail.com";
		String from = "angi.lecas93@gmail.com";
		String to = "angi.lecas93@gmail.com";

		// Set properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.debug", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);

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
			msg.setText("This is the text for this simple demo using JavaMail.");

			// Send the message
			Transport transport = session.getTransport("smtp");
			transport.connect("angi.lecas93@gmail.com", "dsk1dn789");
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Correo enviado!");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
