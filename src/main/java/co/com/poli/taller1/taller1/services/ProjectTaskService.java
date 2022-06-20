package co.com.poli.taller1.taller1.services;

import co.com.poli.taller1.taller1.persistence.entity.ProjectTask;
import co.com.poli.taller1.taller1.persistence.entity.TaskStatus;
import co.com.poli.taller1.taller1.services.dto.ProjectTaskInDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectTaskService {

    ProjectTask save(ProjectTaskInDTO projectTaskInDTO);

    List<ProjectTask> findByProjectIdentifier(String projectIdentifier);

    Double countHoursNoDeleted(String projectIdentifier);

    Double countHoursByStatus(String projectIdentifier, TaskStatus status);

    String deleteTaskLogicoById(Long id, String projectIdentifier);

}
