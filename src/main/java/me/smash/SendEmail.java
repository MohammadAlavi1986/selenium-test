package me.smash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
import javax.mail.util.ByteArrayDataSource;

public class SendEmail {

  public static void main(String[] args) throws MessagingException {
    final String username = "m.alavi1986@gmail.com";
    final String password = "SMASH8151882365";

    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true"); //TLS

    try (FileInputStream fis = new FileInputStream(
        "/Users/mohammad/Downloads/test1/spring-boot-thymeleaf/Nopackage.pdf")) {
      Session session = Session.getInstance(prop,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
            }
          });

      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username));
      message.setRecipients(
          Message.RecipientType.TO,
          InternetAddress.parse("m.alavi1986@gmail.com, m.alavi1986@yahoo.com")
      );

      MimeMultipart multipart = new MimeMultipart();
      MimeBodyPart part1 = new MimeBodyPart();
      part1.setText("Dear Mail Crawler,"
          + "\n\n Please do not spam my email!");
      multipart.addBodyPart(part1);
      MimeBodyPart part2 = new MimeBodyPart();
      DataSource dataSrc = new ByteArrayDataSource(fis, "application/pdf");
      part2.setDataHandler(new DataHandler(dataSrc));
      part2.setFileName("report");
      multipart.addBodyPart(part2);

      message.setSubject("Testing Gmail TLS");

      message.setContent(multipart);

      Transport.send(message);

    } catch (IOException e) {
      e.printStackTrace();
    }


  }

}
