package Log;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

	public Email() {
	}

	public void sendMail() {
		// Set up the SMTP server.
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", "smtp.myisp.com");
		Session session = Session.getDefaultInstance(props, null);

		// Construct the message
		String to = "angi.lecas93@gmail.com";
		String from = "angi_lecas@hotmail.com";
		String subject = "Hello";
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText("Hi,\n\nHow are you?");

			// Send the message.
			// Transport.send(msg);
			Transport transport = session.getTransport("smtp");
			transport.connect();
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			// Error.
		}
	}

	public void sendMail2() {
		String host = "smtp.gmail.com";
		String from = "angi.lecas93@gmail.com";
		String to = "angi.lecas93@gmail.com";

		// Set properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.debug", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("username", "angi.lecas93@gmail.com");
		props.put("password", "dsk1dn789");

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
			//Transport.send(msg);
			Transport transport = session.getTransport("smtp");
			transport.connect("angi.lecas93@gmail.com", "dsk1dn789");;
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Correo enviado!");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
