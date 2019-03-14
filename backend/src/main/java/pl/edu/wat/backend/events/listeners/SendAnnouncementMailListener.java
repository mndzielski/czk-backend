package pl.edu.wat.backend.events.listeners;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import pl.edu.wat.backend.dto.AnnouncementDto;
import pl.edu.wat.backend.events.SendAnnouncementMailEvent;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Data
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SendAnnouncementMailListener implements ApplicationListener<SendAnnouncementMailEvent> {

    private final JavaMailSender emailSender;

    private final Configuration freemarkerConfig;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Override
    public void onApplicationEvent(SendAnnouncementMailEvent sendAnnouncementMailEvent) {
        ModelMap model = new ModelMap();
        AnnouncementDto announcement = sendAnnouncementMailEvent.getAnnouncement();
        model.put("announcement", announcement);

        try {
            MimeMessage message = constructEmailMessage("/mail/send-announcement.ftl",
                    model, sendAnnouncementMailEvent.getEmail(), announcement.getTitle());
            emailSender.send(message);
        } catch (MessagingException | TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    protected MimeMessage constructEmailMessage(String template, ModelMap model, String sendTo, String subject) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Template t = freemarkerConfig.getTemplate(template);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setTo(sendTo);
        helper.setSubject(subject);
        helper.setText(html, true);
        helper.setFrom(mailUsername);

        return message;
    }
}
