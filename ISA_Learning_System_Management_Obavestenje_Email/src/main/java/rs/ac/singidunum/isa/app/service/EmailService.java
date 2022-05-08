package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.dto.EmailDTO;
import rs.ac.singidunum.isa.app.dto.PriloziDTO;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailUsername;

    public void sendMail(EmailDTO email) {
        String[] mailovi = new String[email.getEmailovi().size()];
        for(int i = 0; i < email.getEmailovi().size(); i++) {
            mailovi[i] = email.getEmailovi().get(i);
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailUsername);
        message.setTo(mailovi);
        message.setText(email.getSadrzaj());
        message.setSubject(email.getNaslov());

        mailSender.send(message);
    }

    public void sendMailWithAttachment(EmailDTO email) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            message.setFrom(emailUsername);
            String[] mailovi = new String[email.getEmailovi().size()];
            for(int i = 0; i < email.getEmailovi().size(); i++) {
                mailovi[i] = email.getEmailovi().get(i);
            }
            message.setTo(mailovi);
            message.setSubject(email.getNaslov());
            message.setText(email.getSadrzaj());
            for(PriloziDTO p : email.getPrilozi()) {
                FileSystemResource file = new FileSystemResource(new File(p.getUrl()));
                message.addAttachment(file.getFilename(), file);
            }
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
