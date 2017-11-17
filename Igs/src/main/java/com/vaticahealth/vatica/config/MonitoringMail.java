package com.vaticahealth.vatica.config;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MonitoringMail {

	public static int FailedTestCount;
	public static int PassedTestCount;
	public static int SkippedTestCount;
	public static int TotalTestCount;

	public int getTotalTestCount() {
		return TotalTestCount;
	}

	public int getPassedTestCount() {
		return PassedTestCount;
	}

	public void setTotalTestCount(int totalTestCount) {
		TotalTestCount = totalTestCount;
	}

	public int getSkippedTestCount() {
		return SkippedTestCount;
	}

	public void setPassedTestCount(int passedTestCount) {
		PassedTestCount = passedTestCount;
	}

	public void setSkippedTestCount(int skippedTestCount) {
		SkippedTestCount = skippedTestCount;
	}

	public int getFailedTestCount() {
		return FailedTestCount;
	}

	public void setFailedTestCount(int failedTestCount) {
		FailedTestCount = failedTestCount;
	}

	public static final String server = "smtp.gmail.com";
	public static final String from = "nitun.pachauri@one97.net";
	public static final String password2 = "";

	public static final String[] to = { "nitun.pachauri@one97.net" };

	public static final String attachmentPath = (System.getProperty("user.dir")
			+ "\\test-output\\Vatica_Extent_Report.html");

	public static final String attachmentName = "VaticaDailyAutomationReport.html";

	public void sendMail(String mailServer, String from, String[] to, String subject, String messageBody,
			String attachmentPath, String attachmentName) throws MessagingException, AddressException {
		boolean debug = false;
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.host", mailServer);
		props.put("mail.debug", "true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);

		session.setDebug(debug);

		try {

			Transport bus = session.getTransport("smtp");
			bus.connect();
			Message message = new MimeMessage(session);

			// X-Priority values are generally numbers like 1 (for highest
			// priority), 3 (normal) and 5 (lowest).

			message.addHeader("X-Priority", "1");
			message.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				addressTo[i] = new InternetAddress(to[i]);
			message.setRecipients(Message.RecipientType.TO, addressTo);
			message.setSubject(subject);

			BodyPart body = new MimeBodyPart();

			// body.setText(messageBody);
			body.setContent(messageBody, "text/html");

			BodyPart attachment = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentPath);
			attachment.setDataHandler(new DataHandler(source));
			attachment.setFileName(attachmentName);
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(body);
			multipart.addBodyPart(attachment);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Successfully Sent mail to All Users");
			bus.close();

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = from;
			String password = password2;
			return new PasswordAuthentication(username, password);
		}
	}

}
