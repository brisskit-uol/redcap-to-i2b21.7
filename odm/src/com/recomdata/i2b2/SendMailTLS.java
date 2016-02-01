package com.recomdata.i2b2;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
public class SendMailTLS {
 
	private static final Log logger = LogFactory.getLog(SendMailTLS.class);

	public static void sendMail(String mailserver,String port,String sendmail,String email_password, String auth, String starttls_enable, String groupname, String email, String civi_url) {
	
		logger.info("Trying to send email");
		logger.info("mailserver " + mailserver);
		logger.info("port " + port);
		logger.info("sendmail " + sendmail);
		logger.info("email_password " + email_password);
		logger.info("auth " + auth);
		logger.info("starttls_enable " + starttls_enable);
		logger.info("groupname " + groupname);
		logger.info("email " + email);
		logger.info("civi_url " + civi_url);
		
		final String username = sendmail;
		final String password = email_password;
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", starttls_enable);
		props.put("mail.smtp.host", mailserver);
		props.put("mail.smtp.port", port);
 
		logger.info("1 ");
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		 
		logger.info("2 ");
		
		try {
 
			Message message = new MimeMessage(session);
			logger.info("3 ");
			message.setFrom(new InternetAddress(sendmail));
			logger.info("4 ");
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject(groupname /*+ "created in civicrm."*/);
			message.setText(/*"Your group has been created in civicrm,"
				+ "\n\n Thanks!\n\n Click " +
				"http:////" + */civi_url /*+ "/civicrm/civicrm/group?reset=1" +
				""*/);
 
			logger.info("5 ");
			
			Transport.send(message);
 
			logger.info("Done");
 
		} catch (MessagingException e) {
			logger.info("MessagingException " + e.getStackTrace());
			logger.info("MessagingException " + e);
			logger.info("MessagingException " + e.toString());
			logger.info("MessagingException " + e.getMessage());
			logger.info("MessagingException " + e.getLocalizedMessage());
			
			throw new RuntimeException(e);
		}
	}	
}