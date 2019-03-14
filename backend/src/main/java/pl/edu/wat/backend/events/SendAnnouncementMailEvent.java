package pl.edu.wat.backend.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;
import pl.edu.wat.backend.dto.AnnouncementDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendAnnouncementMailEvent extends ApplicationEvent {

    private AnnouncementDto announcement;

    private String email;

    public SendAnnouncementMailEvent(Object source, AnnouncementDto announcement, String email) {
        super(source);
        this.announcement = announcement;
        this.email = email;
    }
}
