package pl.edu.wat.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.repository.AnnouncementRepository;
import pl.edu.wat.backend.service.AnnouncementService;
import pl.edu.wat.domain.entity.Announcement;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository repository;

    @Override
    public Page<Announcement> findPaginatedByProvinces(
            List<String> provinces, Pageable pageable) {
        return repository.findPaginatedByProvinces(provinces, pageable);
    }

    @Transactional
    @Override
    public Announcement save(Announcement announcement) {
        if (Boolean.TRUE.equals(announcement.getWholeProvince())) {
            announcement.getDistricts().clear();
        }
        return repository.save(announcement);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Announcement> findAll() {
        return repository.findAll();
    }
}

