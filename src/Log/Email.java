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
			Transport.send(msg);
		} catch (MessagingException e) {
			// Error.
		}
	}

}
