package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom; // precisar saber qual é o email from

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel){
        try {
            // colocra informações do email model que vão faltar
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom); // deixar no banco de dados quem enviou o email

            // criar a mensagem de email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo()); // passar o email para quem vai ser enviado
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message); // usar o metodo send e passar a mensagem que sera enviada

            emailModel.setStatusEmail(StatusEmail.SENT); // para controler mostrar se foi enviado
        } catch (MailException e ){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel); // salvar
        }
    }
}
