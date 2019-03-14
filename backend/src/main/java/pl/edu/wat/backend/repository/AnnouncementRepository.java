package pl.edu.wat.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wat.domain.entity.Announcement;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query("SELECT a FROM Announcement a " +
            "WHERE (COALESCE(:provinces, NULL) IS NULL OR a.province IN :provinces)")
    Page<Announcement> findPaginatedByProvinces(
            @Param("provinces") List<String> provinces,
            Pageable pageable);
}
