package rs.ac.singidunum.isa.app.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Component;
import rs.ac.singidunum.isa.app.dto.EmailDTO;
import rs.ac.singidunum.isa.app.service.EmailService;

@Component
public class ObavestenjeListener {
    @Autowired
    private EmailService emailService;

    @JmsListener(destination = "obavestenje")
    public void PosaljiEmail(EmailDTO email) {
        if(email.getPrilozi().isEmpty()) {
            emailService.sendMail(email);
            System.out.println("radi 1");
        } else {
            emailService.sendMailWithAttachment(email);
            System.out.println("radi 2");
        }
    }
}
