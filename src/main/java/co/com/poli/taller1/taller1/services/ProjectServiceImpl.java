package co.com.poli.taller1.taller1.services;

import co.com.poli.taller1.taller1.mapper.ProjectDTOtoProject;
import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.persistence.repository.ProjectRepository;
import co.com.poli.taller1.taller1.services.dto.ProjectInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectDTOtoProject projectDTOtoProject;

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project save(ProjectInDTO projectDTO) {
        Project project = projectDTOtoProject.mapper(projectDTO);

        return this.projectRepository.save(project);
    }
}
