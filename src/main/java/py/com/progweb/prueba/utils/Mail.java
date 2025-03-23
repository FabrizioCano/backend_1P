package py.com.progweb.prueba.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        String remitente = "cesaralonso99@gmail.com";
        String password = "wwzjgtygcliolyfv";
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport.send(message);
            System.out.println("Correo electronico enviado correctamente.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}