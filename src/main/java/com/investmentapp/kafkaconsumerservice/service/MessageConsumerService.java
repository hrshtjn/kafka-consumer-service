package com.investmentapp.kafkaconsumerservice.service;

import com.investmentapp.kafkaconsumerservice.model.EndDateNotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerService.class);
    private static final String TOPIC = "ending_investments";
    private static final String SIGNUP_CONF_TOPIC = "signup_confirmation";

    private final String GROUP_ID = "email-consumer-group";
    private final String SIGNUP_CONF_GRPID = "signup-consumer-group";

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void sendEndDateNotification(EndDateNotificationMessage data){
        LOGGER.info(String.format("Message received -> %s", data.toString()));

        String to = data.getEmailId();
        String assetType= data.getAssetType();
        String maturityDate = data.getEndDate();
        StringBuilder subject = new StringBuilder();
        subject.append("Your ").append(assetType).append(" is maturing on ").append(maturityDate);
        StringBuilder body = new StringBuilder();
        body.append("Hi ").append(data.getUsername()+"\n");
        body.append("Your ").append(data.getBankName()+" ").append(assetType).append(" worth ").append(data.getInvestmentAmount())
                .append(" is maturing on ").append(maturityDate + ". \n");
        body.append("Yours truly, \n").append("Investment App Inc.");

        emailService.sendEmail(to,subject.toString(),body.toString());
    }

    @KafkaListener(topics = SIGNUP_CONF_TOPIC, groupId = SIGNUP_CONF_GRPID,containerFactory = "stringKafkaListenerContainerFactory")
    public void sendSignupConfirmation(String email){
        LOGGER.info(String.format("Message received -> %s", email));
        String to = email;
        String subject = "Welcome to investment app";
        String body = "Hi \n Welcome to the investment app. Manage your portfolio, track your wealth";
        emailService.sendEmail(to,subject,body);
    }
}
