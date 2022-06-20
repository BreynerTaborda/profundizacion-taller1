package co.com.poli.taller1.taller1.persistence.repository;

import co.com.poli.taller1.taller1.persistence.entity.ProjectTask;
import co.com.poli.taller1.taller1.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
    List<ProjectTask> findByProjectIdentifier(String ProjectIdentifier);


    @Query(value = "SELECT SUM(hours) FROM project_task WHERE project_identifier=:projectIdentifier AND status <> 3", nativeQuery = true)
    Double countHoursNoDeleted(@Param("projectIdentifier") String projectIdentifier);

    @Query(value = "SELECT SUM(hours) FROM project_task WHERE project_identifier=:projectIdentifier AND status=:status", nativeQuery = true)
    Double countHoursByStatus(@Param("projectIdentifier") String projectIdentifier, @Param("status")int status);

    @Modifying
    @Query(value = "UPDATE project_task SET status=:status WHERE id=:id and project_identifier=:projectIdentifier", nativeQuery = true)
    void deleteTaskLogicoById(@Param("id")Long id, @Param("projectIdentifier") String projectIdentifier, @Param("status") int status);
}
