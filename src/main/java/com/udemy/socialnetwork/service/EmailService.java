package com.udemy.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.HTML;
import java.util.Date;

@Service
public class EmailService {

    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.enable}")
    private Boolean enableMail;


    private void send(MimeMessagePreparator preparator) {
        if(enableMail) {
            mailSender.send(preparator);
        }
    }

    @Autowired
    public EmailService(TemplateEngine templateEngine) {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        templateEngine.setTemplateResolver(templateResolver);

        this.templateEngine = templateEngine;


    }
    public void sendVerificationEmail(String emailAddress) {

        Context context = new Context();
        context.setVariable("name", "Bob");

        String emailContents = templateEngine.process("verifyemail", context);

        System.out.println(emailContents);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

                messageHelper.setTo(emailAddress);
                messageHelper.setFrom(new InternetAddress("no-reply@caveofprogrammin.com"));
                messageHelper.setSubject("Please Verify your email address");
                messageHelper.setSentDate(new Date());

                messageHelper.setText(emailContents, true);
            }
        };
        send(preparator);
    }
}
