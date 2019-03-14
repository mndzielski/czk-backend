package pl.edu.wat.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.domain.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
