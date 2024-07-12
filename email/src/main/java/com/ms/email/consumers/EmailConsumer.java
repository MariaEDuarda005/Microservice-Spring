package com.ms.email.consumers;

import com.ms.email.dtos.EmailRecordDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}")
    // fazer a conversão para o tipo emailRecordDto
    public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto){
        System.out.println(emailRecordDto.emailTo());

        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecordDto, emailModel); // vamos converter o DTO em model
        // send email
        emailService.sendEmail(emailModel);
    }

}
