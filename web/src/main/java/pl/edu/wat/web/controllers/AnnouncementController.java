package pl.edu.wat.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dto.AnnouncementDto;
import pl.edu.wat.backend.events.SendAnnouncementMailEvent;
import pl.edu.wat.backend.service.AnnouncementService;
import pl.edu.wat.domain.entity.Announcement;

import java.util.List;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping(value = "/api/secure/announcements/save")
    public ResponseEntity<Announcement> saveAnnouncement(
            @RequestBody Announcement announcement) {
        return new ResponseEntity<>(service.save(announcement), HttpStatus.OK);
    }

    @PostMapping(value = "/api/secure/announcements/delete/{id}")
    public void deleteAnnouncement(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @GetMapping(value = "/api/public/announcements/fetch")
    public ResponseEntity<Page<Announcement>> fetchAnnouncements(
            @RequestParam(value = "provinces", required = false)
                    List<String> provinces, Pageable pageable) {
        return new ResponseEntity<>(
                service.findPaginatedByProvinces(provinces, pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/public/announcements/send-email", method = RequestMethod.POST)
    public ResponseEntity sendEmailAnnouncement(@RequestParam(value = "email", required = false) String email,
                                                @RequestBody AnnouncementDto announcement) {
        applicationEventPublisher.publishEvent(new SendAnnouncementMailEvent(this, announcement, email));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
