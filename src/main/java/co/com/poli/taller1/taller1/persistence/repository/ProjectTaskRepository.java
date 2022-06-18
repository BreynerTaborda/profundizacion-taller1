package co.com.poli.taller1.taller1.persistence.repository;

import co.com.poli.taller1.taller1.persistence.entity.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
}
