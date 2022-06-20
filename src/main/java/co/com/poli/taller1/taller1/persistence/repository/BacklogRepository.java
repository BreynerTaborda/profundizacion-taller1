package co.com.poli.taller1.taller1.persistence.repository;

import co.com.poli.taller1.taller1.persistence.entity.Backlog;
import co.com.poli.taller1.taller1.persistence.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String projectIdentifier);
}
