package maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Mojo(name = "mailSender", defaultPhase = LifecyclePhase.PROCESS_CLASSES, threadSafe = true)
public class MailSenderPlugin extends AbstractMojo {

    @Parameter(property = "emailTo")
    private String emailTo;
    @Parameter(property = "emailFrom")
    private String emailFrom;
    @Parameter(property = "authServ")
    private String authServ;
    @Parameter(property = "authUser")
    private String authUser;
    @Parameter(property = "authPass")
    private String authPass;
    @Parameter(property = "port")
    private String port;

    public void execute() throws MojoExecutionException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", authServ);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", "smtp.mail-trap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authUser, authPass);
            }
        });
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Java mail Plugin send the jar");
            String msg = "Java mail Plugin send the jar: versioned-0.0.1-SNAPSHOT.jar";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            mimeBodyPart.attachFile(new File("ru/progwards/java2/lessons/builders/versioned/target/versioned-0.0.1-SNAPSHOT.jar"));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
