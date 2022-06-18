package co.com.poli.taller1.taller1.mapper;

import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.services.dto.ProjectInDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectDTOtoProject implements IMapper<ProjectInDTO, Project>{
    @Override
    public Project mapper(ProjectInDTO in) {
        Project project = new Project();

        project.setProjectName(in.getProjectName());
        project.setProjectIdentifier(in.getProjectIdentifier());
        project.setDescription(in.getDescription());
        project.setStartDate(in.getStartDate());
        project.setEndDate(in.getEndDate());

        return project;
    }
}
