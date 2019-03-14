package pl.edu.wat.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.wat.domain.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    Page<Announcement> findPaginatedByProvinces(List<String> provinces, Pageable pageable);

    Announcement save(Announcement announcement);

    void deleteById(Long id);

    List<Announcement> findAll();
}
