package co.com.poli.taller1.taller1.mapper;

import co.com.poli.taller1.taller1.persistence.entity.ProjectTask;
import co.com.poli.taller1.taller1.services.dto.ProjectTaskInDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectTaskDTOtoProjectTask implements IMapper<ProjectTaskInDTO, ProjectTask>{
    @Override
    public ProjectTask mapper(ProjectTaskInDTO in) {
        ProjectTask projectTask = new ProjectTask();

        projectTask.setName(in.getName());
        projectTask.setSummary(in.getSummary());
        projectTask.setAcceptanceCriteria(in.getAcceptanceCriteria());
        projectTask.setStatus(in.getStatus());
        projectTask.setPriority(in.getPriority());
        projectTask.setHours(in.getHours());
        projectTask.setStartDate(in.getStartDate());
        projectTask.setEndDate(in.getEndDate());
        projectTask.setProjectIdentifier(in.getProjectIdentifier());

        return projectTask;
    }
}
